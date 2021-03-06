package com.applications.severin.baron.syncup.DataModels;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by erikrudie on 11/22/16.
 */

public class Invitation {

  private final long invitationId;
  private final long eventId;
  private final int userId;
  private final int status;

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({INVITED, ACCEPTED, DECLINED})
  public  @interface InvitationStatus {}

  public static final int INVITED = 0;
  public static final int ACCEPTED = 1;
  public static final int DECLINED = 2;

  public Invitation(long eventId, int userId, @InvitationStatus int status) {
    this.invitationId = (long) (System.currentTimeMillis() * Math.random());
    this.eventId = eventId;
    this.userId = userId;
    this.status = status;
  }

  private Invitation(long invitationId, long eventId, int userId, @InvitationStatus int status) {
    this.invitationId = invitationId;
    this.eventId = eventId;
    this.userId = userId;
    this.status = status;
  }

  public static Invitation inflateInvitationFromDb(long invitationId, long eventId, int userId, @InvitationStatus int status) {
    return new Invitation(invitationId, eventId, userId, status);
  }

  public long getInvitationId() {
    return invitationId;
  }

  public long getEventId() {
    return eventId;
  }

  public int getUserId() {
    return userId;
  }

  public int getStatus() {
    return status;
  }
}

