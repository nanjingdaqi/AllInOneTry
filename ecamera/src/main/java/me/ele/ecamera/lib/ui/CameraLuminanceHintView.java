package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.widget.TextView;
import me.ele.commons.AppLogger;
import me.ele.ecamera.lib.CameraController;

public class CameraLuminanceHintView extends TextView implements Camera.PreviewCallback {

  static final long SAMPLE_POINT_AMOUNT = 10000;
  static final long SHOW_HINT_LUMINANCE_BOUND = 50;

  private CameraController cameraController;

  public CameraLuminanceHintView(Context context, CameraController cameraController) {
    super(context);

    init(context, cameraController);
  }

  private void init(Context context, CameraController cameraController) {
    this.cameraController = cameraController;

    setTextSize(20);
    setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
    setText("请使用闪光灯");
    //setVisibility(GONE);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    start();
  }

  @Override protected void onDetachedFromWindow() {
    stop();
    super.onDetachedFromWindow();
  }

  public void start() {
    cameraController.addPreviewCallback(this);
  }

  public void stop() {
    cameraController.removePreviewCallback(this);
  }

  @Override public void onPreviewFrame(byte[] data, Camera camera) {
    final Camera.Size previewSize = camera.getParameters().getPreviewSize();
    long l = computeY(data, previewSize.width, previewSize.height);
    AppLogger.e("data size: " + data.length + ", val: " + l);
    if (l < SHOW_HINT_LUMINANCE_BOUND) {
      //setVisibility(VISIBLE);
      requestLayout();
    } else {
      //setVisibility(GONE);
    }

    setText("Y val: " + l);
  }

  private long computeY(byte[] data, int width, int height) {
    long size = width * height;
    long n = Math.min(width * height, SAMPLE_POINT_AMOUNT);
    long step = size / n;
    int y;
    int num = 0;
    long max = 0;
    long len = data.length;
    for (int i = 0; i < size && i < len; ) {
      y = data[i] & 0xff;
      max += y;
      i += step;
      ++num;
    }
    return max / num;
  }
}
