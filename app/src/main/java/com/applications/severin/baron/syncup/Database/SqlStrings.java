package com.applications.severin.baron.syncup.Database;

/**
 * Created by erikrudie on 11/20/16.
 */

public class SqlStrings {

   static final String TABLE_EVENT = "table_event";
   static final String EVENT_ID = "event_id";
   static final String NAME = "name";
   static final String PICTURE_MEDIUM = "picture_medium";
   static final String LOCATION = "location";
   static final String FROM_TIME = "from_time";
   static final String TO_TIME = "to_time";

   static final String TABLE_NOTE = "table_note";
   static final String NOTE_ID = "note_id";
   static final String NOTE = "note";

   static final String TABLE_INVITATION = "table_invitation";
   static final String INVITATION_ID = "invitation_id";
   static final String USER_PHONE = "user_phone";

   static final String TABLE_USER = "table_user";
   static final String DISPLAY_NAME = "display_name";
   static final String PICTURE_SMALL = "picture_small";


  static final String createStringTableEvent =
    "CREATE TABLE IF NOT EXISTS " + TABLE_EVENT + " (" +
      EVENT_ID + " INTEGER PRIMARY KEY, " +
      NAME + " TEXT, " +
      PICTURE_MEDIUM + " TEXT, " +
      PICTURE_SMALL + " TEXT, " +
      LOCATION + " TEXT, " +
      FROM_TIME + " TEXT, " +
      TO_TIME + " TEXT" +
      ");";

  static final String createStringTableNote =
    "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE + " (" +
      NOTE_ID + " INTEGER PRIMARY KEY, " +
      EVENT_ID + " Integer, " +
      NOTE + " TEXT" +
      ");";

  static final String createStringTableInvitation =
    "CREATE TABLE IF NOT EXISTS " + TABLE_INVITATION + " (" +
      INVITATION_ID + " INTEGER PRIMARY KEY, " +
      EVENT_ID + " INTEGER, " +
      USER_PHONE + " INTEGER" +
      ");";

  static final String createStringTableUser =
    "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
      USER_PHONE + " INTEGER PRIMARY KEY, " +
      DISPLAY_NAME + " TEXT, " +
      PICTURE_SMALL + " TEXT" +
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