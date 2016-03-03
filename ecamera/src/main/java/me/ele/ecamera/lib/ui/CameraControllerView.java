package me.ele.ecamera.lib.ui;

import me.ele.ecamera.R;
import me.ele.ecamera.activity.CropActivity;
import me.ele.ecamera.activity.EcameraActivity;
import me.ele.ecamera.consts.ECameraConsts;
import me.ele.ecamera.lib.CameraController.PhotoOutputSize;
import me.ele.ecamera.lib.ui.CameraStateButton.State;
import me.ele.ecamera.lib.ui.CameraStateButton.State.StateChangedListener;
import me.ele.ecamera.utils.ImageDecoder;
import me.ele.ecamera.utils.LimitedDiscCache;
import me.ele.ecamera.utils.ScreenUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class CameraControllerView extends ViewGroup implements
		View.OnClickListener {
	
	public static final int REQUEST_PICK_IMAGE = 1;
	public static final int REQUEST_CROP_IMAGE = 2;
	private static final int SMOOTH_SCROLL_DURATION = 400;
	private CameraStateButton shutter;
	private CameraStateButton gallery;
	private CameraStateButton flashMode;
	private CameraStateButton photoDelete;
	private CameraStateButton photoOk;
	private CameraStateButton photoFilter;
	private CameraView cameraView;
	private ScrollableLayout cameraControllerScrollableLayout;
	private ScrollableLayout photoOptimizingScrollableLayout;
	private CameraStateButton.State autoFlashMode = new CameraStateButton.State(
			R.drawable.camera_lamp_open, new StateChangedListener() {

				@Override
				public void stateChanged(State preState) {
					cameraView.getCameraController().setAutoFlash();
				}
			});
	private CameraStateButton.State closeFlashMode = new CameraStateButton.State(
			R.drawable.camera_lamp_close, new StateChangedListener() {

				@Override
				public void stateChanged(State preState) {
					cameraView.getCameraController().setCloseFlash();
				}
			});
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
		photoFilter = (CameraStateButton) findViewById(R.id.photo_optimize);
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
		flashMode.changeState(autoFlashMode);
		flashMode.setOnClickListener(this);
		gallery.setOnClickListener(this);
		shutter.setOnClickListener(this);
		photoOk.setOnClickListener(this);
		photoDelete.setOnClickListener(this);
		photoFilter.setOnClickListener(this);
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
        } else if (id == R.id.photo_optimize) {
			if (actionCallback != null) {
				actionCallback.onPhotoOptimize(data);
			}
            optimizePhoto(v);
        }
	}

    protected void optimizePhoto(View v) {
        if (!v.isSelected()) {
            v.setSelected(true);
            cameraView.filter();
        } else {
            v.setSelected(false);
            cameraView.reset();
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
        int imageResource = flashMode.getImageResource();
        if (imageResource == R.drawable.camera_lamp_open) {
            flashMode.changeState(closeFlashMode);
        } else if (imageResource == R.drawable.camera_lamp_close) {
            flashMode.changeState(autoFlashMode);
        }
    }

    protected void gotGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);;
        photoPickerIntent.setType("image/*");
        getActivity().startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);
    }

    protected void takePicture() {
        cameraView.getCameraController().takePicture(new PictureCallback() {
        	
        	@Override
        	public void onPictureTaken(byte[] data, Camera camera) {
        	    PhotoOutputSize photoOutputSize = cameraView.getCameraController().getAdjustedPhotoOutputSize();
        		cameraView.stopPreviewAndShow(ImageDecoder.decodeByteArrayAndCropSquare(getContext()
        		        , data
        		        , photoOutputSize.width, photoOutputSize.height));
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
		showGuideToast();
	}
	
	private void showGuideToast() {
	    SharedPreferences spf = getContext().getSharedPreferences(ECameraConsts.ECAMERA_SPF, Context.MODE_PRIVATE);
	    if (!spf.getBoolean(ECameraConsts.ECAMERA_SPF_KEY_PHOTO_OPTIMIZED_GUIDE, false)) {
	        Toast guide = Toast.makeText(getActivity(), "点击中间按钮自动优化图片", Toast.LENGTH_LONG);
	        guide .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, ScreenUtils.dip2px(getActivity(), 150));
	        guide.show();
	        spf.edit().putBoolean(ECameraConsts.ECAMERA_SPF_KEY_PHOTO_OPTIMIZED_GUIDE, true).commit();
	    }
	}

	public void hidePhotoOptimizingLayout() {
		if (isScrolling()) {
			return;
		}
		photoOptimizingScrollableLayout.smoothScrollBy(0, - getMeasuredHeight(),
				SMOOTH_SCROLL_DURATION);
		cameraControllerScrollableLayout.smoothScrollBy(0, getMeasuredHeight(),
				SMOOTH_SCROLL_DURATION);
	}
	
    public void handleActivityResult(final int requestCode,
            final int resultCode, final Intent data) {
        if (cameraView == null) {
            return;
        }
        if (resultCode != Activity.RESULT_OK) {
            cameraView.startPreview();
            return;
        }
        switch (requestCode) {
        case CameraControllerView.REQUEST_PICK_IMAGE:
            cameraView.stopRreview();
            gotoCropImage(data);
            break;
        case CameraControllerView.REQUEST_CROP_IMAGE:
            showPhotoOptimizingLayout();
            cameraView.stopPreviewAndShow(data.getData());
            break;
        default:
            cameraView.startPreview();
            break;
        }
    }
    
    private void gotoCropImage(Intent data) {
        Intent intent = new Intent(getContext(), CropActivity.class);
        intent.setData(data.getData());
        ((Activity) getContext()).startActivityForResult(intent, CameraControllerView.REQUEST_CROP_IMAGE);
    }

	public void setActionCallback(EcameraActivity.ActionCallback actionCallback) {
		this.actionCallback = actionCallback;
	}

	public void setData(Bundle data) {
		this.data = data;
	}
}
