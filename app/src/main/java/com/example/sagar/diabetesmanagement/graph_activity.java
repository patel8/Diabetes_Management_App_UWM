package com.example.sagar.diabetesmanagement;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by Sagar on 8/10/2016.
 */
public class graph_activity extends AppCompatActivity
{

    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.graph_activity);

        dbHelper = new DbHelper(this);



        Cursor cursor = dbHelper.getAllBGLwithTimeStamp();


        //Create ArrayList of All objects that needs to be added to Graph
        ArrayList<Activity_Information> info = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {

                String Date = cursor.getString(cursor.getColumnIndex(DbHelper.colDate));
                String Time = cursor.getString(cursor.getColumnIndex(DbHelper.colTime));
                String Description = cursor.getString(cursor.getColumnIndex(DbHelper.colDescription));


                Activity_Information activity_information = new Activity_Information();
                activity_information.setDate(Date);
                activity_information.setTime(Time);
                activity_information.setValue(Description);
                info.add(activity_information);

            }while(cursor.moveToNext());


        }


        BarChart barChart = (BarChart) findViewById(R.id.chart);
        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0; i< info.size(); i++)
        {
            entries.add(new BarEntry(Float.parseFloat(info.get(i).getValue()), i));
        }

        BarDataSet dataset = new BarDataSet(entries, "BGL Level");

        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i< info.size(); i++)
        {
            labels.add(info.get(i).getTime() + "\n" +info.get(i).getDate());
        }


        BarData data = new BarData(labels, dataset);
        barChart.setData(data);
        dbHelper.close();

    }


}
