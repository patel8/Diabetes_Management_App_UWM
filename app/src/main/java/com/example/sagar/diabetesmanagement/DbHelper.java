package com.example.sagar.diabetesmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DbHelper extends SQLiteOpenHelper {
    // Creating the database and their columns

    public static final String DBNAME = "diabetesManagementDB.db";

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

    /**
     *  long idDB = cursor.getLong(DbHelper.colId);
     String label = cursor.getString(DbHelper.colLable);
     String Date = cursor.getString(DbHelper.colDate);
     String Time = cursor.getString(DbHelper.colTime);
     String StartTime = cursor.getString(DbHelper.colStartTime);
     String EndTime = cursor.getString(DbHelper.colEndTime);
     String Description = cursor.getString(DbHelper.colDescription);
     String ApxCalorie = cursor.getString(DbHelper.colApxCalorie);

     * @param context
     */
    public static final String tblHistory = "history";
    public static final String colId = "_id";
    public static final String colLable = "lable";
    public static final String colDate = "date";
    public static final String colTime = "time";
    public static final String colStartTime = "starttime";
    public static final String colEndTime = "endtime";
    public static final String colDescription = "description";
    public static final String colApxCalorie = "apxcalorie";
    public static final String colFasting = "fasting";
    public static final String colBGLAmount = "bglamount";


    public DbHelper(Context context) {
        super(context, DBNAME, null, 4);
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
        db.execSQL("CREATE TABLE "+ tblHistory +" ("+colId+" INTEGER PRIMARY KEY AUTOINCREMENT, " + colLable + " TEXT, " + colDate + " Date, " + colTime + " Time, " + colStartTime + " Time, " + colEndTime + " Time, " + colDescription + " Text, " + colApxCalorie +" Text, " + colFasting + " INTEGER, " + colBGLAmount + " INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TBLBGL);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLDIET);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLEXERCISE);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLMEDICATION);
        db.execSQL("DROP TABLE IF EXISTS "+ TBLPERSCRIBEDREGIMEN);
        db.execSQL("DROP TABLE IF EXISTS " + tblHistory);
        onCreate(db);

    }

    public boolean insert(Activity_Information info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        //cValues.put(colId, info.getId());
        cValues.put(colTime, info.getTime());
        cValues.put(colApxCalorie, info.getApxCalory());



        cValues.put(colDate, info.getDate());
        cValues.put(colEndTime, info.getEndTime());
        if(info.getLabel().equalsIgnoreCase("bgl")){
            cValues.put(colBGLAmount, info.getIntBGL());
        }
        cValues.put(colDescription, info.getValue());
        cValues.put(colStartTime, info.getStartTime());
        cValues.put(colLable, info.getLabel());
        cValues.put(colFasting, info.getFasting());

        long res = db.insert(tblHistory, null, cValues);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Update everything in that ID info.id
    public boolean updateCurrentRowWithId(Activity_Information info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();

        cV.put(colDate,info.getDate());
        cV.put(colTime,info.getTime());
        cV.put(colStartTime,info.getStartTime());
        cV.put(colEndTime,info.getEndTime());
        if(info.getLabel().equalsIgnoreCase("bgl")){
            cV.put(colBGLAmount, info.getIntBGL());
        }
        cV.put(colDescription,info.getValue());
        cV.put(colApxCalorie,info.getApxCalory());
        cV.put(colFasting,info.getFasting());

        long res = db.update(tblHistory,cV, colId+" = "+info.getId() ,null);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertInfo (Activity_Information info){
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




    /**
     * getAllData(); rOrder By Date
     * getInfoWithID(id);       // Return Cursor
     * getWeeklyGlucoseLevel()  //Return int
     * getDailyGlucoseLevel()  // Return Int
     * getMonthlyGlucoseLevel() // Return Int
     * updateCurrentRowWithId(Activity_information, id); Return Boolean
     */
    public Cursor runQuery(String qry){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(qry,null);
        return result;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from "+ tblHistory + " order by "+ colDate + ", "+ colTime, null);

        return result;
    }

    public Cursor getRowWithID(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+ tblHistory + " where "+colId+" = "+ id, null);
        return result;
    }

    public boolean deleteRowWithId(int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(tblHistory,colId + "=" +id, null );
        if(result==0)
            return false;
        return true;
    }

    public int getDailyGlucoseLevel()
    {
        Calendar calendar = Calendar.getInstance();
        final int  year = calendar.get(Calendar.YEAR);
        final int  month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+day;

        int sum = 0;
        int cnt = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+ tblHistory + " where ("+colDate+ " = '"+date+"' and "+ colLable +" = 'BGL')" ,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    int val = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbHelper.colDescription)));
                    sum = sum + val;
                    cnt++;
                }while(cursor.moveToNext());
            }
        }

        if(cnt == 0)
            return 0;
        else {
            return sum / cnt;
        }
    }

    public int getWeeklyGlucoseLevel()
    {
        Calendar calendar = Calendar.getInstance();
        final int  year = calendar.get(Calendar.YEAR);
        final int  month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH,-7);
        final int year2 = cal2.get(Calendar.YEAR);
        final int month2 = cal2.get(Calendar.MONTH) +1;
        final int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+day;
        String date2 = year2+"-"+month2+"-"+day2;
        int sum = 0;
        int cnt = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from "+ tblHistory + " where ("+colDate+ " between '"+date2+"' and '"+date+"') and ("+ colLable +" = 'BGL')";
        //"select * from "+ tblHistory + " where ("+colDate+ " between '"+date2+"' and '"+date+"') and ("+ colLable +" = 'BGL')"
        Cursor cursor = db.rawQuery(query ,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    int val = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbHelper.colDescription)));
                    sum = sum + val;
                    cnt++;
                }while(cursor.moveToNext());
            }
        }

        if(cnt == 0)
            return 0;
        else {
            return sum / cnt;
        }
    }

    public int getMonthlyGlucoseLevel()
    {
        Calendar calendar = Calendar.getInstance();
        final int  year = calendar.get(Calendar.YEAR);
        final int  month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH,-1);
        final int year2 = cal2.get(Calendar.YEAR);
        final int month2 = cal2.get(Calendar.MONTH) + 1 ;
        final int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+day;
        String date2 = year2+"-"+month2+"-"+day2;
        int sum = 0;
        int cnt = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        //String testDate = "8/4/2016";
        Cursor cursor = db.rawQuery("select * from "+ tblHistory + " where ("+colDate+ " between '"+date2+"' and '"+date+"') and ("+ colLable +" = 'BGL')" ,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    int val = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbHelper.colDescription)));
                    sum = sum + val;
                    cnt++;
                }while(cursor.moveToNext());
            }
        }

        if(cnt == 0)
            return 0;
        else {
            return sum / cnt;
        }
    }


