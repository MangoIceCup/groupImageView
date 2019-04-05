package com.example.localviewer.logic.entity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.example.localviewer.MyImageView;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public MyImageView imageView;
    public String url;

    public ImageViewHolder(@NonNull MyImageView imageView) {
        super(imageView);
        this.imageView=imageView;
    }
}
