package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import me.ele.ecamera.R;

public class CameraFilterPreviewView extends FrameLayout {

  private ImageView orgImageView;
  private ImageView filteredImageView;

  private FrameLayout orgView;
  private FrameLayout filteredView;

  private View orgIndicator;
  private View filteredIndicator;

  private GPUImage gpuImage = new GPUImage(getContext());
  private Bitmap filteredBitmap;

  private FilterSelectedListener filterSelectedListener;

  public CameraFilterPreviewView(Context context) {
    this(context, null);
  }

  public CameraFilterPreviewView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CameraFilterPreviewView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    inflate(context, R.layout.camera_filter_view, this);

    GPUImageFilterHelper.applyFilter(gpuImage);

    orgImageView = (ImageView) findViewById(R.id.original_image);
    filteredImageView = (ImageView) findViewById(R.id.filtered_image);

    // hack way. ImageView#setImageBitmap cannot show latest images
    orgImageView.setLayerType(LAYER_TYPE_HARDWARE, null);
    filteredImageView.setLayerType(LAYER_TYPE_HARDWARE, null);

    orgIndicator = findViewById(R.id.org_indicator);
    filteredIndicator = findViewById(R.id.filter_indicator);

    orgView = (FrameLayout) findViewById(R.id.original);
    orgView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        orgIndicator.setVisibility(VISIBLE);
        filteredIndicator.setVisibility(INVISIBLE);
        if (filterSelectedListener != null) {
          filterSelectedListener.onSelected(false);
        }
      }
    });

    filteredView = (FrameLayout) findViewById(R.id.filtered);
    filteredView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        orgIndicator.setVisibility(INVISIBLE);
        filteredIndicator.setVisibility(VISIBLE);
        if (filterSelectedListener != null) {
          filterSelectedListener.onSelected(true);
        }
      }
    });
  }

  public void setFilterSelectedListener(FilterSelectedListener filterSelectedListener) {
    this.filterSelectedListener = filterSelectedListener;
  }

  public void stopPreviewAndShow(Bitmap bitmap) {
    setImage(bitmap);
  }

  public void stopPreviewAndShow(Uri uri) {
    try {
      Bitmap bitmap =
          BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
      setImage(bitmap);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void setImage(Bitmap bitmap) {
    orgImageView.setImageBitmap(bitmap);
    gpuImage.setImage(bitmap);
    if (filteredBitmap != null) {
      filteredBitmap.recycle();
    }
    filteredBitmap = gpuImage.getBitmapWithFilterApplied();
    filteredImageView.setImageBitmap(filteredBitmap);

    // select org view at first
    orgView.performClick();
  }

  public interface FilterSelectedListener {
    void onSelected(boolean hasFilter);
  }
}
