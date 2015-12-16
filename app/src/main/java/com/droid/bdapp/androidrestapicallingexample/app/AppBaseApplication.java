package com.droid.bdapp.androidrestapicallingexample.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.TaskManager;


/**
 * Created by mdruhulamin on 2/12/15.
 */
public class AppBaseApplication extends Application {
    public static final String TAG = AppBaseApplication.class.getSimpleName();

    private static AppBaseApplication sInstance;
    private static TaskManager sTaskManager;
    private static String sVersionName = "unknown";
    private static String sPackageName = "com.droid.bdapp.androidrestapicallingexample";
    private static int sVersionNumber = 0;

    public static AppBaseApplication getInstance() {
        return sInstance;
    }

    public static AppBaseApplication getContext() {
        return sInstance;
    }


    public static TaskManager getGlobalTaskManager() {
        return sTaskManager;
    }

    public static int getVersionNumber() {
        return sVersionNumber;
    }

    public static String getVersionName() {
        return sVersionName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        sTaskManager = new TaskManager();
        //get app version name & code
        PackageInfo pinfo = null;
        try {
            sPackageName = getPackageName();
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            if (pinfo != null) {
                sVersionNumber = pinfo.versionCode;
                sVersionName = pinfo.versionName;
            }
        } catch (final Exception e) {
            Log.e(TAG, "", e);
        }


    }
}
