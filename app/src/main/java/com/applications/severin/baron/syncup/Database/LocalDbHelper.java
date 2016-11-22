package com.applications.severin.baron.syncup.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erikrudie on 11/20/16.
 */

public class LocalDbHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "SYNCUP_LOCAL";
  private static final int DATABASE_VERSION = 1;

  public LocalDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    db.execSQL(SqlStrings.createStringTableEvent);
    db.execSQL(SqlStrings.createStringTableInvitation);
    db.execSQL(SqlStrings.createStringTableNote);
    db.execSQL(SqlStrings.createStringTableUser);
    return true;
  }

  private boolean dropAllTables(SQLiteDatabase db) {
    String sql = "DROP TABLE " + SqlStrings.TABLE_EVENT;
    db.execSQL(sql);
    sql = "DROP TABLE " + SqlStrings.TABLE_INVITATION;
    db.execSQL(sql);
    sql = "DROP TABLE " + SqlStrings.TABLE_NOTE;
    db.execSQL(sql);
    sql = "DROP TABLE " + SqlStrings.TABLE_USER;
    db.execSQL(sql);
    return true;
  }


}
