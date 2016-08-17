package com.example.sagar.diabetesmanagement;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class medicine_graph_activity extends AppCompatActivity {

    DbHelper db;
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
        ArrayList<Activity_Information> info = db.cursorToArrayList(db.runQuery(query));


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
    }

}
