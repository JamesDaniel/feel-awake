package com.undertherainbowapps.feelawake.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.undertherainbowapps.feelawake.R;
import com.undertherainbowapps.feelawake.models.FeelAwakeLog;
import com.undertherainbowapps.feelawake.models.User;
import com.undertherainbowapps.feelawake.views.HomeView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = "HomeActivity";

  private FirebaseAuth mAuth;

  private HomeView homeView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    mAuth = FirebaseAuth.getInstance();

    homeView = findViewById(R.id.home_view);
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (mAuth.getCurrentUser() == null) {
      signOut();
    }
  }

  public void signOut() {
    Log.d(TAG, "signOut");
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

  public void saveAwakeLog() {

    // todo This isn't quite right yet. Need to fix so it only saves a log per user and get
    // todo actual data from date and time pickers.

    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("users");

    User user = new User();
    List<FeelAwakeLog> feelAwakeLogs = new ArrayList<>();
    FeelAwakeLog feelAwakeLog = new FeelAwakeLog();
    feelAwakeLog.setPercentageAwake(100);
    feelAwakeLogs.add(feelAwakeLog);
    user.setFeelAwakeLog(feelAwakeLogs);
    user.setUid(mAuth.getUid());

    users.add(user)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
          @Override
          public void onSuccess(DocumentReference documentReference) {
            Log.d(TAG, "fin");
          }
        })
        .addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            Log.d(TAG, "fin");
          }
        });
  }
}
