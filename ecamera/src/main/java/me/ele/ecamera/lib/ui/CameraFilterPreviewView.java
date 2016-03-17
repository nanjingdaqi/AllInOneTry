package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import me.ele.ecamera.R;

public class CameraFilterPreviewView extends FrameLayout {

  private ImageView orgImageView;
  private ImageView filteredImageView;

  private FrameLayout orgView;
  private FrameLayout filteredView;

  private TextView orgTitleView;
  private TextView filteredTitleView;

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

    orgTitleView = (TextView) findViewById(R.id.org_title);
    filteredTitleView = (TextView) findViewById(R.id.filter_title);

    orgView = (FrameLayout) findViewById(R.id.original);
    orgView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        orgIndicator.setVisibility(VISIBLE);
        filteredIndicator.setVisibility(INVISIBLE);
        if (filterSelectedListener != null) {
          filterSelectedListener.onSelected(false);
        }
        orgImageView.setAlpha(1.0f);
        orgTitleView.setAlpha(1.0f);
        filteredImageView.setAlpha(0.7f);
        filteredTitleView.setAlpha(0.7f);
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
        orgImageView.setAlpha(0.7f);
        orgTitleView.setAlpha(0.7f);
        filteredImageView.setAlpha(1.0f);
        filteredTitleView.setAlpha(1.0f);
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
