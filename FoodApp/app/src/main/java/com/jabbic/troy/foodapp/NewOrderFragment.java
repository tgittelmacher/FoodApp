package com.jabbic.troy.foodapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Troy on 2/6/2016.
 */
public class NewOrderFragment extends Fragment {
    public NewOrderFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.new_order_view, container, false);



        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle sharedInstanceState) {
        super.onViewCreated(view, sharedInstanceState);


        /*
        THIS NEXT SUPER UGLY BLOCK OF CODE CONFIGURES PRICE RANGE SEEK BAR... IGNORE
         */
        final TextView priceRangeTextView = (TextView) view.findViewById(R.id.price_range_title);
        SeekBar priceRangeSeekBar = (SeekBar) view.findViewById(R.id.price_range);
        priceRangeTextView.setText("Price range per order: $" + (priceRangeSeekBar.getProgress() + 5));
        priceRangeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceRangeTextView.setText("Price range per order: $" + String.valueOf(progress + 5));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*
        THIS NEXT SUPER UGLY BLOCK OF CODE CONFIGURES NUMBER OF ORDERS SEEK BAR... ALSO IGNORE
         */
        final TextView numOrdersTextView = (TextView) view.findViewById(R.id.number_of_people_title);
        SeekBar numOrdersSeekBar = (SeekBar) view.findViewById(R.id.number_of_people);
        numOrdersTextView.setText("Number of Orders: " + (numOrdersSeekBar.getProgress() + 1));
        numOrdersSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numOrdersTextView.setText("Number of Orders: " + String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SharedPreferences sharedPref = getActivity().getSharedPreferences("orderDetails", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        SeekBar priceBar = (SeekBar)getActivity().findViewById(R.id.price_range);
        SeekBar peopleBar = (SeekBar)getActivity().findViewById(R.id.number_of_people);

        editor.putInt("Price", priceBar.getProgress());
        editor.putInt("People", peopleBar.getProgress() + 1);

        editor.commit();
    }
}
