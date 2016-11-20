package com.applications.severin.baron.syncup.Database;

/**
 * Created by erikrudie on 11/20/16.
 */

public class SqlStrings {

  public static final String TABLE_EVENT = "table_event";
  public static final String EVENT_ID = "event_id";
  public static final String NAME = "name";
  public static final String PICTURE_MEDIUM = "picture_medium";
  public static final String LOCATION = "location";
  public static final String FROM_TIME = "from_time";
  public static final String TO_TIME = "to_time";

  public static final String TABLE_NOTE = "table_note";
  public static final String NOTE_ID = "note_id";
  public static final String NOTE = "note";

  public static final String TABLE_INVITATION = "table_invitation";
  public static final String INVITATION_ID = "invitation_id";
  public static final String USER_PHONE = "user_phone";

  public static final String TABLE_USER = "table_user";
  public static final String DISPLAY_NAME = "display_name";
  public static final String PICTURE_SMALL = "picture_small";


  public static final String createStringTableEvent =
    "CREATE TABLE IF NOT EXISTS " + TABLE_EVENT + " (" +
      EVENT_ID + " INTEGER, " +
      NAME + " FLOAT, " +
      PICTURE_MEDIUM + " FLOAT, " +
      LOCATION + " FLOAT, " +
      FROM_TIME + " FLOAT, " +
      TO_TIME + " FLOAT" +
      ");";

  public static final String createStringTableNote =
    "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE + " (" +
      NOTE_ID + " INTEGER, " +
      EVENT_ID + " FLOAT, " +
      NOTE + " FLOAT" +
      ");";

  public static final String createStringTableInvitation =
    "CREATE TABLE IF NOT EXISTS " + TABLE_INVITATION + " (" +
      INVITATION_ID + " INTEGER, " +
      EVENT_ID + " FLOAT, " +
      USER_PHONE + " FLOAT" +
      ");";

  public static final String createStringTableUser =
    "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
      USER_PHONE + " INTEGER, " +
      DISPLAY_NAME + " FLOAT, " +
      PICTURE_SMALL + " FLOAT" +
      ");";

}
//
//-TableEvent
//  -EventId : Integer (primary key)
//  -Name : String
//  -PictureMedium : String
//  -Location : String
//  -FromTime : String (time)
//  -ToTime : String (time)

//-TableNote
//  -NoteId : Integer (primary key)
//  -EventId : Integer
//  -Note : String

//-TableInvitation
//  -InvitationId : Integer (primary key)
//  -EventId : Integer
//  -UserPhone : Integer

//-TableUser
//  -UserPhone : Integer (primary key)
//  -DisplayName : String
//  -PictureSmall : String