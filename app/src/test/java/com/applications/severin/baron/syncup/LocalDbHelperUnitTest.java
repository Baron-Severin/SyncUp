package com.applications.severin.baron.syncup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.applications.severin.baron.syncup.Database.LocalDbHelper;
import com.applications.severin.baron.syncup.Database.SqlStrings;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertTrue;

/**
 * Created by erikrudie on 11/22/16.
 */

  @RunWith(RobolectricGradleTestRunner.class)
// To use Robolectric you'll need to setup some constants.
// Change it according to your needs.
  @Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
  public class LocalDbHelperUnitTest {

    private Context mContext;
    private LocalDbHelper mHelper;
    private SQLiteDatabase mDb;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setup() {
      mContext = RuntimeEnvironment.application;
      mHelper = new LocalDbHelper(mContext, true);
      mDb = mHelper.getWritableDatabase();
    }

    @After
    public void cleanup() {
      mDb.close();
    }

    @Test
    public void db_shouldBeCreated() {
      // Verify is the DB is opening correctly
      assertTrue("DB didn't open", mDb.isOpen());
    }

    @Test
    public void eventTable_shouldHaveProperColumns() {
      String sql = "SELECT * FROM " + SqlStrings.TABLE_EVENT;
      Cursor cursor = mDb.rawQuery(sql, null);
      String[] columns = cursor.getColumnNames();

      String[] projectColumns = {SqlStrings.EVENT_ID, SqlStrings.OWNER_ID, SqlStrings.NAME,
        SqlStrings.PICTURE_MEDIUM, SqlStrings.PICTURE_SMALL, SqlStrings.LOCATION,
        SqlStrings.FROM_TIME, SqlStrings.TO_TIME};
      for (String column : projectColumns) {
        assertThat("Column not implemented: " + column,
          columns, hasItemInArray(column));
        Assert.assertThat("False positives are a bad thing: ",
          columns, not(hasItemInArray("anUnlikelyColumn")));
      }
      cursor.close();
    }

  @Test
  public void noteTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + SqlStrings.TABLE_NOTE;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {SqlStrings.NOTE_ID, SqlStrings.EVENT_ID, SqlStrings.NOTE_CONTENT};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns, hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns, not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  @Test
  public void invitationTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + SqlStrings.TABLE_INVITATION;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {SqlStrings.INVITATION_ID, SqlStrings.EVENT_ID,
      SqlStrings.USER_PHONE};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns, hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns, not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  @Test
  public void userTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + SqlStrings.TABLE_USER;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {SqlStrings.USER_PHONE, SqlStrings.DISPLAY_NAME,
      SqlStrings.PICTURE_SMALL, SqlStrings.TIME_ZONE};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns, hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns, not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  

}


