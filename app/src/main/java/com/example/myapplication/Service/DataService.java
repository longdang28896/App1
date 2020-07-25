package com.example.myapplication.Service;

import com.example.myapplication.Model.Album;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.Model.ChuDevaTheLoai;
import com.example.myapplication.Model.Playlist;
import com.example.myapplication.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloai.php")
    Call<ChuDevaTheLoai>GetCategoryMusic();
    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao (@Field("idquangcao")String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist")String idplaylist);



}
