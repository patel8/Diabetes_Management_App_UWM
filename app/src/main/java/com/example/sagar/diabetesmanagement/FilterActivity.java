package com.example.sagar.diabetesmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Filter;


public class FilterActivity extends AppCompatActivity {

    String ActivityName = "";

    LinearLayout WholeParameterBGL;
    LinearLayout WholeParameterFood;
    LinearLayout WholeParameterExercise;
    LinearLayout WholeParameterMedicine;
    LinearLayout ParameterLayoutBGL;
    LinearLayout ParameterLayoutFood;
    LinearLayout ParameterLayoutExercise;
    LinearLayout ParameterLayoutMedicine;

    CheckBox chkBoxBGL;
    CheckBox chkBoxFood;
    CheckBox chkBoxExercise;
    CheckBox chkBoxMedicine;

    TextView DateViewFrom;
    TextView DateViewTo;

    EditText txtFrommMinimum;
    EditText txtToMaximum;
    EditText txtExerciseDescriptionVal;
    EditText txtMedicineDescriptionVal;
    EditText txtFoodDescriptionVal;

    Button buttonFiler;
    DbHelper dbHelper;

    private boolean isGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.filter_activity);
        ActivityName = getIntent().getStringExtra("ActivityInfo");
        dbHelper = new DbHelper(this);
        registerWidgets();
        registerListener();
        showandHide(ActivityName);

        super.onCreate(savedInstanceState);
    }

    private void registerListener() {
        registerChkBoxes();
        registerButton();
    }

    private void registerButton() {
        buttonFiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Whaterever Buutton needs to do
                filterQuery filterquery = new filterQuery();
                filterquery.setBGLChecked(chkBoxBGL.isChecked());
                filterquery.setExerciseChecked(chkBoxExercise.isChecked());
                filterquery.setMedicineChecked(chkBoxMedicine.isChecked());
                filterquery.setFoodChecked(chkBoxFood.isChecked());

                filterquery.setExerciseDescription(txtExerciseDescriptionVal.getText().toString());
                filterquery.setFoodDescription(txtFoodDescriptionVal.getText().toString());
                filterquery.setMedicineDescription(txtMedicineDescriptionVal.getText().toString());

                filterquery.setFromDate(DateViewFrom.getText().toString());
                filterquery.setToDate(DateViewTo.getText().toString());

                filterquery.setMinBGL(txtFrommMinimum.getText().toString());
                filterquery.setMaxBGL(txtToMaximum.getText().toString());

                Intent intent;
                switch (ActivityName) {
                    case "BGL Graph":
                        if(!chkBoxBGL.isChecked())
                        {
                            Toast.makeText(FilterActivity.this, "Please select the Activity", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(txtFrommMinimum.getText().toString().equals("") || txtToMaximum.getText().toString().equals(""))
                        {
                            Toast.makeText(FilterActivity.this, "Please correct Min and Max values", Toast.LENGTH_SHORT).show();
                            return;
                        }
                       intent = new Intent(FilterActivity.this, graph_activity.class);
                        //intent = new Intent(FilterActivity.this, graph_activity.class);
                        intent.putExtra("Query", filterquery.Query());
                        startActivity(intent);
                        break;


                    case "Food Graph":

                        if(!chkBoxFood.isChecked())
                        {
                            Toast.makeText(FilterActivity.this, "Please select the Activity", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        intent = new Intent(FilterActivity.this, food_graph_activity.class);
                        intent.putExtra("Query", filterquery.Query());
                        startActivity(intent);

                        break;
                    case "Exercise Graph":

                        if(!chkBoxExercise.isChecked())
                        {
                            Toast.makeText(FilterActivity.this, "Please select the Activity", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        intent = new Intent(FilterActivity.this, exercise_graph_activity.class);
                        intent.putExtra("Query", filterquery.Query());
                        startActivity(intent);


                        break;
                    case "Medicine Graph":
                        if(!chkBoxMedicine.isChecked())
                        {
                            Toast.makeText(FilterActivity.this, "Please select the Activity", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        intent = new Intent(FilterActivity.this, medicine_graph_activity.class);
                        intent.putExtra("Query", filterquery.Query());
                        startActivity(intent);


                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setDate(final TextView dateWidget)
    {
        Calendar calendar = Calendar.getInstance();
        final int  year = calendar.get(Calendar.YEAR);
        final int  month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(calendar.getTime());

        dateWidget.setText(date);

        dateWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dg = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month++;
                        String NewDay = day+"";
                        String NewMonth = month+"";
                        if(month < 10){

                            NewMonth = "0" + month;
                        }
                        if(day < 10){

                            NewDay  = "0" + day ;
                        }
                        dateWidget.setText(new StringBuilder().append(year).append("-").append(NewMonth).append("-").append(NewDay));
                    }
                }, year, month, day);
                dg.show();

            }
        });
    }

    private void registerChkBoxes() {
        chkBoxBGL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ParameterLayoutBGL.setVisibility(View.VISIBLE);
                }
                else{
                    ParameterLayoutBGL.setVisibility(View.GONE);
                }

            }
        });
        chkBoxExercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ParameterLayoutExercise.setVisibility(View.VISIBLE);
                }
                else{
                    ParameterLayoutExercise.setVisibility(View.GONE);
                }

            }
        });
        chkBoxFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ParameterLayoutFood.setVisibility(View.VISIBLE);
                }
                else{
                    ParameterLayoutFood.setVisibility(View.GONE);
                }

            }
        });
        chkBoxMedicine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ParameterLayoutMedicine.setVisibility(View.VISIBLE);
                }
                else{
                    ParameterLayoutMedicine.setVisibility(View.GONE);
                }

            }
        });
    }

    private void registerWidgets() {
        chkBoxBGL = (CheckBox)findViewById(R.id.chkBoxBGL);
        chkBoxFood = (CheckBox)findViewById(R.id.chkBoxFood);
        chkBoxExercise = (CheckBox)findViewById(R.id.chkBoxExercise);
        chkBoxMedicine = (CheckBox)findViewById(R.id.chkBoxMedicine);
        WholeParameterBGL= (LinearLayout) findViewById(R.id.WholeParameterBGL);
        WholeParameterFood= (LinearLayout) findViewById(R.id.WholeParameterFood);
        WholeParameterExercise= (LinearLayout) findViewById(R.id.WholeParameterExercise);
        WholeParameterMedicine= (LinearLayout) findViewById(R.id.WholeParameterMedicine);
        ParameterLayoutBGL= (LinearLayout) findViewById(R.id.ParameterLayoutBGL);
        ParameterLayoutFood= (LinearLayout) findViewById(R.id.ParameterLayoutFood);
        ParameterLayoutExercise= (LinearLayout) findViewById(R.id.ParameterLayoutExercise);
        ParameterLayoutMedicine= (LinearLayout) findViewById(R.id.ParameterLayoutMedicine);
        DateViewFrom = (TextView) findViewById(R.id.dateViewFrom);
        DateViewTo = (TextView) findViewById(R.id.dateViewTo);
        txtFrommMinimum = (EditText) findViewById(R.id.txtFromMinimum);
        txtToMaximum = (EditText) findViewById(R.id.txtToMaximum);
        txtExerciseDescriptionVal =(EditText) findViewById(R.id.txtExerciseDescriptionVal);
        txtMedicineDescriptionVal=(EditText) findViewById(R.id.txtExerciseDescriptionVal);
        txtFoodDescriptionVal=(EditText) findViewById(R.id.txtFoodDescriptionVal);
        buttonFiler = (Button) findViewById(R.id.buttonFilter);
        //Attach Date Picket to Date View
        setDate(DateViewFrom);
        setDate(DateViewTo);


    }

    private void showandHide(String activityName) {
        switch(activityName)
        {
            case "BGL Graph":
                //Hide Everything else but BGL
                WholeParameterExercise.setVisibility(View.GONE);
                WholeParameterFood.setVisibility(View.GONE);
                WholeParameterMedicine.setVisibility(View.GONE);
                isGraph = true;


                break;
            case "Food Graph":
                //Hide Everything else but Food
                WholeParameterExercise.setVisibility(View.GONE);
                WholeParameterBGL.setVisibility(View.GONE);
                WholeParameterMedicine.setVisibility(View.GONE);
                isGraph = true;

                break;
            case "Exercise Graph":
                //Hide Everything else but Exercise
                WholeParameterFood.setVisibility(View.GONE);
                WholeParameterBGL.setVisibility(View.GONE);
                WholeParameterMedicine.setVisibility(View.GONE);
                isGraph = true;

                break;
            case "Medicine Graph":
                //Hide everything else but Medicine
                WholeParameterFood.setVisibility(View.GONE);
                WholeParameterBGL.setVisibility(View.GONE);
                WholeParameterExercise.setVisibility(View.GONE);
                isGraph = true;

                break;
            default:
                //Show everything

                break;

        }
    }
}