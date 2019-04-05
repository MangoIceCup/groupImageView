package com.example.localviewer.logic.entity;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.localviewer.R;
import com.example.localviewer.serverRalated.Zone;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    Zone zone;
    Context context;
    Handler originHandler;

    public AlbumAdapter(Zone zone, Context context,Handler originHandler) {
        this.zone = zone;
        this.context = context;
        this.originHandler=originHandler;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_content_item,viewGroup,false);
        AlbumViewHolder albumViewHolder=new AlbumViewHolder(view);
        return  albumViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int i) {
        albumViewHolder.imageAlbum=zone.imageAlbums[i];

        TextView textView= albumViewHolder.itemView.findViewById(R.id.list_item_title_id);
        textView.setText(albumViewHolder.imageAlbum.name);

        RecyclerView list_item_recycler_view=albumViewHolder.itemView.findViewById(R.id.list_item_recyclerview_id);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(albumViewHolder.itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ImageAdapter imageAdapter=new ImageAdapter(albumViewHolder.imageAlbum,albumViewHolder.itemView.getContext(),originHandler);
        list_item_recycler_view.setLayoutManager(linearLayoutManager);
        list_item_recycler_view.setAdapter(imageAdapter);
    }

    @Override
    public int getItemCount() {
        if (zone == null) {
            return 0;
        } else {
            if (zone.imageAlbums == null) {
                return 0;
            }
            return zone.imageAlbums.length;
        }
    }
}
