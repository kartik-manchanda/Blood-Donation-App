package com.example.blooddonation.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blooddonation.R;
import com.example.blooddonation.activity.LoginActivity;
import com.example.blooddonation.activity.SignUpActivity;

public class ThreeFragment extends Fragment {

    TextView txtNext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.example.blooddonation.fragment
        View view=inflater.inflate(R.layout.fragment_three, container, false);

        txtNext=view.findViewById(R.id.txt_next);

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

            }
        });
        return view;
    }
}