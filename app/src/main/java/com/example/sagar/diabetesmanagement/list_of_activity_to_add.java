package com.example.sagar.diabetesmanagement;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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


          titleAdapter = new custom_adapter(this, finalList);
        listView.setAdapter(titleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String toast = ((Button) view).getText().toString();
                Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
            }
        });


    }




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
                       finalList.addAll(seletedItems);
                        ((BaseAdapter)titleAdapter).notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                }).create();
        dialog.show();


    }




}
