package com.example.localviewer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.example.localviewer.network.Downloader;

public class DetailActivity extends Activity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView=findViewById(R.id.detail_view_id);
        Intent intent=getIntent();
        if(intent!=null){
            final String url=intent.getStringExtra("zzj_url");
            final Handler handler=new Handler();
            if(url!=null){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap= Downloader.bitmapFromUrl(url,DetailActivity.this);
                        if(bitmap!=null){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                }).start();
            }
        }
    }
}
