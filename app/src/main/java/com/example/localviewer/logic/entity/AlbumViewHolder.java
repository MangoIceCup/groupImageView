package com.example.localviewer.logic.entity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.localviewer.serverRalated.ImageAlbum;

public class AlbumViewHolder extends RecyclerView.ViewHolder {
    ImageAlbum imageAlbum;

    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
    }

}
