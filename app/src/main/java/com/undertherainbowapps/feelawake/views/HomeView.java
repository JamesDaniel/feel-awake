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

/**
 * Created by user on 30/05/18.
 */

public class HomeView extends RelativeLayout {

    private Button pickTime;
    private Button pickDate;

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

    }

    private void showTimePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Activity activity = (Activity) this.getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setCancelable(false);
        builder.setView(inflater.inflate(R.layout.time_picker, null))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
    }

    private void showDatePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Activity activity = (Activity) this.getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setCancelable(false);
        builder.setView(inflater.inflate(R.layout.date_picker, null))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
    }
}
