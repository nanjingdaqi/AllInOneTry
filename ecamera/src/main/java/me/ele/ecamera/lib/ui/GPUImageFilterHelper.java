package me.ele.ecamera.lib.ui;

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

public class GPUImageFilterHelper {

  public static void applyFilter(GPUImage gpuImage) {
    gpuImage.setFilter(new GPUImageFilterGroup(createFilters()));
  }

  private static List<GPUImageFilter> createFilters() {
    return new ArrayList<GPUImageFilter>() {
      {
        add(new GPUImageGammaFilter(1.15f));
        add(new GPUImageSharpenFilter(0.15f));
        add(new GPUImageContrastFilter(1.15f));
        add(new GPUImageSaturationFilter(1.3f));
        add(new GPUImageBrightnessFilter(0.14f));
      }
    };
  }
}
