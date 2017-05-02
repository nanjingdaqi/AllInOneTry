package org.peace.allinone;

import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by daqi on 17-4-10.
 */

public class SettingsLeakService extends DisplayLeakService {

  static final String TAG = SettingsLeakService.class.getSimpleName();

  File ROOT_DIR;

  @Override public void onCreate() {
    super.onCreate();
    ROOT_DIR = new File(Environment.getExternalStorageDirectory(), "leak-canary" + File.separator + getPackageName());
    if (!ROOT_DIR.exists()) {
      ROOT_DIR.mkdirs();
    }
  }

  @Override
  protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
    super.afterDefaultHandling(heapDump, result, leakInfo);

    Log.e(TAG, "get result");

    if (!result.leakFound || result.excludedLeak) {
      return;
    }

    Log.e(TAG, "simple leak trace: " + result.leakTrace);

    dumpLeakInfo(heapDump, result, leakInfo);

    File heapDumpFile = heapDump.heapDumpFile;
    File dest = new File(ROOT_DIR, heapDumpFile.getName());
    if (dest.exists()) {
      dest.delete();
    }
    try {
      dest.createNewFile();
      copy(heapDumpFile, dest);
    } catch (IOException e) {
      e.printStackTrace();
    }

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

  public void dumpLeakInfo(HeapDump heapDump, AnalysisResult result, String leakInfo) {
    File infoFile = touchLeakInfoFile();
    String content = new StringBuilder()
        .append("simple ref:\n")
        .append(result.leakTrace + "\n")
        .append("\n")
        .append("hprof file name: " + heapDump.heapDumpFile.getName() + "\n")
        .toString();
    try {
      FileOutputStream fos = new FileOutputStream(infoFile);
      fos.write(content.getBytes());
      fos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public File touchLeakInfoFile() {
    String suffix = DateFormat.format("MM-dd_hh-mm-ss", new Date()).toString();
    File file = new File(ROOT_DIR, "leak-info_" + suffix + ".txt");
    if (file.exists()) {
      file.delete();
    }
    try {
      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return file;
  }
}
