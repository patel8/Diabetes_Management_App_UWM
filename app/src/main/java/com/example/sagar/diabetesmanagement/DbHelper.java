package com.example.sagar.diabetesmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class DbHelper extends SQLiteOpenHelper {
    // Creating the database and their columns

    public static final String DBNAME = "diabetes.db";

    public static final String TBLBGL = "tblBGL";
    public static final String BGLID = "BglId";
    public static final String VALUE = "Value";
    public static final String TIMESTAMP = "TimeStamp";
    public static final String FASTING = "Fasting";

    public static final String TBLDIET = "tblDiet";
    public static final String DIETID = "DietId";
    //public static final String TIMESTAMP = "TimeStamp";
    public static final String CALORIES = "Calories";
    public static final String SUGARS = "Sugars";
    public static final String DESCRIPTION = "Description";

    public static final String TBLEXERCISE = "tblExcercise";
    public static final String EXERCISEID = "ExerciseId";
    //public static final String DESCRIPTION = "Description";
    public static final String STARTTIME = "StartTime";
    public static final String ENDTIME = "EndTime";

    public static final String TBLMEDICATION = "tblMedication";
    public static final String MEDICATIONID = "MedicationId";
    public static final String COMMENTS = "Comments";
    public static final String AMOUNT = "Amount";
    //public static final String TIMESTAMP = "TimeStamp";

    public static final String TBLPERSCRIBEDREGIMEN = "tblPrescribedRegimen";
    public static final String PRESCRIBEDREGIMENID = "PrescribedRegimenId";
    public static final String DIET = "Diet";
    public static final String EXERCISE = "Exercise";
    public static final String MEDICATION = "Medication";
    //public static final String STARTTME = "StartTime";
    //public static final String ENDTIME = "EndTime";
    public static final String DESIREDBGL = "DesiredBGL";

    public DbHelper(Context context) {
        super(context, DBNAME, null, 1);
    }
    // this method could be created automatically otherwise, I have to create them: onCreate (), onUpgrade
    @Override
    public void onCreate(SQLiteDatabase db) {
        // execSQL() this to execute the table and assign the data type for each instance.
        // INTEGER PRIMARY  KEY AUTOINCREMENT: this to make the ID the primary key and increases automatically
        db.execSQL("CREATE TABLE "+ TBLBGL +"("+BGLID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + VALUE + " TEXT, " + TIMESTAMP + " DATETIME, " + FASTING + " CHAR(1))");
        db.execSQL("CREATE TABLE "+ TBLDIET +"("+DIETID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + CALORIES + " INTEGER, " + SUGARS + " INTEGER, " + TIMESTAMP + " DATETIME, " + DESCRIPTION + " TEXT)");
        db.execSQL("CREATE TABLE "+ TBLEXERCISE +"("+EXERCISEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + STARTTIME + " DATETIME, " + ENDTIME + " DATETIME, " + DESCRIPTION + " TEXT)");
        db.execSQL("CREATE TABLE "+ TBLMEDICATION +"("+MEDICATIONID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + AMOUNT + " INTEGER, " + COMMENTS + " TEXT, " + TIMESTAMP + " DATETIME)");
        db.execSQL("CREATE TABLE "+ TBLPERSCRIBEDREGIMEN +"("+PRESCRIBEDREGIMENID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + STARTTIME + " DATETIME, " + ENDTIME + " DATETIME, " + DIET + " TEXT, " + EXERCISE + " TEXT, " + MEDICATION + " TEXT, " + DESIREDBGL + " INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TBLBGL);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLDIET);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLEXERCISE);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLMEDICATION);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLPERSCRIBEDREGIMEN);
        onCreate(db);

    }
    public boolean insert (Activity_Information info){
        if(info.getLabel().equals("BGL")) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cValues = new ContentValues();
            cValues.put(VALUE, info.getValue());
            cValues.put(TIMESTAMP, info.getDate() + " " + info.getTime());
            //cValues.put(FASTING, fasting);
            long res = db.insert(TBLBGL, null, cValues);
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else if (info.getLabel().equals("Food")){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cValues = new ContentValues();
            cValues.put(DESCRIPTION, info.getValue());
            cValues.put(TIMESTAMP, info.getDate() + " " + info.getTime());
            cValues.put(CALORIES, info.getApxCalory());
            long res = db.insert(TBLDIET, null, cValues);
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else if (info.getLabel().equals("Medicine")){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cValues = new ContentValues();
            cValues.put(COMMENTS, info.getValue());
            cValues.put(TIMESTAMP, info.getDate() + " " + info.getTime());
            long res = db.insert(TBLMEDICATION, null, cValues);
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else if (info.getLabel().equals("Exercise")){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cValues = new ContentValues();
            cValues.put(DESCRIPTION, info.getValue());
            cValues.put(STARTTIME, info.getDate() + " " + info.getStartTime());
            cValues.put(ENDTIME, info.getDate() + " " + info.getEndTime());
            long res = db.insert(TBLEXERCISE, null, cValues);
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}