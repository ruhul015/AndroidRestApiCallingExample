package com.droid.bdapp.androidrestapicallingexample.datasources.cloud.utils;

/**
 * Created by Ruhul on 12/9/2015.
 */
public class ApiUrl {

    public static final String getBaseUrl() {
        return "http://droid.bdapp.com";//example. you should be replaced by your url
    }

    public static final String getLoginUrl() {
        return getBaseUrl() + "/user/login"; //example. you should be replaced by your url
    }


}
