package com.example.sagar.diabetesmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class home_activity extends AppCompatActivity {

        ImageButton historyButton;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        historyButton = (ImageButton) findViewById(R.id.imageButtonHistory);

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), history.class);
                startActivity(intent);
            }
        });



    }

    // This method will add main_menu.xml to this activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        db = new DbHelper(this);
        refreshAverageGlucoceValues();


    }

    //This method will perform action when 'Add Activity button' is called.

    public void onButtonAddActivityClick(View view){

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


                         // This Code will Add all checked Activities to ArrayList 'SelectedItems'. Then will send it to 'list_of_activity_to_add.java' and Start that Activity.
                        if(!seletedItems.isEmpty()) {
                            Intent intent = new Intent(getApplicationContext(), list_of_activity_to_add.class);
                            //This list will contain the selected Activities.
                            intent.putExtra("SelectedItem", seletedItems);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "No Activities selected!", Toast.LENGTH_SHORT).show();
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



    public void refreshAverageGlucoceValues()
    {

        setDailyGlucoceText();
        setMonthlyGlucoceText();
        setWeeklyGlucoceText();
    }


    private void setDailyGlucoceText()
    {

        //Perform Database Operations in orderto get Daily Glucoze

        TextView DailyTextView = (TextView) findViewById(R.id.txtDaily);
        //Set the Text for Daily Text View
//        int DailyGlcoseLevel = db.getDailyGlucoseLevel();
//        DailyTextView.setText(DailyGlcoseLevel+"");



    }

    private void setWeeklyGlucoceText()
    {

        //Perform Database Operations in orderto get Daily Glucoze

        TextView DailyTextView = (TextView) findViewById(R.id.txtWeekly);
        //Set the Text for Weekly Text View
      //  int WeeklyGlcoseLevel = db.getWeeklyGlucoseLevel();
//        DailyTextView.setText(WeeklyGlcoseLevel);


    }

    private void setMonthlyGlucoceText()
    {

        //Perform Database Operations in orderto get Daily Glucoze

        TextView DailyTextView = (TextView) findViewById(R.id.txtMonthly);
        //Set the Text for Monthly Text View
      //  int MonthlyGlcoseLevel = db.getMonthlyGlucoseLevel();
       // DailyTextView.setText(MonthlyGlcoseLevel);


    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
