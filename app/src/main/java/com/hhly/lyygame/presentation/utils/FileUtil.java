package com.hhly.lyygame.presentation.utils;

import android.text.TextUtils;

import com.hhly.lyygame.domain.config.SystemConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by kailin.wen on 16/6/27.
 */
public class FileUtil {

    /**
     * 从url获取文件名
     * @param urlString
     * @return
     */
    public static String getFileNameFromUrl(String urlString){
        if(TextUtils.isEmpty(urlString)){
            return null;
        }
        return urlString.substring(urlString.lastIndexOf("/")+1,urlString.length());
    }

    /**
     * 音乐文件是否已下载
     * @param urlString
     * @return
     */
    public static boolean isExistMusicFile(String urlString){
        String musicDir = SystemConfig.get().getPath()+"/music";
        String fileName = getFileNameFromUrl(urlString);
        File file = new File(musicDir,fileName);
        return file.exists();
    }


    /** Create a File for saving an image or video */
    public static File getOutputMediaFile()
    {
        File mediaStorageDir = null;
        try
        {
            // This location works best if you want the created images to be
            // shared
            // between applications and persist after your app has been
            // uninstalled.
            mediaStorageDir = new File(SystemConfig.get().getPath(), "picture");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
        return mediaFile;
    }


    /**
     * Makes a directory, including any necessary but nonexistent parent
     * directories. If a file already exists with specified name but it is
     * not a directory then an IOException is thrown.
     * If the directory cannot be created (or does not already exist)
     * then an IOException is thrown.
     *
     * @param directory directory to create, must not be {@code null}
     * @throws NullPointerException if the directory is {@code null}
     * @throws IOException  if the directory cannot be created or the file already exists but is not a directory
     */
    public static void forceMkdir(File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                String message =
                        "File "
                                + directory
                                + " exists and is "
                                + "not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        } else {
            if (!directory.mkdirs()) {
                // Double-check that some other thread or process hasn't made
                // the directory in the background
                if (!directory.isDirectory()) {
                    String message =
                            "Unable to create directory " + directory;
                    throw new IOException(message);
                }
            }
        }
    }

    public static boolean mkdirs(File directory) {
        try {
            forceMkdir(directory);
            return true;
        } catch (IOException e){
        }
        return false;
    }


    public static byte[] readFile(File file) {
        byte[] datas = null;
        FileInputStream fis = null;
        try {
            if (file.exists()) {
                fis = new FileInputStream(file);
                datas = readFileStream(fis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
        }
        return datas;
    }

    private static byte[] readFileStream(FileInputStream fis) {
        byte[] data = null;
        try {
            data = new byte[fis.available()];
            fis.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void closeStream(FileInputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            is = null;
        }

    }

    /**
     * 获取文件扩展名
     * @param file
     * @return
     */
    public static String getExtensionName (File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if (index > 0) {
            return name.substring(index + 1, name.length());
        }
        return "";
    }


    public static boolean isImageFormat (File file) {
        String extName = getExtensionName(file);
        if (!TextUtils.isEmpty(extName)) {
            if (Pattern.compile("^(jpg|jpeg|png)$").matcher(extName).matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 写入文件
     *
     * @param in
     * @param file
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file != null && file.exists())
            file.delete();

        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 128];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

}
