package com.example.localviewer.logic.entity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.localviewer.DetailActivity;
import com.example.localviewer.MyImageView;
import com.example.localviewer.R;
import com.example.localviewer.network.Downloader;
import com.example.localviewer.serverRalated.ImageAlbum;


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
        //imageView.setImageResource(R.drawable.prefix_9062820c0378edcd000);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(v instanceof MyImageView){
                    final String url= ((MyImageView) v).getUrl();
                    Intent intent=new Intent(context, DetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("zzj_url",url);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder imageViewHolder, int i) {
        imageViewHolder.url = imageAlbum.images[i].url;
        imageViewHolder.imageView.setUrl(imageViewHolder.url);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = Downloader.bitmapFromUrl(imageViewHolder.url, context);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageViewHolder.imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();

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
