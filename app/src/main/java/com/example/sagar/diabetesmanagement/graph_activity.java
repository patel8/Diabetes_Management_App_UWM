package com.example.sagar.diabetesmanagement;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by Sagar on 8/10/2016.
 */
public class graph_activity extends AppCompatActivity
{

    DbHelper db;
    Button statistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.graph_activity);

        db = new DbHelper(this);

        String fq = getIntent().getStringExtra("Query");

        statistics = (Button) findViewById(R.id.btnStatistics);
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fq = getIntent().getStringExtra("Query");
                ArrayList<Integer> stats =  db.getBGLStats(fq);
                AlertDialog alertDialog = new AlertDialog.Builder(graph_activity.this).create();
                String alert1 = "Average BGL :  " + stats.get(1);
                String alert2 = "Minimum BGL : " + stats.get(0);
                String alert3 = "Maximum BGL : " + stats.get(2);
                alertDialog.setMessage(alert1 +"\n"+ alert2 +"\n"+ alert3);
                alertDialog.show();
            }
        });

        ArrayList<Activity_Information> info = db.cursorToArrayList(db.runQuery(fq));


        LineChart barChart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i< info.size(); i++)
        {
            entries.add(new BarEntry(Float.parseFloat(info.get(i).getValue()), i));
        }

        LineDataSet dataset = new LineDataSet(entries, "BGL Level");

        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i< info.size(); i++)
        {
            labels.add(info.get(i).getTime() + "\n" +info.get(i).getDate());
        }


        LineData data = new LineData(labels, dataset);
        barChart.setData(data);
        db.close();

    }


}