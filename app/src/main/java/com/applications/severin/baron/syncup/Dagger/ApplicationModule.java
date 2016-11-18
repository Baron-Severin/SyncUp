package com.applications.severin.baron.syncup.Dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erikrudie on 11/18/16.
 */

@Module
public class ApplicationModule {

  private Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  public Context provideContext() {
    return application;
  }

}
