package com.example.sagar.diabetesmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
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


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class custom_adapter extends ArrayAdapter<String> {


    ArrayList<String> lists;

    public custom_adapter(Context c, ArrayList<String> list)
    {

        super(c, R.layout.inserting_information, list);
        lists = list;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View customView = convertView;
        final holder infoHolder;
        if(customView==null) {
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            customView = inflater.inflate(R.layout.inserting_information, parent, false);

            infoHolder = new holder();
            infoHolder.saveButton = (Button) customView.findViewById(R.id.saveButton);
            infoHolder.value = (EditText) customView.findViewById(R.id.txtValue);
            infoHolder.label = (TextView) customView.findViewById(R.id.lblLabel);
            infoHolder.apxcalorie = (EditText) customView.findViewById(R.id.txtCalories);
            infoHolder.DuartionLayout = (LinearLayout) customView.findViewById(R.id.TimeContainer);
            infoHolder.DateView = (TextView) customView.findViewById(R.id.dateView);
            infoHolder.startTime = (TextView) customView.findViewById(R.id.txtStartTime);
            infoHolder.endTime = (TextView) customView.findViewById(R.id.txtEndTime);
            infoHolder.TimeView = (TextView) customView.findViewById(R.id.timeView);
            infoHolder.label.setText(getItem(position));
            infoHolder.position = position;
            if(!getItem(position).equals("Exercise"))
            {
                infoHolder.DuartionLayout.setVisibility(LinearLayout.GONE);
            }
            
            //Set On SAVE button Listener.
            //// TODO: 7/28/2016  Add All of this to database. 
            infoHolder.saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   DoSomething(infoHolder.value);
                    lists.remove(infoHolder.position);
                    notifyDataSetChanged();

                }
            });

           final int year = 2333;
            final int month = 3;
            final int day = 2;


            setDate(infoHolder.DateView);
            setTime(infoHolder.startTime);
            setTime(infoHolder.endTime);
            setTime(infoHolder.TimeView);

            customView.setTag(infoHolder);

        }
        else
        {
            infoHolder = (holder) customView.getTag();
        }
        return customView;
    }
    private class holder{
        public int position;
        public Button saveButton;
        public EditText value;
        public TextView label;
        public TextView DateView;
        public TextView TimeView;
        public TextView startTime;
        public TextView endTime;
        public EditText apxcalorie;
        public LinearLayout DuartionLayout;
    }

    public void setDate(final TextView dateWidget)
    {
       Calendar calendar = Calendar.getInstance();
       final int  year = calendar.get(Calendar.YEAR);
        final int  month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateWidget.setText(new StringBuilder().append(month).append("/").append(day).append("/").append(year));


        dateWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dg = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String hourMin = dateFormat.format(new Date());
        timeWidget.setText(hourMin);


        timeWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dg = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        timeWidget.setText(new StringBuilder().append(hour).append(" : ").append(minute));
                    }
                }, hour, minute, false);
                dg.show();

            }
        });
    }






}
