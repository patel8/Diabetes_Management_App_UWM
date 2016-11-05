package com.example.sagar.diabetesmanagement;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class medicine_graph_activity extends AppCompatActivity {

    DbHelper db;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_graph_activity);
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

        final PieChart pieChart = (PieChart) findViewById(R.id.pie_chart_med);
        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i< info.size(); i++)
        {
            entries.add(new Entry(info.get(i).getCount(), i));
        }

         PieDataSet dataset = new PieDataSet(entries, "Medicine Chart");

       final ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i< info.size(); i++)
        {
            labels.add(info.get(i).getName());
        }
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(labels, dataset);
        pieChart.animateXY(4000,4000);
        pieChart.setCenterText("MEDICINE");
        pieChart.setCenterTextSize(18);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
            public void onValueSelected(Entry e, int index, Highlight h){
                pieChart.setCenterText("Type: "+labels.get(e.getXIndex()).toString()+ "\nOccurrence: "+e.getVal()+"");
            }
            public void onNothingSelected(){
                pieChart.setCenterText("MEDICINE");
            }
        });
        pieChart.setData(data);

    }

}
