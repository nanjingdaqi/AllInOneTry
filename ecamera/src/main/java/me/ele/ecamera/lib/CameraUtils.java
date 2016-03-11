/***
 * Copyright (c) 2013 CommonsWare, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.ele.ecamera.lib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CameraUtils {

  public static Camera.Size getBestPreviewSize(int displayOrientation, int squareSideLength,
      Camera.Parameters parameters) {

    List<Size> sizes = parameters.getSupportedPreviewSizes();
    Size optimalSize = null;
    Collections.sort(sizes, new SizeComparator());
    int minDiff = Integer.MAX_VALUE;
    for (Size size : sizes) {
      //			android.util.Log.e("supported preview size", size.width + "X" + size.height);
      int supportedPreviewWidth = size.width;
      if (displayOrientation == 90 || displayOrientation == 270) {
        supportedPreviewWidth = size.height;
      }
      int diff = Math.abs(supportedPreviewWidth - squareSideLength);
      if (diff < minDiff) {
        optimalSize = size;
        minDiff = diff;
      }
    }
    return (optimalSize);
  }

  //	public static Camera.Size getBestAspectPreviewSize(int displayOrientation,
  //			int width, int height, Camera.Parameters parameters) {
  //		return (getBestAspectPreviewSize(displayOrientation, width, height,
  //				parameters, 0.0d));
  //	}

  //	public static Camera.Size getBestAspectPreviewSize(int displayOrientation,
  //			int width, int height, Camera.Parameters parameters,
  //			double closeEnough) {
  //		double targetRatio = (double) width / height;
  //		Camera.Size optimalSize = null;
  //		double minDiff = Double.MAX_VALUE;
  //		if (displayOrientation == 90 || displayOrientation == 270) {
  //			targetRatio = (double) height / width;
  //		}
  //
  //		List<Size> sizes = parameters.getSupportedPreviewSizes();
  //
  //		Collections.sort(sizes, new SizeComparator());
  //		for (Size size : sizes) {
  //			double ratio = (double) size.width / size.height;
  //
  //			if (Math.abs(ratio - targetRatio) < minDiff) {
  //				optimalSize = size;
  //				minDiff = Math.abs(ratio - targetRatio);
  //			}
  //
  //			if (minDiff < closeEnough) {
  //				break;
  //			}
  //		}
  //		return (optimalSize);
  //	}

  public static Camera.Size getBestAspectPictureSize(int displayOrientation,
      Camera.Size previewSize, Camera.Parameters parameters) {
    double targetRatio = (double) previewSize.width / previewSize.height;
    Camera.Size optimalSize = null;
    double minDiff = Double.MAX_VALUE;
    List<Size> sizes = parameters.getSupportedPictureSizes();
    Collections.sort(sizes, Collections.reverseOrder(new SizeComparator()));
    for (Iterator<Size> i = sizes.iterator(); i.hasNext(); ) {
      Camera.Size size = i.next();
      if (size.width < previewSize.width) {
        i.remove();
      } else {
        //				android.util.Log.e("supported picture size", size.width + "X" + size.height);
      }
    }

    for (Size size : sizes) {
      double ratio = (double) size.width / size.height;

      if (Math.abs(ratio - targetRatio) < minDiff) {
        optimalSize = size;
        minDiff = Math.abs(ratio - targetRatio);
      }
    }
    return (optimalSize);
  }

  //	public static Camera.Size getLargestPictureSize(
  //			Camera.Parameters parameters) {
  //		Camera.Size result = null;
  //
  //		for (Camera.Size size : parameters.getSupportedPictureSizes()) {
  //
  //			if (result == null) {
  //				result = size;
  //			} else {
  //				int resultArea = result.width * result.height;
  //				int newArea = size.width * size.height;
  //
  //				if (newArea > resultArea) {
  //					result = size;
  //				}
  //			}
  //		}
  //
  //		return (result);
  //	}

  //	public static Camera.Size getSmallestPictureSize(
  //			Camera.Parameters parameters) {
  //		Camera.Size result = null;
  //
  //		for (Camera.Size size : parameters.getSupportedPictureSizes()) {
  //			if (result == null) {
  //				result = size;
  //			} else {
  //				int resultArea = result.width * result.height;
  //				int newArea = size.width * size.height;
  //
  //				if (newArea < resultArea) {
  //					result = size;
  //				}
  //			}
  //		}
  //
  //		return (result);
  //	}

  //	public static String findBestFlashModeMatch(Camera.Parameters params,
  //			String... modes) {
  //		String match = null;
  //
  //		List<String> flashModes = params.getSupportedFlashModes();
  //
  //		if (flashModes != null) {
  //			for (String mode : modes) {
  //				if (flashModes.contains(mode)) {
  //					match = mode;
  //					break;
  //				}
  //			}
  //		}
  //
  //		return (match);
  //	}

  private static class SizeComparator implements Comparator<Camera.Size> {
    @Override
    public int compare(Size lhs, Size rhs) {
      int left = lhs.width * lhs.height;
      int right = rhs.width * rhs.height;

      if (left < right) {
        return (-1);
      } else if (left > right) {
        return (1);
      }

      return (0);
    }
  }

  public static boolean hasCameraPermission(Context context) {
    int perm = context.checkCallingOrSelfPermission("android.permission.CAMERA");
    return perm == PackageManager.PERMISSION_GRANTED;
  }
}
