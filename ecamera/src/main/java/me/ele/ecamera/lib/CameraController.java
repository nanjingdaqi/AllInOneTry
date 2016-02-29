package me.ele.ecamera.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ele.ecamera.lib.image.ImageSaveTask;
import me.ele.ecamera.utils.FocusCompleteSoundCompat;
import me.ele.ecamera.utils.ImageDecoder;
import me.ele.ecamera.utils.ScreenUtils;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class CameraController {

    private static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";
    private static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 0x00000001;
    private static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0x00000000;
    private static final int METERING_AREA_SIDE_LENGTH = 150;
    private static final int FOCUS_AREA_SIDE_LENGTH = 50;
    private Context context;
    private Camera camera;
    private final int displayOrientation = 90;
    private final int outputOrientation = 90;
    private boolean inPreview = false;
    private Camera.Size previewSize;
    private PhotoOutputSize photoOutputSize;
    private OnCameraOpenedListener cameraOpened;
    private String flashMode = Camera.Parameters.FLASH_MODE_AUTO;

    public interface OnCameraOpenedListener {
        void onOpened();
    }
    
    public static abstract class TouchAutoFocusCallback implements AutoFocusCallback {
        public abstract void onAutoFocusStarted();
        public abstract void onAutoFocusFinished(boolean success, Camera camera);
        
        @Override
        public final void onAutoFocus(boolean success, Camera camera) {
            onAutoFocusFinished(success, camera);
        }
        
    }

    public CameraController(Context context,
            OnCameraOpenedListener onCameraOpened) {
        super();
        this.context = context;
        this.cameraOpened = onCameraOpened;
    }

    public Context getContext() {
        return context;
    }

    public boolean inPreview() {
        return inPreview;
    }

    public Camera getCameraInctance() {
        if (camera == null) {
            open();
        }
        return camera;
    }

    public void setScreenBrightness(Activity activity, float brigthness) {
        Window win = activity.getWindow();
        int mode;
        if (Build.VERSION.SDK_INT >= 0x00000008) {
            mode = Settings.System.getInt(activity.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                WindowManager.LayoutParams winParams = win.getAttributes();
                winParams.screenBrightness = brigthness;
                win.setAttributes(winParams);
            }
        } else {
            mode = Settings.System.getInt(activity.getContentResolver(),
                    SCREEN_BRIGHTNESS_MODE, SCREEN_BRIGHTNESS_MODE_MANUAL);
            if (mode == SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                WindowManager.LayoutParams winParams = win.getAttributes();
                winParams.screenBrightness = brigthness;
                win.setAttributes(winParams);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setPreviewSize(Camera.Size previewSize) {
        this.previewSize = previewSize;
        if (camera != null) {
            Camera.Parameters parameters;
            try {
                parameters = getCameraParameters();
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    parameters.setFocusAreas(null);
                }
                parameters.setPreviewSize(previewSize.width, previewSize.height);
                setCameraParams(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setPictureSize(Camera.Size pictureSize) {
        if (camera != null) {
            Camera.Parameters parameters;
            try {
                parameters = getCameraParameters();
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    parameters.setFocusAreas(null);
                }
                parameters.setPictureSize(pictureSize.width, pictureSize.height);
                setCameraParams(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void adjustSize(int squareSideLength) {
        adjustPreviewSize(squareSideLength);
        adjustPictureSize(squareSideLength);
    }

    private void adjustPictureSize(int squareSideLength) {
        if (camera != null) {
            try {
                setPictureSize(CameraUtils.getBestAspectPictureSize(
                        squareSideLength, previewSize, getCameraParameters()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                params.setRotation(outputOrientation);
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void adjustPreviewSize(int squareSideLength) {
        if (camera != null) {
            try {
                setPreviewSize(getAdjustedPreviewSize());
            } catch (Exception e) {
                e.printStackTrace();
            }
            camera.setDisplayOrientation(displayOrientation);
        }
    }

    public synchronized Camera.Size getAdjustedPreviewSize() {
        if (previewSize != null) {
            return previewSize;
        }
        return null;
    }

    public synchronized PhotoOutputSize getAdjustedPhotoOutputSize() {
        if (photoOutputSize == null) {
            final int outputSquareSideLength = ImageDecoder
                    .getOutputSquareSideLength(context);
            int outputWidth = 0;
            int outoutHeight = 0;
            if (previewSize != null) {
                outputWidth = Math.min(previewSize.height, previewSize.width);
                outoutHeight = outputWidth;
            }
            if (outputWidth < outputSquareSideLength) {
                outputWidth = outputSquareSideLength;
                outoutHeight = outputSquareSideLength;
            }
            photoOutputSize = new PhotoOutputSize(outputWidth, outoutHeight);
        }
        return photoOutputSize;
    }

    public synchronized Camera open() {
        if (camera == null) {
            try {
                camera = Camera.open();
                if (previewSize == null) {
                    previewSize = CameraUtils.getBestPreviewSize(
                            displayOrientation,
                            ScreenUtils.getScreenWidth(getContext()),
                            getCameraParameters());
                }
                if (camera != null && cameraOpened != null) {
                    cameraOpened.onOpened();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "相机启动失败", Toast.LENGTH_LONG).show();
            }
        }
        return camera;
    }

    public void setPreviewCallback(Camera.PreviewCallback callback) {
        try {
            if (camera != null) {
                camera.setPreviewCallback(callback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPreview() {
        try {
            if (camera != null) {
                camera.startPreview();
                inPreview = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPreview() {
        try {
            if (camera != null) {
                camera.setPreviewCallback(null);
                camera.stopPreview();
                inPreview = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        if (camera != null) {
            previewStopped();
            camera.release();
            camera = null;
        }
    }

    private void previewStopped() {
        if (inPreview) {
            stopPreview();
        }
    }

    public void restartPreview() {
        if (!inPreview) {
            startPreview();
        }
    }

    public void takePicture(final PictureCallback cb) {
        if (inPreview) {
            try {
                camera.takePicture(null, null, new PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        stopPreview();
                        if (data != null) {
                            new ImageSaveTask(getContext(), data, CameraController.this).start();
                        }
                        if (cb != null) {
                            cb.onPictureTaken(data, camera);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void autoFocus(final AutoFocusCallback callback) {
        if (isAutoFocusAvailable()) {
            try {
                camera.cancelAutoFocus();
                camera.autoFocus(new AutoFocusCallback() {
                    
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            FocusCompleteSoundCompat.play();
                        }
                        if (callback != null) {
                            callback.onAutoFocus(success, camera);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public boolean focusOnTouch(MotionEvent event, final TouchAutoFocusCallback cb,
            int surfaceViewWidth, int surfaceViewHeight) {
        if (cb == null) {
            return false;
        }
        if (camera == null) {
            return false;
        }
        if (!inPreview) {
            return false;
        }
        try {
            Parameters parameters = getCameraParameters();
            parameters.setFocusMode(Parameters.FOCUS_MODE_AUTO);
            if (isTouchAreasFocusSupported()) {
                List<Camera.Area> areas = getFocusAreas(event, surfaceViewWidth,
                        surfaceViewHeight);
                parameters.setFocusAreas(areas);
            }
            if (isTouchAreasMeteringSupported()) {
                parameters.setMeteringAreas(getMeteringAreas(event,
                        surfaceViewWidth, surfaceViewHeight));
            }
            setCameraParams(parameters);
            cb.onAutoFocusStarted();
            autoFocus(cb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private List<Camera.Area> getFocusAreas(MotionEvent event,
            int surfaceViewWidth, int surfaceViewHeight) {
        List<Camera.Area> areas = new ArrayList<Camera.Area>();
        areas.add(new Camera.Area(calculateTapArea(event,
                FOCUS_AREA_SIDE_LENGTH, surfaceViewWidth, surfaceViewHeight),
                1000));
        return areas;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private List<Camera.Area> getMeteringAreas(MotionEvent event,
            int surfaceViewWidth, int surfaceViewHeight) {
        List<Camera.Area> areas = new ArrayList<Camera.Area>();
        areas.add(new Camera.Area(
                calculateTapArea(event, METERING_AREA_SIDE_LENGTH,
                        surfaceViewWidth, surfaceViewHeight), 1000));
        return areas;
    }

    public boolean isTouchAreasFocusSupported() throws Exception {
        if (camera == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return false;
        }
        return getCameraParameters().getMaxNumFocusAreas() > 0;
    }

    public boolean isTouchAreasMeteringSupported() throws Exception {
        if (camera == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return false;
        }
        return getCameraParameters().getMaxNumMeteringAreas() > 0;
    }

    private Rect calculateTapArea(MotionEvent event, int areaSize,
            int surfaceViewWidth, int surfaceViewHeight) {

        float cameraX = event.getX() / surfaceViewWidth * 2000 - 1000;
        float cameraY = event.getY() / surfaceViewHeight * 2000 - 1000;
        RectF rectF = new RectF(clamp(cameraX - areaSize), clamp(cameraY
                - areaSize), clamp(cameraX + areaSize), clamp(cameraY
                + areaSize));
        Matrix matrix = new Matrix();
        matrix.setRotate(-displayOrientation);
        matrix.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top),
                Math.round(rectF.right), Math.round(rectF.bottom));
    }

    private float clamp(float x) {
        if (x > 1000) {
            return 1000;
        }
        if (x < -1000) {
            return -1000;
        }
        return x;
    }

    public void continuousAutoFocus() {
        if (camera != null) {
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                List<String> focusModes = params.getSupportedFocusModes();
                if (focusModes
                        .contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setAutoWhiteBalance() {
        if (camera != null) {
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setCameraParams(Camera.Parameters params) {
        try {
            camera.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAutoScene() {
        if (camera != null) {
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Camera.Parameters getCameraParameters() throws Exception {
        return camera.getParameters();
    }

    public void initFlashMode() {
        if (camera != null) {
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                params.setFlashMode(flashMode);
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setAutoFlash() {
        if (camera != null) {
            this.flashMode = Camera.Parameters.FLASH_MODE_AUTO;
            initFlashMode();
        }
    }

    public void setCloseFlash() {
        if (camera != null) {
            this.flashMode = Camera.Parameters.FLASH_MODE_OFF;
            initFlashMode();
        }
    }

    public void setOpenFlash() {
        if (camera != null) {
            this.flashMode = Camera.Parameters.FLASH_MODE_ON;
            initFlashMode();
        }
    }

    @SuppressWarnings("deprecation")
    public void setPreviewMaxFps() {
        if (camera != null) {
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                List<Integer> frameRates = params.getSupportedPreviewFrameRates();
                if (frameRates != null) {
                    Integer max = Collections.max(frameRates);
                    params.setPreviewFrameRate(max);
                }
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelAutoFocus() {
        if (camera != null) {
            try {
                camera.cancelAutoFocus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAutoFocusAvailable() {
        if (camera != null) {
            if (!inPreview) {
                return false;
            }
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                if (Camera.Parameters.FOCUS_MODE_AUTO.equals(params.getFocusMode())
                        || Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
                                .equals(params.getFocusMode())) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getFlashMode() throws Exception {
        if (camera != null) {
            return (getCameraParameters().getFlashMode());
        }
        return "";
    }

    public void setFlashMode(String mode) {
        if (camera != null) {
            Camera.Parameters params;
            try {
                params = getCameraParameters();
                params.setFlashMode(mode);
                setCameraParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getDisplayOrientation() {
        return (displayOrientation);
    }

    public void setPreviewDisplay(SurfaceHolder previewHolder) {
        try {
            camera.setPreviewDisplay(previewHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class PhotoOutputSize {
        public int width;
        public int height;

        public PhotoOutputSize(int width, int height) {
            super();
            this.width = width;
            this.height = height;
        }
    }
}
