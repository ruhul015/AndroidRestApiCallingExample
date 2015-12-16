package com.droid.bdapp.androidrestapicallingexample.datasources.cloud;

public interface TaskCallback
{
	public void onEndCallback(final TaskType type, final TaskParameter param);
	public void onCancelCallback(final TaskType type, final TaskParameter param);
	public void onProgressCallback(final TaskType type, final TaskParameter param);
}
