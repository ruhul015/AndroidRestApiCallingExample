package com.droid.bdapp.androidrestapicallingexample.datasources.cloud.tasks;

import android.content.Context;

import com.droid.bdapp.androidrestapicallingexample.app.AppBaseApplication;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.ApiRequest;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.ApiResponse;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskBase;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskParameter;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.utils.ApiHeader;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.utils.ApiUrl;
import com.droid.bdapp.androidrestapicallingexample.utils.AppConstants;


public class UserLoaderTask extends TaskBase {
    public static final String TAG = UserLoaderTask.class.getName();

    private static final Context APP_CONTEXT = AppBaseApplication.getContext();

    private int mStatusCode = 0;
    private String mStatusMessage = "Unknown";

    @Override
    protected TaskParameter doInBackgroundTask(final TaskParameter param)
            throws Exception {
        final String url = ApiUrl.getLoginUrl();

        final String userName = (String) param.get(AppConstants.KEY_USER_NAME);
        final String password = (String) param.get(AppConstants.KEY_PASSWORD);

        final ApiRequest apiRequest = new ApiRequest();
        //write your own code as your needs. here is a example.
        final ApiResponse apiResponse = apiRequest.doPost(ApiUrl.getLoginUrl(), null, ApiHeader.getLoginApiHeaders());

        //
        if (apiResponse != null && !apiResponse.hasCaughtException()) {
            try {
                final String str = apiResponse.getJsonString();
                //TODO: write your codes
                //....
            } catch (Exception e) {
                //parsing problem
                //body string null

            }
        } else {
            // API caught exception
            //client side exception such as null-pointer,etc

        }

        param.put(AppConstants.KEY_STATUS_CODE, mStatusCode);
        param.put(AppConstants.KEY_STATUS_MESSAGE, mStatusMessage);
        return param;

    }

    @Override
    protected void onPostExecuteTask(final TaskParameter result)
            throws Exception {
        callTaskEnd(result);
    }

}
