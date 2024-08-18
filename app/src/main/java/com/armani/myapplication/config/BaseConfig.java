package com.armani.myapplication.config;

import android.content.Context;
import android.widget.Toast;

import com.armani.myapplication.config.dto.UserDTO;

public class BaseConfig {

    //region user singleton
    public static UserDTO userDTO;

    public static UserDTO getUserDTO() {
        return userDTO;
    }
    //endregion

    //region public
    //TODO replace this ip with ur ip address
    private final static String BASE_IP = "192.168.54.34";
    private final static String BASE_PROTOCOL = "http";
    private final static String BASE_PORT = "9080";
    private final static String BASE_URI = "/api/";
    private static final String BASE_URL = BASE_PROTOCOL + "://" + BASE_IP + ":" + BASE_PORT + BASE_URI;
    //endregion

    //region user
    private static final String USER_URI = "users/";
    public static final String USER_INFO = BASE_URL + USER_URI + "access";
    //endregion

    //region mobile
    private static final String MOBILE_URI = "mobiles/";
    public static final String MOBILE_SIMPLE_LIST = BASE_URL + MOBILE_URI + "simple";
    public static final String MOBILE_SIMPLE_DTO = BASE_URL + MOBILE_URI;
    public static final String MOBILE_COMPARE = BASE_URL + MOBILE_URI + "compare";
    //endregion

    //region accessory
    private static final String ACCESSORY_URL = "accessories/";
    public static final String ACCESSORY_SIMPLE_LIST = BASE_URL + ACCESSORY_URL + "simple";
    public static final String ACCESSORY_SIMPLE_DTO = BASE_URL + ACCESSORY_URL;
    //endregion

    //region image
    public static final String MOBILE_IMAGE = BASE_URL + MOBILE_URI + "mobile-one-image/";
    public static final String MOBILE_IMAGE2 = BASE_URL + MOBILE_URI + "1/image/download";
    public static final String MOBILE_ONE_IMAGE = BASE_URL + MOBILE_URI + "/mobile-one-image/";
    //endregion

    //region basket
    private static final String BASKET_URI = "baskets/";
    public static final String BASKET_ADD = BASE_URL + BASKET_URI + "addToUserBasket/";
    public static final String MOBILE_ADD = "/mobile/";
    public static final String ACCESSORY_ADD = "/accessory/";
    public static final String BASKET_INFO = BASE_URL + BASKET_URI + "basket-info/";
    //endregion

    //region public method
    public static void shortMsgBox(Context applicationContext, String msg) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void msgBox(Context applicationContext, String msg) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show();
    }
    //endregion
}
