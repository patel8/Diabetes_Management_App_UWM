package com.example.sagar.diabetesmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Sagar on 8/13/2016.
 */
public class filterQuery{
    String fromDate;
    String toDate;
    String MinBGL;
    String MaxBGL;
    String FoodDescription;
    String ExerciseDescription;
    String MedicineDescription;

    ArrayList<String> checkedItem = new ArrayList<String>();
    public String getMinBGL() {
        return MinBGL;
    }

    public void setMinBGL(String minBGL) {
        MinBGL = minBGL;
    }

    public String getMaxBGL() {
        return MaxBGL;
    }

    public void setMaxBGL(String maxBGL) {
        MaxBGL = maxBGL;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public String getExerciseDescription() {
        return ExerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        ExerciseDescription = exerciseDescription;
    }

    public String getMedicineDescription() {
        return MedicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        MedicineDescription = medicineDescription;
    }


    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setBGLChecked(boolean BGLChecked) {

        if(BGLChecked) checkedItem.add("BGL");
    }

    public void setExerciseChecked(boolean exerciseChecked) {
        if(exerciseChecked) checkedItem.add("Exercise");
    }

    public void setFoodChecked(boolean foodChecked) {
        if(foodChecked) checkedItem.add("Food");
    }

    public void setMedicineChecked(boolean medicineChecked) {
        if(medicineChecked) checkedItem.add("Medicine");
    }

    public String Query(){

        String query ="";
        ArrayList<Activity_Information> result = new ArrayList<>();

        //Construct the query here
        for(int i=0; i< checkedItem.size(); i++)
        {
            switch (checkedItem.get(i))
            {

                case "Exercise":
                    //Code goes here
                    query = "select * from history where (lable = 'Exercise') and (description like '%" + ExerciseDescription + "%') and (date between '" + fromDate + "' and '" + toDate + "') order by date, time";
                    //result.addAll(db.cursorToArrayList(db.runQuery(query)));
                    break;
                case "Food":
                    //Code goes here
                    query = "select * from history where (lable = 'Food') and (description like '%" + FoodDescription + "%') and (date between '" + fromDate + "' and '" + toDate + "') order by date, time";
                    // result.addAll(db.cursorToArrayList(db.runQuery(query)));
                    break;
                case "BGL":
                    //code goes here
                    query = "select * from history where (lable = 'BGL') and (bglamount between " + MinBGL + " and "+ MaxBGL +") and (date between '" + fromDate + "' and '" + toDate + "') order by date, time";
                    //  result.addAll(db.cursorToArrayList(db.runQuery(query)));
                    break;
                case "Medicine":
                    //code goes here
                    query = "select * from history where (lable = 'Medicine') and (description like '%" + MedicineDescription + "%')  and (date between '" + fromDate + "' and '" + toDate + "') order by date, time";
                    // result.addAll(db.cursorToArrayList(db.runQuery(query)));
                    break;
            }
        }

        return query ;
    }
}