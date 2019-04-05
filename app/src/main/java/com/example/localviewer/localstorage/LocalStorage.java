package com.example.localviewer.localstorage;

import android.content.Context;
import android.util.Log;

import java.io.*;

import static android.content.ContentValues.TAG;

public class LocalStorage {
    Context context;

    public LocalStorage(Context context) {
        this.context = context;
    }

    public  void saveInfoJsonCacheFile(byte[] bytes) throws IOException {
        File cache = context.getCacheDir();
        cache = cache.toPath().resolve("./info.json").toFile();
        if (cache.exists()) {
            return;
        }

        cache.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(cache)) {
            fileOutputStream.write(bytes);
        }


    }

    public  byte[] readCacheFile(String file) throws FileNotFoundException {
        File cache = context.getCacheDir();
        cache = cache.toPath().resolve("./" + file).toFile();
        if (cache.exists()) {
            FileInputStream fileInputStream = new FileInputStream(cache);
            return readAllBytes(fileInputStream);
        }else {
            return null;
        }
    }

    private  byte[] readAllBytes(InputStream inputStream) {
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

}
