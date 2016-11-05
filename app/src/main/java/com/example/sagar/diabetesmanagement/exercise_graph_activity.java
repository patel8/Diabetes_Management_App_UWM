package com.example.sagar.diabetesmanagement;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class exercise_graph_activity extends AppCompatActivity {

    DbHelper db;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_graph_activity);
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
        //ArrayList<Activity_Information> info = db.cursorToArrayList(db.runQuery(query));
        ArrayList<Tuple> info = new ArrayList<Tuple>();
        Cursor cursor = db.runQuery(query);
        while(cursor.moveToNext()) {
            Tuple toList = new Tuple();
            toList.setName((cursor.getString(cursor.getColumnIndex(db.colDescription))));
            toList.setCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex("count"))));
            info.add(toList);
        }


        final BarChart barChart = (BarChart) findViewById(R.id.bar_chart_exe);
        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0; i< info.size(); i++)
        {
            entries.add(new BarEntry((info.get(i).getCount()), i));
        }

        BarDataSet dataset = new BarDataSet(entries, "Exercise");

        final ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i< info.size(); i++)
        {
            labels.add(info.get(i).getName());
        }

        BarData data = new BarData(labels, dataset);
        barChart.animateXY(2000,2000);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setDescription("Exercise");
        barChart.setDescriptionTextSize(28);
        barChart.setDescriptionColor(Color.WHITE);
        barChart.setBackgroundColor(Color.DKGRAY);
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
            public void onValueSelected(Entry e, int index, Highlight h){
                barChart.setDescription("Exercise: "+labels.get(e.getXIndex()).toString()+ " -- Total: "+e.getVal()+"");
            }
            public void onNothingSelected(){
                barChart.setDescription("EXERCISE");
            }
        });

        barChart.setData(data);
    }

}
