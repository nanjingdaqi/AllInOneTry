/***
 * Copyright (c) 2013 CommonsWare, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.View;
import me.ele.ecamera.lib.CameraController;

public class SurfacePreviewStrategy implements SurfaceHolder.Callback {
  private CameraPreview preview;
  private SurfaceHolder previewHolder;
  private CameraController cameraController;
  private boolean manualStoped;
  private boolean released;

  @SuppressWarnings("deprecation")
  public SurfacePreviewStrategy(Context context, CameraController cameraController) {
    this.cameraController = cameraController;
    preview = new CameraPreview(context);
    SurfaceHolder holder = preview.getHolder();
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    holder.addCallback(this);
    cameraController.open();
  }

  public SurfaceHolder getPreViewHolder() {
    return previewHolder;
  }

  @Override
  public synchronized void surfaceCreated(SurfaceHolder holder) {
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    cameraController.release();
  }

  public CameraPreview getWidget() {
    return (preview);
  }

  @Override
  public synchronized void surfaceChanged(SurfaceHolder holder, int format, int width,
      int height) {
    surfaceChanged(holder);
  }

  protected void surfaceChanged(SurfaceHolder holder) {
    if (holder.getSurface() == null) {
      return;
    }
    //		Log.d(getClass().getSimpleName(), "surfaceChanged");
    this.previewHolder = holder;
    previewHolder.setKeepScreenOn(true);
    try {
      if (!manualStoped) {
        initAndStartPreview();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void onResume() {
    if (!manualStoped && released && !cameraController.inPreview()) {
      initAndStartPreview();
    }
  }

  private void initAndStartPreview() {
    if (previewHolder != null) {
      cameraController.open();
      cameraController.stopPreview();
      cameraController.continuousAutoFocus();
      cameraController.setPreviewMaxFps();
      cameraController.setAutoWhiteBalance();
      cameraController.setAutoScene();
      cameraController.initFlashMode();
      cameraController.adjustSize(preview.getWidth());
      cameraController.setPreviewDisplay(previewHolder);
      cameraController.startPreview();
      preview.setVisibility(View.VISIBLE);
      released = false;
    }
  }

  public void stopPreview() {
    manualStoped = true;
    cameraController.stopPreview();
    preview.setVisibility(View.GONE);
  }

  public void startPreview() {
    manualStoped = false;
    initAndStartPreview();
  }

  public void onPause() {
    released = true;
    cameraController.release();
  }
}