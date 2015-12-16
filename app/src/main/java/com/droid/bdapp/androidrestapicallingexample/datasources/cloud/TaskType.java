package com.droid.bdapp.androidrestapicallingexample.datasources.cloud;


import com.droid.bdapp.androidrestapicallingexample.datasources.cloud.tasks.UserLoaderTask;

public enum TaskType {
    USER_LOADER;

    public TaskBase createIns() {
        switch (this) {
            case USER_LOADER:
                return new UserLoaderTask();
        }
        return null;
    }
}