//    public int getMonthlyGlucoseLevel(){
//        return 0;
//    }

    public Cursor getAllBGLwithTimeStamp()
    {
        String query = "Select * from "+ tblHistory + " where " + colLable + " = 'BGL'";
        return this.getWritableDatabase().rawQuery(query, null);
    }

//    public ArrayList<Activity_Information> getInfoWithFilterdQuery(filterQuery query) {
//        Cursor cursor = getWritableDatabase().rawQuery(query.Query(), null);
//        return cursorToArrayList(cursor);
//    }

    public ArrayList<Activity_Information> cursorToArrayList(Cursor cursor) {
        ArrayList<Activity_Information> result = new ArrayList<>();
        while(cursor.moveToNext()){
            Activity_Information info = new Activity_Information();
            info.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbHelper.colId))));
            info.setLabel(cursor.getString(cursor.getColumnIndex(DbHelper.colLable)));
            info.setDate(cursor.getString(cursor.getColumnIndex(DbHelper.colDate)));
            info.setTime(cursor.getString(cursor.getColumnIndex(DbHelper.colTime)));
            info.setStartTime(cursor.getString(cursor.getColumnIndex(DbHelper.colStartTime)));
            info.setEndTime(cursor.getString(cursor.getColumnIndex(DbHelper.colEndTime)));
            info.setValue(cursor.getString(cursor.getColumnIndex(DbHelper.colDescription)));
            info.setApxCalory(cursor.getString(cursor.getColumnIndex(DbHelper.colApxCalorie)));
            int fasting = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbHelper.colFasting)));
            if(fasting == 1)
                info.setFasting(true);
            else
                info.setFasting(false);
            result.add(info);
        }

        return result;


    }

    public int getBGLStatsMin(String qry){
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cMin = db.rawQuery("select min(bglamount) as MINBGL from ("+qry+")" ,null);
        while(cMin.moveToFirst()) {
            result=(cMin.getInt(cMin.getColumnIndex("MINBGL")));
            break;
        }
        return result;
    }

    public int getBGLStatsAvg(String qry){
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cAvg = db.rawQuery("select avg(bglamount) as AVGBGL from ("+qry+")" ,null);
        while(cAvg.moveToFirst()) {
            result=(cAvg.getInt(cAvg.getColumnIndex("AVGBGL")));
            break;
        }
        return result;
    }

    public int getBGLStatsMax(String qry){
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cMax = db.rawQuery("select max(bglamount) as MAXBGL from ("+qry+")" ,null);
        while(cMax.moveToFirst()) {
            result=(cMax.getInt(cMax.getColumnIndex("MAXBGL")));
            break;
        }
        return result;
    }

    public ArrayList<Integer> getBGLStats(String qry){
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(getBGLStatsMin(qry));
        result.add(getBGLStatsAvg(qry));
        result.add(getBGLStatsMax(qry));
        return result;
    }

}