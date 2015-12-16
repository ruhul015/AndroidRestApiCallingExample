package com.droid.bdapp.androidrestapicallingexample.datasources.cloud;

import android.content.Context;
import android.os.AsyncTask;

public abstract class TaskBase extends AsyncTask<TaskParameter, Integer, TaskParameter> {
    public static final String TAG = TaskBase.class.getSimpleName();

    protected Context mContext = null;
    protected TaskCallback mCallback = null;
    protected TaskType mType = null;
    protected TaskParameter mParam = null;
    private Throwable mThrowable = null;
    private TaskManager mTaskManager = null;

    public void initialize(final Context context, final TaskManager manager, final TaskType type, final TaskCallback callback) {
        this.mContext = context;
        this.mTaskManager = manager;
        this.mType = type;
        this.mCallback = callback;
    }

    public void setCallback(final TaskCallback mCallback) {
        this.mCallback = mCallback;
    }

    protected void onPreExecuteTask() {
    }

    @Override
    final protected void onPreExecute() {
        super.onPreExecute();
        onPreExecuteTask();
    }

    abstract protected TaskParameter doInBackgroundTask(final TaskParameter param) throws Exception;

    @Override
    final protected TaskParameter doInBackground(final TaskParameter... params) {
        try {
            if (null != params) {
                mParam = params[0];
            } else {
                mParam = null;
            }

            mParam = doInBackgroundTask(mParam);

        } catch (final Throwable th) {
            this.mThrowable = th;
        }

        return mParam;
    }

    public void cancel() {
        super.cancel(true);
    }

    protected void onCancelledTask() {
        mTaskManager.finished(this);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        onCancelledTask();

        if (null != mCallback) {
            mCallback.onCancelCallback(mType, mParam);
        }
    }

    abstract protected void onPostExecuteTask(final TaskParameter result) throws Exception;

    @Override
    final protected void onPostExecute(final TaskParameter result) {
        if (null != mThrowable) {
            errorProcess(mThrowable);
        } else {
            try {
                onPostExecuteTask(result);
            } catch (final Throwable thx) {
                errorProcess(thx);
            }
        }
    }

    protected void callTaskEnd(final TaskParameter obj) throws Exception {
        mTaskManager.finished(this);

        if (null != mCallback) {
            mCallback.onEndCallback(mType, obj);
        }
    }

    protected void errorProcess(final Throwable th) {
        mTaskManager.finished(this);
        // mTaskManager.cancelAll();
    }
}
