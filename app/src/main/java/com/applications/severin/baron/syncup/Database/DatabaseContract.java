package com.applications.severin.baron.syncup.Database;

import com.applications.severin.baron.syncup.DataModels.Event;
import com.applications.severin.baron.syncup.DataModels.User;

/**
 * Created by erikrudie on 11/20/16.
 */

public interface DatabaseContract {

  boolean saveEvent(Event event);
  boolean deleteEvent(Event event);
  boolean deleteEvent(long eventId);
  Event getEvent(long eventId);

  boolean saveUser(User user);
  boolean deleteUser(User user);
  boolean deleteUser(int userId);
  User getUser(int userId);
}
