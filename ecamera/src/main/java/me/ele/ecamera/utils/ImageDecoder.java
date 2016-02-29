package me.ele.ecamera.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.ele.ecamera.exif.ExifInterface;
import me.ele.ecamera.exif.ExifTag;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.net.Uri;
import android.util.Log;

public class ImageDecoder {

    private static final int MIN_OUT_SQUARE_SIDE_LENGTH = 640;

    public synchronized static int getOutputSquareSideLength(Context context) {
        int outputSquareSideLength = context.getResources().getDisplayMetrics().widthPixels;
        return Math.max(outputSquareSideLength, MIN_OUT_SQUARE_SIDE_LENGTH);
    }

    public synchronized static Bitmap decodeUri(Context context, Uri uri, int reqWidth, int reqHeight) {
        try {
            Log.d("ImageDecoder", "decodeUri out reqWidth is " + reqWidth);
            Log.d("ImageDecoder", "decodeUri out reqHeight is " + reqHeight);
            byte[] data = convertInputStream2Bytes(context.getContentResolver().openInputStream(uri));
            ExifInfo exifInfo = defineExifOrientation(data);
            Bitmap originalBitmap = getBestSimpledBitmap(data, exifInfo, reqWidth, reqHeight);
            Matrix matrix = new Matrix();
            int outputWidth = originalBitmap.getWidth();
            int outoutHeight = originalBitmap.getHeight();
            Log.d("ImageDecoder", "decodeUri out outputWidth is " + outputWidth);
            Log.d("ImageDecoder", "decodeUri out outoutHeight is " + outoutHeight);
            // Flip bitmap if need
            if (exifInfo.flipHorizontal) {
                matrix.postScale(-1, 1);
            }
            if (exifInfo.rotation != 0) {
                matrix.postRotate(exifInfo.rotation);
            }

            Bitmap outputBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, outputWidth, outoutHeight, matrix, true);
            if (originalBitmap != outputBitmap) {
                originalBitmap.recycle();
                System.gc();
            }
            return outputBitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized static Bitmap decodeByteArrayAndCropSquare(Context context,
            byte[] data, int outputWidth, int outoutHeight) {
        ExifInfo exifInfo = defineExifOrientation(data);
        Bitmap originalBitmap = getBestSimpledBitmap(data, exifInfo, outputWidth, outoutHeight);
        Log.d("ImageDecoder", "out outputWidth is " + outputWidth);
        Log.d("ImageDecoder", "out outoutHeight is " + outoutHeight);
        int originWidth = originalBitmap.getWidth();
        int originHeight = originalBitmap.getHeight();
        if (exifInfo.rotation == 90 || exifInfo.rotation == 270) {
            originWidth = originalBitmap.getHeight();
            originHeight = originalBitmap.getWidth();
        }
        Log.d("ImageDecoder", "original bitmap width is " + originWidth);
        Log.d("ImageDecoder", "original bitmap height is " + originHeight);
        final float scaleX = outputWidth / (float) originWidth;
        final float scaleY = outputWidth / (float) originHeight;
        final float scale = Math.max(scaleX, scaleY);
        Log.d("ImageDecoder", "scaleRate is " + scale);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        if (exifInfo.flipHorizontal) {
            matrix.postScale(-1, 1);
        }
        if (exifInfo.rotation == 90) {
            matrix.postTranslate(outputWidth, 0);
            matrix.postRotate(exifInfo.rotation, outputWidth, 0);
        } 
        Bitmap outputBitmap = Bitmap.createBitmap((int) outputWidth,
                (int) outputWidth, Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);
        canvas.setMatrix(matrix);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));
        canvas.drawBitmap(originalBitmap, 0, 0, new Paint(
                Paint.FILTER_BITMAP_FLAG));
        return outputBitmap;
    }
    
    public static Bitmap getBestSimpledBitmap(byte[] data, ExifInfo exifInfo, int reqWidth, int reqHeight) {
        return BitmapFactory.decodeByteArray(data, 0,
                data.length, getBestBitmapOptions(data, exifInfo, reqWidth, reqHeight));
    }
    
    public static BitmapFactory.Options getBestBitmapOptions(byte[] data, ExifInfo exifInfo, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int spmpleSize = calculateInSampleSize(exifInfo, options, reqWidth, reqHeight);
        Log.d("ImageDecoder", "best sample size is " + spmpleSize);
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inJustDecodeBounds = false;
        options.inScaled = false;
        options.inSampleSize = spmpleSize;
        return options;
    }

    public static int calculateInSampleSize(ExifInfo exifInfo, BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        // Raw height and width of image
        int height = options.outHeight;
        int width = options.outWidth;
        Log.d("ImageDecoder", "raw bitmap width " + width);
        Log.d("ImageDecoder", "raw bitmap height " + height);
        if (exifInfo.rotation == 270 || exifInfo.rotation == 90) {
            height = options.outWidth;
            width = options.outHeight;
            Log.d("ImageDecoder", "rotation " + exifInfo.rotation);
            Log.d("ImageDecoder", "ratated raw bitmap width " + width);
            Log.d("ImageDecoder", "ratated raw bitmap height " + height);
        }
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((height / inSampleSize) > reqHeight
                    && (width / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

//    public synchronized static Bitmap decodeByteArray(Context context,
//            byte[] data, Camera.Size size) {
//        ExifInfo exifInfo = defineExifOrientation(data);
//
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        options.inScaled = false;
//
//        Bitmap originalBitmap = BitmapFactory.decodeByteArray(data, 0,
//                data.length, options);
//        final float outputSquareSideLength = getOutputSquareSideLength(context);
//        float outputWidth = Math.min(size.height, size.width);
//        float outHeight = Math.max(size.height, size.width);
//        if (outputWidth < outputSquareSideLength) {
//            outputWidth = outputSquareSideLength;
//            outHeight = outHeight * outputSquareSideLength / outputWidth;
//        }
//        final float originalWidth = Math.min(options.outWidth,
//                options.outHeight);
//        final float originalHeight = Math.max(options.outWidth,
//                options.outHeight);
//        float ratioX = outputWidth / originalWidth;
//        float ratioY = outHeight / originalHeight;
//
//        Matrix matrix = new Matrix();
//        matrix.setScale(ratioX, ratioY);
//        // Flip bitmap if need
//        if (exifInfo.flipHorizontal) {
//            matrix.postScale(-1, 1);
//        }
//        matrix.postRotate(exifInfo.rotation);
//
//        Bitmap outputBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,
//                options.outWidth, options.outHeight, matrix, true);
//        if (originalBitmap != outputBitmap) {
//            originalBitmap.recycle();
//        }
//        return outputBitmap;
//    }

    public static synchronized ExifInfo defineExifOrientation(
            InputStream inStream) {
        ExifInterface exif = new ExifInterface();
        try {
            exif.readExif(inStream);
            return defineExifOrientation(exif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExifInfo(0, false);
    }

    public static synchronized ExifInfo defineExifOrientation(byte[] data) {
        ExifInterface exif = new ExifInterface();
        try {
            exif.readExif(data);
            return defineExifOrientation(exif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExifInfo(0, false);
    }

    private static ExifInfo defineExifOrientation(ExifInterface exif) {
        int rotation = 0;
        boolean flip = false;
        try {
            ExifTag exifTag = exif.getTag(ExifInterface.TAG_ORIENTATION);
            if (exifTag == null) {
                return new ExifInfo(rotation, flip);
            }
            int exifOrientation = exifTag.getValueAsInt(rotation);
            switch (exifOrientation) {
            case ExifInterface.Orientation.FLIP_HORIZONTAL:
                flip = true;
            case ExifInterface.Orientation.NORMAL:
                rotation = 0;
                break;
            case ExifInterface.Orientation.TRANSVERSE:
                flip = true;
            case ExifInterface.Orientation.ROTATE_90:
                rotation = 90;
                break;
            case ExifInterface.Orientation.FLIP_VERTICAL:
                flip = true;
            case ExifInterface.Orientation.ROTATE_180:
                rotation = 180;
                break;
            case ExifInterface.Orientation.TRANSPOSE:
                flip = true;
            case ExifInterface.Orientation.ROTATE_270:
                rotation = 270;
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ExifInfo(rotation, flip);
    }

    protected static class ExifInfo {

        public final int rotation;
        public final boolean flipHorizontal;

        protected ExifInfo() {
            this.rotation = 0;
            this.flipHorizontal = false;
        }

        protected ExifInfo(int rotation, boolean flipHorizontal) {
            this.rotation = rotation;
            this.flipHorizontal = flipHorizontal;
        }
    }

    private static synchronized byte[] convertInputStream2Bytes(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        try {
            while ((len = is.read(b, 0, b.length)) != -1) {
                baos.write(b, 0, len);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }
}
