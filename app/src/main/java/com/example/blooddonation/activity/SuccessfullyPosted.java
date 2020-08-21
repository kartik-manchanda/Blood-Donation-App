package com.example.blooddonation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.blooddonation.R;

public class SuccessfullyPosted extends AppCompatActivity {

    Button btnSuccessfullyPosted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_successfully_posted);

        btnSuccessfullyPosted=findViewById(R.id.btn_successfully_posted);

        btnSuccessfullyPosted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuccessfullyPosted.this,MainActivity.class));
                finish();
            }
        });
    }
}