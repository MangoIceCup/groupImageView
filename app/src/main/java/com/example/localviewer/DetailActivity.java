package com.example.localviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.example.localviewer.network.CachedDownloader;
import com.example.localviewer.network.ImageDownloader;

public class DetailActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.detail_view_id);
        Intent intent = getIntent();
        if (intent != null) {
            final String url = intent.getStringExtra("image.url");
            String md5 = intent.getStringExtra("image.md5");
            final Handler handler = new Handler();
            if (url != null) {
                CachedDownloader.submit(new ImageDownloader(this, imageView, handler, url, md5));
            }

        }
    }
}
