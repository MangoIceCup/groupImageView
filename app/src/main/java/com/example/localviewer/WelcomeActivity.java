package com.example.localviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import com.example.localviewer.network.CachedDownloader;
import com.example.localviewer.network.InfoDownloader;
import com.example.localviewer.visual.ProgressBarUpdater;

import static android.content.ContentValues.TAG;

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


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+ getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        new ProgressBarUpdater(progressBar,handler).start();
        CachedDownloader.submit(new InfoDownloader(this,handler,new Intent(this,MainActivity.class)));
    }
}
