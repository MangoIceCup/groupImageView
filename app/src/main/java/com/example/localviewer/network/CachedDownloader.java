package com.example.localviewer.network;

import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * A Generic Downloader , first find in cache by identity , return  bytes
 */
public class CachedDownloader implements Callable<File> {
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
    @SuppressWarnings("uncheked")
    public static void submit(Callable callable){
        executorService.submit(callable);
    }

    private Context context;
    private String url;
    private String fileIdentity;
    private File cacheDir;
    private File cachedFile;

    public CachedDownloader(Context context, String url, String fileIdentity) {
        this.context = context;
        this.url = url;
        this.fileIdentity = fileIdentity;
        cachedFile =getCachedFile();
    }

    @Override
    public File call() throws Exception {
        if (cachedFile.exists()) {
            return cachedFile;
        } else {
            URLConnection urlConnection = null;
            try {
                urlConnection = URI.create(url).toURL().openConnection();
                try (InputStream inputStream = urlConnection.getInputStream(); FileOutputStream fileOutputStream = new FileOutputStream(cachedFile)) {
                    byte[] buffer = new byte[2 * 1024 * 1024];
                    int length = inputStream.read(buffer);
                    while (length != -1) {
                        fileOutputStream.write(buffer, 0, length);
                        length = inputStream.read(buffer);
                    }
                }
                return cachedFile;

            } finally {
                if (urlConnection != null) {
                    if (urlConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection) urlConnection).disconnect();
                    }
                }
            }

        }
    }

    public File getCachedFile(){
       return getCacheDir().toPath().resolve(fileIdentity).toFile();
    }
    public File getCacheDir() {
        if (cacheDir != null) {
            return cacheDir;
        } else {
            cacheDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            cacheDir.mkdirs();
            return cacheDir;
        }
    }

}
