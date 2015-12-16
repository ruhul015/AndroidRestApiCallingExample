package com.droid.bdapp.androidrestapicallingexample.datasources.cloud;

import android.text.TextUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * THIS CALSS SHOULD BE REPLACE BY USING HttpUrlConnection
 */


@SuppressWarnings("deprecation")
public class ApiRequest {
    public static final String TAG = ApiRequest.class.getSimpleName();

    private static final int CONNECTION_TIMEOUT = 10000;// wait max 10 secs to open connection
    private static final int READ_TIMEOUT = 0;// no read time out, as file uploads can take long!

    /**
     * @param url
     * @param bodyJsonDataAsString
     * @param apiHeaders
     * @return
     */
    public ApiResponse doPost(final String url, final String bodyJsonDataAsString, final Map<String, String> apiHeaders) {
        final ApiResponse apiResponse = new ApiResponse();

        try {
            final DefaultHttpClient apiClient = getApiClient();
            final HttpPost httpPost = getHttpPost(url, bodyJsonDataAsString, apiHeaders);

            //get api response
            final HttpResponse httpResponse = apiClient.execute(httpPost);

            final HttpEntity httpEntity = httpResponse.getEntity();

            apiResponse.setContentTypeName(httpEntity.getContentType().getName());
            apiResponse.setContentTypeValue(httpEntity.getContentType().getValue());
            apiResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            apiResponse.setStatusMessage(httpResponse.getStatusLine().getReasonPhrase());
            apiResponse.setJsonString(EntityUtils.toString(httpEntity));
            apiResponse.setHasCaughtException(false);

        } catch (Exception e) {
            apiResponse.setHasCaughtException(true);
        }
        return apiResponse;
    }

    /**
     * @param url
     * @param apiHeaders
     * @return
     */
    public ApiResponse doGet(final String url, final Map<String, String> apiHeaders) {
        ApiResponse apiResponse = null;

        try {
            final DefaultHttpClient apiClient = getApiClient();
            final HttpGet httpGet = getHttpGet(url, apiHeaders);

            //get api response
            final HttpResponse httpResponse = apiClient.execute(httpGet);

            //build readable api response
            apiResponse = new ApiResponse();
            final HttpEntity httpEntity = httpResponse.getEntity();

            apiResponse.setContentTypeName(httpEntity.getContentType().getName());
            apiResponse.setContentTypeValue(httpEntity.getContentType().getValue());
            apiResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            apiResponse.setStatusMessage(httpResponse.getStatusLine().getReasonPhrase());
            apiResponse.setJsonString(EntityUtils.toString(httpEntity));
            apiResponse.setHasCaughtException(false);

        } catch (Exception e) {
            apiResponse.setHasCaughtException(true);
        }
        return apiResponse;
    }

    private DefaultHttpClient getApiClient() {
        final HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, READ_TIMEOUT);

        return new DefaultHttpClient(httpParameters);

    }

    /**
     * @param url
     * @param bodyJsonDataAsString
     * @param apiHeaders
     * @return
     * @throws Exception
     */
    private HttpPost getHttpPost(final String url, final String bodyJsonDataAsString,
                                 final Map<String, String> apiHeaders) throws Exception {
        final HttpPost httpPost = new HttpPost(url);

        if (!TextUtils.isEmpty(bodyJsonDataAsString)) {
            httpPost.setEntity(new StringEntity(bodyJsonDataAsString));
        }

        //
        if (null != apiHeaders && !apiHeaders.isEmpty()) {
            for (final Iterator iterator = apiHeaders.keySet().iterator(); iterator.hasNext(); ) {
                final String key = (String) iterator.next();
                final String value = (String) apiHeaders.get(key);

                httpPost.setHeader(key, value);
            }
        }
        return httpPost;
    }

    /**
     * @param url
     * @param apiHeaders
     * @return
     * @throws Exception
     */
    private HttpGet getHttpGet(final String url, final Map<String, String> apiHeaders) throws Exception {
        final HttpGet httpGet = new HttpGet(url);

        //
        if (null != apiHeaders && !apiHeaders.isEmpty()) {
            for (final Iterator iterator = apiHeaders.keySet().iterator(); iterator.hasNext(); ) {
                final String key = (String) iterator.next();
                final String value = (String) apiHeaders.get(key);

                httpGet.setHeader(key, value);
            }
        }
        return httpGet;
    }
}
