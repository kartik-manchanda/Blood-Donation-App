package com.example.blooddonation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blooddonation.R;


public class OneFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.example.blooddonation.fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
}