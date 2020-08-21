package com.example.blooddonation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blooddonation.R;
import com.example.blooddonation.model.User;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorActivity extends AppCompatActivity {

    private EditText etName, etAge, etEmail, etAadhar, etLocation, etCity;
    private TextView question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, question14;
    private RadioButton yes1, yes2, yes3, yes4, yes5, yes6, yes7, yes8, yes9, yes10, yes11, yes12, yes13, yes14;
    private TextView[] question;
    private ChipGroup chipGroupGender, chipGroupBlood;
    private Button btnSave;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private FirebaseUser firebaseUser;
    private String gender="Male", bloodGroup="A+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);


        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        chipGroupGender = findViewById(R.id.chip_group_gender_donor);
        chipGroupBlood=findViewById(R.id.chip_group_blood_donor);

        etName = findViewById(R.id.et_name_donor);
        etAge = findViewById(R.id.et_age_donor);
        etEmail = findViewById(R.id.et_email_donor);
        etAadhar = findViewById(R.id.et_aadhar_donor);
        etLocation = findViewById(R.id.et_location_donor);
        etCity = findViewById(R.id.et_city_donor);

        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);
        question9 = findViewById(R.id.question9);
        question10 = findViewById(R.id.question10);
        question11 = findViewById(R.id.question11);
        question12 = findViewById(R.id.question12);
        question13 = findViewById(R.id.question13);
        question14 = findViewById(R.id.question14);
        question = new TextView[]{null, question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, question14};

        yes1 = findViewById(R.id.yes1);
        yes2 = findViewById(R.id.yes2);
        yes3 = findViewById(R.id.yes3);
        yes4 = findViewById(R.id.yes4);
        yes5 = findViewById(R.id.yes5);
        yes6 = findViewById(R.id.yes6);
        yes7 = findViewById(R.id.yes7);
        yes8 = findViewById(R.id.yes8);
        yes9 = findViewById(R.id.yes9);
        yes10 = findViewById(R.id.yes10);
        yes11 = findViewById(R.id.yes11);
        yes12 = findViewById(R.id.yes12);
        yes13 = findViewById(R.id.yes13);
        yes14 = findViewById(R.id.yes14);


        btnSave = findViewById(R.id.btn_save_donor);

        final String[] answer = {"", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no"};


        chipGroupGender.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                final Chip chip = chipGroup.findViewById(i);
                if (chip != null) {

                    gender=chip.getText().toString();


                }
            }
        });

        chipGroupBlood.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                final Chip chip = chipGroup.findViewById(i);
                if (chip != null) {

                    bloodGroup=chip.getText().toString();

                }
            }
        });

        ref=FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                etName.setText(user.getName());
                etLocation.setText(user.getLocation());
                etEmail.setText(user.getEmail());
                etAge.setText(user.getAge());
                etCity.setText(user.getCity());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (yes1.isChecked()) {
                    answer[1] = "yes";
                }
                if (yes2.isChecked()) {
                    answer[2] = "yes";
                }
                if (yes3.isChecked()) {
                    answer[3] = "yes";
                }
                if (yes4.isChecked()) {
                    answer[4] = "yes";
                }
                if (yes5.isChecked()) {
                    answer[5] = "yes";
                }
                if (yes6.isChecked()) {
                    answer[6] = "yes";
                }
                if (yes7.isChecked()) {
                    answer[7] = "yes";
                }
                if (yes8.isChecked()) {
                    answer[8] = "yes";
                }
                if (yes9.isChecked()) {
                    answer[9] = "yes";
                }
                if (yes10.isChecked()) {
                    answer[10] = "yes";
                }
                if (yes11.isChecked()) {
                    answer[11] = "yes";
                }
                if (yes12.isChecked()) {
                    answer[12] = "yes";
                }
                if (yes13.isChecked()) {
                    answer[13] = "yes";
                }
                if (yes14.isChecked()) {
                    answer[14] = "yes";
                }



                Toast.makeText(DonorActivity.this, "Successfully Registered as Donor", Toast.LENGTH_SHORT).show();


                final String userId = firebaseUser.getUid();
                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        user.setRole("donor");
                        user.setAge(etAge.getText().toString());
                        user.setGender(gender);
                        user.setBlood_group(bloodGroup);
                        user.setCity(etCity.getText().toString());
                        user.setLocation(etLocation.getText().toString());
                        user.setEmail(etEmail.getText().toString());
//                        user.setPost(user.getName() + " from " + user.getCity() + " wants to donate " + user.getBlood_group() + " blood");
//                        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("post");
//                        ref.setValue(user.getPost());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("role");
                ref.setValue("donor");

                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("name");
                ref.setValue(etName.getText().toString());

                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("age");
                ref.setValue(etAge.getText().toString());

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("gender");
                ref.setValue(gender);

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("blood_group");
                ref.setValue(bloodGroup);

                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("email");
                ref.setValue(etEmail.getText().toString());


                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Aadhar Number");
                ref.setValue(etAadhar.getText().toString());

                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("city");
                ref.setValue(etCity.getText().toString());

                ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("location");
                ref.setValue(etLocation.getText().toString());

                for (int i = 1; i <= 14; i++) {
                    ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Donors's Questions").child(question[i].getText().toString());
                    ref.setValue(answer[i]);
                }

                startActivity(new Intent(DonorActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}