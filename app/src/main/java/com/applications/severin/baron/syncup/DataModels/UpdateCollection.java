package com.applications.severin.baron.syncup.DataModels;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by erikrudie on 11/22/16.
 */

public class UpdateCollection {

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({PICTURE, TEXT})
  public @interface ContentUpdated {}

  public static final int PICTURE = 0;
  public static final int TEXT = 1;

  private final List<EventUpdate> eventUpdates;
  private final List<UserUpdate> userUpdates;

  public UpdateCollection(List<EventUpdate> eventUpdates, List<UserUpdate> userUpdates) {
    this.eventUpdates = eventUpdates;
    this.userUpdates = userUpdates;
  }

  public List<UserUpdate> getUserUpdates() {
    return userUpdates;
  }

  public List<EventUpdate> getEventUpdates() {
    return eventUpdates;
  }
}
