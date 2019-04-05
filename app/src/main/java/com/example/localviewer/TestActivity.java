package com.example.localviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.localviewer.logic.entity.ImageAdapter;
import com.example.localviewer.serverRalated.Image;
import com.example.localviewer.serverRalated.ImageAlbum;

public class TestActivity extends Activity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_item);
        recyclerView=findViewById(R.id.list_item_recyclerview_id);
        ImageAlbum imageAlbum=new ImageAlbum();
        imageAlbum.name="cdscdsdcds";
        imageAlbum.images=new Image[1];
        imageAlbum.images[0]=new Image();
        imageAlbum.images[0].url="http://192.168.1.33/bitch/%E9%80%99%E6%AC%A1%E8%82%8F%E5%BE%97%E5%A4%AA%E7%94%A8%E5%8A%9B%E6%8A%8A%E5%A6%B9%E5%AD%90%E7%9A%84%E5%B0%8F%E5%B1%84%E8%82%8F%E5%87%BA%E8%A1%80%E4%BA%86/d5e137273d7916fd030.jpg";
        ImageAdapter imageAdapter=new ImageAdapter(imageAlbum,this,null);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(imageAdapter);

    }
}
