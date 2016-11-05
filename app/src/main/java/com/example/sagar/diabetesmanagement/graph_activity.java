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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Sagar on 8/10/2016.
 */
public class graph_activity extends AppCompatActivity
{

    DbHelper db;
    Button statistics;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_activity);
        query = getIntent().getStringExtra("Query");
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

    @Override
    protected void onStart() {
        super.onStart();

        db = new DbHelper(this);
        statistics = (Button) findViewById(R.id.btnStatistics);
        statisticButtonListener();
        ArrayList<Activity_Information> info = db.cursorToArrayList(db.runQuery(query));


        LineChart barChart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i< info.size(); i++)
        {
            entries.add(new BarEntry(Float.parseFloat(info.get(i).getValue()), i));
        }

        LineDataSet dataset = new LineDataSet(entries, "BGL Level");
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i< info.size(); i++)
        {
            labels.add(info.get(i).getTime() + "\n" +info.get(i).getDate());
        }

        LineData data = new LineData(labels, dataset);

        barChart.setPinchZoom(true);

        barChart.setData(data);
    }

    private void statisticButtonListener() {
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fq = getIntent().getStringExtra("Query");
                ArrayList<Integer> stats =  db.getBGLStats(fq);
                AlertDialog alertDialog = new AlertDialog.Builder(graph_activity.this).create();
                alertDialog.setTitle("BGL STATISTICS");
                String alert1 = "Average BGL :  " + stats.get(1);
                String alert2 = "Minimum BGL : " + stats.get(0);
                String alert3 = "Maximum BGL : " + stats.get(2);
                alertDialog.setMessage(alert1 +"\n"+ alert2 +"\n"+ alert3);

                alertDialog.show();
            }
        });

    }
}