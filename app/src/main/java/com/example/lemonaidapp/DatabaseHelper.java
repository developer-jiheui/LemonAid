package com.example.lemonaidapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "lemonAidDB.db";

    final static int DATABASE_VERSION = 6;
    final static String TABLE1_NAME = "Patient_table";
    final static String T1COL_1 = "user_Id";
    final static String T1COL_2 = "f_Name";
    final static String T1COL_3 = "l_Name";
    final static String T1COL_4 = "email_Add";
    final static String T1COL_5 = "phone_Num";
    final static String T1COL_6 = "msp_Num";
    final static String T1COL_7 = "age";
    final static String T1COL_8 = "postal_Code";
    final static String T1COL_9 = "isAdminCreated";
    final static String T1COL_10 = "amountOwed";


    final static String TABLE2_NAME = "Staff_table";
    final static String T2COL_1 = "staff_Id";
    final static String T2COL_2 = "f_Name";
    final static String T2COL_3 = "l_Name";
    final static String T2COL_4 = "email_AddS";
    final static String T2COL_5 = "phone_Num";
    final static String T2COL_6 = "stuff_Type";
    final static String T2COL_7 = "office_Id";

    final static String TABLE3_NAME = "Office_table";
    final static String T3COL_1 = "office_Id";
    final static String T3COL_2 = "country";
    final static String T3COL_3 = "province";
    final static String T3COL_4 = "street_address";
    final static String T3COL_5 = "postal_Code";

    final static String TABLE4_NAME = "Login_table";
    final static String T4COL_1 = "email_Add";
    final static String T4COL_2 = "password";

    final static String TABLE5_NAME = "Appointment_table";
    final static String T5COL_1 = "appt_id";
    final static String T5COL_2 = "staff_Id";
    final static String T5COL_3 = "email_Add";
    final static String T5COL_4 = "start_Time";
    final static String T5COL_5 = "end_Time";
    final static String T5COL_6 = "didHappen";

    final static String TABLE6_NAME = "Transaction_table";
    final static String T6COL_1 = "comment_id";
    final static String T6COL_2 = "amount_Owe";
    final static String T6COL_3 = "email_Add";
    final static String T6COL_4 = "dateOfCharge";

    final static String TABLE7_NAME = "Comment_table";
    final static String T7COL_1 = "comment_id";
    final static String T7COL_2 = "staff_Id";
    final static String T7COL_3 = "email_Add";
    final static String T7COL_4 = "patientMessage";
    final static String T7COL_5 = "doctorReply";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE1_NAME + "(" + T1COL_1 + " INTEGER PRIMARY KEY," + T1COL_2 + " TEXT," +
                T1COL_3 + " TEXT," + T1COL_4 + " TEXT," + T1COL_5 + " TEXT," + T1COL_6 + " TEXT," + T1COL_7 + " INTEGER," + T1COL_8 + " TEXT," + T1COL_9 + " TEXT," + T1COL_10 + " TEXT)";
        db.execSQL(query);
        String query2 = "CREATE TABLE " + TABLE2_NAME + "(" + T2COL_1 + " INTEGER PRIMARY KEY," + T2COL_2 + " TEXT," +
                T2COL_3 + " TEXT," + T2COL_4 + " TEXT," + T2COL_5 + " TEXT," + T2COL_6 + " TEXT," + T2COL_7 + " INTEGER)";

        db.execSQL(query2);
        String query3 = "CREATE TABLE " + TABLE3_NAME + "(" + T3COL_1 + " INTEGER PRIMARY KEY," + T3COL_2 + " TEXT," +
                T3COL_3 + " TEXT," + T3COL_4 + " TEXT," + T3COL_5 + " TEXT)";
        db.execSQL(query3);
        String query4 = "CREATE TABLE " + TABLE4_NAME + "(" + T4COL_1 + " TEXT," + T4COL_2 + " TEXT)";
        db.execSQL(query4);
        String query5 = "CREATE TABLE " + TABLE5_NAME + "(" + T5COL_1 + " INTEGER PRIMARY KEY," + T5COL_2 + " INTEGER," + T5COL_3 + " TEXT," + T5COL_4 + " TEXT," + T5COL_5 + " TEXT,"
                + T5COL_6 + " TEXT)";
        db.execSQL(query5);
        String query6 = "CREATE TABLE " + TABLE6_NAME + "(" + T6COL_1 + " INTEGER," + T6COL_2 + " INTEGER," + T6COL_3 + " TEXT," + T6COL_4+ " TEXT)";
        db.execSQL(query6);
        String query7 = "CREATE TABLE " + TABLE7_NAME + "(" + T7COL_1 + " INTEGER PRIMARY KEY," + T7COL_2 + " INTEGER," + T7COL_3 + " TEXT," + T7COL_4 + " TEXT," + T7COL_5 + " TEXT)";
        db.execSQL(query7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE7_NAME);
        onCreate(db);
    }

    public boolean addrecordPatient(String fN, String lN, String emAdd, String phNum, String mspNum, int age, String postalC, boolean adminC) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL_2, fN);
        contentValues.put(T1COL_3, lN);
        contentValues.put(T1COL_4, emAdd);
        contentValues.put(T1COL_5, phNum);
        contentValues.put(T1COL_6, mspNum);
        contentValues.put(T1COL_7, age);
        contentValues.put(T1COL_8, postalC);
        contentValues.put(T1COL_9, adminC);
        contentValues.put(T1COL_10,"0");
        long r = db.insert(TABLE1_NAME, null, contentValues);
        if (r > 0) {
            return true;
        } else
            return false;
    }

    public boolean addrecordStaff(String fN, String lN, String emAdd, String phNum, String userType, int oID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T2COL_2, fN);
        contentValues.put(T2COL_3, lN);
        contentValues.put(T2COL_4, emAdd);
        contentValues.put(T2COL_5, phNum);
        contentValues.put(T2COL_6, userType);
        contentValues.put(T2COL_7, oID);
        long r = db.insert(TABLE2_NAME, null, contentValues);
        if (r > 0) {
            return true;
        } else
            return false;
    }

    public void addrecordOffice(String c, String pr, String strAdd, String postalC) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T3COL_2, c);
        contentValues.put(T3COL_3, pr);
        contentValues.put(T3COL_4, strAdd);
        contentValues.put(T3COL_5, postalC);
        db.insert(TABLE3_NAME, null, contentValues);
    }

    public boolean addrecordLogin(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T4COL_1, email);
        contentValues.put(T4COL_2, pass);
        long r = db.insert(TABLE4_NAME, null, contentValues);
        if (r > 0) {
            return true;
        } else
            return false;
    }

    public boolean addrecordAppt(int staff_id, String patient_email, String start, String end, String didHappen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T5COL_2, staff_id);
        contentValues.put(T5COL_3, patient_email);
        contentValues.put(T5COL_4, start);
        contentValues.put(T5COL_5, end);
        contentValues.put(T5COL_6, didHappen);
        long r = db.insert(TABLE5_NAME, null, contentValues);
        if (r > 0) {
            return true;
        } else
            return false;
    }

    public boolean addrecordTransaction(int appt_id, int amountOwed, String patient_email, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T6COL_1, appt_id);
        contentValues.put(T6COL_2, amountOwed);
        contentValues.put(T6COL_3, patient_email);
        contentValues.put(T6COL_4,date);

        long r = db.insert(TABLE6_NAME, null, contentValues);
        if (r > 0) {
            return true;
        } else
            return false;
    }

    public boolean addrecordComment(int staff_id, String email, String patientMessage, String doctorReply) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T7COL_2, staff_id);
        contentValues.put(T7COL_3, email);
        contentValues.put(T7COL_4, patientMessage);
        contentValues.put(T7COL_5, doctorReply);
        long r = db.insert(TABLE7_NAME, null, contentValues);
        if (r > 0) {
            return true;
        } else
            return false;
    }

    public String getPasswordForLogin(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(email)};
        Cursor c = db.rawQuery("select * from Login_table where " + T4COL_1 + " =?", selectionArgs);
        if (c.moveToFirst())
            return c.getString(c.getColumnIndex(T4COL_2));
        return "";

    }
    public ArrayList<Integer> getAllAmountOwed(String email){
        ArrayList<Integer> listOfCharges=new ArrayList<Integer>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(email)};
        Cursor c = db.rawQuery("select * from Transaction_table where " + T6COL_3 + " =?", selectionArgs);
        while(c.moveToNext()) {
            listOfCharges.add(c.getInt(c.getColumnIndex(T6COL_2)));
        }
        return listOfCharges;
    }
    public ArrayList<String> getAllDatesOfCharges(String email){
        ArrayList<String> listOfDates=new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(email)};
        Cursor c = db.rawQuery("select * from Transaction_table where " + T6COL_3 + " =?", selectionArgs);
        while(c.moveToNext()) {
            listOfDates.add(c.getString(c.getColumnIndex(T6COL_4)));
        }
        return listOfDates;
    }


    public String getdataPatient(String email, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(email)};

        Cursor c = db.rawQuery("select * from Patient_table where " + T1COL_4 + " =?", selectionArgs);
        if (c.moveToFirst()) {
            if (i == 1) {
                return c.getString(c.getColumnIndex(T1COL_2));
            }
            if (i == 2) {
                return c.getString(c.getColumnIndex(T1COL_3));
            }
            if (i == 3) {
                return c.getString(c.getColumnIndex(T1COL_5));
            }
            if (i == 4) {
                return c.getString(c.getColumnIndex(T1COL_6));
            }
            if (i == 5) {
                return c.getString(c.getColumnIndex(T1COL_7));
            }
            if (i == 6) {
                return c.getString(c.getColumnIndex(T1COL_8));
            }
            if (i == 7)
                return c.getString(c.getColumnIndex(T1COL_9));
            if (i ==8)
                return c.getString(c.getColumnIndex(T1COL_10));

        }
        return "";
    }
    public boolean updateAmountOwed (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL_10,"0");
        int update =  db.update(TABLE1_NAME,contentValues, "email_Add =?", new String[]{email});
        if (update >0){
            return true;
        }
        else
            return false;
    }

    public void updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T4COL_2,password);
        int update =  db.update(TABLE4_NAME,contentValues, "email_Add =?", new String[]{email});
    }
}



