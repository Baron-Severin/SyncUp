package com.applications.severin.baron.syncup.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.database.SQLException;

import com.applications.severin.baron.syncup.DataModels.Event;
import com.applications.severin.baron.syncup.DataModels.Invitation;
import com.applications.severin.baron.syncup.DataModels.Note;
import com.applications.severin.baron.syncup.DataModels.User;
import com.applications.severin.baron.syncup.Utility.PictureTextConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erikrudie on 11/20/16.
 */

public class LocalDbHelper extends SQLiteOpenHelper implements DatabaseContract {

  private static final String DATABASE_NAME = "SYNCUP_LOCAL";
  private static final int DATABASE_VERSION = 1;

  private static LocalDbHelper DB;

  private static boolean dbTest;

  public static LocalDbHelper getInstance(Context context) {
    if (DB == null) {
      DB = new LocalDbHelper(context);
    }
    dbTest = false;
    return DB;
  }

  public static LocalDbHelper getInstance(Context context, boolean testMode) {
    if (DB == null) {
      DB = new LocalDbHelper(context, testMode);
    }
    dbTest = true;
    return DB;
  }

  private LocalDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  private LocalDbHelper(Context context, boolean testMode) {
    super(context, null, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    createAllTables(db);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    this.onCreate(db);
    // TODO: handle this gracefully
  }

  private boolean createAllTables(SQLiteDatabase db) {
    db.execSQL(PH.createStringTableEvent);
    db.execSQL(PH.createStringTableInvitation);
    db.execSQL(PH.createStringTableNote);
    db.execSQL(PH.createStringTableUser);
    return true;
  }

  public boolean dropAllTables(SQLiteDatabase db) {
    String sql = "DROP TABLE IF EXISTS " + PH.TABLE_EVENT;
    db.execSQL(sql);
    sql = "DROP TABLE IF EXISTS " + PH.TABLE_INVITATION;
    db.execSQL(sql);
    sql = "DROP TABLE IF EXISTS " + PH.TABLE_NOTE;
    db.execSQL(sql);
    sql = "DROP TABLE IF EXISTS " + PH.TABLE_USER;
    db.execSQL(sql);
    return true;
  }


  @Override
  public boolean saveEvent(Event event) {
    SQLiteDatabase db = this.getWritableDatabase();

    saveEventDetails(db, event);
    for (Invitation invitation : event.getInvitations()) {
      saveInvitationDetails(db, invitation);
    }
    for (Note note : event.getNotes()) {
      saveNoteDetails(db, note);
    }

    if (!dbTest) {
      db.close();
    }
    return true;
  }

  // TODO: 11/24/2016 this save fails on the in memory db.  seems to work fine with the normal db
  private void saveEventDetails(SQLiteDatabase db, Event event) {
    String picStringMed, picStringSmall;
    if (event.getPictureMedium() != null && event.getPictureSmall() != null) {
      picStringMed = PictureTextConverter.bitmapToString(event.getPictureMedium());
      picStringSmall = PictureTextConverter.bitmapToString(event.getPictureSmall());
    } else {
      picStringMed = "";
      picStringSmall = "";
    }
    String sql = "INSERT OR REPLACE INTO " + PH.TABLE_EVENT + " (" +
      PH.EVENT_ID + ", " +
      PH.OWNER_ID + ", " +
      PH.NAME + ", " +
      PH.PICTURE_MEDIUM + ", " +
      PH.PICTURE_SMALL + ", " +
      PH.LOCATION + ", " +
      PH.FROM_TIME + ", " +
      PH.TO_TIME +
      ") VALUES ('" +
      event.getEventId() + "', '" +
      event.getOwnerId() + "', '" +
      event.getName() + "', '" +
      picStringMed + "', '" +
      picStringSmall + "', '" +
      event.getLocation() + "', '" +
      event.getFromTime() + "', '" +
      event.getToTime() + "');";

    try {
      db.execSQL(sql);
    } catch (SQLException e) {
      System.out.println("SEVTEST");
      e.printStackTrace();
    }
    System.out.println("");
  }

  private void saveNoteDetails(SQLiteDatabase db, Note note) {
    String sql = "INSERT OR REPLACE INTO " + PH.TABLE_NOTE + " (" +
      PH.NOTE_ID + ", " +
      PH.EVENT_ID + ", " +
      PH.USER_PHONE + ", " +
      PH.NOTE_CONTENT +
      ") VALUES ('" +
      note.getNoteId() + "', '" +
      note.getEventId() + "', '" +
      note.getUserPhone() + "', '" +
      note.getContent() + "');";

    db.execSQL(sql);
  }

  private void saveInvitationDetails(SQLiteDatabase db, Invitation invitation) {
    String sql = "INSERT OR REPLACE INTO " + PH.TABLE_INVITATION + " (" +
      PH.INVITATION_ID + ", " +
      PH.EVENT_ID + ", " +
      PH.INVITATION_STATUS + ", " +
      PH.USER_PHONE +
      ") VALUES ('" +
      invitation.getInvitationId() + "', '" +
      invitation.getEventId() + "', '" +
      invitation.getStatus() + "', '" +
      invitation.getUserId() + "');";

    db.execSQL(sql);
  }

  @Override
  public boolean deleteEvent(Event event) {
    return false;
  }

  @Override
  public Event getEvent(long eventId) {
    SQLiteDatabase db = this.getReadableDatabase();

    List<Invitation> invitations = getInvitationsByEventId(db, eventId);
    List<Note> notes = getNotesByEventId(db, eventId);
    Event event = getEventByEventId(db, eventId, notes, invitations);
    db.close();
    return event;
  }

  private Event getEventByEventId(SQLiteDatabase db, long eventId,
                                  List<Note> notes, List<Invitation> invitations) {
    String sql = "SELECT *" +
            " FROM " + PH.TABLE_EVENT +
            " WHERE " + PH.EVENT_ID + " = '" + eventId + "';";
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    int ownerId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.OWNER_ID));
    String name = cursor.getString(cursor.getColumnIndexOrThrow(PH.NAME));
    String location = cursor.getString(cursor.getColumnIndexOrThrow(PH.LOCATION));
    Bitmap pictureMedium = PictureTextConverter.stringToBitmap(
            cursor.getString(cursor.getColumnIndexOrThrow(PH.PICTURE_MEDIUM)));
    Bitmap pictureSmall = PictureTextConverter.stringToBitmap(
            cursor.getString(cursor.getColumnIndexOrThrow(PH.PICTURE_SMALL)));
    long fromTime = cursor.getLong(cursor.getColumnIndexOrThrow(PH.FROM_TIME));
    long toTime = cursor.getLong(cursor.getColumnIndexOrThrow(PH.TO_TIME));
    cursor.close();

