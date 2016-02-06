package com.jabbic.troy.foodapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /*
    MAIN FRAGMENT EVENT HANDLERS, ETC. BELOW
     */

    public void onOrderClick(View view) {
        //Example hook for a UI event.
        //Ie, for a button's "onClick" attribute, add "onOrderClick" to
        //call this method.

        //Below line of code just makes a "Toast" message pop up momentarily on the screen
        Toast.makeText(getActivity().getBaseContext(),
                "You clicked me!", Toast.LENGTH_SHORT).show();
    }
}
