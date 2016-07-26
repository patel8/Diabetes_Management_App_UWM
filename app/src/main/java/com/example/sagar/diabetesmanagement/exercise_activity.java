package com.example.sagar.diabetesmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class exercise_activity extends AppCompatActivity {

    private TextView DateView;
    private TextView TimeView;
    private DatePicker datePicker;
    private Calendar calendar;
    private final int DATE_PICKER = 0;
    private final int TIME_PICKER = 1;
    private TextView currentView;
    private int year, month, day;
    private int hours, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_insert_info);
        setTimeAndDate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //This will set the current time and date to view.
        setTimeAndDate();



    }




    // This method should get called on OnCreate() and OnStart() in order to set current date and time.
    public void setTimeAndDate()
    {

        //This is to initialize the Date and Time with initial Date.
        DateView = (TextView) findViewById(R.id.dateView);

        TimeView = (TextView) findViewById(R.id.timeView);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String hour = dateFormat.format(new Date());
        TimeView.setText(hour);
        showDate(year, month+1, day);

    }


    // Onclick Listener for Set Date
    public void setDate(View view) {
        showDialog(R.id.dateView);
        //currentView = (TextView) view;
    }

    //OnClick Liseter for Set Time
    public void setTime(View view)
    {

        showDialog(R.id.timeView);
        //currentView = (TextView) view;
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == R.id.dateView) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        else {
            if (id == R.id.timeView) {
                return new TimePickerDialog(this, timePickerListener,hours,minute,false);
            }
        }
        return null;
    }




    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    showTime(selectedHour, selectedMinute);
                }
            };


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2+1, arg3);
        }
    };


    private void showDate(int year, int month, int day) {
        DateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    private void showTime(int hour, int minute)
    {
        TimeView.setText(new StringBuilder().append(hour).append(" : ").append(minute));
    }
}
