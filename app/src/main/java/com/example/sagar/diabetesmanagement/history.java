package com.example.sagar.diabetesmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


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

//        while(DBRows.moveToFirst()) {
//            int idDB = DBRows.getInt(0);
//            String label = DBRows.getString(1);
//            String Date = DBRows.getString(2);
//            String Time = DBRows.getString(3);
//            String StartTime = DBRows.getString(4);
//            String EndTime = DBRows.getString(5);
//            String Description = DBRows.getString(6);
//            String ApxCalorie = DBRows.getString(7);
//        }


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

        SimpleCursorAdapter cursor = new SimpleCursorAdapter(this,R.layout.ui_history,DBRows,fromFieldName,toViewIds);
        listView.setAdapter(cursor);


    }

    private void OnListItemClickListner() {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                    Toast.makeText(getApplicationContext(), " ID IS " + id, Toast.LENGTH_LONG).show();

                    Cursor cursor = dbHelper.getRowWithID((int)id);
                    Intent intent = new Intent(getApplicationContext(), all_activity_information.class);

                  while(cursor.moveToFirst())
                    {
                       int idDB = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbHelper.colId)));
                        String label = cursor.getString(cursor.getColumnIndex(DbHelper.colLable));
                        String Date = cursor.getString(cursor.getColumnIndex(DbHelper.colDate));
                        String Time = cursor.getString(cursor.getColumnIndex(DbHelper.colTime));
                        String StartTime = cursor.getString(cursor.getColumnIndex(DbHelper.colStartTime));;
                        String EndTime = cursor.getString(cursor.getColumnIndex(DbHelper.colEndTime));;
                        String Description = cursor.getString(cursor.getColumnIndex(DbHelper.colDescription));;
                        String ApxCalorie = cursor.getString(cursor.getColumnIndex(DbHelper.colApxCalorie));;


                        intent.putExtra("_id", idDB);
                        intent.putExtra("Label", label);
                        intent.putExtra("Date", Date);
                        intent.putExtra("Time", Time);
                        intent.putExtra("StartTime", StartTime);
                        intent.putExtra("EndTime", EndTime);
                        intent.putExtra("Description", Description);
                        intent.putExtra("ApxCalorie", ApxCalorie);

                    break;


                    }

                    cursor.close();
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

