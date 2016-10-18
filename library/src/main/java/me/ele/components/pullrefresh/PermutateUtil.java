package me.ele.components.pullrefresh;

import java.util.LinkedList;
import java.util.List;

class PermutateUtil {
  private PermutateUtil() {
  }

  private static void swap(int[] a, int i, int j) {
    int tmp;
    tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private static void arrange(int[] a, int start, int length, List<int[]> outList) {
    if (start == length - 1) {
      int[] res = new int[length];
      System.arraycopy(a, 0, res, 0, length);
      outList.add(res);
    } else {
      for (int i = start; i < length; i++) {
        swap(a, start, i);
        arrange(a, start + 1, length, outList);
        swap(a, start, i);
      }
    }
  }

  public static int[][] arrange(int[] array) {
    List<int[]> result = new LinkedList<>();
    arrange(array, 0, array.length, result);
    return result.toArray(new int[0][]);
  }
}
