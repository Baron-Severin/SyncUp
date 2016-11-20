package com.applications.severin.baron.syncup.Dagger;

import com.applications.severin.baron.syncup.EventListActivity;
import com.applications.severin.baron.syncup.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by erikrudie on 11/18/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(LoginActivity target);
  void inject(EventListActivity target);


}
