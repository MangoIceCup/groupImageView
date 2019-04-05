package com.example.localviewer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.localviewer.localstorage.LocalStorage;
import com.example.localviewer.logic.entity.AlbumAdapter;
import com.example.localviewer.serverRalated.Zone;
import com.google.gson.Gson;

import java.io.FileNotFoundException;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    RecyclerView rootView;
    Handler originHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView=findViewById(R.id.main_activity_recycler_view_id);
        Zone zone=null;
        try {
            zone=new Gson().fromJson(new String(new LocalStorage(MainActivity.this).readCacheFile("info.json")),Zone.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: ", e);
            finish();
        }
        originHandler =new Handler();
        AlbumAdapter albumAdapter=new AlbumAdapter(zone,this,originHandler);
        rootView.setAdapter(albumAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rootView.setLayoutManager(linearLayoutManager);

    }
}
