package com.example.doctor.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.Adapter.CityAdapter;
import com.example.doctor.Adapter.DoctorAdapter;
import com.example.doctor.Adapter.SpecializationAdapter;
import com.example.doctor.Model.DoctorModel;
import com.example.doctor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerViewDoctor;
    private List<DoctorModel> mModel;
    private DoctorAdapter doctorAdapter;

    private RecyclerView recyclerViewSpecialization;
    private List<String> mSpecialization;
    private List<String> mSpecializationDoctorsCount;
    private SpecializationAdapter specializationAdapter;

    private RecyclerView recyclerViewCity;
    private List<String> mCity;
    private List<String> mCityDoctorsCount;
    private CityAdapter cityAdapter;

    private SocialAutoCompleteTextView search_bar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerViewDoctor = view.findViewById(R.id.recycler_view_names);
        recyclerViewDoctor.setHasFixedSize(true);
        recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewSpecialization = view.findViewById(R.id.recycler_view_specialization);
        recyclerViewSpecialization.setHasFixedSize(true);
        recyclerViewSpecialization.setLayoutManager(new LinearLayoutManager(getContext()));


        recyclerViewCity = view.findViewById(R.id.recycler_view_city);
        recyclerViewCity.setHasFixedSize(true);
        recyclerViewCity.setLayoutManager(new LinearLayoutManager(getContext()));


        mSpecialization = new ArrayList<>();
        mSpecializationDoctorsCount = new ArrayList<>();
        specializationAdapter = new SpecializationAdapter(getContext() , mSpecialization , mSpecializationDoctorsCount);
        recyclerViewSpecialization.setAdapter(specializationAdapter);

        mCity = new ArrayList<>();
        mCityDoctorsCount = new ArrayList<>();
        cityAdapter = new CityAdapter(getContext() , mCity , mCityDoctorsCount);
        recyclerViewCity.setAdapter(cityAdapter);

        mModel = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(getContext() , mModel , true);
        recyclerViewDoctor.setAdapter(doctorAdapter);

        search_bar = view.findViewById(R.id.search_bar);

        readSpecialization();
        readCitys();
        readDoctors();

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchDoctor(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCity(s.toString());
                filterSpecialization(s.toString());
            }
        });

        return view;
    }

    private void readSpecialization() {

        FirebaseDatabase.getInstance().getReference().child("Specialization").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSpecialization.clear();
                mSpecializationDoctorsCount.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    mSpecialization.add(snapshot.getKey());
                    mSpecializationDoctorsCount.add(snapshot.getChildrenCount() + "");
                }

                specializationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void readCitys() {

        FirebaseDatabase.getInstance().getReference().child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCity.clear();
                mCityDoctorsCount.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    mCity.add(snapshot.getKey());
                    mCityDoctorsCount.add(snapshot.getChildrenCount() + "");
                }

                cityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readDoctors() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (TextUtils.isEmpty(search_bar.getText().toString())){
                    mModel.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        DoctorModel user = snapshot.getValue(DoctorModel.class);
                        mModel.add(user);
                    }

                    doctorAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void searchDoctor (String s) {
        Query query = FirebaseDatabase.getInstance().getReference().child("Doctors")
                .orderByChild("name").startAt(s).endAt(s + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mModel.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DoctorModel user = snapshot.getValue(DoctorModel.class);
                    mModel.add(user);
                }
                doctorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void filterSpecialization (String text) {
        List<String> mSearchSpecialization = new ArrayList<>();
        List<String> mSearchSpecializationCount = new ArrayList<>();

        for (String s : mSpecialization) {
            if (s.toLowerCase().contains(text.toLowerCase())){
                mSearchSpecialization.add(s);
                mSearchSpecializationCount.add(mSpecializationDoctorsCount.get(mSpecialization.indexOf(s)));
            }
        }

        specializationAdapter.filter(mSearchSpecialization , mSearchSpecializationCount);
    }
    private void filterCity(String text) {
        List<String> mSearchCity = new ArrayList<>();
        List<String> mSearchCityCount = new ArrayList<>();

        for (String s : mSpecialization) {
            if (s.toLowerCase().contains(text.toLowerCase())){
                mSearchCity.add(s);
                mSearchCityCount.add(mSpecializationDoctorsCount.get(mSpecialization.indexOf(s)));
            }
        }

        cityAdapter.filter(mSearchCity , mSearchCityCount);
    }

    }

