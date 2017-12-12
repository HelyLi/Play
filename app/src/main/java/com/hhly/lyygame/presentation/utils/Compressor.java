package com.hhly.lyygame.presentation.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.hhly.lyygame.domain.config.SystemConfig;

import java.io.File;
import java.io.IOException;

/**
 * 图片压缩
 */
public class Compressor {

    //定高
    private static final int MAX_HEIGHT = 1080;
    private static final int MAX_WIDTH = 1080;
    //压缩80
    private static final int QUALITY = 80;

    public static File compress(File file) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        Bitmap bitmap = null;
        Bitmap resultBitmap = null;
        if (outHeight >= outWidth && outHeight > MAX_HEIGHT) {
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            float scale = MAX_HEIGHT * 1.0f / outHeight;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, outWidth, outHeight, matrix, false);
        } else if (outWidth > outHeight && outWidth > MAX_WIDTH) {
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            float scale = MAX_WIDTH * 1.0f / outWidth;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, outWidth, outHeight, matrix, false);
        }

        if (resultBitmap == null) {
            options.inJustDecodeBounds = false;
            resultBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        }

        String fileName = file.getName();
        String cachePath = SystemConfig.get().getImageCachePath();
        String filePath = cachePath + File.separator + fileName;
        BitmapUtil.saveImageToSD(filePath, resultBitmap, QUALITY);

        recycleBitmap(bitmap);
        recycleBitmap(resultBitmap);

        return new File(filePath);
    }

    private static void recycleBitmap (Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = null;
    }
}
