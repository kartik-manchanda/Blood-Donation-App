package com.example.blooddonation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.blooddonation.fragment.OneFragment;
import com.example.blooddonation.R;
import com.example.blooddonation.fragment.ThreeFragment;
import com.example.blooddonation.fragment.TwoFragment;
import com.example.blooddonation.adapter.CustomPagerAdapter;

import java.util.List;
import java.util.Vector;

public class WalkOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_over);

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, OneFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, TwoFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, ThreeFragment.class.getName()));


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(super.getSupportFragmentManager(), fragments));

    }
}