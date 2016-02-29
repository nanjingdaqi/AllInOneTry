package me.ele.ecamera.lib.ui;

import me.ele.ecamera.R;
import me.ele.ecamera.lib.CameraController;
import me.ele.ecamera.lib.CameraController.TouchAutoFocusCallback;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class FocusIndicator extends SurfaceView implements Callback {

    private static final int FOCUS_CLEAR = 1;
    private static final int FOCUS_CLEAR_DELAY = 700;
    private Drawable focusSussedIndicator;
    private Drawable focusFailedIndicator;
    private Drawable focusingIndicator;
    private CameraController cameraController;
    private Handler focusHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == FOCUS_CLEAR) {
                SurfaceHolder holder = getHolder();
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            return false;
        }
    });
    private boolean isTouchAreasFocusSupported;

    public FocusIndicator(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        getHolder().addCallback(this);
        setZOrderMediaOverlay(true);
    }

    public FocusIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        initFocusInficatorBitmap();
        if (event.getAction() == MotionEvent.ACTION_UP
                && cameraController != null) {
            final float x = event.getX();
            final float y = event.getY();
            cameraController.focusOnTouch(event, new TouchAutoFocusCallback() {

                @Override
                public void onAutoFocusStarted() {
                    focusStateChanged(x, y, focusingIndicator);
                }

                @Override
                public void onAutoFocusFinished(boolean success, Camera camera) {
                    if (success) {
                        focusStateChanged(x, y, focusSussedIndicator);
                    } else {
                        focusStateChanged(x, y, focusFailedIndicator);
                    }
                    delayClear();
                }
            }, getWidth(), getHeight());
        }
        return true;
    }

    private void initFocusInficatorBitmap() {
        if (focusSussedIndicator == null) {
            try {
                isTouchAreasFocusSupported = cameraController.isTouchAreasFocusSupported();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isTouchAreasFocusSupported) {
                focusSussedIndicator = getResources().getDrawable(
                        R.drawable.camera_focus_frame_success);
                focusFailedIndicator = getResources().getDrawable(
                        R.drawable.camera_focus_frame_fail);
                focusingIndicator = getResources().getDrawable(
                        R.drawable.camera_focus_frame_processing);
            } else {
                focusSussedIndicator = getResources().getDrawable(
                        R.drawable.camera_focus_frame_big_success);
                focusFailedIndicator = getResources().getDrawable(
                        R.drawable.camera_focus_frame_big_fail);
                focusingIndicator = getResources().getDrawable(
                        R.drawable.camera_focus_frame_big_processing);
            }
        }
    }

    public void attach(CameraView cameraView) {
        this.cameraController = cameraView.getCameraController();
    }

    private void focusStateChanged(float x, float y, Drawable drawable) {
        focusHandler.removeMessages(FOCUS_CLEAR);
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            Paint p = new Paint();
            if (isTouchAreasFocusSupported) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                canvas.drawBitmap(bitmap, x - bitmap.getWidth() / 2,
                        y - bitmap.getHeight() / 2, p);
            } else {
                drawable.setBounds(0, 0, getWidth(), getWidth());
                drawable.draw(canvas);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void delayClear() {
        focusHandler.sendEmptyMessageDelayed(FOCUS_CLEAR, FOCUS_CLEAR_DELAY);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
