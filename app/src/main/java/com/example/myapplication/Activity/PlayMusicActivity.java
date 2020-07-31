package com.example.myapplication.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ViewPagerPlaylistnhac;
import com.example.myapplication.Fragment.Fragment_Dia_Nhac;
import com.example.myapplication.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {


     Toolbar toolbarPlayMusic;
     TextView tvTimeSong,tvTotalTimeSong;
     SeekBar sktime;
     ImageButton imgplay,imgrepeat,imgnext,imgprev,imgrandom;
     ViewPager viewPagerPlayMusic;
     public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
     public static ViewPagerPlaylistnhac adapternhac;
     Fragment_Dia_Nhac fragment_dia_nhac;
     Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;
     MediaPlayer mediaPlayer;
     //NotificationManager notificationManager;
     int position =0;
     boolean repeat = false;
     boolean checkrandom = false;
     boolean next = false;
     //List<Track> tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy
                = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GetDataFromIntent();
        init();
        eventClick();
//        popluateTracks();
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
//            creatChannel();
//        }
//        imgplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreatNotification.creatNotification(PlayMusicActivity.this,mangbaihat.get(position),R.drawable.pause,
//                        1,mangbaihat.size());
//            }
//        });

    }

//    private void creatChannel() {
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//            NotificationChannel channel =
//                    new NotificationChannel(CreatNotification.CHANNEL_ID,"KOD DEV", NotificationManager.IMPORTANCE_LOW);
//
//            notificationManager = getSystemService(NotificationManager.class);
//            if (notificationManager !=null){
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
//    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) !=null){
                    if (mangbaihat.size()> 0){
                        fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play);
                    if (fragment_dia_nhac.objectAnimator !=null){
                        fragment_dia_nhac.objectAnimator.pause();
                    }
                }else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.pause);
                    if (fragment_dia_nhac.objectAnimator !=null){
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                }

            }

        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkrandom == true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.repeat);
                        imgrandom.setImageResource(R.drawable.disrandom);
                    }
                    imgrepeat.setImageResource(R.drawable.repeat);
                    repeat = true;
                }else {
                    imgrepeat.setImageResource(R.drawable.unrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.random);
                        imgrepeat.setImageResource(R.drawable.repeat);
                    }
                    imgrandom.setImageResource(R.drawable.random);
                    checkrandom = true;
                }else {
                    imgrandom.setImageResource(R.drawable.disrandom);
                    checkrandom = false;
                }

            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size()>0){
                    if (mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;

                    }
                    if (position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size()-1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgprev.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },3000);
            }
        });
        imgprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size()>0){
                    if (mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;

                    }
                    if (position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.pause);
                        position--;
                        if (position < 0){
                            position = mangbaihat.size() -1;
                        }
                        if (repeat == true){
                            position += 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgprev.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },3000);

            }
        });

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent !=null){
            if (intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                Toast.makeText(this,baihat.getTenbaihat(),Toast.LENGTH_SHORT).show();
                mangbaihat.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;

            }
        }

    }

    private void init() {
        toolbarPlayMusic = findViewById(R.id.toolbarPlayMusic);
        tvTimeSong = findViewById(R.id.tvTimeSong);
        tvTotalTimeSong = findViewById(R.id.tvtotalTimeSong);
        sktime = findViewById(R.id.seekbarSong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext= findViewById(R.id.imagebuttonnext);
        imgprev = findViewById(R.id.imagebuttonprev);
        imgrandom = findViewById(R.id.imagebuttonrandom);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerPlayMusic = findViewById(R.id.viewpagerPlayMusic);
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPagerPlayMusic.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if (mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.pause);
        }

    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer !=null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }

                imgprev.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
                next =false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
//    private void popluateTracks(){
//      String ten= mangbaihat.get(position).getTenbaihat();
//      String photo=mangbaihat.get(position).getHinhbaihat();
//      String casi = mangbaihat.get(position).getCasi();
//    }

}