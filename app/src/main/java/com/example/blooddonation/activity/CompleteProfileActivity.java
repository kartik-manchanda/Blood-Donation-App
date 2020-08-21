package com.example.blooddonation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blooddonation.LocationTrack;
import com.example.blooddonation.R;
import com.example.blooddonation.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class CompleteProfileActivity extends AppCompatActivity {

    private EditText etName, etMobile, etEmail, etCity, etAge, etLocation;
    private Button btnContinue;
    private FirebaseUser firebaseUser;
    private DatabaseReference ref;
    private String userId;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        etName = findViewById(R.id.et_complete_name);
        etMobile = findViewById(R.id.et_complete_phone_number);
        etEmail = findViewById(R.id.et_complete_email);
        etCity = findViewById(R.id.et_complete_city);
        etAge = findViewById(R.id.et_complete_age);
        etLocation = findViewById(R.id.et_complete_location);
        btnContinue = findViewById(R.id.btn_continue);

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), MODE_PRIVATE);

        etLocation.setText(sharedPreferences.getString("location", ""));
        etCity.setText(sharedPreferences.getString("city", ""));


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = firebaseUser.getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                etName.setText(user.getName());
                etEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        user.setName(etName.getText().toString());
                        user.setPhone(etMobile.getText().toString());
                        user.setAge(etAge.getText().toString());
                        user.setEmail(etEmail.getText().toString());
                        user.setCity(etCity.getText().toString());
                        user.setLocation(etLocation.getText().toString());

                        ref.child("name").setValue(user.getName());
                        ref.child("phone").setValue(user.getPhone());
                        ref.child("age").setValue(user.getAge());
                        ref.child("email").setValue(user.getEmail());
                        ref.child("city").setValue(user.getCity());
                        ref.child("location").setValue(user.getLocation());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                startActivity(new Intent(CompleteProfileActivity.this, MainActivity.class));
                finish();
            }
        });

    }


}