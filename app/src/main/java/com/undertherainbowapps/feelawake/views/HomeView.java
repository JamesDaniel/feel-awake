package com.undertherainbowapps.feelawake.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.undertherainbowapps.feelawake.R;
import com.undertherainbowapps.feelawake.activities.HomeActivity;

/**
 * Created by user on 30/05/18.
 */

public class HomeView extends RelativeLayout {

    private Button pickTime;
    private Button pickDate;
    private Button logoutBtn;

    public HomeView(Context context) {
        super(context);
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.pickTime = findViewById(R.id.pick_time);
        this.pickDate = findViewById(R.id.pick_date);
        this.logoutBtn = findViewById(R.id.logoutBtn);

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getContext()).signOut();
            }
        });
    }

    private void showTimePicker() {
        showDialog(R.layout.time_picker);
    }

    private void showDatePicker() {
        showDialog(R.layout.date_picker);
    }

    private void showDialog(int layout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Activity activity = (Activity) this.getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setCancelable(false);
        builder.setView(inflater.inflate(layout, null))
                .setPositiveButton(getContext().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(getContext().getString(R.string.cancel), null);
        builder.create();
        builder.show();
    }
}
