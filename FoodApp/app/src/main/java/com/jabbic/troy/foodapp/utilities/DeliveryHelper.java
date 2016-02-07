package com.jabbic.troy.foodapp.utilities;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jabbic.troy.foodapp.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Troy on 2/6/2016.
 */
public class DeliveryHelper {

    public static final int GET_ITEMS = 0;
    public static final int MAKE_PAYMENT = 1;

    private static final String CLIENT_ID = "ODhiM2RmMWU1Mjg3MzA0YjYxNjE5OWNhYjk4ZjhmZTNh";
    private static final String CLIENT_SECRET = "aZun6EfU15Qpnc0fy8vhPUuS6R7s1ZGB9B9P0GWY";

    final static String host = "http://sandbox.delivery.com/";

    final static String GUEST_TOKEN = "Guest-Token";
    final static String AUTH_TOKEN = "Authorization";
    final static String GUEST_TOKEN_URL = "customer/auth/guest";
    final static String CUSTOMER_CART_URL = "customer/cart";
    final static String CHECKOUT_URL = "customer/cart";
    final static String CC_URL = "customer/cc";
    final static String AUTH_URL = "customer/auth";
    final static String LOCATION_URL = "customer/location";
    final static String ORDER_URL = "customer/orders/recent";
    final static String SEARCH_URL = "merchant/search/delivery";
    final static String SEARCH_ADDRESS = "200 Vesey Street, 10281";
    final static String ADDRESS_APT = "";
    final static String ORDER_TYPE = "delivery";


    public RequestQueue mQueue;
    private Activity mActivity;

    //CANONICAL SINGLETON
    private static DeliveryHelper instance;

    private DeliveryHelper() {
        //TODO: INITIALIZE API AUTHENTICATION
    }

    public static DeliveryHelper getInstance() {
        /*
        CALL THIS STATIC PSEUDO-CONTSTRUCTOR ANYWHERE TO RETRIEVE SINGLETON INSTANCE
         */
        if (instance == null)
            instance = new DeliveryHelper();
        return instance;
    }

    /*
    API CALL FACADE METHODS BELOW
     */
//    @GET(host + SEARCH_URL + '/<param1>/<param2>')
    public void findRestaurants() {
//        StringBuilder
    }

    public void makeTransaction() {
    }

    private void constructRequest() {

    }


}
