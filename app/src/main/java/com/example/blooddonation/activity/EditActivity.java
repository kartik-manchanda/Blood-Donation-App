package com.example.blooddonation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blooddonation.R;
import com.example.blooddonation.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends AppCompatActivity {

    private EditText etName, etAge, etEmail, etPhone;
    private FirebaseUser firebaseUser;
    private DatabaseReference ref;
    private Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        etName=findViewById(R.id.et_edit_name);
        etAge=findViewById(R.id.et_edit_age);
        etPhone=findViewById(R.id.et_edit_phone_number);
        etEmail=findViewById(R.id.et_edit_email);
        btnSave=findViewById(R.id.btn_edit_save);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        final String userId=firebaseUser.getUid();
        ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                etName.setText(user.getName());
                etAge.setText(user.getAge());
                etPhone.setText(user.getPhone());
                etEmail.setText(user.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EditActivity.this,"Saved Successfully",Toast.LENGTH_SHORT).show();

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user=snapshot.getValue(User.class);
                        user.setName(etName.getText().toString());
                        user.setAge(etAge.getText().toString());
                        user.setPhone(etPhone.getText().toString());
                        user.setEmail(etEmail.getText().toString());

                        ref.child("name").setValue(user.getName());
                        ref.child("age").setValue(user.getAge());
                        ref.child("phone").setValue(user.getPhone());
                        ref.child("email").setValue(user.getEmail());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                startActivity(new Intent(EditActivity.this,MainActivity.class));


            }
        });
    }
}