package com.example.doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.R;

import java.security.AccessControlContext;
import java.util.List;

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mSpecialization;
    private List<String> mDoctorsCount;

    public SpecializationAdapter(Context mContext, List<String> mTags, List<String> mTagsCount) {
        this.mContext = mContext;
        this.mSpecialization = mTags;
        this.mDoctorsCount = mTagsCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.specialization_item , parent , false);

        return new SpecializationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.specialization.setText( mSpecialization.get(position));
        holder.noOfDoctors.setText(mDoctorsCount.get(position) + " Doctors");

    }

    @Override
    public int getItemCount() {
        return mSpecialization.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView specialization;
        public TextView noOfDoctors;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            specialization = itemView.findViewById(R.id.hash_tag);
            noOfDoctors = itemView.findViewById(R.id.no_of_posts);
        }
    }

    public void filter (List<String> filterSpecialization , List<String> filterSpecializationCount) {
        this.mSpecialization = filterSpecialization;
        this.mDoctorsCount = filterSpecializationCount;

        notifyDataSetChanged();
    }
}
