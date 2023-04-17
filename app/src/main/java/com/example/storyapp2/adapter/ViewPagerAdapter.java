package com.example.storyapp2.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.storyapp2.StoryUserFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<StoryUserFragment> fragmentsList = new ArrayList<>();
    private ArrayList<String> fragmentTitleList = new ArrayList<>();
    private Context context;


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);

    }

    public void addFragment(StoryUserFragment fragment, String title){
        fragmentsList.add(fragment);
        fragmentTitleList.add(title);
    }


}



