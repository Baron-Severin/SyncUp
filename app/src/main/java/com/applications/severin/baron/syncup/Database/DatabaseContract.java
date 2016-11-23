package com.applications.severin.baron.syncup.Database;

import com.applications.severin.baron.syncup.DataModels.Event;
import com.applications.severin.baron.syncup.DataModels.User;

/**
 * Created by erikrudie on 11/20/16.
 */

public interface DatabaseContract {

  void saveEvent(Event event);
  void deleteEvent(Event event);
  Event getEvent(long eventId);

  void saveUser(User user);
  User getUser(int userId);
}
