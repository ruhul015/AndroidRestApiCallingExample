package com.droid.bdapp.androidrestapicallingexample.datasources.cloud;

import java.util.HashMap;

public class TaskParameter extends HashMap<String, Object> {
    public static final String TAG = TaskParameter.class.getSimpleName();

    private static final long SERIAL_VERSION_UID = -5111482748849799850L;

    private int mResultCode = 0;
    public boolean isSilentMode = false;

    public int getmResultCode() {
        return mResultCode;
    }

    public void setmResultCode(final int code) {
        mResultCode = code;
    }

    public void safePut(final String key, final Object object) {
        if (object != null) {
            put(key, object);
        }
    }
}
