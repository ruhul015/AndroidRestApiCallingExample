package com.droid.bdapp.androidrestapicallingexample.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskManager;


/**
 * Created by mdruhulamin on 02/12/15.
 */
public class AppBaseActivity extends AppCompatActivity {
    public static final String TAG = AppBaseActivity.class.getSimpleName();

    protected TaskManager mTaskManager = AppBaseApplication.getGlobalTaskManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mTaskManager.initialize(this);
    }


}
