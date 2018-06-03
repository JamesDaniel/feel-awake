package com.undertherainbowapps.feelawake.activities;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.undertherainbowapps.feelawake.R;
import com.undertherainbowapps.feelawake.views.LoginView;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

  private static final String TAG = "LoginActivity";
  private FirebaseAuth mAuth;
  private LoginView loginView;

  private CallbackManager callbackManager;
  private LoginButton loginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    this.loginView = findViewById(R.id.login_view);

    this.mAuth = FirebaseAuth.getInstance();
    initFacebookLogin();
  }

  public void initFacebookLogin() {
    callbackManager = CallbackManager.Factory.create();
    loginButton = findViewById(R.id.login_button);
    loginButton.setReadPermissions(Arrays.asList(EMAIL, "public_profile"));
    callbackManager = CallbackManager.Factory.create();

    LoginManager.getInstance().registerCallback(callbackManager,
        new FacebookCallback<LoginResult>() {
          @Override
          public void onSuccess(LoginResult loginResult) {
            Log.d(TAG, "onSuccess");
            handleFacebookAccessToken(loginResult.getAccessToken());
          }

          @Override
          public void onCancel() {
            Log.d(TAG, "onCancel");
          }

          @Override
          public void onError(FacebookException exception) {
            Log.d(TAG, "onError");
          }
        });
  }

  @Override
  protected void onStart() {
    super.onStart();
    checkLogin();
  }

  private void checkLogin() {
    FirebaseUser user = mAuth.getCurrentUser();
    if (user != null) {
      startHomeActivity();
    }
  }

  public void login() {
    mAuth.signInWithEmailAndPassword(loginView.getEmailInput(), loginView.getPasswordInput())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              Log.d(TAG, "signInWithEmail:success");
              startHomeActivity();
            } else {
              Log.w(TAG, "signInWithEmail:failure", task.getException());
              Toast.makeText(LoginActivity.this, getString(R.string.auth_fail),
                  Toast.LENGTH_SHORT).show();
            }
          }
        });
  }

  public void register() {
    mAuth.createUserWithEmailAndPassword(loginView.getEmailInput(), loginView.getPasswordInput())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              Log.d(TAG, "createUserWithEmail:success");
              startHomeActivity();
            } else {
              Log.w(TAG, "createUserWithEmail:failure", task.getException());
              Toast.makeText(LoginActivity.this, getString(R.string.registration_fail),
                  Toast.LENGTH_SHORT).show();
            }
          }
        });
  }

  public void resetPassword() {
    FirebaseAuth.getInstance().sendPasswordResetEmail(loginView.getEmailInput())
        .addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
              Log.d(TAG, getString(R.string.email_sent));
              Toast.makeText(LoginActivity.this, getString(R.string.email_sent),
                  Toast.LENGTH_SHORT).show();
            } else {
              Log.w(TAG, "createUserWithEmail:failure", task.getException());
              Toast.makeText(LoginActivity.this, getString(R.string.password_reset_fail),
                  Toast.LENGTH_SHORT).show();
            }
          }
        });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    callbackManager.onActivityResult(requestCode, resultCode, data);
    super.onActivityResult(requestCode, resultCode, data);
  }

  private void startHomeActivity() {
    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
    startActivity(intent);
  }

  private void handleFacebookAccessToken(AccessToken token) {
    Log.d(TAG, "handleFacebookAccessToken:" + token);

    AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              Log.d(TAG, "signInWithCredential:success");
              startHomeActivity();
            } else {
              Log.w(TAG, "signInWithCredential:failure", task.getException());
              Toast.makeText(LoginActivity.this, getString(R.string.auth_fail),
                  Toast.LENGTH_SHORT).show();
            }
          }
        });
  }
}
