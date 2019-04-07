package com.example.localviewer.logic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.example.localviewer.DetailActivity;
import com.example.localviewer.MyImageView;
import com.example.localviewer.network.CachedDownloader;
import com.example.localviewer.network.ImageDownloader;
import com.example.localviewer.entity.ImageAlbum;


public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    ImageAlbum imageAlbum;
    Context context;
    Handler originHandler;

    public ImageAdapter(ImageAlbum imageAlbum, Context context, Handler originHandler) {
        this.imageAlbum = imageAlbum;
        this.context = context;
        this.originHandler = originHandler;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyImageView imageView = new MyImageView(viewGroup.getContext());
        imageView.setAdjustViewBounds(true);
        imageView.setMaxHeight(360);
        imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageViewHolder imageViewHolder = new ImageViewHolder(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(v instanceof MyImageView){
                    final String url= ((MyImageView) v).getUrl();
                    String md5=((MyImageView) v).getMd5();
                    Intent intent=new Intent(context, DetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("image.url",url);
                    bundle.putString("image.md5",md5);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder imageViewHolder, int i) {
        String url=imageAlbum.images[i].url;
        MyImageView myImageView=(MyImageView)imageViewHolder.itemView;
        myImageView.setUrl(url);
        myImageView.setMd5(imageAlbum.images[i].md5);
        final Handler handler = new Handler();
        CachedDownloader.submit(new ImageDownloader(context,myImageView,handler,url,imageAlbum.images[i].md5));
    }

    @Override
    public int getItemCount() {
        if (imageAlbum == null) {
            return 0;
        } else {
            if (imageAlbum.images == null) {
                return 0;
            } else {
                return imageAlbum.images.length;
            }
        }
    }
}
