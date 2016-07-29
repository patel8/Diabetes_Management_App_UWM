package com.example.sagar.diabetesmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class home_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        refreshAverageGlucoceValues();

    }

    // This method will add main_menu.xml to this activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        return true;
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
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here

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



    }

    private void setWeeklyGlucoceText()
    {

        //Perform Database Operations in orderto get Daily Glucoze

        TextView DailyTextView = (TextView) findViewById(R.id.txtWeekly);
        //Set the Text for Weekly Text View



    }

    private void setMonthlyGlucoceText()
    {

        //Perform Database Operations in orderto get Daily Glucoze

        TextView DailyTextView = (TextView) findViewById(R.id.txtMonthly);
        //Set the Text for Monthly Text View



    }



}
