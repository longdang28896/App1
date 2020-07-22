package com.example.myapplication.Service;

import com.example.myapplication.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();
}
