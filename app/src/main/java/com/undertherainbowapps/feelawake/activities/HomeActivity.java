package com.undertherainbowapps.feelawake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.undertherainbowapps.feelawake.R;

public class HomeActivity extends AppCompatActivity {

  private FirebaseAuth mAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    mAuth = FirebaseAuth.getInstance();
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (mAuth.getCurrentUser() == null) {
      signOut();
    }
  }

  public void signOut() {
    mAuth.signOut();
    logoutFacebook();
    super.onBackPressed();
  }

  public void logoutFacebook() {
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
    if (isLoggedIn)
      LoginManager.getInstance().logOut();
  }

  @Override
  public void onBackPressed() {
    signOut();
  }
}
