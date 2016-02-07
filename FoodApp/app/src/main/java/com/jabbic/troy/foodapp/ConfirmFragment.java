package com.jabbic.troy.foodapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @Override
    public void onViewCreated(View view, Bundle sharedInstanceState) {
        super.onViewCreated(view, sharedInstanceState);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("orderDetails", Context.MODE_PRIVATE);

        //final TextView orderAmount = (TextView) view.findViewById(R.id.order_amount);
        //orderAmount.setText("Amount: $" + String.valueOf(sharedPref.getInt("Price", 0)));
        //final TextView numberOfOrders = (TextView) view.findViewById(R.id.num_order_final);
        //numberOfOrders.setText("Number of orders: " + String.valueOf(sharedPref.getInt("People", 0)));

    }
}
