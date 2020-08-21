package com.example.blooddonation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.blooddonation.R;

public class ResourcesActivity extends AppCompatActivity {

    TextView link1,link2,link3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_resources);



        link1 = findViewById(R.id.link1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        link2 = findViewById(R.id.link2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        link3 = findViewById(R.id.link3);
        link3.setMovementMethod(LinkMovementMethod.getInstance());
    }
}