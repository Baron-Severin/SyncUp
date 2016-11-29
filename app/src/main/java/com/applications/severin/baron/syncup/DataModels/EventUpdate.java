package com.applications.severin.baron.syncup.DataModels;

import java.util.Map;
import java.util.Set;

/**
 * Created by erikrudie on 11/22/16.
 */

public class EventUpdate {

  private long eventId;
  private Set<Integer> updated;

  public EventUpdate(long eventId, Set<Integer> updated) {
    this.eventId = eventId;
    this.updated = updated;
  }

  public long getEventId() {
    return eventId;
  }

  public Set<Integer> getUpdated() {
    return updated;
  }
}

// How am I going to deal with multiple users pulling down updates at 1:00,
// then making updates at 1:05? Force them to pull down constantly before
// making any changes? Recording the last pull, then splitting any duplicates
// into multiple notes?
