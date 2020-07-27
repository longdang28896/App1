package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapter.DanhsachbaihatAdapter;
import com.example.myapplication.Model.Album;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.Model.Playlist;
import com.example.myapplication.Model.Quangcao;
import com.example.myapplication.Model.TheLoai;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhSachBaiHat;
    Quangcao quangcao;
    ArrayList<Baihat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        DataIntent();
        anhxa();
        init();
        if (quangcao !=null && !quangcao.getTenBaiHat().equals("")){
            setVaLueView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }
        if (playlist !=null && !playlist.getTen().equals("")){
            setVaLueView(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setVaLueView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album !=null && !album.getTenAlbum().equals("")){
            setVaLueView(album.getTenAlbum(),album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }

    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }


    private void GetDataTheLoai(String idtheloai){
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });


    }

    private void GetDataPlaylist(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void setVaLueView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhSachBaiHat);

    }
    private void GetDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                 mangbaihat = (ArrayList<Baihat>)response.body();
                 danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                 recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                 recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);


            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerViewDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingbutton);
        imgDanhSachBaiHat = findViewById(R.id.imgDanhSachBaiHat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent !=null){
            if (intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this,quangcao.getTenBaiHat(),Toast.LENGTH_SHORT).show();

            }
            if (intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")){
                 theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }
}