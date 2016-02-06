package com.jabbic.troy.foodapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Troy on 2/6/2016.
 */
public class ConfirmFragment extends Fragment {

    public ConfirmFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView( inflater, container, savedInstanceState );
        View rootView = inflater.inflate(R.layout.confirm_view, container, false);

        return rootView;
    }
}
