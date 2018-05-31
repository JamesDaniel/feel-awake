package com.undertherainbowapps.feelawake.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.undertherainbowapps.feelawake.R;
import com.undertherainbowapps.feelawake.activities.LoginActivity;

/**
 * Created by user on 30/05/18.
 */

public class LoginView extends RelativeLayout {

    private TextView emailTv;
    private TextView passwordTv;
    private Button loginBtn;
    private Button registerBtn;

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
        this.emailTv = findViewById(R.id.emailTv);
        this.passwordTv = findViewById(R.id.passwordTv);
        this.loginBtn = findViewById(R.id.loginBtn);
        this.registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput())
                    ((LoginActivity) getContext()).login();
            }
        });
        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput())
                    ((LoginActivity) getContext()).register();
            }
        });
    }

    private boolean validateInput() {
        if (emailTv.getText().toString().isEmpty() ||
                passwordTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), getContext().getString(R.string.invalid_login_input),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
