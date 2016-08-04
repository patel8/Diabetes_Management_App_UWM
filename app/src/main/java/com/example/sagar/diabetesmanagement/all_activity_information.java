package com.example.sagar.diabetesmanagement;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class all_activity_information extends AppCompatActivity {

    private TextView DateView;
    private TextView TimeView;
    private LinearLayout DurationLayOut;
    private int _id;
    private Button saveButton;
    private EditText value;
    private TextView label;
    private EditText apxCalory;
    private TextView startTime;
    private TextView endTime;
    private Button deleteButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserting_information);



        intent = getIntent();

        saveButton = (Button) findViewById(R.id.saveButton);
        DateView = (TextView) findViewById(R.id.dateView);
        TimeView = (TextView) findViewById(R.id.timeView);
        startTime = (TextView) findViewById(R.id.txtStartTime);
        endTime = (TextView) findViewById(R.id.txtEndTime);
        value = (EditText) findViewById(R.id.txtValue);
        label = (TextView) findViewById(R.id.lblLabel);
        apxCalory = (EditText) findViewById(R.id.txtCalories);
        DurationLayOut = (LinearLayout) findViewById(R.id.TimeContainer);
        deleteButton = (Button) findViewById(R.id.buttonDelete);
        deleteButton.setVisibility(View.VISIBLE);

            _id =  intent.getIntExtra("_id", 0);
            label.setText(intent.getStringExtra("Label").toString());
            DateView.setText(intent.getStringExtra("Date").toString());
            TimeView.setText(intent.getStringExtra("Time").toString());
            startTime.setText(intent.getStringExtra("StartTime").toString());
            endTime.setText(intent.getStringExtra("EndTime").toString());
            value.setText(intent.getStringExtra("Description").toString());
            apxCalory.setText(intent.getStringExtra("ApxCalorie").toString());

        if(!label.getText().equals("Exercise"))
        {
            DurationLayOut.setVisibility(LinearLayout.GONE);
        }
        setDate(DateView);
        setTime(startTime);
        setTime(endTime);
        setTime(TimeView);

        deleteButtonActionListner();

    }


    public void deleteButtonActionListner()
    {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper db = new DbHelper(getApplicationContext());
                if(db.deleteRowWithId(_id))
                {
                    db.close();
                    Intent intent = new Intent(all_activity_information.this,history.class);
                    Toast.makeText(all_activity_information.this, "Activity has been deleted!", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }
                else
                {
                    db.close();
                    Toast.makeText(all_activity_information.this, "Something went wrong!! Data is not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

   // This function will handle ON 'SAVE' Button event.
    public void onSaveButtonClick()
    {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Activity_Information activity = new Activity_Information();

                activity.setDate(DateView.getText().toString());
                activity.setTime(TimeView.getText().toString());
                activity.setLabel(label.getText().toString());
                activity.setValue(value.getText().toString());
                activity.setStartTime(startTime.getText().toString());
                activity.setEndTime(endTime.getText().toString());
                activity.setApxCalory(apxCalory.getText().toString());
                activity.setId(_id);
                //Send this object to Database to get added.

            //    dbhelper.updateCurrentRowWithId(activity);

            }
        });

    }


    public void setDate(final TextView dateWidget)
    {
       final int year=0, month=0, day=0;

        dateWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dg = new DatePickerDialog(all_activity_information.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateWidget.setText(new StringBuilder().append(month).append("/").append(day).append("/").append(year));
                    }
                }, year, month, day);

                dg.show();

            }
        });
    }
    public void setTime(final TextView timeWidget)
    {

        final int hour = 0;
        final int minute = 0;


        // THis is when they click on it.

        timeWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dg = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {

                        timeWidget.setText(new StringBuilder().append(hour).append(":").append(minute));
                    }
                }, hour, minute, false);
                dg.show();

            }
        });
    }



}
