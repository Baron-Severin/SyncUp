package com.applications.severin.baron.syncup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity
  implements GoogleApiClient.OnConnectionFailedListener,
  View.OnClickListener{

  GoogleApiClient mGoogleApiClient;

  private static final String TAG = "LoginActivity";
  private static final int RC_SIGN_IN = 9001;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    setUpGoogleSignIn();
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
