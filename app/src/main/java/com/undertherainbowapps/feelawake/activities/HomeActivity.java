package com.undertherainbowapps.feelawake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        if (mAuth.getCurrentUser() == null)
            signOut();
    }

    public void signOut() {
        mAuth.signOut();
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        signOut();
    }
}
