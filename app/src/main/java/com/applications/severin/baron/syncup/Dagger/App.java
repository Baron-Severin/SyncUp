package com.applications.severin.baron.syncup.Dagger;

import android.app.Application;

/**
 * Created by erikrudie on 11/18/16.
 */

public class App extends Application {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    applicationComponent = DaggerApplicationComponent.builder()
      .applicationModule(new ApplicationModule(this))
      .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
