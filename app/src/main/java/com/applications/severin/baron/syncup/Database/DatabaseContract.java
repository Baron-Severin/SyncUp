package com.applications.severin.baron.syncup.Database;

import com.applications.severin.baron.syncup.Models.Event;

/**
 * Created by erikrudie on 11/20/16.
 */

public interface DatabaseContract {

  int SUCCESS = 2;

  void addEvent(Event event);
  void removeEvent(Event event);
  void updateEvent(Event event);

  // TODO: get events

}
