package com.undertherainbowapps.feelawake.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;

import android.widget.TimePicker;
import com.undertherainbowapps.feelawake.R;
import com.undertherainbowapps.feelawake.activities.HomeActivity;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by user on 30/05/18.
 */

public class HomeView extends RelativeLayout {

  private Button pickTime;
  private Button pickDate;
  private Button logoutBtn;
  private Button saveAwakeLogBtn;
  private EditText percentageInputTv;

  private TimePicker timePicker;
  private DatePicker datePicker;

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
    this.saveAwakeLogBtn = findViewById(R.id.saveAwakeLogBtn);
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

    saveAwakeLogBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ((HomeActivity) getContext()).saveAwakeLog();
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
    View view = inflateView(R.layout.time_picker);
    timePicker = view.findViewById(R.id.time_picker);
    showDialog(view);
  }


  private void showDatePicker() {
    View view = inflateView(R.layout.date_picker);
    datePicker = view.findViewById(R.id.date_picker);
    showDialog(view);
  }

  private View inflateView(int layoutId) {
    Activity activity = (Activity) this.getContext();
    LayoutInflater inflater = activity.getLayoutInflater();
    return inflater.inflate(layoutId, null);
  }

  private void showDialog(View view) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

    builder.setCancelable(false);
    builder.setView(view)
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

  public String getSelectedTime() {
    if (timePicker == null) {
      Calendar rightNow = Calendar.getInstance();
      int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
      int currentMinute = rightNow.get(Calendar.MINUTE);
      return currentHour + ":" + currentMinute;
    }
    return timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
  }

  public String getSelectedDate() {
    if (datePicker == null) {
      Calendar rightNow = Calendar.getInstance();
      int currentYear = rightNow.get(Calendar.YEAR);
      int currentMonth = rightNow.get(Calendar.MONTH) + 1;
      int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
      return currentYear + ":" + currentMonth + ":" + currentDay;
    }
    return datePicker.getYear() + ":" + (datePicker.getMonth()+1) + ":" + datePicker.getDayOfMonth();
  }
}
