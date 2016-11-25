package com.applications.severin.baron.syncup.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

  public LocalDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public LocalDbHelper(Context context, boolean testMode) {
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

  private boolean dropAllTables(SQLiteDatabase db) {
    String sql = "DROP TABLE " + PH.TABLE_EVENT;
    db.execSQL(sql);
    sql = "DROP TABLE " + PH.TABLE_INVITATION;
    db.execSQL(sql);
    sql = "DROP TABLE " + PH.TABLE_NOTE;
    db.execSQL(sql);
    sql = "DROP TABLE " + PH.TABLE_USER;
    db.execSQL(sql);
    return true;
  }


  @Override
  public boolean saveEvent(Event event) {
    SQLiteDatabase db = this.getWritableDatabase();

    saveEventDetails(db, event);
    for (Note note : event.getNotes()) {
      saveNoteDetails(db, note);
    }
    for (Invitation invitation : event.getInvitations()) {
      saveInvitationDetails(db, invitation);
    }

    db.close();
    return true;
  }

  private void saveEventDetails(SQLiteDatabase db, Event event) {
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
      PictureTextConverter.bitmapToString(event.getPictureMedium()) + "', '" +
      PictureTextConverter.bitmapToString(event.getPictureSmall()) + "', '" +
      event.getLocation() + "', '" +
      event.getFromTime() + "', '" +
      event.getToTime() + "');";

    db.execSQL(sql);
  }

  private void saveNoteDetails(SQLiteDatabase db, Note note) {
    String sql = "INSERT OR REPLACE INTO " + PH.TABLE_NOTE + " (" +
      PH.NOTE_ID + ", " +
      PH.EVENT_ID + ", " +
      PH.NOTE_CONTENT +
      ") VALUES ('" +
      note.getNoteId() + "', '" +
      note.getEventId() + "', '" +
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
  public void deleteEvent(Event event) {

  }

  @Override
  public Event getEvent(long eventId) {
    SQLiteDatabase db = this.getReadableDatabase();

    List<Note> notes = getNotesAttachedToEvent(db, eventId);
    List<Invitation> invitations = getInvitationsAttachedToEvent(db, eventId);
// TODO: 11/22/16 This is where I left off 

  }

  private List<Note> getNotesAttachedToEvent(SQLiteDatabase db, long eventId) {
    List<Note> notes = new ArrayList<>();
    String sql = "SELECT * FROM " + PH.TABLE_NOTE +
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

  private List<Invitation> getInvitationsAttachedToEvent(SQLiteDatabase db, long eventId) {
    List<Invitation> invitations = new ArrayList<>();
    String sql = "SELECT"
  }

//  private long noteId;
//  private long eventId;
//  private int userPhone;
//  private String content;

  @Override
  public void saveUser(User user) {

  }

  @Override
  public User getUser(int userId) {
    return null;
  }
}
