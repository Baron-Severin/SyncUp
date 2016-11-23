package com.applications.severin.baron.syncup.DataModels;

import java.util.Map;
import java.util.Set;

/**
 * Created by erikrudie on 11/22/16.
 */

public class UserUpdate {

  private final Map<Integer, Set<Integer>> userToUpdatedContent;

  public UserUpdate(Map<Integer, Set<Integer>> userToUpdatedContent) {
    this.userToUpdatedContent = userToUpdatedContent;
  }

  public Map<Integer, Set<Integer>> getUserToUpdatedContent() {
    return userToUpdatedContent;
  }
}
