package com.example.doctor.Fragments;
import com.google.android.gms.ads.AdView;
import android.content.Context;
import android.net.Uri;
import com.google.android.gms.ads.AdRequest;
import android.os.Bundle;
import com.google.android.gms.ads.InterstitialAd;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;


public class HomeFragment extends Fragment {
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

          return view;
    }


}
