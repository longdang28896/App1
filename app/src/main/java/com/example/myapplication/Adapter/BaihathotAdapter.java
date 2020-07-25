package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Baihat;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    Context context;
    ArrayList<Baihat> baihatArrayList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.tvTenCaSiBaiHatHot.setText(baihat.getCasi());
        holder.tvTenBaiHatHot.setText(baihat.getTenbaihat());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imgBaiHatHot);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenCaSiBaiHatHot, tvTenBaiHatHot;
        ImageView imgBaiHatHot,imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenBaiHatHot = itemView.findViewById(R.id.tvTenBaiHatHot);
            tvTenCaSiBaiHatHot = itemView.findViewById(R.id.tvTenCasiBaihathot);
            imgBaiHatHot = itemView.findViewById(R.id.imgBaiHatHot);
            imgLuotThich = itemView.findViewById(R.id.imgLuotThich);
        }
    }
}
