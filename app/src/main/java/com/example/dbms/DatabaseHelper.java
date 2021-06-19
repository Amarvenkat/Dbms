package com.example.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Ticket.db";
    public static final String TABLE_NAME = "Ticket_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DEPATURE";
    public static final String COL_4 = "ARRIVAL";
    public static final String COL_5 = "DATE";
    public static final String COL_6 = "TIME";
    public static final String COL_7 = "CLASS_PREFERENCES";
    public static final String COL_8 = "SEAT_NO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME,DEPATURE,ARRIVAL,DATE,TIME,CLASS_PREFERENCES,SEAT_NO)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String depature,String arrival,String date,String time,String class_preferences,String seat_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,depature);
        contentValues.put(COL_4,arrival);
        contentValues.put(COL_5,date);
        contentValues.put(COL_6,time);
        contentValues.put(COL_7,class_preferences);
        contentValues.put(COL_8,seat_no);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String depature,String arrival,String date,String time,String class_preferences,String seat_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,depature);
        contentValues.put(COL_4,arrival);
        contentValues.put(COL_5,date);
        contentValues.put(COL_6,time);
        contentValues.put(COL_7,class_preferences);
        contentValues.put(COL_8,seat_no);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
