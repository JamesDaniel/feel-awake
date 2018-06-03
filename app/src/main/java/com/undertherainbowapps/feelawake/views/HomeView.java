package com.undertherainbowapps.feelawake.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
  private EditText percentageInputTv;

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
    this.percentageInputTv = findViewById(R.id.percentageInputTv);

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

    percentageInputTv.addTextChangedListener(new TextWatcher() {
      String strEnteredValPrev;
      String strEnteredValNew;

      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        strEnteredValPrev = percentageInputTv.getText().toString();
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        strEnteredValNew = percentageInputTv.getText().toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (!strEnteredValNew.equals("")) {
          int num = Integer.parseInt(strEnteredValNew);
          if (num > 100) {
            percentageInputTv.setText(strEnteredValPrev);
            percentageInputTv.setSelection(percentageInputTv.getText().length());
          } else if (num == 0) {
            percentageInputTv.setText("");
          }
        }
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
        .setPositiveButton(getContext().getString(R.string.ok),
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {

              }
            })
        .setNegativeButton(getContext().getString(R.string.cancel), null);
    builder.create();
    builder.show();
  }
}
