package com.example.doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.Model.DoctorModel;
import com.example.doctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{
    private Context mContext;
    private List<DoctorModel> mUsers;
    private boolean isFargment;

    private FirebaseUser firebaseUser;

    public DoctorAdapter(Context mContext, List<DoctorModel> mUsers, boolean isFargment) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.isFargment = isFargment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.doctor_item , parent , false);
        return new DoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final DoctorModel user = mUsers.get(position);


        holder.specialization.setText(user.getSpecialization());
        holder.DoctorName.setText(user.getName());

        Picasso.get().load(user.getImageurl()).placeholder(R.mipmap.ic_launcher).into(holder.DoctorImage);




    }




    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView DoctorImage;
        public TextView DoctorName;
        public TextView specialization;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DoctorImage = itemView.findViewById(R.id.image_profile);
            DoctorName = itemView.findViewById(R.id.username);
            specialization = itemView.findViewById(R.id.fullname);
        }
    }
}
