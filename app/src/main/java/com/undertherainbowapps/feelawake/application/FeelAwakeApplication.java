package com.undertherainbowapps.feelawake.application;

import android.app.Application;
import android.content.res.Configuration;

public class FeelAwakeApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImpl());
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
  }

}
