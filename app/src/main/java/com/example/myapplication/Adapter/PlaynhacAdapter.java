package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Baihat;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    public PlaynhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    Context context;
    ArrayList<Baihat> mangbaihat;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.tvtencasi.setText(baihat.getCasi());
        holder.tvindex.setText(position + 1 + "");
        holder.tvtenbaihat.setText(baihat.getTenbaihat());

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvindex,tvtenbaihat,tvtencasi;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtencasi = itemView.findViewById(R.id.tvPlaynhacTenCaSi);
            tvindex = itemView.findViewById(R.id.tvPlaynhacIndex);
            tvtenbaihat = itemView.findViewById(R.id.tvPlaynhacTenBaihat);
        }
    }
}
