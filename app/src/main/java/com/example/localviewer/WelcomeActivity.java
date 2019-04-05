package com.example.localviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import com.example.localviewer.localstorage.LocalStorage;
import com.example.localviewer.serverRalated.Zone;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;

import static android.content.ContentValues.TAG;

public class WelcomeActivity extends Activity {
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        progressBar = findViewById(R.id.loading_poregressbar);
        progressBar.setProgress(40);
        handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Zone zone=null;
                try {
                    zone=InfoJson.get(WelcomeActivity.this);
                } catch (Exception e) {
                    Log.d(TAG, "run: 下载图片专辑列表失败",e);
                    //finish();
                }
                Message message= Message.obtain();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(100);
                    }
                });
                try {
                    new LocalStorage(WelcomeActivity.this).saveInfoJsonCacheFile(new Gson().toJson(zone).getBytes(Charset.defaultCharset()));
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "run: 内存不足", e);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    return;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        WelcomeActivity.this.finish();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setProgress(60);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setProgress(80);
    }
}
