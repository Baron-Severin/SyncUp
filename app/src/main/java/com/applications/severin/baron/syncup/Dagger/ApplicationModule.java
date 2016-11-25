package com.applications.severin.baron.syncup.Dagger;

import android.app.Application;
import android.content.Context;

import com.applications.severin.baron.syncup.Database.LocalDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by erikrudie on 11/18/16.
 */

@Module
public class ApplicationModule {

  private Application application;
  private LocalDbHelper localHelper;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  public Context provideContext() {
    return application;
  }

  @Provides
  @Singleton
  public LocalDbHelper providesLocalDbHelper() {return localHelper;}

}
