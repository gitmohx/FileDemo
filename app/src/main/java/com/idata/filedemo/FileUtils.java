package com.idata.filedemo;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class FileUtils {
    private static String TAG = "FileUtils";
    /**
     * 将字符串写入到文本文件中
     * @param strcontent
     * @param filePath
     * @param fileName
     */
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        makeFilePath(filePath, fileName);
        String strFilePath = filePath + fileName;
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d(TAG, "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e(TAG, "Error on write File:" + e);
        }
    }

    /**
     * 创建文件
     * @param filePath
     * @param fileName
     * @return
     */
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 创建文件目录
     *
     * @param filePath
     */
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    /**
     * 读取文件
     *
     * @param strFilePath
     * @return
     */
    public static String ReadTxtFile(String strFilePath) {
        String path = strFilePath;
        String content = "";
        File file = new File(path);
        if (file.isDirectory()) {
            Log.d("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    while ((line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }
                    instream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                Log.d(TAG, "The File doesn't not exist.");
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        return content;
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        Log.i(TAG, "delete2:" + path);
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        Log.i(TAG, "delete3:" + path);
        File file = new File(path);
        Log.i(TAG, "file.exists():" + file.exists());
        if (file.exists()) {
            try {
                Log.i(TAG, "file.isFile():" + file.isFile());
                if (file.isFile()) {
                    Log.i(TAG, "delete file.isFile():true");
                    return deleteFile(path);
                } else {
                    return deleteDirectory(path);
                }
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
                return false;
            }
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        Log.i(TAG, "delete deleteFile:path:" + path);
        if (file.exists() && file.isFile()) {
            boolean b = file.delete();
            Log.i(TAG, "deleteFile:" + b);
            return b;
        } else {
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String dir) {
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        return dirFile.delete();
    }
}
