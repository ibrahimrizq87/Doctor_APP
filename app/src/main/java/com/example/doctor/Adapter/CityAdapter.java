package com.example.doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mCity;
    private List<String> mDoctorsCount;

    public CityAdapter(Context mContext, List<String> mTags, List<String> mTagsCount) {
        this.mContext = mContext;
        this.mCity = mTags;
        this.mDoctorsCount = mTagsCount;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.specialization_item , parent , false);

        return new CityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {

        holder.city.setText( mCity.get(position));
        holder.noOfDoctors.setText(mDoctorsCount.get(position) + " Doctors");

    }

    @Override
    public int getItemCount() {
        return mCity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView city;
        public TextView noOfDoctors;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.hash_tag);
            noOfDoctors = itemView.findViewById(R.id.no_of_posts);
        }
    }

    public void filter (List<String> filterSpecialization , List<String> filterSpecializationCount) {
        this.mCity = filterSpecialization;
        this.mDoctorsCount = filterSpecializationCount;

        notifyDataSetChanged();
    }
}
