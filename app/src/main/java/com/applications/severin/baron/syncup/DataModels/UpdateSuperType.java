package com.applications.severin.baron.syncup.DataModels;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by erikrudie on 11/22/16.
 */

public abstract class UpdateSuperType {

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({PICTURE, TEXT})
  public @interface ContentUpdated {}

  public static final Integer PICTURE = 0;
  public static final Integer TEXT = 1;

}
