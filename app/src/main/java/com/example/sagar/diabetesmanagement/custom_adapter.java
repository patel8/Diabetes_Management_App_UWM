package com.example.sagar.diabetesmanagement;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class custom_adapter extends ArrayAdapter<String> {

    private TextView DateView;
    private TextView TimeView;
    private Calendar calendar;
    private int year, month, day;

    private LinearLayout DurationLayOut;
     private Button saveButton;
    private EditText value;
    private TextView label;
    private EditText apxCalory;
    private TextView startTime;
    private TextView endTime;

    public custom_adapter(Context c, ArrayList<String> list)
    {
        super(c, R.layout.inserting_information, list);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.inserting_information, parent, false);

        String listItem = getItem(position);

        saveButton = (Button) customView.findViewById(R.id.saveButton);
        value = (EditText) customView.findViewById(R.id.txtValue);
        label = (TextView) customView.findViewById(R.id.lblLabel);
        apxCalory = (EditText) customView.findViewById(R.id.txtCalories);
        DurationLayOut = (LinearLayout) customView.findViewById(R.id.TimeContainer);
        DateView = (TextView) customView.findViewById(R.id.dateView);
        startTime = (TextView) customView.findViewById(R.id.txtStartTime);
        endTime = (TextView) customView.findViewById(R.id.txtEndTime);
        TimeView = (TextView) customView.findViewById(R.id.timeView);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if(!listItem.equals("Exercise"))
        {
            DurationLayOut.setVisibility(LinearLayout.GONE);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String hour = dateFormat.format(new Date());
        TimeView.setText(hour);
        showDate(year, month+1, day);
        label.setText(listItem);

        return customView;
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
