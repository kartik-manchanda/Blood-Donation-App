package com.example.blooddonation.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class CustomPagerAdapter extends FragmentPagerAdapter {


     private List<Fragment> fragments;

     public CustomPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
         super(fm);
         this.fragments = fragments;
     }

     @Override
     public Fragment getItem(int position) {
         return this.fragments.get(position);
     }


     @Override
     public int getCount() {
         return this.fragments.size();
     }


}
