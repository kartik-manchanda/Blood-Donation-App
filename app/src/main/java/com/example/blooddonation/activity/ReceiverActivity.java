package com.example.blooddonation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.blooddonation.fragment.DashboardFragment;
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

public class ReceiverActivity extends AppCompatActivity {

    private EditText etName, etAge, etLocation, etMobile, etCity;
    private TextView question1, question2;
    private TextView[] question;
    private RadioButton yes1, yes2;
    private ChipGroup chipGroupGender, chipGroupBlood;
    private Button btnSave;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private FirebaseUser firebaseUser;
    private String gender="Male", bloodGroup="A+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);


        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();



        chipGroupGender = findViewById(R.id.chip_group_gender);
        chipGroupBlood=findViewById(R.id.chip_group_blood);

        etName = findViewById(R.id.et_name_receiver);
        etAge = findViewById(R.id.et_age_receiver);
        etMobile = findViewById(R.id.et_mobile_receiver);
        etLocation = findViewById(R.id.et_location_receiver);
        etCity = findViewById(R.id.et_city_receiver);

        question1=findViewById(R.id.question1_receiver);
        question2=findViewById(R.id.question2_receiver);
        question=new TextView[]{null, question1, question2};

        yes1 = findViewById(R.id.yes1_receiver);
        yes2 = findViewById(R.id.yes2_receiver);


        btnSave = findViewById(R.id.btn_save_receiver);

        final String[] answer = {"", "no", "no"};


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
                etAge.setText(user.getAge());
                etCity.setText(user.getCity());
                etMobile.setText(user.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ReceiverActivity.this, "Successfully Registered as Receiver", Toast.LENGTH_SHORT).show();

                firebaseUser=auth.getCurrentUser();
                final String userId=firebaseUser.getUid();
                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user=snapshot.getValue(User.class);
                        user.setRole("receiver");
                        user.setAge(etAge.getText().toString());
                        user.setCity(etCity.getText().toString());
                        user.setLocation(etLocation.getText().toString());
//                        user.setPost(user.getName() + " from " + user.getCity() + " needs " + user.getBlood_group() + " blood");
//                        ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("post");
//                        ref.setValue(user.getPost());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if (yes1.isChecked()) {
                    answer[1] = "yes";
                }
                if (yes2.isChecked()) {
                    answer[2] = "yes";
                }


                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("role");
                ref.setValue("receiver");

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("name");
                ref.setValue(etName.getText().toString());

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("age");
                ref.setValue(etAge.getText().toString());

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("gender");
                ref.setValue(gender);

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("blood_group");
                ref.setValue(bloodGroup);

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("phone");
                ref.setValue(etMobile.getText().toString());

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("city");
                ref.setValue(etCity.getText().toString());

                ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("location");
                ref.setValue(etLocation.getText().toString());

                for(int i=1;i<=2;i++){
                    ref= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Receivers's Questions").child(question[i].getText().toString());
                    ref.setValue(answer[i]);
                }

                startActivity(new Intent(ReceiverActivity.this,MainActivity.class));
                finish();
            }

        });

    }
}