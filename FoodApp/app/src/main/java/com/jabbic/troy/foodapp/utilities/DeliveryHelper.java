package com.jabbic.troy.foodapp.utilities;

/**
 * Created by Troy on 2/6/2016.
 */
public class DeliveryHelper {

    private static final String CLIENT_ID = "ODhiM2RmMWU1Mjg3MzA0YjYxNjE5OWNhYjk4ZjhmZTNh";
    private static final String CLIENT_SECRET = "aZun6EfU15Qpnc0fy8vhPUuS6R7s1ZGB9B9P0GWY";

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
}
