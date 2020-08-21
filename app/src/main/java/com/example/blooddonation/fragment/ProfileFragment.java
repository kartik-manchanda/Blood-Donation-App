package com.example.blooddonation.fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blooddonation.R;
import com.example.blooddonation.activity.EditActivity;
import com.example.blooddonation.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    private TextView txtName, txtAge, txtPhone, txtEmail, txtCity;
    private ImageView imgEdit;
    private FirebaseUser firebaseUser;
    private DatabaseReference ref;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        txtName = view.findViewById(R.id.txt_name_profile);
        txtAge = view.findViewById(R.id.txt_age_profile);
        txtPhone = view.findViewById(R.id.txt_phone_profile);
        txtEmail = view.findViewById(R.id.txt_email_profile);
        txtCity = view.findViewById(R.id.txt_city_profile);
        imgEdit = view.findViewById(R.id.img_edit);


        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        userId=firebaseUser.getUid();

        ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                txtName.setText("Name: " + user.getName());
                txtAge.setText("Age: " + user.getAge());
                txtPhone.setText("Mobile Number: " + user.getPhone());
                txtEmail.setText("Email: " + user.getEmail());
                txtCity.setText("City: " + user.getCity());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}