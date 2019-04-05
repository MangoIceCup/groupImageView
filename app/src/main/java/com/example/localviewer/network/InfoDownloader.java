package com.example.localviewer.network;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import java.io.File;


public class InfoDownloader extends CachedDownloader {
    static final String url = "http://192.168.1.33/bitch/info.json";
    static final String fileIdentity = "info.json";

    Handler handler;
    Intent intent;
    Activity activity;

    public InfoDownloader(Activity context, Handler handler, Intent intent) {
        super(context, url, fileIdentity);
        this.handler=handler;
        this.intent=intent;
        activity=context;
    }

    @Override
    public File call() throws Exception {
        final File file= super.call();
        handler.post(new Runnable() {
            @Override
            public void run() {
                intent.putExtra("file.json.info",file.toPath().normalize().toString());
                    activity.startActivity(intent);
                    activity.finish();
            }
        });
        return null;
    }
}
