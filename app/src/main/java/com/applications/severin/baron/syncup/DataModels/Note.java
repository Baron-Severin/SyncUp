package com.applications.severin.baron.syncup.DataModels;

import java.util.UUID;

/**
 * Created by erikrudie on 11/22/16.
 */

public class Note {

  private long noteId;
  private long eventId;
  private int userPhone;
  private String content;

  public Note(long eventId, int userPhone, String content) {
    this.noteId = (long) (System.currentTimeMillis() * Math.random());
    this.eventId = eventId;
    this.userPhone = userPhone;
    this.content = content;
  }

  private Note(long noteId, long eventId, int userPhone, String content) {
    this.noteId = noteId;
    this.eventId = eventId;
    this.userPhone = userPhone;
    this.content = content;
  }

  public Note inflateNoteFromDb(long noteId, long eventId, int userPhone, String content) {
    return new Note(noteId, eventId, userPhone, content);
  }

  public long getNoteId() {
    return noteId;
  }

  public long getEventId() {
    return eventId;
  }

  public int getUserPhone() {
    return userPhone;
  }

  public String getContent() {
    return content;
  }
}

