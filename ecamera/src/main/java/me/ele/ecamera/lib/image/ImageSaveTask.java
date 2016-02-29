/***
  Copyright (c) 2013-2014 CommonsWare, LLC
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package me.ele.ecamera.lib.image;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import me.ele.ecamera.lib.CameraController;
import me.ele.ecamera.utils.LimitedDiscCache;
import android.content.ContentValues;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.util.Log;

public class ImageSaveTask extends Thread {
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String CAMERA_IMAGE_BUCKET_NAME =
            Environment.getExternalStorageDirectory().toString()
            + "/DCIM/Camera";
    private static final String[] SCAN_TYPES = { IMAGE_JPEG };
    private Context ctxt = null;
    private byte[] data;

    public ImageSaveTask(Context ctxt, byte[] data,
            CameraController cameraController) {
        this.data = data;
        this.ctxt = ctxt;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis();
        saveImage(data);
        Log.e("image save cost time", System.currentTimeMillis() - now + " ms");
    }

    public void saveImage(byte[] image) {
        String title = LimitedDiscCache.getFilename();
        String fileName = title + ".jpg";
        File photo = getPhotoFile(fileName);

        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos = new FileOutputStream(photo.getPath());
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bos.write(image);
            bos.flush();
            fos.getFD().sync();
            bos.close();
            ContentValues values = new ContentValues(9);
            values.put(Images.Media.TITLE, title);

            // That filename is what will be handed to Gmail when a user shares
            // a
            // photo. Gmail gets the name of the picture attachment from the
            // "DISPLAY_NAME" field.
            values.put(Images.Media.DISPLAY_NAME, fileName);
            values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(Images.Media.MIME_TYPE, IMAGE_JPEG);
            values.put(Images.Media.ORIENTATION, 0);
            values.put(Images.Media.DATA, photo.getCanonicalPath());
            values.put(Images.Media.SIZE, photo.length());

            ctxt.getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, values);
            MediaScannerConnection.scanFile(ctxt, new String[] { photo.getPath() }, SCAN_TYPES, null);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    

    protected File getPhotoFile(String name) {
        File dir = new File(CAMERA_IMAGE_BUCKET_NAME);
        dir.mkdirs();
        return (new File(dir, name));
    }
}