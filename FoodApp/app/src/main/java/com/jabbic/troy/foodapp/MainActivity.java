package com.jabbic.troy.foodapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Fragment mFragment;
    public Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SET MAIN ACTIVITY VIEW
        setContentView(R.layout.activity_main);

        //CONFIGURE TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //CREATE DEFAULT FRAGMENT. LATER CAN BE CHANGED TO SPLASH SCREEN.
        mFragment = new MainScreenFragment();   //note: Android naming convention for class variables is "m" + its name.

        //TELL MAIN ACTIVITY TO SHOW mFRAGMENT
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mFragment, "MAIN")
                    .commit();
        }
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

        setTitle("New Order");
        mMenu.clear();
    }

    public void makeConfirmFragment(View view) {
        mFragment = new ConfirmFragment();
        swapFragment("CONFIRM");

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
}
