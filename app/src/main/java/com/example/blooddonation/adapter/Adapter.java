package com.example.blooddonation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonation.R;
import com.example.blooddonation.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private Context mContext;
        private List<User> mUsers;

        public Adapter(Context mContext, List<User> mUsers){
            this.mContext=mContext;
            this.mUsers=mUsers;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(mContext).inflate(R.layout.single_recycler_post,parent,false);
            return new Adapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            final User user=mUsers.get(position);
            holder.txtName.setText(user.getName());
            holder.txtAge.setText(user.getAge());

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getId());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    holder.txtHospital.setText(snapshot.child("post").child("hospital").getValue().toString());
                    holder.txtBloodGroup.setText(snapshot.child("post").child("blood").getValue().toString());
                    holder.txtPost.setText(snapshot.child("post").child("posted").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView txtName, txtAge, txtPost, txtHospital, txtBloodGroup;

            public ViewHolder(View itemView){
                super(itemView);

                txtName=itemView.findViewById(R.id.txt_name_post);
                txtAge=itemView.findViewById(R.id.txt_age_post);
                txtPost=itemView.findViewById(R.id.txt_post);
                txtHospital=itemView.findViewById(R.id.txt_hospital_name_post);
                txtBloodGroup=itemView.findViewById(R.id.txt_blood_group_post);
            }
        }


    }


