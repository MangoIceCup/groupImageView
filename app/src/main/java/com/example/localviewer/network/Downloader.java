package com.example.localviewer.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.ContentValues.TAG;

public class Downloader {
    public static byte[] downloadImage(String url) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) URI.create(url).toURL().openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return readAllBytes(httpURLConnection.getInputStream());
            } else {
                return null;
            }

        } catch (IOException e) {
            Log.e(TAG, "downloadImage: 下载图片失败", e);
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] readAllBytes(InputStream inputStream) {
        byte[] buffer = new byte[64];
        try {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                int length = inputStream.read(buffer);
                while (-1 != length) {
                    byteArrayOutputStream.write(buffer, 0, length);
                    length = inputStream.read(buffer);
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap bitmapFromUrl(String url, Context context) {
        byte[] raw = null;
        raw = readFromCache(url, context);
        if (raw == null) {
            raw = downloadImage(url);
        }
        if (raw != null) {
            cacheFile(url, raw, context);
            return BitmapFactory.decodeByteArray(raw, 0, raw.length);
        } else {
            return null;
        }
    }

    public static void cacheFile(String url, byte[] images, Context context) {
        String m = digest(url);
        File file = getPrivateCacheStorageDir(context).toPath().resolve("./" + m + ".jpg").toFile();
        //Log.d(TAG, "cacheFile: " + file.toPath().toString());
        if (!file.exists()) {
            try {
                Files.write(file.toPath(), images, StandardOpenOption.CREATE);
            } catch (IOException e) {
            }
        }
    }

    public static byte[] readFromCache(String url, Context context) {
        String digest = digest(url);
        File file = getPrivateCacheStorageDir(context).toPath().resolve("./" + digest + ".jpg").toFile();
        if (file.exists()) {
            Log.d(TAG, "readFromCache: Cache 命中");
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                if (file.exists()) file.delete();
                return null;
            }
        } else {
            return null;
        }

    }

    public static boolean isCacheHit(String url, Context context) {
        String d = digest(url);
        File f = getPrivateCacheStorageDir(context).toPath().resolve("./" + d + ".jpg").toFile();
        return f.exists();
    }

    public static String digest(String msg) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return Base64.encodeToString(messageDigest.digest(msg.getBytes(Charset.forName("UTF-8"))), Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "0000000000000000";
    }

    public static File getPrivateCacheStorageDir(Context context) {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), "cache");
        file.mkdirs();
        return file;
    }
}
