package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.Activity.PlayMusicActivity;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.R;
import java.util.ArrayList;

public class ViewPagerPlaylistnhac extends FragmentPagerAdapter {
public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerPlaylistnhac(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void AddFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
