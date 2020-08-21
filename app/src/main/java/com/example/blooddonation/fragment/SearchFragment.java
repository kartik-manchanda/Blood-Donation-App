package com.example.blooddonation.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.blooddonation.R;
import com.example.blooddonation.adapter.Adapter;
import com.example.blooddonation.adapter.SearchAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchFragment extends Fragment {

    private DatabaseReference ref;
    private RecyclerView recycler;
    private SearchAdapter adapter;
    private List<User> mUsers, donors, receivers, aPositive, bPositive, abPositive, oPositive, aNegative, bNegative, abNegative, oNegative;
//    private View radioButtonView;
   private ChipGroup chipGroupSearch, chipGroupBlood;
   private String roleSelected="All", bloodGroupSelected="All";
   private FirebaseAuth auth;
   private FirebaseUser firebaseUser;
   private String currentUserCity="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        chipGroupSearch = view.findViewById(R.id.chip_group_search);
        chipGroupBlood=view.findViewById(R.id.chip_group_blood_search);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUsers = new ArrayList<>();
        donors = new ArrayList<>();
        receivers = new ArrayList<>();
        aPositive = new ArrayList<>();
        bPositive = new ArrayList<>();
        abPositive = new ArrayList<>();
        oPositive = new ArrayList<>();
        aNegative = new ArrayList<>();
        bNegative = new ArrayList<>();
        abNegative = new ArrayList<>();
        oNegative = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUserCity=snapshot.child("city").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search();



        chipGroupSearch.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                final Chip chip = chipGroup.findViewById(i);
                if (chip != null) {
                    roleSelected=chip.getText().toString();
                    search();
                }
            }
        });

        chipGroupBlood.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {

                final Chip chip = chipGroup.findViewById(i);
                if (chip != null) {
                    bloodGroupSelected=chip.getText().toString();
                    search();

                }
            }
        });

        return view;
    }

    public void search(){

        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    if (!user.getId().equals(firebaseUser.getUid())) {
                        if (!user.getRole().equals("")) {

                            if (roleSelected.equals("All")) {
                                switch (bloodGroupSelected) {
                                    case "All":
                                        mUsers.add(user);
                                        break;
                                    case "A+":
                                        System.out.println("A+");
                                        if (user.getBlood_group().equals("A+"))
                                            mUsers.add(user);
                                        break;
                                    case "B+":
                                        if (user.getBlood_group().equals("B+"))
                                            mUsers.add(user);
                                        break;
                                    case "AB+":
                                        if (user.getBlood_group().equals("AB+"))
                                            mUsers.add(user);
                                        break;
                                    case "O+":
                                        if (user.getBlood_group().equals("O+"))
                                            mUsers.add(user);
                                        break;
                                    case "A-":
                                        if (user.getBlood_group().equals("A-"))
                                            mUsers.add(user);
                                        break;
                                    case "B-":
                                        if (user.getBlood_group().equals("B-"))
                                            mUsers.add(user);
                                        break;
                                    case "AB-":
                                        if (user.getBlood_group().equals("AB-"))
                                            mUsers.add(user);
                                        break;
                                    case "O-":
                                        if (user.getBlood_group().equals("O-"))
                                            mUsers.add(user);
                                        break;

                                }
                            } else if (roleSelected.equals("Donors near me")) {
                                    if (user.getCity().equals(currentUserCity) && (user.getRole().equals("donor"))) {

                                        switch (bloodGroupSelected) {
                                            case "All":
                                                mUsers.add(user);
                                                break;
                                            case "A+":
                                                if (user.getBlood_group().equals("A+"))
                                                    mUsers.add(user);
                                                break;
                                            case "B+":
                                                if (user.getBlood_group().equals("B+"))
                                                    mUsers.add(user);
                                                break;
                                            case "AB+":
                                                if (user.getBlood_group().equals("AB+"))
                                                    mUsers.add(user);
                                                break;
                                            case "O+":
                                                if (user.getBlood_group().equals("O+"))
                                                    mUsers.add(user);
                                                break;
                                            case "A-":
                                                if (user.getBlood_group().equals("A-"))
                                                    mUsers.add(user);
                                                break;
                                            case "B-":
                                                if (user.getBlood_group().equals("B-"))
                                                    mUsers.add(user);
                                                break;
                                            case "AB-":
                                                if (user.getBlood_group().equals("AB-"))
                                                    mUsers.add(user);
                                                break;
                                            case "O-":
                                                if (user.getBlood_group().equals("O-"))
                                                    mUsers.add(user);
                                                break;

                                        }
                                    }
                            } else {
                                    if ((user.getCity().equals(currentUserCity)) && (user.getRole().equals("receiver"))) {

                                        switch (bloodGroupSelected) {
                                            case "All":
                                                mUsers.add(user);
                                                break;
                                            case "A+":
                                                if (user.getBlood_group().equals("A+"))
                                                    mUsers.add(user);
                                                break;
                                            case "B+":
                                                if (user.getBlood_group().equals("B+"))
                                                    mUsers.add(user);
                                                break;
                                            case "AB+":
                                                if (user.getBlood_group().equals("AB+"))
                                                    mUsers.add(user);
                                                break;
                                            case "O+":
                                                if (user.getBlood_group().equals("O+"))
                                                    mUsers.add(user);
                                                break;
                                            case "A-":
                                                if (user.getBlood_group().equals("A-"))
                                                    mUsers.add(user);
                                                break;
                                            case "B-":
                                                if (user.getBlood_group().equals("B-"))
                                                    mUsers.add(user);
                                                break;
                                            case "AB-":
                                                if (user.getBlood_group().equals("AB-"))
                                                    mUsers.add(user);
                                                break;
                                            case "O-":
                                                if (user.getBlood_group().equals("O-"))
                                                    mUsers.add(user);
                                                break;

                                        }
                                    }
                            }
                        }
                    }
                }

                    adapter = new SearchAdapter(getActivity(), mUsers);
                    recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.donors:
//                mUsers = donors;
//                adapter = new SearchAdapter(getActivity(), mUsers);
//                recycler.setAdapter(adapter);
//                break;
//            case R.id.receivers:
//                mUsers = receivers;
//                adapter = new SearchAdapter(getActivity(), mUsers);
//                recycler.setAdapter(adapter);
//                break;
//            case R.id.blood_type:
//                String[] blood_group = {"A+", "B+", "AB+", "O+", "A-", "B-", "Ab-", "O-"};
//                int checkedItem = -1;
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Select Blood Group");
//                builder.setSingleChoiceItems(blood_group, checkedItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        switch (i) {
//                            case 0:
//                                adapter = new SearchAdapter(getActivity(), aPositive);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 1:
//                                adapter = new SearchAdapter(getActivity(), bPositive);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 2:
//                                adapter = new SearchAdapter(getActivity(), abPositive);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 3:
//                                adapter = new SearchAdapter(getActivity(), oPositive);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 4:
//                                adapter = new SearchAdapter(getActivity(), aNegative);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 5:
//                                adapter = new SearchAdapter(getActivity(), bNegative);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 6:
//                                adapter = new SearchAdapter(getActivity(), abNegative);
//                                recycler.setAdapter(adapter);
//                                break;
//                            case 7:
//                                adapter = new SearchAdapter(getActivity(), oNegative);
//                                recycler.setAdapter(adapter);
//                                break;
//                        }
//                    }
//                });
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.create().show();
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}