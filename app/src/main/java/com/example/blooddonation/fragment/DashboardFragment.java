package com.example.blooddonation.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blooddonation.LocationTrack;
import com.example.blooddonation.R;
import com.example.blooddonation.activity.DonorActivity;
import com.example.blooddonation.activity.MainActivity;
import com.example.blooddonation.activity.ReceiverActivity;
import com.example.blooddonation.activity.SuccessfullyPosted;
import com.example.blooddonation.adapter.Adapter;
import com.example.blooddonation.adapter.SearchAdapter;
import com.example.blooddonation.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class DashboardFragment extends Fragment {

    private Button btnDonor, btnReceiver;
    private ImageView btnPost, btnAdd, btnBlood;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private TextView txtNoOfDonors, txtNoOfReceivers, txtNoOfMatches;
    private EditText etPost;
    private int donors=0, receivers=0;
    private String selectedBloodGroup="A+", selectedHospitalName="";

    private RecyclerView recycler;
    private Adapter adapter;
    private List<User> mUsers;

    private LinearLayout llDonorReceiver;
    private TextView txtSuccessfullyDonor;
    private TextView txtSuccessfullyReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);


        btnDonor =view.findViewById(R.id.btn_donor);
        btnReceiver =view.findViewById(R.id.btn_receiver);
        btnPost=view.findViewById(R.id.btn_send_post);
        btnAdd=view.findViewById(R.id.btn_add_hospital);
        btnBlood=view.findViewById(R.id.btn_add_blood_group);
        llDonorReceiver=view.findViewById(R.id.ll_donor_and_receiver);
        txtSuccessfullyDonor=view.findViewById(R.id.txt_successfully_donor);
        txtSuccessfullyReceiver=view.findViewById(R.id.txt_successfully_receiver);

        llDonorReceiver.setVisibility(View.GONE);
        txtSuccessfullyDonor.setVisibility(View.GONE);
        txtSuccessfullyReceiver.setVisibility(View.GONE);
        checkRole();
        count();

        txtNoOfDonors=view.findViewById(R.id.txt_no_of_donors);
        txtNoOfReceivers=view.findViewById(R.id.txt_no_of_receivers);

        recycler=view.findViewById(R.id.recycler_dashboard);
        recycler.setLayoutFrozen(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mUsers=new ArrayList<>();


        etPost=view.findViewById(R.id.et_post);


        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();

        postList();

        btnBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] blood_group = {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"};
                int checkedItem = -1;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Blood Group");
                builder.setSingleChoiceItems(blood_group, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                selectedBloodGroup=blood_group[0];
                                break;
                            case 1:
                                selectedBloodGroup=blood_group[1];
                                break;
                            case 2:
                                selectedBloodGroup=blood_group[2];
                                break;
                            case 3:
                                selectedBloodGroup=blood_group[3];
                                break;
                            case 4:
                                selectedBloodGroup=blood_group[4];
                                break;
                            case 5:
                                selectedBloodGroup=blood_group[5];
                                break;
                            case 6:
                                selectedBloodGroup=blood_group[6];
                                break;
                            case 7:
                                selectedBloodGroup=blood_group[7];
                                break;
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),selectedBloodGroup + " Blood Group Selected",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialogBuilder = new AlertDialog.Builder(getActivity()).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.hospital_name, null);

                final EditText etHospital = (EditText) dialogView.findViewById(R.id.et_hospital);
                Button btnSubmitHospital = (Button) dialogView.findViewById(R.id.btn_submit_hospital);

                btnSubmitHospital.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedHospitalName=etHospital.getText().toString();
                        Toast.makeText(getActivity(),selectedHospitalName + "Selected",Toast.LENGTH_SHORT).show();
                        dialogBuilder.dismiss();
                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }
        });



        btnDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), DonorActivity.class));

            }
        });

        btnReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReceiverActivity.class));

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Posted Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), SuccessfullyPosted.class));



                ref=FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("post");
                ref.child("posted").setValue(etPost.getText().toString());
                etPost.setText("");


                ref=FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("post");
                ref.child("hospital").setValue(selectedHospitalName);



                ref=FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("post");
                ref.child("blood").setValue(selectedBloodGroup);

            }
        });



        return view;
    }

    public void checkRole(){
        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();

        ref=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("role").getValue().toString().equals("donor")){
                    txtSuccessfullyDonor.setVisibility(View.VISIBLE);
                }

                else if(snapshot.child("role").getValue().toString().equals("receiver")){
                    txtSuccessfullyReceiver.setVisibility(View.VISIBLE);
                }

                else {
                    llDonorReceiver.setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void count(){
        donors=receivers=0;
        ref=FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                    if(ds.child("role").getValue().toString().equals("donor")){
                        System.out.println("donor");
                        donors++;
                        txtNoOfDonors.setText("Total Number of donors:" + donors);
                    }
                    else if(ds.child("role").getValue().toString().equals("receiver")){
                        System.out.println("receiver");
                        receivers++;
                        txtNoOfReceivers.setText("Total Number of receivers:" + receivers);
                    }
                    else if(ds.child("role").getValue().toString().equals("")){
                        System.out.println("nothing");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void postList(){

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        ref=FirebaseDatabase.getInstance().getReference("Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    User user=ds.getValue(User.class);

                    assert user != null;
                    assert firebaseUser != null;


                    if(ds.child("post").exists()){
                        mUsers.add(user);
                    }
                }
                adapter=new Adapter(getActivity(),mUsers);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}