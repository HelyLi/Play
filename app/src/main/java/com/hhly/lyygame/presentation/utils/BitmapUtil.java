package com.hhly.lyygame.presentation.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Kanon on 2016/5/24.
 */

/**
 * 图片管理
 */
public class BitmapUtil {


    /**
     * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
     */
    public static void AdjustImageDegree(String filePath) {
        try {
            int degree = readImageDegree(filePath);
            Log.d("TEST", "degree:" + degree);
            if (degree != 0) {
                BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上
                opts.inJustDecodeBounds=true;
                BitmapFactory.decodeFile(filePath, opts);
                opts.inSampleSize = 2;
                opts.inJustDecodeBounds=false;
                Bitmap cbitmap = BitmapFactory.decodeFile(filePath, opts);
                /**
                 * 把图片旋转为正的方向
                 */
                Bitmap newbitmap = rotaingImageView(degree, cbitmap);
                cbitmap.recycle();
                saveImageToSD(filePath, newbitmap, 60);
                newbitmap.recycle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param filePath 图片绝对路径
     * @return degree旋转的角度
     */
    private static int readImageDegree(String filePath) {
        int degree = 270;
        if (TextUtils.isEmpty(filePath)) {
            return degree;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /* 旋转图片
      * @param angle
      * @param bitmap
      * @return Bitmap
      */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
//        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 写图片文件到SD卡
     *
     * @throws IOException
     */
    public static void saveImageToSD(String filePath,
                                     Bitmap bitmap, int quality) throws IOException {
        if (bitmap != null) {
            File file = new File(filePath.substring(0,
                    filePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(filePath));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();
        }
    }
}
