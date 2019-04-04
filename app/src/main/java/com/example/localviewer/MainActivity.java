package com.example.localviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import com.example.localviewer.logic.entity.Album;
import com.example.localviewer.logic.entity.AlbumItemAdapter;

public class MainActivity extends Activity {
    RecyclerView rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_item);
        rootView=findViewById(R.id.list_item_recyclerview_id);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        rootView.setLayoutManager(linearLayoutManager);
        AlbumItemAdapter albumItemAdapter=new AlbumItemAdapter();
        rootView.setAdapter(albumItemAdapter);
    }
}
