package com.example.localviewer.network;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

import static android.content.ContentValues.TAG;

public class ImageDownloader extends CachedDownloader {
    Handler handler;
    ImageView imageView;
    public ImageDownloader(Context context, ImageView imageView, Handler handler, String url, String fileIdentity) {
        super(context, url, fileIdentity);
        this.handler=handler;
        this.imageView=imageView;
    }

    @Override
    public File call() throws Exception {
        final File file= super.call();
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageURI(Uri.fromFile(file));
            }
        });
        return null;
    }
}
