package com.jabbic.troy.foodapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Troy on 2/6/2016.
 */
public class SplashFragment extends Fragment {
    public SplashFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView( inflater, container, savedInstanceState );
        View rootView = inflater.inflate(R.layout.splash_view, container, false);
        ((MainActivity)(MainActivity.getInstance())).getSupportActionBar().hide();

        return rootView;
    }
}
