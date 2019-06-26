/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.peace.allinone.ui;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Trace;
import android.util.Log;

import java.io.File;
import java.nio.ByteBuffer;


/**
 * Record video of a game in progress.
 *
 * Not generally thread-safe.  The recorder should be set up by the Activity before the
 * Render thread is started, then updates should be made from the GLSurfaceView render thread.
 */
public class GameRecorder {
    private static final String TAG = "daqi";

    // parameters for the encoder
    private static final String MIME_TYPE = "video/avc";    // H.264 Advanced Video Coding
    private static final int FRAME_RATE = 30;               // 30fps
    private static final int IFRAME_INTERVAL = 10;          // 10 seconds between I-frames
    private static final int VIDEO_WIDTH = 1080;
    private static final int VIDEO_HEIGHT = 2076;
    private static final int BIT_RATE = 6000000;            // 6Mbps

    private static final Object sSyncObj = new Object();
    private static volatile GameRecorder sInstance;

    private File mOutputFile;
    private MediaCodec mEncoder;
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaMuxer mMuxer;
    private int mTrackIndex;
    private boolean mMuxerStarted;


    /** singleton */
    private GameRecorder() {}

    /**
     * Retrieves the singleton, creating the instance if necessary.
     */
    public static GameRecorder getInstance() {
        if (sInstance == null) {
            synchronized (sSyncObj) {
                if (sInstance == null) {
                    sInstance = new GameRecorder();
                }
            }
        }
        return sInstance;
    }

    public void prepareEncoder() {
        MediaCodec encoder;
        MediaMuxer muxer;

        if (mEncoder != null) {
            throw new RuntimeException("prepareEncoder called twice?");
        }

        mOutputFile = new File("/sdcard/test.mp4");
        if (mOutputFile.exists()) mOutputFile.delete();
        Log.d(TAG, "Video recording to file " + mOutputFile);
        mBufferInfo = new MediaCodec.BufferInfo();

        try {
            // Create and configure the MediaFormat.
            MediaFormat format = MediaFormat.createVideoFormat(MIME_TYPE,
                    VIDEO_WIDTH, VIDEO_HEIGHT);
            format.setInteger(MediaFormat.KEY_COLOR_FORMAT,
                    MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
            format.setInteger(MediaFormat.KEY_BIT_RATE, BIT_RATE);
            format.setInteger(MediaFormat.KEY_FRAME_RATE, FRAME_RATE);
            format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL);

            // Create a MediaCodec encoder, and configure it with our format.
            encoder = MediaCodec.createEncoderByType(MIME_TYPE);
            encoder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);

            // Create a MediaMuxer.  We can't add the video track and start() the muxer here,
            // because our MediaFormat doesn't have the Magic Goodies.  These can only be
            // obtained from the encoder after it has started processing data.
            muxer = new MediaMuxer(mOutputFile.getAbsolutePath(),
                    MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
            mMuxerStarted = false;

            mEncoder = encoder;
            mEncoder.start();

            mMuxer = muxer;
        } catch (Exception ex) {
            Log.w(TAG, "Something failed during recorder init: " + ex);
            releaseEncoder();
            throw new RuntimeException(ex);
        }
    }

    public int frameIndex = 1;

    public void feedInputBuffer(ByteBuffer rawInputBuffer) {
        int bufferId = mEncoder.dequeueInputBuffer(16000);
        if (bufferId > 0) {
            Log.d(TAG, "Valid buffer, id: " + bufferId);
            ByteBuffer dstInputBuffer = mEncoder.getInputBuffer(bufferId);
            Log.d(TAG, "dstByteBuffer has arr: " + dstInputBuffer.hasArray() + ", remaining: " + dstInputBuffer.remaining());
            dstInputBuffer.clear();
            dstInputBuffer.put(rawInputBuffer.array());
            long presentationTime = frameIndex * 16 * 1000;
            frameIndex++;
            mEncoder.queueInputBuffer(bufferId, 0, rawInputBuffer.array().length, presentationTime, 0);
        } else {
            Log.d(TAG, "No Valid buffer, id: " + bufferId);
        }
    }

    /**
     * Extracts all pending data from the encoder.
     *
     * If endOfStream is not set, this returns when there is no more data to drain.  If it
     * is set, we send EOS to the encoder, and then iterate until we see EOS on the output.
     */
    public void drainEncoder(boolean endOfStream) {
        if (!isRecording()) {
            return;
        }

        Trace.beginSection("drainEncoder");

        //Log.d(TAG, "GameRecorder drainEncoder " + endOfStream);
        ByteBuffer[] encoderOutputBuffers = mEncoder.getOutputBuffers();

        while (true) {
            int encoderStatus = mEncoder.dequeueOutputBuffer(mBufferInfo, 10000);
            if (encoderStatus == MediaCodec.INFO_TRY_AGAIN_LATER) {
                // no output available yet
                Log.d(TAG, "Try again later.");
                break;      // out of while
            } else if (encoderStatus == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                // not expected for an encoder
                Log.d(TAG, "Info output buffers changed.");
                encoderOutputBuffers = mEncoder.getOutputBuffers();
            } else if (encoderStatus == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                // should happen before receiving buffers, and should only happen once
                if (mMuxerStarted) {
                    throw new RuntimeException("format changed twice");
                }
                MediaFormat newFormat = mEncoder.getOutputFormat();
                Log.d(TAG, "encoder output format changed: " + newFormat);
                mTrackIndex = mMuxer.addTrack(newFormat);
                mMuxer.start();
                mMuxerStarted = true;
            } else {
                ByteBuffer encodedData = encoderOutputBuffers[encoderStatus];
                if (encodedData == null) {
                    throw new RuntimeException("encoderOutputBuffer " + encoderStatus + " was null");
                }

                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                    // the codec config data was pulled out and fed to the muxer when we got
                    // the INFO_OUTPUT_FORMAT_CHANGED status.  Ignore it.
                    Log.d(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
                    mBufferInfo.size = 0;
                }

                if (mBufferInfo.size != 0) {
                    if (!mMuxerStarted) {
                        throw new RuntimeException("muxer hasn't started");
                    }

                    // Adjust the ByteBuffer values to match BufferInfo.  (not needed?)
                    encodedData.position(mBufferInfo.offset);
                    encodedData.limit(mBufferInfo.offset + mBufferInfo.size);

                    mMuxer.writeSampleData(mTrackIndex, encodedData, mBufferInfo);
                    Log.d(TAG, "wrote " + mBufferInfo.size + " bytes");
                }

                Log.d(TAG, "Release output buffer");
                mEncoder.releaseOutputBuffer(encoderStatus, false);
                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                    if (!endOfStream) {
                        Log.w(TAG, "reached end of stream unexpectedly");
                    } else {
                        Log.d(TAG, "end of stream reached");
                    }
                    break;      // out of while
                }
            }
        }

        Trace.endSection();
    }

    /**
     * Releases encoder resources.  May be called after partial / failed initialization.
     */
    public void releaseEncoder() {
        Log.d(TAG, "releasing encoder objects");
        if (mEncoder != null) {
            mEncoder.stop();
            mEncoder.release();
            mEncoder = null;
        }
        if (mMuxer != null) {
            mMuxer.stop();
            mMuxer.release();
            mMuxer = null;
        }
    }

    /**
     * Returns true if a recording is in progress.
     */
    public boolean isRecording() {
        return mEncoder != null;
    }
}
