package com.example.doctor.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.Adapter.DoctorAdapter;
import com.example.doctor.Adapter.ListAdapter;
import com.example.doctor.Model.DoctorModel;
import com.example.doctor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView recyclerViewDoctor;
    private List<DoctorModel> mModel;
    private ListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerViewDoctor = view.findViewById(R.id.recycler_view_list);

        recyclerViewDoctor.setHasFixedSize(true);

        recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(getContext()));


        mModel = new ArrayList<>();
        listAdapter = new ListAdapter(getContext(), mModel, true);
        recyclerViewDoctor.setAdapter(listAdapter);
        readDoctors();
        return view;
    }

    private void readDoctors() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mModel.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DoctorModel user = snapshot.getValue(DoctorModel.class);
                    mModel.add(user);
                }

                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}