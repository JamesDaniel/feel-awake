package com.undertherainbowapps.feelawake.application;

import android.app.Application;
import android.content.res.Configuration;
import com.bugfender.sdk.Bugfender;
import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.undertherainbowapps.feelawake.BuildConfig;
import com.undertherainbowapps.feelawake.R;
import io.fabric.sdk.android.Fabric;

public class FeelAwakeApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImpl());

    Bugfender.init(this, BuildConfig.BugfenderApiKey, BuildConfig.DEBUG);
    Bugfender.enableLogcatLogging();
    Bugfender.enableUIEventLogging(this);

    Fabric.with(this, new Crashlytics());

    FacebookSdk.setApplicationId(BuildConfig.FacebookAppId);
    FacebookSdk.sdkInitialize(getApplicationContext());
    AppEventsLogger.activateApp(this);
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
