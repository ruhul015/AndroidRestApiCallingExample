package com.droid.bdapp.androidrestapicallingexample.ui.activities;

import android.os.Bundle;

import com.droid.bdapp.androidrestapicallingexample.R;
import com.droid.bdapp.androidrestapicallingexample.app.AppBaseActivity;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskCallback;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskParameter;
import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskType;
import com.droid.bdapp.androidrestapicallingexample.utils.AppConstants;

public class MainActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        loadUser("name", "password");
    }


    private void loadUser(final String userName, final String password) {
        final TaskParameter params = new TaskParameter();
        params.put(AppConstants.KEY_USER_NAME, userName);
        params.put(AppConstants.KEY_PASSWORD, password);
        mTaskManager.startTask(TaskType.USER_LOADER, new TaskCallback() {

            @Override
            public void onProgressCallback(TaskType type, TaskParameter param) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onEndCallback(TaskType type, TaskParameter param) {
                final int statusCode = (Integer) param.get(AppConstants.KEY_STATUS_CODE);
                final String statusMessage = (String) param.get(AppConstants.KEY_STATUS_MESSAGE);

                //TODO: Wrtite your own codes as your needs
            }

            @Override
            public void onCancelCallback(TaskType type, TaskParameter param) {
                // TODO Auto-generated method stub

            }
        }, params);
    }
}
