package com.anan.springboot.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *  @author anan
 * Created on 2017-07-04 01:30
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
