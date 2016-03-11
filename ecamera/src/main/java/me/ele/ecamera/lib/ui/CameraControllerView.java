package me.ele.ecamera.lib.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.ele.ecamera.R;
import me.ele.ecamera.activity.CropActivity;
import me.ele.ecamera.activity.EcameraActivity;
import me.ele.ecamera.lib.CameraController.PhotoOutputSize;
import me.ele.ecamera.utils.ImageDecoder;
import me.ele.ecamera.utils.LimitedDiscCache;

public class CameraControllerView extends ViewGroup implements
    View.OnClickListener, CameraFilterPreviewView.FilterSelectedListener {

  public static final int REQUEST_PICK_IMAGE = 1;
  public static final int REQUEST_CROP_IMAGE = 2;
  private static final int SMOOTH_SCROLL_DURATION = 400;

  // record flash state during the whole app life
  private static CameraStateButton.State flashState;

  private CameraStateButton.State autoFlashState =
      new CameraStateButton.State(R.drawable.camera_lamp_auto) {
        @Override
        public void apply() {
          cameraView.getCameraController().setAutoFlash();
        }
      };
  private CameraStateButton.State closeFlashState =
      new CameraStateButton.State(R.drawable.camera_lamp_close) {
        @Override
        public void apply() {
          cameraView.getCameraController().setCloseFlash();
        }
      };
  private CameraStateButton.State openFlashState =
      new CameraStateButton.State(R.drawable.camera_lamp_open) {
        @Override public void apply() {
          cameraView.getCameraController().setOpenFlash();
        }
      };

  private CameraStateButton shutter;
  private CameraStateButton gallery;
  private CameraStateButton flashMode;
  private CameraStateButton photoDelete;
  private CameraStateButton photoOk;

  private CameraFilterPreviewView photoFilter;

  private CameraView cameraView;
  private ScrollableLayout cameraControllerScrollableLayout;
  private ScrollableLayout photoOptimizingScrollableLayout;
  private LimitedDiscCache discCache;

  private EcameraActivity.ActionCallback actionCallback;

  private Bundle data;

  public CameraControllerView(Context context) {
    super(context);
    init(context);
  }

  public CameraControllerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CameraControllerView(Context context, AttributeSet attrs,
      int defStyle) {
    super(context, attrs, defStyle);
    init(context);
  }

  private void init(Context context) {
    discCache = new LimitedDiscCache(context);
    setBackgroundColor(Color.BLACK);
    setClickable(true);
    cameraControllerScrollableLayout = (ScrollableLayout) LayoutInflater
        .from(context).inflate(R.layout.camera_conroller_view, this,
            false);
    photoOptimizingScrollableLayout = (ScrollableLayout) LayoutInflater
        .from(context).inflate(R.layout.photo_optimizing_view, this,
            false);
    addView(cameraControllerScrollableLayout);
    addView(photoOptimizingScrollableLayout);
    shutter = (CameraStateButton) findViewById(R.id.shutter);
    gallery = (CameraStateButton) findViewById(R.id.camera_gallery);
    flashMode = (CameraStateButton) findViewById(R.id.camera_flash_mode);
    photoDelete = (CameraStateButton) findViewById(R.id.photo_del);
    photoOk = (CameraStateButton) findViewById(R.id.photo_ok);
    photoFilter = (CameraFilterPreviewView) findViewById(R.id.photo_optimize);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    measureChildren(widthMeasureSpec, heightMeasureSpec);
    int minWidth = getSuggestedMinimumWidth();
    int minHeight = getSuggestedMinimumHeight();
    int childWidth = cameraControllerScrollableLayout.getMeasuredWidth();
    int childHeight = cameraControllerScrollableLayout.getMeasuredHeight();
    if (minWidth < childWidth) {
      minWidth = childWidth;
    }
    if (minHeight < childHeight) {
      minHeight = childHeight;
    }
    final int width = resolveSize(minWidth, widthMeasureSpec);
    final int height = resolveSize(minHeight, heightMeasureSpec);
    setMeasuredDimension(width, height);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if (changed) {
      cameraControllerScrollableLayout.layout(l, 0, r, getMeasuredHeight());
      photoOptimizingScrollableLayout.layout(l, 0, r, getMeasuredHeight());
      photoOptimizingScrollableLayout.scrollBy(0, -getMeasuredHeight());
    }
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    Activity activity = getActivity();
    this.cameraView = (CameraView) activity.findViewById(R.id.camera_view);
    if (flashState == null) {
      flashState = closeFlashState;
    }
    flashMode.changeState(flashState);
    flashMode.setOnClickListener(this);
    gallery.setOnClickListener(this);
    shutter.setOnClickListener(this);
    photoOk.setOnClickListener(this);
    photoDelete.setOnClickListener(this);
    photoFilter.setFilterSelectedListener(this);
  }

  @Override public void onSelected(boolean hasFilter) {
    if (hasFilter) {
      cameraView.filter();
      if (actionCallback != null) {
        actionCallback.onPhotoOptimize(data);
      }
    } else {
      cameraView.reset();
    }
  }

  @Override
  public void onClick(View v) {
    if (isScrolling()) {
      return;
    }
    int id = v.getId();
    if (id == R.id.shutter) {
      if (actionCallback != null) {
        actionCallback.onTakePhoto(data);
      }
      takePicture();
    } else if (id == R.id.camera_gallery) {
      if (actionCallback != null) {
        actionCallback.onSwitchGallery(data);
      }
      gotGallery();
    } else if (id == R.id.camera_flash_mode) {
      if (actionCallback != null) {
        actionCallback.onToggleFlashMode(data);
      }
      toggleFlashMode();
    } else if (id == R.id.photo_del) {
      if (actionCallback != null) {
        actionCallback.onPhotoCancel(data);
      }
      deletePhoto();
    } else if (id == R.id.photo_ok) {
      if (actionCallback != null) {
        actionCallback.onPhotoConfirm(data);
      }
      submitPhoto();
    }
  }

  protected void submitPhoto() {
    try {
      Activity activity = getActivity();
      Intent intent = new Intent(activity.getIntent());
      intent.setData(discCache.save(cameraView.getPhoto(), true));
      activity.setResult(Activity.RESULT_OK, intent);
      activity.finish();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void deletePhoto() {
    photoFilter.setSelected(false);
    hidePhotoOptimizingLayout();
    cameraView.startPreview();
  }

  protected void toggleFlashMode() {
    if (flashState == openFlashState) {
      flashState = autoFlashState;
    } else if (flashState == closeFlashState) {
      flashState = openFlashState;
    } else if (flashState == autoFlashState) {
      flashState = closeFlashState;
    }
    flashMode.changeState(flashState);
  }

  protected void gotGallery() {
    Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
    photoPickerIntent.setType("image/*");
    getActivity().startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);
  }

  protected void takePicture() {
    cameraView.getCameraController().takePicture(new PictureCallback() {

      @Override
      public void onPictureTaken(byte[] data, Camera camera) {
        PhotoOutputSize photoOutputSize =
            cameraView.getCameraController().getAdjustedPhotoOutputSize();
        Bitmap bitmap =
            ImageDecoder.decodeByteArrayAndCropSquare(getContext(), data, photoOutputSize.width,
                photoOutputSize.height);
        cameraView.stopPreviewAndShow(bitmap);
        photoFilter.stopPreviewAndShow(bitmap);
        showPhotoOptimizingLayout();
      }
    });
  }

  protected Activity getActivity() {
    return (Activity) getContext();
  }

  private boolean isScrolling() {
    return photoOptimizingScrollableLayout.isScrolling()
        || cameraControllerScrollableLayout.isScrolling();
  }

  private void showPhotoOptimizingLayout() {
    if (isScrolling()) {
      return;
    }
    photoOptimizingScrollableLayout.smoothScrollBy(0, getMeasuredHeight(),
        SMOOTH_SCROLL_DURATION);
    cameraControllerScrollableLayout.smoothScrollBy(0,
        -getMeasuredHeight(), SMOOTH_SCROLL_DURATION);
  }

  public void hidePhotoOptimizingLayout() {
    if (isScrolling()) {
      return;
    }
    photoOptimizingScrollableLayout.smoothScrollBy(0, -getMeasuredHeight(),
        SMOOTH_SCROLL_DURATION);
    cameraControllerScrollableLayout.smoothScrollBy(0, getMeasuredHeight(),
        SMOOTH_SCROLL_DURATION);
  }

  public void handleActivityResult(final int requestCode, final int resultCode, final Intent data) {
    if (cameraView == null) {
      return;
    }
    if (resultCode != Activity.RESULT_OK) {
      cameraView.startPreview();
      return;
    }
    switch (requestCode) {
      case CameraControllerView.REQUEST_PICK_IMAGE:
        cameraView.stopPreview();
        gotoCropImage(data);
        break;
      case CameraControllerView.REQUEST_CROP_IMAGE:
        showPhotoOptimizingLayout();
        cameraView.stopPreviewAndShow(data.getData());
        photoFilter.stopPreviewAndShow(data.getData());
        break;
      default:
        cameraView.startPreview();
        break;
    }
  }

  private void gotoCropImage(Intent data) {
    Intent intent = new Intent(getContext(), CropActivity.class);
    intent.setData(data.getData());
    ((Activity) getContext()).startActivityForResult(intent,
        CameraControllerView.REQUEST_CROP_IMAGE);
  }

  public void setActionCallback(EcameraActivity.ActionCallback actionCallback) {
    this.actionCallback = actionCallback;
  }

  public void setData(Bundle data) {
    this.data = data;
  }
}
