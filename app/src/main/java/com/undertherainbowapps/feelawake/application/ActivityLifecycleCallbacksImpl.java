package com.undertherainbowapps.feelawake.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

  private static final String TAG = "ActivityLifecycleCallba";

  public void onActivityCreated(Activity activity, Bundle bundle) {
    Log.d(TAG, "onActivityCreated:" + activity.getLocalClassName());
  }

  public void onActivityDestroyed(Activity activity) {
    Log.d(TAG, "onActivityDestroyed:" + activity.getLocalClassName());
  }

  public void onActivityPaused(Activity activity) {
    Log.d(TAG, "onActivityPaused:" + activity.getLocalClassName());
  }

  public void onActivityResumed(Activity activity) {
    Log.d(TAG, "onActivityResumed:" + activity.getLocalClassName());
  }

  public void onActivitySaveInstanceState(Activity activity,
      Bundle outState) {
    Log.d(TAG, "onActivitySaveInstanceState:" + activity.getLocalClassName());
  }

  public void onActivityStarted(Activity activity) {
    Log.d(TAG, "onActivityStarted:" + activity.getLocalClassName());
  }

  public void onActivityStopped(Activity activity) {
    Log.d(TAG, "onActivityStopped:" + activity.getLocalClassName());
  }

}
