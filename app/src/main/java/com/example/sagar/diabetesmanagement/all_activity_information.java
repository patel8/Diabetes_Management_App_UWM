package com.example.sagar.diabetesmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class all_activity_information extends AppCompatActivity {

    private TextView DateView;
    private TextView TimeView;
    private DatePicker datePicker;
    private Calendar calendar;
    private final int DATE_PICKER = 0;
    private final int TIME_PICKER = 1;
    private int year, month, day;
    private int hours, minute;
    private LinearLayout DurationLayOut;


    private Button saveButton;
    private EditText value;
    private TextView label;
    private EditText apxCalory;
    private TextView startTime;
    private TextView endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserting_information);
        setTimeAndDate();


        //Initialize all widgets
        saveButton = (Button) findViewById(R.id.saveButton);
        value = (EditText) findViewById(R.id.txtValue);
        label = (TextView) findViewById(R.id.lblLabel);
        apxCalory = (EditText) findViewById(R.id.txtCalories);
        DurationLayOut = (LinearLayout) findViewById(R.id.TimeContainer);


   //DurationLayOut.setVisibility(LinearLayout.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //This will set the current time and date to view.
        setTimeAndDate();

    }

   // This function will handle ON 'SAVE' Button event.
    public void onSaveButtonClick(View view)
    {

        Activity_Information activity = new Activity_Information();

        activity.setDate(DateView.getText().toString());
        activity.setTime(TimeView.getText().toString());
        activity.setLabel(label.getText().toString());
        activity.setValue(value.getText().toString());
        activity.setStartTime(startTime.getText().toString());
        activity.setEndTime(endTime.getText().toString());

        //Send this object to Database to get added.

    }

    // This method should get called on OnCreate() and OnStart() in order to set current date and time.
    public void setTimeAndDate()
    {

        //This is to initialize the Date and Time with initial Date.
        DateView = (TextView) findViewById(R.id.dateView);
        startTime = (TextView) findViewById(R.id.txtStartTime);
        endTime = (TextView) findViewById(R.id.txtEndTime);
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
    }

    //OnClick Liseter for Set Start Time
    public void setStartTime(View view)
    {
        showDialog(R.id.txtStartTime);
    }

    //OnClick Liseter for Set Start Time
    public void setEndTime(View view)
    {
        showDialog(R.id.txtEndTime);
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == R.id.dateView) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        else if (id == R.id.timeView) {
                return new TimePickerDialog(this, timePickerListener,hours,minute,false);
            }
        else if(id == R.id.txtStartTime)
        {
            return new TimePickerDialog(this, StartTimePickerListener,hours,minute,false);

        }
        else if(id == R.id.txtEndTime)
        {
            return new TimePickerDialog(this, EndTimePickerListener, hours, minute, false);
        }
        return null;
    }




    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    showTime(selectedHour, selectedMinute);
                }
            };


    private TimePickerDialog.OnTimeSetListener StartTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    showStartTime(selectedHour, selectedMinute);
                }
            };
    private TimePickerDialog.OnTimeSetListener EndTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    showEndTime(selectedHour, selectedMinute);
                }
            };

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2+1, arg3);
        }
    };


    private void showDate(int year, int month, int day) {
        DateView.setText(new StringBuilder().append(month).append("/").append(day).append("/").append(year));
    }

    private void showTime(int hour, int minute)
    {
        TimeView.setText(new StringBuilder().append(hour).append(" : ").append(minute));
    }

    private void showStartTime(int hour, int minute)
    {
        startTime.setText(new StringBuilder().append(hour).append(" : ").append(minute));
        endTime.setText(startTime.getText().toString());
    }

    private void showEndTime(int hour, int minute)
    {
       endTime.setText(new StringBuilder().append(hour).append(" : ").append(minute));
    }
}
