package com.jabbic.troy.foodapp.utilities;

import android.widget.Toast;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Troy on 2/6/2016.
 */
public class DeliveryHelper {

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

    public String getDatAccess() {
        return getGuestToken(CLIENT_ID);
    }

    private static String getAccessToken(String code, String clientId, String clientSecret, String redirectURI)
    {
        String url = "http://sandbox.delivery.com/api/third_party/access_token";
        WebResource resource = Client.create().
                resource(
                        UriBuilder.fromUri(url).clone().build().toASCIIString()
                );

        JSONObject postData = new JSONObject();
        postData.put("client_id", clientId);
        postData.put("client_secret", clientSecret);
        postData.put("redirect_uri", redirectURI);
        postData.put("grant_type", "authorization_code");
        postData.put("code", code);

        ClientResponse resi = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, postData.toString());
        if (resi.getStatus() == ClientResponse.Status.OK.getStatusCode())
        {
            return JSONObject.fromObject(resi.getEntity(String.class)).getString("access_token");
        } else
        {
            String rs = resi.getEntity(String.class);
            throw new RuntimeException(JSONObject.fromObject(rs).getString("error_description"));
        }
    }
    private static String getGuestToken(String clientId)
    {
        WebResource resource = Client.create().resource(UriBuilder.fromUri(host + GUEST_TOKEN_URL).queryParam("client_id", clientId).clone().build().toASCIIString());
        ClientResponse res = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if (res.getStatus() == ClientResponse.Status.OK.getStatusCode())
        {
            String token = res.getEntity(String.class);
            return JSONObject.fromObject(token).getString("Guest-Token");
        } else
        {
            throw new RuntimeException(JSONObject.fromObject(res.getEntity(String.class)).getJSONArray("message").getJSONObject(0).getString("code"));
        }
    }
}
