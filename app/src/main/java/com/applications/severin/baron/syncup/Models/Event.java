package com.applications.severin.baron.syncup.Models;

import android.graphics.Bitmap;

import java.util.List;
import java.util.UUID;

/**
 * Created by erikrudie on 11/20/16.
 */

public class Event {

  private long eventId;
  private String name;
  private String location;
  private Bitmap pictureMedium;
  private Bitmap pictureSmall;
  private long fromTime;
  private long toTime;
  private List<Note> notes;
  private List<Invitation> invitations;


}

// TODO: 11/22/16 set times in miliseconds, then convert to local time zone 
