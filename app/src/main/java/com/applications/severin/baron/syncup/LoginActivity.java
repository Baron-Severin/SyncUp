package com.applications.severin.baron.syncup;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.severin.baron.syncup.Dagger.App;
import com.applications.severin.baron.syncup.DataModels.Event;
import com.applications.severin.baron.syncup.DataModels.Invitation;
import com.applications.severin.baron.syncup.DataModels.Note;
import com.applications.severin.baron.syncup.Database.LocalDbHelper;
import com.applications.severin.baron.syncup.Utility.BitmapScaler;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity
  implements GoogleApiClient.OnConnectionFailedListener,
  View.OnClickListener{

  GoogleApiClient mGoogleApiClient;

  private static final String TAG = "LoginActivity";
  private static final int RC_SIGN_IN = 9001;

  @BindView(R.id.imageview_med)
  ImageView imageViewMed;
  @BindView(R.id.imageview_small)
  ImageView imageViewSmall;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    ButterKnife.setDebug(true);
    ButterKnife.bind(this);

    setUpGoogleSignIn();

    ((App) getApplication()).getApplicationComponent().inject(this);

    // TODO: 11/24/2016 BEGIN TEMP CODE

    LocalDbHelper helper = new LocalDbHelper(this);
    SQLiteDatabase db = helper.getWritableDatabase();
    helper.dropAllTables(db);
    helper.onCreate(db);
    db.close();
    // Set up sample Event
    long eventId = 1;
    int ownerId = 2;
    String name = "Fred";
    String location = "Seattle";
    Bitmap pictureMedium = BitmapFactory.decodeResource(getResources(), R.drawable.cookie_small);
    pictureMedium = BitmapScaler.scaleToSizeMedium(pictureMedium);
    Bitmap pictureSmall = BitmapFactory.decodeResource(getResources(), R.drawable.cookie_small);
    pictureSmall = BitmapScaler.scaleToSizeSmall(pictureSmall);

    int medSize = pictureMedium.getByteCount();
    int smallSize = pictureSmall.getByteCount();

    imageViewMed.setImageBitmap(pictureMedium);
    imageViewSmall.setImageBitmap(pictureSmall);

    long fromTime = 3;
    long toTime = 4;
    List<Note> notes = new ArrayList<>();
    notes.add(new Note(eventId, ownerId, "my note"));
    List<Invitation> invitations = new ArrayList<>();
    invitations.add(Invitation.inflateInvitationFromDb(1, eventId, ownerId, Invitation.INVITED));

    Event testEvent = new Event.EventBuilder().setEventId(eventId).setOwnerId(ownerId)
            .setName(name).setLocation(location).setPictureMedium(pictureMedium)
            .setPictureSmall(pictureSmall).setFromTime(fromTime).setToTime(toTime).setNotes(notes)
            .setInvitations(invitations).build();
//    helper.saveEvent(testEvent);
//    Event retrievedEvent = helper.getEvent(testEvent.getEventId());
    System.out.println("");
    // TODO: 11/24/2016 END TEST CODE


  }

  private void setUpGoogleSignIn(){
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestEmail()
      .build();
    mGoogleApiClient = new GoogleApiClient.Builder(this)
      .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
      .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
      .build();
    findViewById(R.id.sign_in_button).setOnClickListener(this);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleSignInResult(result);
    }
  }

  private void handleSignInResult(GoogleSignInResult result) {
    Log.d(TAG, "handleSignInResult:" + result.isSuccess());
    if (result.isSuccess()) {
      GoogleSignInAccount acct = result.getSignInAccount();
//    TODO:  acct.getEmail();

      ((TextView) findViewById(R.id.textView)).setText(acct.getEmail());

//      mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//      updateUI(true);
    } else {
      // Signed out, show unauthenticated UI.
//      updateUI(false);
      ((TextView) findViewById(R.id.textView)).setText("Failed");
    }
  }

  private void signIn() {
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.sign_in_button:
        signIn();
        break;
    }
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Log.d(TAG, "onConnectionFailed:" + connectionResult);
  }
}
