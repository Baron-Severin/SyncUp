package com.applications.severin.baron.syncup.Dagger;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

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
    setUpStetho();
  }

  private void setUpStetho() {
// Create an InitializerBuilder
    Stetho.InitializerBuilder initializerBuilder =
            Stetho.newInitializerBuilder(this);

// Enable Chrome DevTools
    initializerBuilder.enableWebKitInspector(
            Stetho.defaultInspectorModulesProvider(this)
    );

// Enable command line interface
    initializerBuilder.enableDumpapp(
            Stetho.defaultDumperPluginsProvider(this)
    );

// Use the InitializerBuilder to generate an Initializer
    Stetho.Initializer initializer = initializerBuilder.build();

// Initialize Stetho with the Initializer
    Stetho.initialize(initializer);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
