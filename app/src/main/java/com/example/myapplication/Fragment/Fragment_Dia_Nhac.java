package com.example.myapplication.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.time.temporal.ChronoUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_Nhac extends Fragment {
    //ImageView imgBackGround;
    View view;
    CircleImageView circleImageView;
    public  ObjectAnimator objectAnimator;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.IMGCircle);
        //imgBackGround = view.findViewById(R.id.imgBackGround);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

        return view;
    }
    public void Playnhac(String hinhanh) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        //setAnhnen();
        Picasso.with(getActivity()).load(hinhanh).into(circleImageView);
        //Picasso.with(getActivity()).load(hinhanh).into(imgBackGround);
    }

//    private void setAnhnen(int anh) {
//
//    }
//
//    private void setAnhnen(int anh){
//
//    }
}
