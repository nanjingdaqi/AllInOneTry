package org.peace.allinone;

import android.os.Environment;
import android.util.Log;
import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by daqi on 17-4-10.
 */

public class SettingsLeakService extends DisplayLeakService {

  static final String TAG = SettingsLeakService.class.getSimpleName();

  static final String HPROF_PATH = "/sdcard/settings_hprof_path.txt";

  @Override
  protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
    super.afterDefaultHandling(heapDump, result, leakInfo);

    Log.e(TAG, "get result");

    if (!result.leakFound || result.excludedLeak) {
      return;
    }

    Log.e(TAG, "simple leak trace: " + result.leakTrace);

    Log.e(TAG, "start saving leak heap");

    File heapDumpFile = heapDump.heapDumpFile;
    File dir = new File(Environment.getExternalStorageDirectory(), "leak-canary" + File.separator + getPackageName());
    File dest = new File(dir, heapDumpFile.getName());
    if (dest.exists()) {
      dest.delete();
    }
    dest.getParentFile().mkdirs();
    try {
      dest.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      copy(heapDumpFile, dest);
    } catch (IOException e) {
      e.printStackTrace();
    }

    writeHeapPath(dest.getAbsolutePath());

    Log.e(TAG, "save path: " + dest.getAbsolutePath());
    Log.e(TAG, "start kill myself");

    throw new RuntimeException("yes find a memory leak");
  }

  public void copy(File src, File dst) throws IOException {
    InputStream in = new FileInputStream(src);
    try {
      OutputStream out = new FileOutputStream(dst);
      try {
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
          out.write(buf, 0, len);
        }
      } finally {
        out.close();
      }
    } finally {
      in.close();
    }
  }

  public static void writeHeapPath(String heapPath) {
    try {
      File addressFile = new File(HPROF_PATH);
      if (addressFile.exists()) {
        addressFile.delete();
      }
      addressFile.createNewFile();
      OutputStream os = new FileOutputStream(addressFile);
      os.write(heapPath.getBytes());
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
