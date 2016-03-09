package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.hardware.Camera;
import android.view.Gravity;
import android.widget.TextView;
import me.ele.ecamera.R;
import me.ele.ecamera.lib.CameraController;
import me.ele.ecamera.utils.ScreenUtils;

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

    setTextSize(14);
    setTextColor(getResources().getColor(android.R.color.white));
    setBackgroundResource(R.drawable.light_dark_hint_bg);
    setGravity(Gravity.CENTER);
    int plr = ScreenUtils.dip2px(context, 11);
    setPadding(plr, 0, plr, 0);
    setText(R.string.light_dark_hint);
    setVisibility(GONE);
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
    if (l < SHOW_HINT_LUMINANCE_BOUND) {
      setVisibility(VISIBLE);
    } else {
      setVisibility(GONE);
    }
  }

  // Calculate Y value of YUV format image since Y represents Luminance of one pixel.
  private long computeY(byte[] data, int width, int height) {
    long size = width * height;
    // We only calculate some pixels, not all.
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
