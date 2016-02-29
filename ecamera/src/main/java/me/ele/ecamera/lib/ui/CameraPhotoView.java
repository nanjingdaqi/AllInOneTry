package me.ele.ecamera.lib.ui;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraPhotoView extends SurfaceView implements Callback {

	private Bitmap photo;
	private Bitmap filtedPhoto;
	private GPUImage gpuImage;
	private Paint paint;
	private final List<GPUImageFilter> filters = new ArrayList<GPUImageFilter>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8741176838422845670L;

		{
			add(new GPUImageGammaFilter(1.15f));
			add(new GPUImageSharpenFilter(0.15f));
			add(new GPUImageContrastFilter(1.15f));
			add(new GPUImageSaturationFilter(1.3f));
			add(new GPUImageBrightnessFilter(0.14f));
		}
	};

	public CameraPhotoView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		if (isInEditMode()) {
			return;
		}
		this.paint = new Paint();
		gpuImage = new GPUImage(context);
		gpuImage.setFilter(new GPUImageFilterGroup(filters));
		getHolder().setKeepScreenOn(true);
		getHolder().setFormat(PixelFormat.TRANSPARENT);
		getHolder().addCallback(this);
		setZOrderMediaOverlay(true);
	}

	public CameraPhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CameraPhotoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (photo != null) {
			display(photo);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

	public synchronized void display(Bitmap bitmap) {
		this.photo = bitmap;
		drawBitmap(bitmap);
	}

	private void drawBitmap(Bitmap bitmap) {
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		if (canvas != null) {
			Rect rect = new Rect();
			rect.left = 0;
			rect.top = 0;
			rect.right = getWidth();
			rect.bottom = (int) ((float) getWidth() / bitmap.getWidth() * bitmap.getHeight());
			canvas.drawBitmap(bitmap, null, rect, paint);
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	public synchronized void filter() {
		if (photo != null) {
			this.filtedPhoto = gpuImage.getBitmapWithFilterApplied(photo);
			drawBitmap(filtedPhoto);
		}
	}
	
	public synchronized Bitmap getPhoto() {
	    if (filtedPhoto != null) {
	        Log.d(getClass().getSimpleName(), "return filted photo");
	        return this.filtedPhoto;
	    }
		return this.photo;
	}

	public void clear() {
	    if (photo != null) {
	        photo.recycle();
	        photo = null;
	    }
	    if (filtedPhoto != null) {
	        filtedPhoto.recycle();
	        filtedPhoto = null;
	    }
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		if (canvas != null) {
			canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void display(Uri data) {
		try {
			display(BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(data)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		if (photo != null) {
			display(photo);
		}
		if (filtedPhoto != null) {
		    filtedPhoto.recycle();
            filtedPhoto = null;
        }
	}
	
}
