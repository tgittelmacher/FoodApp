package com.jabbic.troy.foodapp.utilities;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jabbic.troy.foodapp.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static final int GET_MENU = 1;

    private static final String CLIENT_ID = "ODhiM2RmMWU1Mjg3MzA0YjYxNjE5OWNhYjk4ZjhmZTNh";
    private  static final String CLIENT_ID2 = "YjU3ZTliNjY4YmU5NDY0YzIyZWMyMGM1Y2NhZTMxYTk4";
    private static final String CLIENT_SECRET = "aZun6EfU15Qpnc0fy8vhPUuS6R7s1ZGB9B9P0GWY";
    private static final String CLIENT_SECRET2 = "NvNtJKvf2W6F307c1g2EmBLlinCqyp3Zotu2mi3B";

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
    final static String MERCHANT_URL = "merchant/";
    final static String SEARCH_ADDRESS = "3 Little Ct 07092";
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
        Toast.makeText(MainActivity.getInstance(), "REQUESTING JSON...", Toast.LENGTH_SHORT).show();
//        StringBuilder
//        StringRequest sr = new StringRequest(Request.Method.GET, host,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("HttpClient", "success! response: " + response.toString());
//                        Toast.makeText(MainActivity.getInstance(), "IT WORKED OMG", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("HttpClient", "error: " + error.toString());
//                    }
//                })
//        {
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("method",SEARCH_URL);
//                params.put("address",SEARCH_ADDRESS);
//                params.put("merchant_type", "R");
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        mQueue.add(sr);
        Map<String,String> params = new HashMap<String, String>();
        params.put("method","delivery");
        params.put("address",SEARCH_ADDRESS);
        //params.put("merchant_type", "R");

        String finalURL = host + SEARCH_URL + "?client_id=" +CLIENT_ID2+"&method=delivery&address=" +SEARCH_ADDRESS +"&merchant_type=R";



        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,finalURL , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            //JSONArray array = response.names();
                            //Object o = response.get("merchants");

                            //Toast.makeText(MainActivity.getInstance(), response.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("JSON", "success! response: " + response.toString());
                            ((MainActivity)MainActivity.getInstance()).setRestaurants(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (BullshitException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(MainActivity.getInstance(), "Error retrieving JSON", Toast.LENGTH_SHORT).show();
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(
                100000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        mQueue.add(req);
    }

    public void getMenu() {
        String merchant = ((MainActivity)MainActivity.getInstance()).mMerchant;

        String finalURL = host + MERCHANT_URL +merchant + "/menu" + "?client_id=" +CLIENT_ID2+"&merchant_id=" +Integer.parseInt(merchant)
                +"&item_only=1&hide_unavailable=1";



        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,finalURL , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            //JSONArray array = response.names();
                            //Object o = response.get("merchants");

                            //Toast.makeText(MainActivity.getInstance(), response.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("JSON", "successful menu grab! response: " + response.toString());
                            ((MainActivity)MainActivity.getInstance()).setRestaurants(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (BullshitException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(MainActivity.getInstance(), "Error retrieving JSON", Toast.LENGTH_SHORT).show();
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(
                100000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        mQueue.add(req);
    }

    private void constructRequest() {

    }


}
