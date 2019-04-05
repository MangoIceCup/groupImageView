package com.example.localviewer.visual;

import android.app.Activity;
import android.os.Handler;
import android.widget.ProgressBar;


public class ProgressBarUpdater extends Thread {
    ProgressBar progressBar;
    Handler handler;

    public ProgressBarUpdater( ProgressBar progressBar, Handler handler) {
        this.progressBar = progressBar;
        this.handler = handler;
    }

    @Override
    public void run() {
        final int[] m = new int[1];
        for (int i = 1; i <= 100; i++) {
            try {
                m[0] = i;
                Thread.sleep(18);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(m[0]);
                    }
                });
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
