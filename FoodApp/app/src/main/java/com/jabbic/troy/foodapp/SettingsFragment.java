package com.jabbic.troy.foodapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        populateEntries(rootView);
        return rootView;
    }

    private void populateEntries(View view) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
        EditText name = (EditText) view.findViewById(R.id.name_field);
        name.setText(sharedPref.getString("cardHolderName", ""));

        EditText number = (EditText) view.findViewById(R.id.cc_number_field);
        number.setText(sharedPref.getString("cardNumber", ""));

        EditText secnumber = (EditText) view.findViewById(R.id.card_security_num);
       secnumber.setText(sharedPref.getString("cardSecNumber", ""));

        EditText expmon = (EditText) view.findViewById(R.id.expiration_month);
        expmon.setText(sharedPref.getString("cardExpMonth", ""));

        EditText expyear = (EditText) view.findViewById(R.id.expiration_year);
        expyear.setText(sharedPref.getString("cardExpYear", ""));

        EditText billadd = (EditText) view.findViewById(R.id.billing_address);
        billadd.setText(sharedPref.getString("billAddress", ""));

        EditText deliadd = (EditText) view.findViewById(R.id.delivery_address);
        deliadd.setText(sharedPref.getString("deliAddress", ""));
    }
}
