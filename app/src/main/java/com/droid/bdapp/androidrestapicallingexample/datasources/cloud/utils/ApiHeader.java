package com.droid.bdapp.androidrestapicallingexample.datasources.cloud.utils;


import com.droid.bdapp.androidrestapicallingexample.utils.AppConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruhul on 12/12/2015.
 */
public class ApiHeader {

    public static Map<String, String> getApiCommonHeaders() {
        final Map<String, String> apiHeaders = new HashMap<>();
        apiHeaders.put(AppConstants.KEY_CONTENT_TYPE, "application/json");
        apiHeaders.put(AppConstants.KEY_CLIENT_AGENT, "ANDROID");

        return apiHeaders;
    }

    public static Map<String, String> getLoginApiHeaders() {
        final Map<String, String> apiHeaders = ApiHeader.getApiCommonHeaders();
        final String authorizationToken = "sdfsdblke56#$-565qsfwr";
        apiHeaders.put(AppConstants.KEY_AUTHORIZATION_TOKEN, authorizationToken);
        return apiHeaders;
    }
}
