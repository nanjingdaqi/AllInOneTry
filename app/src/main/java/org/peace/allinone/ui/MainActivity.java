package org.peace.allinone.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Choreographer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements Choreographer.FrameCallback {
    static final boolean VERBOSE = true;
    static String TAG = "daqi";
    private static final long TIMEOUT_US = 10000;

    static final String MP3_FILE = "/sdcard/test.mp3";

    private MyGLSurfaceView mGLView;
    int mInputBufferSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
//            playAudio();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                playAudio();
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }
    }

    void playAudio() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                AudioTrack audioTrack = null;
                MediaExtractor audioExtractor = new MediaExtractor();
                MediaCodec audioCodec = null;
                try {
                    audioExtractor.setDataSource(MP3_FILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < audioExtractor.getTrackCount(); i++) {
                    MediaFormat mediaFormat = audioExtractor.getTrackFormat(i);
                    String mime = mediaFormat.getString(MediaFormat.KEY_MIME);
                    if (mime.startsWith("audio/")) {
                        audioExtractor.selectTrack(i);
                        int audioChannels = mediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
                        int audioSampleRate = mediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE);
                        int minBufferSize = AudioTrack.getMinBufferSize(audioSampleRate,
                                (audioChannels == 1 ? AudioFormat.CHANNEL_OUT_MONO : AudioFormat.CHANNEL_OUT_STEREO),
                                AudioFormat.ENCODING_PCM_16BIT);
                        int maxInputSize = mediaFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE);

                        mInputBufferSize = minBufferSize > 0 ? minBufferSize * 4 : maxInputSize;
                        int frameSizeInBytes = audioChannels * 2;
                        mInputBufferSize = (mInputBufferSize / frameSizeInBytes) * frameSizeInBytes;

                        final int sampleRate = 44100;
                        AudioAttributes attr = new AudioAttributes.Builder()
                                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                                .build();
                        AudioFormat format = new AudioFormat.Builder()
                                .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                                .setSampleRate(sampleRate)
                                .build();
                        audioTrack = new AudioTrack(attr, format, minBufferSize, AudioTrack.MODE_STREAM, AudioManager.AUDIO_SESSION_ID_GENERATE);
                        audioTrack.play();

                        try {
                            audioCodec = MediaCodec.createDecoderByType(mime);
                            audioCodec.configure(mediaFormat, null, null, 0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                if (audioCodec == null) {
                    throw new RuntimeException("Fail to create codec");
                }

                audioCodec.start();
                final ByteBuffer[] buffers = audioCodec.getOutputBuffers();
                int sz = buffers[0].capacity();
                if (sz <= 0) {
                    sz = mInputBufferSize;
                }
                byte[] mAudioOutTempBuf = new byte[sz];

                MediaCodec.BufferInfo audioBufferInfo = new MediaCodec.BufferInfo();
                ByteBuffer[] inputBuffers = audioCodec.getInputBuffers();
                ByteBuffer[] outputBuffers = audioCodec.getOutputBuffers();

                boolean isAudioEOS = false;
                long startMs = System.currentTimeMillis();

                for (; ; ) {
                    // 解码
                    if (!isAudioEOS) {
                        isAudioEOS = decodeMediaData(audioExtractor, audioCodec, inputBuffers);
                    }

                    // 解码
                    if (!isAudioEOS) {
                        isAudioEOS = decodeMediaData(audioExtractor, audioCodec, inputBuffers);
                    }
                    // 获取解码后的数据
                    int outputBufferIndex = audioCodec.dequeueOutputBuffer(audioBufferInfo, TIMEOUT_US);
                    switch (outputBufferIndex) {
                        case MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:
                            if (VERBOSE) {
                                Log.d(TAG, "INFO_OUTPUT_FORMAT_CHANGED");
                            }
                            break;
                        case MediaCodec.INFO_TRY_AGAIN_LATER:
                            if (VERBOSE) {
                                Log.d(TAG, "INFO_TRY_AGAIN_LATER");
                            }
                            break;
                        case MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED:
                            outputBuffers = audioCodec.getOutputBuffers();
                            if (VERBOSE) {
                                Log.d(TAG, "INFO_OUTPUT_BUFFERS_CHANGED");
                            }
                            break;
                        default:
                            ByteBuffer outputBuffer = outputBuffers[outputBufferIndex];
                            // 延时解码，跟视频时间同步
                            decodeDelay(audioBufferInfo, startMs);
                            // 如果解码成功，则将解码后的音频PCM数据用AudioTrack播放出来
                            if (audioBufferInfo.size > 0) {
                                if (mAudioOutTempBuf.length < audioBufferInfo.size) {
                                    mAudioOutTempBuf = new byte[audioBufferInfo.size];
                                }
                                outputBuffer.position(0);
                                outputBuffer.get(mAudioOutTempBuf, 0, audioBufferInfo.size);
                                outputBuffer.clear();
                                if (audioTrack != null)
                                    audioTrack.write(mAudioOutTempBuf, 0, audioBufferInfo.size);
                            }
                            // 释放资源
                            audioCodec.releaseOutputBuffer(outputBufferIndex, false);
                            break;
                    }

                    // 结尾了
                    if ((audioBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                        Log.d("daqi", "BUFFER_FLAG_END_OF_STREAM");
                        break;
                    }
                }


                audioTrack.stop();
                audioTrack.release();
            }
        });
    }

    /**
     * 解码延时
     *
     * @param bufferInfo
     * @param startMillis
     */
    private void decodeDelay(MediaCodec.BufferInfo bufferInfo, long startMillis) {
        while (bufferInfo.presentationTimeUs / 1000 > System.currentTimeMillis() - startMillis) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 解复用，得到需要解码的数据
     *
     * @param extractor
     * @param decoder
     * @param inputBuffers
     * @return
     */
    private static boolean decodeMediaData(MediaExtractor extractor, MediaCodec decoder, ByteBuffer[] inputBuffers) {
        boolean isMediaEOS = false;
        int inputBufferIndex = decoder.dequeueInputBuffer(TIMEOUT_US);
        if (inputBufferIndex >= 0) {
            ByteBuffer inputBuffer = inputBuffers[inputBufferIndex];
            int sampleSize = extractor.readSampleData(inputBuffer, 0);
            if (sampleSize < 0) {
                decoder.queueInputBuffer(inputBufferIndex, 0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM);
                isMediaEOS = true;
                if (VERBOSE) {
                    Log.d(TAG, "end of stream");
                }
            } else {
                decoder.queueInputBuffer(inputBufferIndex, 0, sampleSize, extractor.getSampleTime(), 0);
                extractor.advance();
            }
        }
        return isMediaEOS;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }

    @Override
    public void doFrame(long frameTimeNanos) {

    }
}
