package com.undertherainbowapps.feelawake.views;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.undertherainbowapps.feelawake.R;
import com.undertherainbowapps.feelawake.activities.LoginActivity;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 30/05/18.
 */

public class LoginView extends RelativeLayout {

  private Pattern pattern;
  private Matcher matcher;

  private static final String PASSWORD_PATTERN =
      "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";

  private TextView emailTv;
  private TextView passwordTv;
  private Button loginBtn;
  private Button registerBtn;
  private Button resetPasswordBtn;
  private LoginButton loginButton;

  public LoginView(Context context) {
    super(context);
  }

  public LoginView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public String getEmailInput() {
    return emailTv.getText().toString();
  }

  public String getPasswordInput() {
    return passwordTv.getText().toString();
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    this.pattern = Pattern.compile(PASSWORD_PATTERN);
    this.emailTv = findViewById(R.id.emailTv);
    this.passwordTv = findViewById(R.id.passwordTv);
    this.loginBtn = findViewById(R.id.loginBtn);
    this.registerBtn = findViewById(R.id.registerBtn);
    this.resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
    this.loginButton = findViewById(R.id.login_button);
    this.loginButton.setReadPermissions(Arrays.asList(EMAIL, "public_profile"));

    loginBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (isValidEmail(emailTv.getText()) && isValidPassword(
            passwordTv.getText().toString())) {
          ((LoginActivity) getContext()).firebaseLogin();
        } else {
          validationToast(R.string.invalid_login_input);
        }
      }
    });
    registerBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (isValidEmail(emailTv.getText()) && isValidPassword(
            passwordTv.getText().toString())) {
          ((LoginActivity) getContext()).firebaseRegister();
        } else {
          validationToast(R.string.invalid_login_input);
        }
      }
    });
    resetPasswordBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (isValidEmail(emailTv.getText())) {
          ((LoginActivity) getContext()).firebaseResetPassword();
        } else {
          validationToast(R.string.invalid_email);
        }
      }
    });
  }

  private boolean isValidEmail(CharSequence target) {
    if ((!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())) {
      return true;
    }
    return false;
  }

  public void enableButtons(boolean enabled) {
    loginBtn.setEnabled(enabled);
    registerBtn.setEnabled(enabled);
    resetPasswordBtn.setEnabled(enabled);
    loginButton.setEnabled(enabled);
  }

  public boolean isValidPassword(final String password) {
    matcher = pattern.matcher(password);
    if (matcher.matches()) {
      return true;
    }
    return false;
  }

  private void validationToast(int msgResId) {
    Toast.makeText(getContext(), getContext().getString(msgResId),
        Toast.LENGTH_SHORT).show();
  }
}
