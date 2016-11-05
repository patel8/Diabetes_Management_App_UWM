package com.example.sagar.diabetesmanagement;

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ehab on 7/31/2016.
 */
public class UI_history extends Activity {
//    private LinearLayout layoutdate;
//    private LinearLayout layoutLabel;
//    private LinearLayout layoutData;
    private  Button butDelet;
    private  Button butEdit;
    private TextView textDate;
    private TextView textTime;
    private TextView textlabel;
    private TextView textData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_history);
//        layoutdate = (LinearLayout) findViewById(R.id.datelayout);
//        layoutLabel = (LinearLayout) findViewById(R.id.labelLayout);
//        layoutData = (LinearLayout) findViewById(R.id.dataLayout);
//
//        butDelet = (Button) findViewById(R.id.buttonDeleteRecord);
//        butEdit = (Button) findViewById(R.id.buttonEditRecord);
//        textDate = (TextView) findViewById(R.id.textViewDate);
//        textTime = (TextView) findViewById(R.id.textViewTime);
//        textlabel = (TextView)findViewById(R.id.textViewTableName);
//        textData = (TextView) findViewById(R.id.textViewDataDB);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
