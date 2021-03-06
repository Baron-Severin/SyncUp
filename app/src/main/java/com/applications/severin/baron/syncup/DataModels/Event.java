package com.applications.severin.baron.syncup.DataModels;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by erikrudie on 11/20/16.
 */

public class Event {

  @Nullable private final long eventId;
  private final int ownerId;
  @Nullable private final String name;
  @Nullable private final String location;
  @Nullable private final Bitmap pictureMedium;
  @Nullable private final Bitmap pictureSmall;
  @Nullable private final long fromTime;
  @Nullable private final long toTime;
  @Nullable private final List<Note> notes;
  @Nullable private final List<Invitation> invitations;

  private Event(long eventId, int ownerId, String name, String location, Bitmap pictureMedium,
                Bitmap pictureSmall, long fromTime, long toTime, List<Note> notes,
                List<Invitation> invitations) {
    // Private constructor to prevent standard instantiation
    this.eventId = eventId;
    this.ownerId = ownerId;
    this.name = name;
    this.location = location;
    this.pictureMedium = pictureMedium;
    this.pictureSmall = pictureSmall;
    this.fromTime = fromTime;
    this.toTime = toTime;
    this.notes = notes;
    this.invitations = invitations;
  }

  @Nullable
  public long getEventId() {
    return eventId;
  }

  public int getOwnerId() {
    return ownerId;
  }

  @Nullable
  public String getName() {
    return name;
  }

  @Nullable
  public String getLocation() {
    return location;
  }

  @Nullable
  public Bitmap getPictureMedium() {
    return pictureMedium;
  }

  @Nullable
  public Bitmap getPictureSmall() {
    return pictureSmall;
  }

  @Nullable
  public long getFromTime() {
    return fromTime;
  }

  @Nullable
  public long getToTime() {
    return toTime;
  }

  @Nullable
  public List<Note> getNotes() {
    return notes;
  }

  @Nullable
  public List<Invitation> getInvitations() {
    return invitations;
  }

  public static class EventBuilder {

    private long eventId;
    private int ownerId;
    private String name;
    private String location;
    private Bitmap pictureMedium;
    private Bitmap pictureSmall;
    private long fromTime;
    private long toTime;
    private List<Note> notes;
    private List<Invitation> invitations;

    public EventBuilder() {
    }

    public Event build() {
      Event event = new Event(eventId, ownerId, name, location, pictureMedium, pictureSmall,
        fromTime, toTime, notes, invitations);
      this.eventId = 0;
      this.ownerId = 0;
      this.name = null;
      this.location = null;
      this.pictureMedium = null;
      this.pictureSmall = null;
      this.fromTime = 0;
      this.toTime = 0;
      this.notes = null;
      this.invitations = null;
      return event;
    }

    public EventBuilder setEventId(long eventId) {
      this.eventId = eventId;
      return this;
    }

    public EventBuilder setOwnerId(int ownerId) {
      this.ownerId = ownerId;
      return this;
    }

    public EventBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public EventBuilder setLocation(String location) {
      this.location = location;
      return this;
    }

    public EventBuilder setPictureMedium(Bitmap pictureMedium) {
      this.pictureMedium = pictureMedium;
      return this;
    }

    public EventBuilder setPictureSmall(Bitmap pictureSmall) {
      this.pictureSmall = pictureSmall;
      return this;
    }

    public EventBuilder setFromTime(long fromTime) {
      this.fromTime = fromTime;
      return this;
    }

    public EventBuilder setToTime(long toTime) {
      this.toTime = toTime;
      return this;
    }

    public EventBuilder setNotes(List<Note> notes) {
      this.notes = notes;
      return this;
    }

    public EventBuilder setInvitations(List<Invitation> invitations) {
      this.invitations = invitations;
      return this;
    }
  }
}
