package com.example.blooddonation.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blooddonation.ExapandableListClass.ExpandableListDataPump;
import com.example.blooddonation.R;
import com.example.blooddonation.activity.FaqsActivity;
import com.example.blooddonation.activity.InformationActivity;
import com.example.blooddonation.activity.ResourcesActivity;
import com.example.blooddonation.adapter.CustomExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InfoFragment extends Fragment {

    CardView cvFaqs,cvInfo,cvResources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);

        cvFaqs=view.findViewById(R.id.cv_faqs);
        cvInfo=view.findViewById(R.id.cv_info);
        cvResources=view.findViewById(R.id.cv_resources);


        cvFaqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FaqsActivity.class));
            }
        });


        cvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InformationActivity.class));
            }
        });


        cvResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ResourcesActivity.class));
            }
        });

        return view;
    }
}