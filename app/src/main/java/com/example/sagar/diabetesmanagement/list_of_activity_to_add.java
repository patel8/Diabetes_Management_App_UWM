package com.example.sagar.diabetesmanagement;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

public class list_of_activity_to_add extends AppCompatActivity {

    ListView listView;
    Button addActivity;
    ArrayList<String> finalList;
    ListAdapter titleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_activity_to_add);



        //Set Reference to List View in Layout.
          addActivity = (Button) findViewById(R.id.buttonAddActivityThroughList);
          listView = (ListView) findViewById(R.id.listOfActivity);
          finalList = getIntent().getStringArrayListExtra("SelectedItem");
          titleAdapter = new custom_adapter(this);
          listView.setAdapter(titleAdapter);

    }



    //This is ADD ACTIVITY BUTTON at bottom of screen. This will ask user to add more Activity and also will add it to the
    public void onButtonAddActivityThroughList(View view){

        final CharSequence[] items = {"Food","BGL","Exercise", "Medicine"};
// arraylist to keep the selected items
        final ArrayList seletedItems =new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select an Activity")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(items[indexSelected]);
                        } else if (seletedItems.contains(items[indexSelected])) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(items[indexSelected]);
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(!seletedItems.isEmpty()) {
                            finalList.addAll(seletedItems);
                           ((BaseAdapter) titleAdapter).notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "No Activities has been added!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                }).create();
        dialog.show();
    }

//    public void removeItemFromListView(int position)
//    {
//        finalList.remove(position);
//        ((BaseAdapter) titleAdapter).notifyDataSetChanged();
//
//    }

    private class custom_adapter extends ArrayAdapter<String> {


        DbHelper dbHelper;

        public custom_adapter(Context c)
        {
            super(c, R.layout.inserting_information, finalList);
            dbHelper = new DbHelper(getContext());

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
                infoHolder.DurationLabel = (TextView) customView.findViewById(R.id.txtDuration);
                infoHolder.position = position;
                infoHolder.label.setText(getItem(position));
                infoHolder.fastingToggleButton = (ToggleButton) customView.findViewById(R.id.FastingToggleButton);
                infoHolder.fastingLabel = (TextView) customView.findViewById(R.id.labelFasting);
                infoHolder.ApxCalorieLable = (TextView) customView.findViewById(R.id.txtApxCal);
                if(!getItem(position).equals("Exercise"))
                {
                    infoHolder.DuartionLayout.setVisibility(LinearLayout.GONE);
                }

                if(getItem(position).equals("BGL"))
                {
                    infoHolder.value.setInputType(InputType.TYPE_CLASS_NUMBER);
                    infoHolder.value.setHint("BGL Value");
                    infoHolder.fastingToggleButton.setVisibility(View.VISIBLE);
                    infoHolder.fastingLabel.setVisibility(View.VISIBLE);
                    infoHolder.apxcalorie.setVisibility(View.GONE);
                    infoHolder.ApxCalorieLable.setVisibility(View.GONE);

                }

                if(getItem(position).equals("Medicine"))
                {
                    infoHolder.ApxCalorieLable.setText("Amount(mg)");
                }



                //Set On SAVE button Listener.

                infoHolder.saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Activity_Information activity_information = infoHolder.getActivityInfomation();


                        boolean result = dbHelper.insert(activity_information);

//                        boolean result1 = dbHelper.insert_fireBase(activity_information);
                        if(result) {
                            Toast.makeText(getContext(), " DATA HAS BEEN STORED SUCCESSFULLY!!", Toast.LENGTH_LONG).show();

                            infoHolder.DisableEverything();



                        }

                        else
                            Toast.makeText(getContext(), " SORRY!! DATA HAS BEEN NOT STORED SUCCESSFULLY!!",Toast.LENGTH_LONG).show();



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
            public ToggleButton fastingToggleButton;
            public TextView fastingLabel;
            public TextView ApxCalorieLable;
            public TextView DurationLabel;

            public Activity_Information getActivityInfomation()
            {
                Activity_Information result = new Activity_Information();
                result.setLabel(label.getText().toString());
                result.setEndTime(endTime.getText().toString());
                result.setStartTime(startTime.getText().toString());
                result.setValue(value.getText().toString());
                result.setDate(DateView.getText().toString());
                result.setApxCalory(apxcalorie.getText().toString());
                result.setTime(TimeView.getText().toString());
                result.setFasting(fastingToggleButton.isChecked());


                return result;
            }
            public void DisableEverything()
            {

                DurationLabel.setEnabled(false);
                saveButton.setVisibility(View.GONE);
                value.setEnabled(false);
                label.setEnabled(false);
                DateView.setEnabled(false);
                TimeView.setEnabled(false);
                startTime.setEnabled(false);
                endTime.setEnabled(false);
                apxcalorie.setEnabled(false);
                DuartionLayout.setEnabled(false);
                fastingToggleButton.setEnabled(false);
                fastingLabel.setEnabled(false);
                ApxCalorieLable.setEnabled(false);
            }

        }

        public void setDate(final TextView dateWidget)
        {
            Calendar calendar = Calendar.getInstance();
            final int  year = calendar.get(Calendar.YEAR);
            final int  month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            dateWidget.setText(new StringBuilder().append(month+1).append("/").append(day).append("/").append(year));


            dateWidget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dg = new DatePickerDialog(list_of_activity_to_add.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            dateWidget.setText(new StringBuilder().append(year).append("-").append(month+1).append("-").append(day));
                        }
                    }, year, month, day);
                    dg.show();

                }
            });
        }
        public void setTime(final TextView timeWidget)
        {

            Calendar c = Calendar.getInstance();
            final int hour = c.get(Calendar.HOUR);
            final int minute = c.get(Calendar.MINUTE);
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            String hourMin = dateFormat.format(new Date());
            timeWidget.setText(hourMin);


            timeWidget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dg = new TimePickerDialog(list_of_activity_to_add.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hour, int minute) {
                            timeWidget.setText(hour%12 + ":" + minute + " " + ((hour>=12) ? "PM" : "AM"));
                        }
                    }, hour, minute, false);
                    dg.show();

                }
            });
        }






    }


}
