package com.example.sagar.diabetesmanagement;

import java.util.ArrayList;

/**
 * Created by Sagar on 8/13/2016.
 */
public class filterQuery {
    String fromDate;
    String toDate;
    boolean isBGLChecked;
    boolean isExerciseChecked;
    boolean isFoodChecked;
    boolean isMedicineChecked;

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
        //Construct the query here

        for(int i=0; i< checkedItem.size(); i++)
        {
            switch (checkedItem.get(i))
            {
                case "Exercise":
                    //Code goes here
                    break;
                case "Food":
                    //Code goes here
                    break;
                case "BGL":
                    //code goes here
                    break;
                case "Medicine":
                    //code goes here
                    break;
            }
        }



        return query;
    }
}
