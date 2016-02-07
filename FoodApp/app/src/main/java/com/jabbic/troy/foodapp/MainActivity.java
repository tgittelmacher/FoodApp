package com.jabbic.troy.foodapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jabbic.troy.foodapp.utilities.BullshitException;
import com.jabbic.troy.foodapp.utilities.DeliveryHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Fragment mFragment;
    public Menu mMenu;
    public RequestQueue mQueue;
    public static Activity mActivity;
    public JSONObject mObject;
    public String mMerchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SET MAIN ACTIVITY VIEW
        setContentView(R.layout.activity_main);

        //CONFIGURE TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //CREATE DEFAULT FRAGMENT. LATER CAN BE CHANGED TO SPLASH SCREEN.
        mFragment = new SplashFragment();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        makeMainScreenFragment(null);
                    }
                });

            }
        }).start();//note: Android naming convention for class variables is "m" + its name.

        //TELL MAIN ACTIVITY TO SHOW mFRAGMENT
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mFragment, "MAIN")
                    .commit();
        }

        DeliveryHelper helper = DeliveryHelper.getInstance();
        mQueue = Volley.newRequestQueue(this);
        helper.mQueue = mQueue;

        mActivity = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (mMenu == null)
            mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings || item.getTitle().equals("Settings")) {
            makeSettingsFragment(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void swapFragment(String tag) {
    /*
    CALL THIS FUNCTION FROM AN ONCLICK LISTENER AFTER CONFIGURING mFRAGMENT. SEE BELOW EXAMPLES
     */
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mFragment, tag).addToBackStack(null).commit();
    }


    public void makeMainScreenFragment(View view) {
    /*
    SETS CURRENT DISPLAYED FRAGMENT TO MAIN SCREEN
     */
        mFragment = new MainScreenFragment();

        //BE SURE TO ADD TAG... DOESNT DO ANYTHING YET, BUT WILL
        swapFragment("MAINSCREEN");

        //TITLE THAT SHOWS UP ON TOOLBAR
        setTitle("FoodApp");

        //ADDS PREVIOUSLY DELETED SETTINGS DROPDOWN
        mMenu.add("Settings");

    }


    public void makeSettingsFragment(View view) {
        //SEE MAIN FRAGMENT EXAMPLE
        mFragment = new SettingsFragment();
        swapFragment("SETTINGS");
        setTitle("Settings");

        //WHY WOULD WE WANT TO ACCESS SETTINGS FROM SETTINGS?
        mMenu.clear();
    }

    public void makeNewOrderFragment(View view) {
        mFragment = new NewOrderFragment();
        swapFragment("NEWORDER");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DeliveryHelper helper = DeliveryHelper.getInstance();
//                Toast.makeText(MainActivity.this, helper.getDatAccess(), Toast.LENGTH_SHORT).show();
//            }
//        }).start();
        setTitle("New Order");
        mMenu.clear();
    }

    public void makeConfirmFragment(View view) throws BullshitException {
        mFragment = new ConfirmFragment();
        swapFragment("CONFIRM");
        apiCall(DeliveryHelper.GET_ITEMS);
        setTitle("Confirm Order");
        mMenu.clear();
    }

    public void makeSuccessFragment(View view) {
        mFragment = new SuccessFragment();
        swapFragment("SUCCESS");

        setTitle("FoodApp");
        mMenu.clear();
    }



    public void makeFailureFragment(View view) {
        mFragment = new FailureFragment();
        swapFragment("FAILURE");

        setTitle("FoodApp");
        mMenu.clear();
    }

    EditText cardHolderNameIn;
    EditText cardNumberIn, cardSecNumberIn;
    EditText cardExpMonthIn, cardExpYearIn;
    EditText billAddressIn, deliAddressIn;

    public void SaveInfo(View view){
        SharedPreferences sharedPref = getSharedPreferences("customerInfo", Context.MODE_PRIVATE);



        cardHolderNameIn = (EditText) findViewById(R.id.name_field);
        String name = cardHolderNameIn.getText().toString();

        cardNumberIn = (EditText) findViewById(R.id.cc_number_field);
        String number = cardNumberIn.getText().toString();

        cardSecNumberIn = (EditText) findViewById(R.id.card_security_num);
        String secnumber = cardSecNumberIn.getText().toString();

        cardExpMonthIn = (EditText) findViewById(R.id.expiration_month);
        String expmon = cardExpMonthIn.getText().toString();

        cardExpYearIn = (EditText) findViewById(R.id.expiration_year);
        String expyear = cardExpYearIn.getText().toString();

        billAddressIn = (EditText) findViewById(R.id.billing_address);
        String billadd = billAddressIn.getText().toString();

        deliAddressIn = (EditText) findViewById(R.id.delivery_address);
        String deliadd = deliAddressIn.getText().toString();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("cardHolderName", name);
        editor.putString("cardNumber", number);
        editor.putString("cardSecNumber", secnumber);
        editor.putString("cardExpMonth", expmon);
        editor.putString("cardExpYear", expyear);
        editor.putString("billAddress", billadd);
        editor.putString("deliAddress", deliadd);
        editor.commit();

        Toast.makeText(MainActivity.this, "Settings Saved.", Toast.LENGTH_SHORT).show();
        makeMainScreenFragment(null);

    }

    private void apiCall(int keyCode) throws BullshitException {
        DeliveryHelper helper = DeliveryHelper.getInstance();
        if (keyCode == 0) {
            //GET RESTAURANTS, ETC
            helper.findRestaurants();
        }
        else if (keyCode == 1) {
           //CREDIT CARD PAYMENT
            helper.getMenu();
        }
        else
            throw new BullshitException();
    }

    public static Activity getInstance() {
        return mActivity;
    }

    public void setRestaurants(JSONObject object) throws BullshitException, JSONException {
        mObject = object;
        Gson gson = new GsonBuilder().create();
        //ArrayList<ArrayList<String>> list = gson.fromJson(object.toString(), new TypeToken<ArrayList<ArrayList<String>>>() {}.getType());
        JSONArray arr = mObject.getJSONArray("merchants");

        JSONObject o = (JSONObject) arr.get((int)(Math.random() * arr.length()));
        String id = (String)o.get("id");
        mMerchant = id;

        Toast.makeText(MainActivity.this, "Data receieved!", Toast.LENGTH_SHORT).show();
        //gson.fromJson()
        apiCall(DeliveryHelper.GET_MENU);
    }

}
