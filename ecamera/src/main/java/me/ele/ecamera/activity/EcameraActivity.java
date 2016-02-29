package me.ele.ecamera.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.ele.ecamera.R;
import me.ele.ecamera.lib.CameraUtils;
import me.ele.ecamera.lib.ui.CameraControllerView;
import me.ele.ecamera.lib.ui.CameraView;

public class EcameraActivity extends Activity {

	public interface ActionCallback {
		void onSwitchGallery(Bundle data);

		void onToggleFlashMode(Bundle data);

		void onTakePhoto(Bundle data);

		void onPhotoOptimize(Bundle data);

		void onPhotoConfirm(Bundle data);

		void onPhotoCancel(Bundle data);
	}

	public static final String TITLE = "title";
    private CameraView cameraView;
	private CameraControllerView cameraControllerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(android.R.color.black);
        if (!CameraUtils.hasCameraPermission(this)) {
            Toast.makeText(this, "相机权限被禁用，无法打开相机", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(Color.BLACK);
		}
		setContentView(R.layout.activity_main);
		cameraView = (CameraView) findViewById(R.id.camera_view);
		cameraControllerView = (CameraControllerView) findViewById(R.id.camera_controller_view);
		cameraControllerView.setData(getIntent().getExtras());
		TextView title = (TextView) findViewById(R.id.food_title);
		title.setText(getIntent().getStringExtra(TITLE));
	}
	
	@Override
	protected void onActivityResult(final int requestCode,
			final int resultCode, final Intent data) {
	    cameraControllerView.handleActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		cameraView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		cameraView.onResume();
	}

    @Override
    public void finish() {
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] {android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);      
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[] {android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        int activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        int activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();
        super.finish();
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }
	
	public void setActionCallback(ActionCallback callback) {
		cameraControllerView.setActionCallback(callback);
	}
}
