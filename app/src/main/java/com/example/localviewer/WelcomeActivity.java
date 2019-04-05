package com.example.localviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import com.example.localviewer.network.CachedDownloader;
import com.example.localviewer.network.InfoDownloader;
import com.example.localviewer.visual.ProgressBarUpdater;

public class WelcomeActivity extends Activity {
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        progressBar = findViewById(R.id.loading_poregressbar);
        progressBar.setProgress(0);
        handler = new Handler();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Zone zone=null;
//                try {
//                    zone=InfoJson.get(WelcomeActivity.this);
//                } catch (Exception e) {
//                    Log.d(TAG, "run: 下载图片专辑列表失败",e);
//                    //finish();
//                }
//                Message message= Message.obtain();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressBar.setProgress(100);
//                    }
//                });
//                try {
//                    new LocalStorage(WelcomeActivity.this).saveInfoJsonCacheFile(new Gson().toJson(zone).getBytes(Charset.defaultCharset()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e(TAG, "run: 内存不足", e);
//                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    return;
//                }
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        WelcomeActivity.this.finish();
//                    }
//                });
//            }
//        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ProgressBarUpdater(progressBar,handler).start();
        CachedDownloader.submit(new InfoDownloader(this,handler,new Intent(this,MainActivity.class)));
    }
}
