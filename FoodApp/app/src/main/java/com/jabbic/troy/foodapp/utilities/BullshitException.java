package com.jabbic.troy.foodapp.utilities;

/**
 * Created by Troy on 2/6/2016.
 */
public class BullshitException extends Throwable {
    @Override
    public void printStackTrace() {
        System.err.println("Some bullshit just happened yo");
        super.printStackTrace();
    }
}
