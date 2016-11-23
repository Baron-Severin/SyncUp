package com.applications.severin.baron.syncup.DataModels;

import android.graphics.Bitmap;

/**
 * Created by erikrudie on 11/22/16.
 */

public class User {

  private final int userId;
  private final Bitmap pictureSmall;
  private final String displayName;
  private final String timezone;

  public User(int userId, Bitmap pictureSmall, String displayName, String timezone) {
    this.userId = userId;
    this.pictureSmall = pictureSmall;
    this.displayName = displayName;
    this.timezone = timezone;
  }

  public int getUserId() {
    return userId;
  }

  public Bitmap getPictureSmall() {
    return pictureSmall;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getTimezone() {
    return timezone;
  }
}
