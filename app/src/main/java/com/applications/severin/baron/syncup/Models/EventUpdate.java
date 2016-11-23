package com.applications.severin.baron.syncup.Models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by erikrudie on 11/22/16.
 */

public class EventUpdate {

  private final Map<Long, Set<Integer>> eventToUpdatedContent;

  public EventUpdate(Map<Long, Set<Integer>> eventToUpdatedContent) {
    this.eventToUpdatedContent = eventToUpdatedContent;
  }

  public Map<Long, Set<Integer>> getEventToUpdatedContent() {
    return eventToUpdatedContent;
  }
}
