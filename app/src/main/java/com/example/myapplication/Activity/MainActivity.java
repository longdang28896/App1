package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.Adapter.MainViewPagerAdapter;
import com.example.myapplication.Fragment.Fragment_Favorite_List;
import com.example.myapplication.Fragment.Fragment_Play_List;
import com.example.myapplication.Fragment.Fragment_Tim_Kiem;
import com.example.myapplication.Fragment.Fragment_Trang_Chu;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();

    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.trangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.search);
    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}