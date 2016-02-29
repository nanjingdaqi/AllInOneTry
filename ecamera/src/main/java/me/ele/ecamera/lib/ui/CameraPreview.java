package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView {

	public CameraPreview(Context context) {
		super(context);
	}

	public Bitmap toBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		Canvas bitCanvas = new Canvas(bitmap);
		draw(bitCanvas);
		return bitmap;
	}
}
