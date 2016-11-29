package com.applications.severin.baron.syncup;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Pair;

import com.applications.severin.baron.syncup.DataModels.Event;
import com.applications.severin.baron.syncup.DataModels.Invitation;
import com.applications.severin.baron.syncup.DataModels.Note;
import com.applications.severin.baron.syncup.DataModels.User;
import com.applications.severin.baron.syncup.Database.LocalDbHelper;
import com.applications.severin.baron.syncup.Database.PH;
import com.applications.severin.baron.syncup.Utility.BitmapScaler;

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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by erikrudie on 11/22/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
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
    mHelper = LocalDbHelper.getInstance(mContext, true);
    mDb = mHelper.getWritableDatabase();
  }

  private Event getTestEvent() {
    long eventId = 1;
    int ownerId = 2;
    String name = "Fred";
    String location = "Seattle";
    long fromTime = 3;
    long toTime = 4;
    List<Note> notes = new ArrayList<>();
    notes.add(new Note(eventId, ownerId, "my note"));
    List<Invitation> invitations = new ArrayList<>();
    invitations.add(Invitation.inflateInvitationFromDb(1, eventId, ownerId, Invitation.INVITED));

    return new Event.EventBuilder().setEventId(eventId).setOwnerId(ownerId)
            .setName(name).setLocation(location)
            .setFromTime(fromTime).setToTime(toTime).setNotes(notes)
            .setInvitations(invitations).build();
  }

  private User getTestUser() {
    int userId = 1;
    Bitmap pictureSmall = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cookie_small);
    pictureSmall = BitmapScaler.scaleToSizeSmall(pictureSmall);
    String displayName = "Hugh";
    String timezone = "Pacific";

    return new User(userId, pictureSmall, displayName, timezone);
  }

  @After
  public void cleanup() {
    mDb.close();
  }

  @Test
  public void db_shouldBeCreated() {
    assertTrue("DB didn't open", mDb.isOpen());
  }

  @Test
  public void eventTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + PH.TABLE_EVENT;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {PH.EVENT_ID, PH.OWNER_ID, PH.NAME,
      PH.PICTURE_MEDIUM, PH.PICTURE_SMALL, PH.LOCATION,
      PH.FROM_TIME, PH.TO_TIME};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns,
        hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns,
        not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  @Test
  public void noteTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + PH.TABLE_NOTE;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {PH.NOTE_ID, PH.EVENT_ID, PH.NOTE_CONTENT};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns,
        hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns,
        not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  @Test
  public void invitationTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + PH.TABLE_INVITATION;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {PH.INVITATION_ID, PH.EVENT_ID,
      PH.USER_PHONE};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns,
        hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns,
        not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  @Test
  public void userTable_shouldHaveProperColumns() {
    String sql = "SELECT * FROM " + PH.TABLE_USER;
    Cursor cursor = mDb.rawQuery(sql, null);
    String[] columns = cursor.getColumnNames();

    String[] projectColumns = {PH.USER_PHONE, PH.DISPLAY_NAME,
      PH.PICTURE_SMALL, PH.TIME_ZONE};
    for (String column : projectColumns) {
      assertThat("Column not implemented: " + column,
        columns, hasItemInArray(column));
      Assert.assertThat("False positives are a bad thing: ",
        columns, not(hasItemInArray("anUnlikelyColumn")));
    }
    cursor.close();
  }

  @Test
  public void eventTable_savedAndRetrievedEventsAreIdentical() {
    Event saved = getTestEvent();
    mHelper.saveEvent(saved);
    Event retrieved = mHelper.getEvent(saved.getEventId());

    areEventsEqual(saved, retrieved);
  }

  private void areEventsEqual(Event saved, Event retrieved) {
    assertEquals(saved.getEventId(), retrieved.getEventId());
    assertEquals(saved.getInvitations().get(0).getUserId(), retrieved.getInvitations().get(0).getUserId());
    assertEquals(saved.getFromTime(), retrieved.getFromTime());
    assertEquals(saved.getToTime(), retrieved.getToTime());
    assertEquals(saved.getLocation(), retrieved.getLocation());
    assertEquals(saved.getName(), retrieved.getName());
    assertEquals(saved.getNotes().get(0).getContent(), retrieved.getNotes().get(0).getContent());
    assertEquals(saved.getOwnerId(), retrieved.getOwnerId());
  }

  @Test
  public void userTable_savedAndRetrievedUsersAreIdentical() {
    User saved = getTestUser();
    mHelper.saveUser(saved);
    User retrieved = mHelper.getUser(saved.getUserId());

    areUsersEqual(saved, retrieved);
  }

  private void areUsersEqual(User saved, User retrieved) {
    assertEquals(saved.getUserId(), retrieved.getUserId());
    assertEquals(saved.getDisplayName(), retrieved.getDisplayName());
    assertEquals(saved.getTimezone(), retrieved.getTimezone());
  }

  @Test
  public void deletedEvent_shouldNotExistInDb() {
    Event saved = getTestEvent();
    mHelper.saveEvent(saved);
    Event retrieved = mHelper.getEvent(saved.getEventId());

    areEventsEqual(saved, retrieved);

    mHelper.deleteEvent(saved);
    exceptionRule.expect(CursorIndexOutOfBoundsException.class);

    retrieved = mHelper.getEvent(saved.getEventId());
  }

  @Test
  public void deletedUser_shouldNotExistInDb() {
    User saved = getTestUser();
    mHelper.saveUser(saved);
    User retrieved = mHelper.getUser(saved.getUserId());
    areUsersEqual(saved, retrieved);

    mHelper.deleteUser(saved);
    exceptionRule.expect(CursorIndexOutOfBoundsException.class);
    retrieved = mHelper.getUser(saved.getUserId());
  }
  
}


