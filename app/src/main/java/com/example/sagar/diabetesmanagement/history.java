package com.example.sagar.diabetesmanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {



    ListView listView;
    Context context = history.this;
    DbHelper dbHelper;


    //ArrayList<String> mylist = new ArrayList<ResorceClass>();
    ListAdapter titleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbHelper = new DbHelper(this);
        listView = (ListView) findViewById(R.id.listViewHistory);

        OnListItemClickListner();

    }



    @Override
    protected void onStart() {
        super.onStart();
        populateListItem();
    }

    private void populateListItem() {
        Cursor DBRows= dbHelper.getAllData();


        String[] fromFieldName = new String[]
                {
                        DbHelper.colLable,
                        DbHelper.colDate,
                        DbHelper.colTime,
                        DbHelper.colDescription

                };

        int[] toViewIds = new int[]
                {
                        R.id.textViewTableName,
                        R.id.textViewDate,
                        R.id.textViewTime,
                        R.id.textViewDescription

                };

        SimpleCursorAdapter cursor = new SimpleCursorAdapter(
                this,
                R.layout.ui_history,
                DBRows,
                fromFieldName,
                toViewIds
        );


    }

    private void OnListItemClickListner() {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                    Activity_Information activity_info = new Activity_Information();
                    Cursor cursor = dbHelper.getInfoWithID(id);

                    while(cursor.moveToFirst())
                    {
                        long idDB = cursor.getLong(DbHelper.colId);
                        String label = cursor.getString(DbHelper.colLable);
                        String Date = cursor.getString(DbHelper.colDate);
                        String Time = cursor.getString(DbHelper.colTime);
                        String StartTime = cursor.getString(DbHelper.colStartTime);
                        String EndTime = cursor.getString(DbHelper.colEndTime);
                        String Description = cursor.getString(DbHelper.colDescription);
                        String ApxCalorie = cursor.getString(DbHelper.colApxCalorie);

                        activity_info = new Activity_Information();
                        activity_info.setLabel(label);
                        activity_info.setTime(Time);
                        activity_info.setDate(Date);
                        activity_info.setApxCalory(ApxCalorie);
                        activity_info.setValue(Description);

                        if(label.equals("Exercise"))
                        {
                            activity_info.setStartTime(StartTime);
                            activity_info.setEndTime(EndTime);
                        }



                    }

                cursor.close();
                    Intent intent = new Intent(getApplicationContext(), all_activity_information.class);
                    intent.putExtra("activityInfo", activity_info);
                    startActivity(intent);


                }
            });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}

