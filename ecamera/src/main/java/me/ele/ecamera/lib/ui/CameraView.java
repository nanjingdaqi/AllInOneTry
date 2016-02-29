/***
  Copyright (c) 2013-2014 CommonsWare, LLC
  Portions Copyright (C) 2007 The Android Open Source Project
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package me.ele.ecamera.lib.ui;

import me.ele.ecamera.lib.CameraController;
import me.ele.ecamera.lib.CameraController.OnCameraOpenedListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CameraView extends ViewGroup implements OnCameraOpenedListener {
	
	private static final float DEFAULT_CAMERA_BRIGHTNESS = 0.7f;
	static final String TAG = "CWAC-Camera";
	private SurfacePreviewStrategy previewStrategy;
	private CameraController cameraController;
	private FocusIndicator focusIndicator;
	private CameraPhotoView photoView;

	public CameraView(Context context) {
		super(context);
		init(context);
	}
	
	public CameraView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init(context);
	}

	public CameraView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		if (isInEditMode()) {
			return;
		}
		this.cameraController = new CameraController(getContext(), this);
		this.previewStrategy = new SurfacePreviewStrategy(getContext(), cameraController);
		addView(previewStrategy.getWidget());
		if (context instanceof Activity) {
			cameraController.setScreenBrightness((Activity) context, DEFAULT_CAMERA_BRIGHTNESS);
		}
		focusIndicator = new FocusIndicator(context);
		focusIndicator.attach(this);
		addView(focusIndicator);
		photoView = new CameraPhotoView(context);
		addView(photoView);
	}

	
	public CameraController getCameraController() {
		return cameraController;
	}

	public void onResume() {
	    previewStrategy.onResume();
	}

	public void onPause() {
		previewStrategy.onPause();
	}

	public void stopPreviewAndShow(Bitmap bitmap) {
		stopRreview();
		photoView.display(bitmap);
	}
	
	public void stopPreviewAndShow(Uri uri) {
	    stopRreview();
	    photoView.display(uri);
	}

    protected void stopRreview() {
        focusIndicator.setVisibility(View.GONE);
	    previewStrategy.stopPreview();
    }
	

	public void startPreview() {
		focusIndicator.setVisibility(View.VISIBLE);
		previewStrategy.startPreview();
		photoView.clear();
	}
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int width = resolveSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);
		final int height = resolveSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);
		setMeasuredDimension(width, height);
	}


	@SuppressLint("DrawAllocation")
    @Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed && getChildCount() > 0) {
            final int width = right - left;
            final int height = bottom - top;
            int previewWidth = width;
            int previewHeight = height;
            Camera.Size previewSize = cameraController.getAdjustedPreviewSize();
            if (previewSize != null) {
                if (cameraController.getDisplayOrientation() == 90
                        || cameraController.getDisplayOrientation() == 270) {
                    previewWidth = previewSize.height;
                    previewHeight = previewSize.width;
                } else {
                    previewWidth = previewSize.width;
                    previewHeight = previewSize.height;
                }
                final int scaledHeight = previewHeight * width / previewWidth;
                left = 0;
                top = 0;
                right = width;
                bottom = scaledHeight;
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).layout(left, top, right, bottom);
                    Log.e(getChildAt(i).getClass().getName() + " layout", left + "," +  top + "," + right + "," + bottom);
                }
            }
        }
	}

	
	public void filter() {
		photoView.filter();
	}
	
	public Bitmap getPhoto() {
	    return photoView.getPhoto();
	}
	
	public void reset() {
		photoView.reset();
	}

    @Override
    public void onOpened() {
//        requestLayout();
    }

}