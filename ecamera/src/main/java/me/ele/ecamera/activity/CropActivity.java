package me.ele.ecamera.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import me.ele.ecamera.R;
import me.ele.ecamera.lib.ui.ContentLoadingProgressBar;
import me.ele.ecamera.lib.ui.photoview.PhotoView;
import me.ele.ecamera.lib.ui.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import me.ele.ecamera.utils.ImageDecoder;
import me.ele.ecamera.utils.LimitedDiscCache;
import me.ele.ecamera.utils.ScreenUtils;

public class CropActivity extends Activity implements OnMatrixChangedListener {

    private TextView title;
    private Bitmap bitmap;
    private PhotoView photoView;
    private float matrixWidth;
    private float matrixHeight;
    private float matrixLeft;
    private float matixTop;
    private View operationBar;
    private ContentLoadingProgressBar loadingView;
    private LimitedDiscCache discCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        getWindow().setBackgroundDrawableResource(android.R.color.black);
        discCache = new LimitedDiscCache(this);
        title = (TextView) findViewById(R.id.title);
        operationBar = findViewById(R.id.operation_bar);
        initPhotoView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoView.recycle();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
            photoView = null;
            System.gc();
        }
    }

    private void initPhotoView() {
        loadingView = (ContentLoadingProgressBar) findViewById(R.id.loading);
        loadingView.show();
        photoView = (PhotoView) findViewById(R.id.iv_photo);
        findViewById(R.id.crop_cancel).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                    }
                });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Uri data = getIntent().getData();
                    bitmap = ImageDecoder.decodeUri(getApplicationContext(), data,
                            ScreenUtils.getScreenWidth(CropActivity.this),
                            ScreenUtils.getScreenWidth(CropActivity.this));
                    if (bitmap == null) {
                        toastErrorAndFinish();
                        //bitmap为空，直接返回，用户无法点击裁剪完成
                        return;
                    }
                    if (data != null) {
                        Log.d("crop image ", data.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    toastErrorAndFinish();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            loadingView.hide();
                            photoView.setPadding(0, title.getMeasuredHeight(), 0, operationBar.getMeasuredHeight());
                            photoView.setOnMatrixChangeListener(CropActivity.this);
                            photoView.setImageBitmap(bitmap);
                            findViewById(R.id.crop_ok).setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (photoView.isFling() || photoView.isZooming()) {
                                        return;
                                    }
                                    cropImage();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            toastErrorAndFinish();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onMatrixChanged(RectF rect) {
        matrixWidth = rect.right - rect.left;
        matrixHeight = rect.bottom - rect.top;
        matrixLeft = Math.abs(rect.left);
        matixTop = Math.abs(rect.top);
    }

    public void cropImage() {
        final float originalWidth = bitmap.getWidth();
        final float originalHeight = bitmap.getHeight();
        final float actualCropX = (int) (originalWidth / matrixWidth * matrixLeft);
        final float actualCropY = (int) (originalHeight / matrixHeight * matixTop);
        
        final float outputSquareSideLength = ImageDecoder.getOutputSquareSideLength(this);
        
        final float actualCropWidth = (int) Math.min(originalWidth, originalWidth / matrixWidth * outputSquareSideLength);
        final float actualCropHeight = (int) Math.min(originalHeight, originalHeight / matrixHeight * outputSquareSideLength);
        float actualCropSquareSideLength = Math.min(actualCropWidth, actualCropHeight);
        
        if ((actualCropX + actualCropSquareSideLength) > originalWidth) {
            actualCropSquareSideLength = originalWidth - actualCropX;
        }
        if ((actualCropY + actualCropSquareSideLength) > originalHeight) {
            actualCropSquareSideLength = originalHeight - actualCropY;
        }
        
        
        Matrix matrix = new Matrix();
        
        float scale = (float) outputSquareSideLength / actualCropSquareSideLength;
        
        matrix.setScale(scale, scale);
        final Bitmap croppedBitmap = Bitmap.createBitmap(bitmap,
                Math.round(actualCropX), Math.round(actualCropY),
                Math.round(actualCropSquareSideLength),
                Math.round(actualCropSquareSideLength), matrix, true);
        if (croppedBitmap != null) {
            try {
                Intent intent = new Intent();
                intent.setData(discCache.save(croppedBitmap, true));
                setResult(Activity.RESULT_OK, intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void toastErrorAndFinish() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CropActivity.this, R.string.fail_to_deal_image, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
