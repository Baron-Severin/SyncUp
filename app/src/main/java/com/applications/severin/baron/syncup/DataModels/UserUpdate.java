package com.applications.severin.baron.syncup.DataModels;

import java.util.Map;
import java.util.Set;

/**
 * Created by erikrudie on 11/22/16.
 */

public class UserUpdate extends UpdateSuperType {

  private final int userId;
  private final Set<Integer> updated;

  public UserUpdate(int userId, Set<Integer> updated) {
    this.userId = userId;
    this.updated = updated;
  }

  public int getUserId() {
    return userId;
  }

  public Set<Integer> getUpdated() {
    return updated;
  }
}
