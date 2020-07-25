package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Album;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
        Context context;
        ArrayList<Album> mangalbum;
        ImageView imgHinhAlbum;
        TextView tvTenAlbum, tvTenCaSiAlbum;

        public AlbumAdapter(Context context, ArrayList<Album> mangalbum) {
            this.context = context;
            this.mangalbum = mangalbum;
        }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imgViewAlbum);
            tvTenAlbum = itemView.findViewById(R.id.tvTenAlbum);
            tvTenCaSiAlbum = itemView.findViewById(R.id.tvTenCaSiAlbum);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Album album = mangalbum.get(position);
            tvTenCaSiAlbum.setText(album.getTencasiAlbum());
            tvTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhanhAlbum()).into(imgHinhAlbum);


    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    }

