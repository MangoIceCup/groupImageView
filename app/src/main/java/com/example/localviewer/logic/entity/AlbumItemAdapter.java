package com.example.localviewer.logic.entity;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.localviewer.R;

public class AlbumItemAdapter extends RecyclerView.Adapter<AlbumItemViewHolder> {


    Album album;

    @NonNull
    @Override
    public AlbumItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ImageView imageView=new ImageView(viewGroup.getContext());
        imageView.setAdjustViewBounds(true);
        imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,300));
        AlbumItemViewHolder holder=new AlbumItemViewHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumItemViewHolder albumItemViewHolder, int i) {
         ImageView imageView=((ImageView)(albumItemViewHolder.itemView));
         imageView.setImageURI( Uri.parse("http://gss0.bdstatic.com/7Ls0a8Sm1A5BphGlnYG/sys/portrait/item/b413e5b08fe5b0ba0201.jpg"));
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
