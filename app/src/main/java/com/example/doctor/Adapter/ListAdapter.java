package com.example.doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.Model.DoctorModel;
import com.example.doctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context mContext;
    private List<DoctorModel> mUsers;

    private boolean isFargment;

    private FirebaseUser firebaseUser;
    public ListAdapter(Context mContext, List<DoctorModel> mUsers,boolean isFargment) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.isFargment = isFargment;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list , parent , false);

        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {




        final DoctorModel user = mUsers.get(position);



        holder.name.setText(user.getName());
        holder.city.setText(user.getCity());
        holder.specialization.setText(user.getSpecialization());
        holder.phone.setText(user.getPhone());
        Picasso.get().load(user.getImageurl()).placeholder(R.mipmap.ic_launcher).into(holder.doctorImage);



    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView phone;
        public TextView city;
        public TextView name;
        public TextView specialization;
        public  ImageView doctorImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorImage = itemView.findViewById(R.id.doctorImage);
            city = itemView.findViewById(R.id.city);
            name = itemView.findViewById(R.id.doctorName);
            phone = itemView.findViewById(R.id.phone);
            specialization = itemView.findViewById(R.id.specialization);
        }
    }


}
