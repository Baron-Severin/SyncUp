package com.applications.severin.baron.syncup.Models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by erikrudie on 11/22/16.
 */

public class Updates {

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({PICTURE, TEXT})
  public @interface ContentUpdated {}

  public static final int PICTURE = 0;
  public static final int TEXT = 1;

  List<EventUpdate> eventUpdates;
  List<UserUpdate> userUpdates;

}
