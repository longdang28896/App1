package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.AllChuDeActivity;
import com.example.myapplication.Activity.DanhSachBaiHatActivity;
import com.example.myapplication.Activity.DanhsachtheloaitheochudeActivity;
import com.example.myapplication.Model.ChuDe;
import com.example.myapplication.Model.ChuDevaTheLoai;
import com.example.myapplication.Model.TheLoai;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScollview);
        txtxemthemchudetheloai = view.findViewById(R.id.txtXemThem);
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllChuDeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<ChuDevaTheLoai> callback = dataService.GetCategoryMusic();
        callback.enqueue(new Callback<ChuDevaTheLoai>() {
            @Override
            public void onResponse(Call<ChuDevaTheLoai> call, Response<ChuDevaTheLoai> response) {
                ChuDevaTheLoai chuDevaTheLoai = response.body();

                 ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDevaTheLoai.getChuDe());

                ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDevaTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,450);
                layout.setMargins(10,20,10,30);

                for (int i =0; i<(chuDeArrayList.size());i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() !=null){
                        Picasso.with(getContext()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent =  new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int j =0; j<(theLoaiArrayList.size());j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() !=null){
                        Picasso.with(getContext()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<ChuDevaTheLoai> call, Throwable t) {

            }


        });
    }
}
