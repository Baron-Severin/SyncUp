package com.applications.severin.baron.syncup.EventList;

import com.applications.severin.baron.syncup.DataModels.Event;

import java.util.List;

/**
 * Created by erikrudie on 11/20/16.
 */

public interface EventListContract {

  interface View {
    void displayEvents(List<Event> events);
    void removeEventFromDisplay(Event event);
    void addEventToDisplay(Event event);
  }

  interface Presenter {

  }


}
