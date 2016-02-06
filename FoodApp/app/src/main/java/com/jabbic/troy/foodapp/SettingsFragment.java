package com.jabbic.troy.foodapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Troy on 2/6/2016.
 */
public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView( inflater, container, savedInstanceState );
        View rootView = inflater.inflate(R.layout.settings_view, container, false);

        return rootView;
    }
}
