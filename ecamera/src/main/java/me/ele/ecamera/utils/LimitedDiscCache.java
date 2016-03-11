/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package me.ele.ecamera.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Abstract disc cache limited by some parameter. If cache exceeds specified limit then file with
 * the most oldest last
 * usage date will be deleted.
 *
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @see BaseDiscCache
 * @see FileNameGenerator
 * @since 1.0.0
 */
public class LimitedDiscCache {

  private static final int QUALITY = 60;
  private static final int CACHE_SIZE_IN_MB = 4;
  private static final int CACHE_SIZE = CACHE_SIZE_IN_MB * 1024 * 1024;

  private static final int INVALID_SIZE = -1;

  private final AtomicLong cacheSize;
  protected File cacheDir;
  private final long sizeLimit = CACHE_SIZE;

  private final Map<File, Long> lastUsageDates =
      Collections.synchronizedMap(new HashMap<File, Long>());

  public LimitedDiscCache(Context context) {
    this.cacheDir = StorageUtils.getIndividualCacheDirectory(context);
    cacheSize = new AtomicLong();
    calculateCacheSizeAndFillUsageMap();
  }

  public synchronized static String getFilename() {
    return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.US)
        .format(new Date());
  }

  public synchronized static String getJpgFileFullName() {
    return new SimpleDateFormat("'IMG_'yyyyMMdd_HHmmss'.jpg'", Locale.US)
        .format(new Date());
  }

  private void calculateCacheSizeAndFillUsageMap() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        int size = 0;
        File[] cachedFiles = cacheDir.listFiles();
        if (cachedFiles != null) { // rarely but it can happen, don't know why
          for (File cachedFile : cachedFiles) {
            size += cachedFile.length();
            lastUsageDates.put(cachedFile, cachedFile.lastModified());
          }
          cacheSize.set(size);
        }
      }
    }).start();
  }

  private void put(File file) {
    long valueSize = file.length();
    long curCacheSize = cacheSize.get();

    while (curCacheSize + valueSize > sizeLimit) {
      long freedSize = removeNext();
      if (freedSize == INVALID_SIZE) break; // cache is empty (have nothing to delete)
      curCacheSize = cacheSize.addAndGet(-freedSize);
    }
    cacheSize.addAndGet(valueSize);

    Long currentTime = System.currentTimeMillis();
    file.setLastModified(currentTime);
    lastUsageDates.put(file, currentTime);
  }

  public Uri save(Bitmap bitmap, boolean recycleBitmap) throws Exception {
    File file = new File(cacheDir, getJpgFileFullName());
    bitmap.compress(CompressFormat.JPEG, QUALITY, new FileOutputStream(file));
    if (recycleBitmap) {
      bitmap.recycle();
      bitmap = null;
    }
    put(file);
    return Uri.fromFile(file);
  }

  public void clear() {
    lastUsageDates.clear();
    cacheSize.set(0);
    File[] files = cacheDir.listFiles();
    if (files != null) {
      for (File f : files) {
        f.delete();
      }
    }
  }

  /** Remove next file and returns it's size */
  private long removeNext() {
    if (lastUsageDates.isEmpty()) {
      return INVALID_SIZE;
    }
    Long oldestUsage = null;
    File mostLongUsedFile = null;
    Set<Entry<File, Long>> entries = lastUsageDates.entrySet();
    synchronized (lastUsageDates) {
      for (Entry<File, Long> entry : entries) {
        if (mostLongUsedFile == null) {
          mostLongUsedFile = entry.getKey();
          oldestUsage = entry.getValue();
        } else {
          Long lastValueUsage = entry.getValue();
          if (lastValueUsage < oldestUsage) {
            oldestUsage = lastValueUsage;
            mostLongUsedFile = entry.getKey();
          }
        }
      }
    }

    long fileSize = 0;
    if (mostLongUsedFile != null) {
      if (mostLongUsedFile.exists()) {
        fileSize = mostLongUsedFile.length();
        if (mostLongUsedFile.delete()) {
          lastUsageDates.remove(mostLongUsedFile);
        }
      } else {
        lastUsageDates.remove(mostLongUsedFile);
      }
    }
    return fileSize;
  }
}