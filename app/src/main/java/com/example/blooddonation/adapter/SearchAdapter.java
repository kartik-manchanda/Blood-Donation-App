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

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public SearchAdapter(Context mContext, List<User> mUsers){
        this.mContext=mContext;
        this.mUsers=mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.single_recycler_search,parent,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user=mUsers.get(position);
        holder.txtName.setText("Name:" + user.getName());
        holder.txtAge.setText("Age:" + user.getAge());
        holder.txtGender.setText("Gender: " + user.getGender());
        holder.txtEmail.setText("Email: " + user.getEmail());
        holder.txtCity.setText("City: " + user.getCity());
        holder.txtLocation.setText("Location:" + user.getLocation());
        holder.txtBloodGroup.setText(user.getBlood_group());
        holder.txtRole.setText(user.getRole().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName, txtAge, txtGender, txtEmail, txtCity, txtLocation, txtRole, txtBloodGroup;

        public ViewHolder(View itemView){
            super(itemView);

            txtName=itemView.findViewById(R.id.txt_name_search);
            txtAge=itemView.findViewById(R.id.txt_age_search);
            txtGender=itemView.findViewById(R.id.txt_gender_search);
            txtEmail=itemView.findViewById(R.id.txt_email_search);
            txtCity=itemView.findViewById(R.id.txt_city_search);
            txtLocation=itemView.findViewById(R.id.txt_location_search);
            txtBloodGroup=itemView.findViewById(R.id.txt_blood_group_search);
            txtRole=itemView.findViewById(R.id.txt_role_search);
        }
    }
}


