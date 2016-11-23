package com.applications.severin.baron.syncup.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.applications.severin.baron.syncup.DataModels.Event;
import com.applications.severin.baron.syncup.DataModels.User;
import com.applications.severin.baron.syncup.Utility.PictureTextConverter;

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
  public void saveEvent(Event event) {
    SQLiteDatabase db = this.getWritableDatabase();
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



    db.close();
  }

//  String sql = "INSERT OR REPLACE INTO " + PH.TABLE_PLAN + " (" +
//    PH.PROJECT_ID + ", " +
//    PH.SHAPE_COORDINATE_STRING + ", " +
//    PH.ROUTE_COORDINATE_STRING +
//    ") VALUES ('" +
//    projectId + "', '" +
//    shapeCoordinateString + "', '" +
//    routeCoordinateString + "');";

  @Override
  public void deleteEvent(Event event) {

  }

  @Override
  public Event getEvent(long eventId) {
    return null;
  }

  @Override
  public void saveUser(User user) {

  }

  @Override
  public User getUser(int userId) {
    return null;
  }
}