    Event event = new Event.EventBuilder().setEventId(eventId).setOwnerId(ownerId).setName(name)
            .setLocation(location).setPictureMedium(pictureMedium).setPictureSmall(pictureSmall)
            .setFromTime(fromTime).setToTime(toTime).setNotes(notes).setInvitations(invitations)
            .build();
    return event;
  }

  private List<Note> getNotesByEventId(SQLiteDatabase db, long eventId) {
    List<Note> notes = new ArrayList<>();
    String sql = "SELECT *" +
            " FROM " + PH.TABLE_NOTE +
            " WHERE " + PH.EVENT_ID + " = '" + eventId + "';";
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      long noteId = cursor.getLong(cursor.getColumnIndexOrThrow(PH.NOTE_ID));
      int userPhone = cursor.getInt(cursor.getColumnIndexOrThrow(PH.USER_PHONE));
      String content = cursor.getString(cursor.getColumnIndexOrThrow(PH.NOTE_CONTENT));
      Note note = Note.inflateNoteFromDb(noteId, eventId, userPhone, content);
      notes.add(note);
      cursor.moveToNext();
    }
    cursor.close();
    return notes;
  }

  private List<Invitation> getInvitationsByEventId(SQLiteDatabase db, long eventId) {
    List<Invitation> invitations = new ArrayList<>();
    String sql = "SELECT *" +
            " FROM " + PH.TABLE_INVITATION +
            " WHERE " + PH.EVENT_ID + " = '" + eventId + "';";
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      long invitationId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.INVITATION_ID));
      int userId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.USER_PHONE));
      @Invitation.InvitationStatus int status = cursor.getInt(cursor.getColumnIndexOrThrow(PH.INVITATION_STATUS));
      Invitation invitation = Invitation.inflateInvitationFromDb(invitationId, eventId, userId, status);
      invitations.add(invitation);
      cursor.moveToNext();
    }
    cursor.close();
    return invitations;
  }

  @Override
  public boolean saveUser(User user) {
    return false;
  }

  @Override
  public User getUser(int userId) {
    return null;
  }
}
