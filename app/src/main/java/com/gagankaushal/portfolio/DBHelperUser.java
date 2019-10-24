package com.gagankaushal.portfolio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelperUser extends SQLiteOpenHelper {
    public static String DB_NAME = "usermangement";
    public static String DB_TABLE_NAME = "user";
    public static String DB_TABLE_NAME_RATE="rate";
    public static String KEY_NAME = "name";
    public static String KEY_EMAIL = "email";
    public static String KEY_ADDRESS = "address";
    public static String KEY_PASSWORD = "password";
    public static String KEY_PHONE = "phone";
    public static String KEY_ID = "id";
    public static String KEY_MSG="msg";
    public static String KEY_RATING="rating";
    public static String DB_VERSION = "1";

    public DBHelperUser(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "create table " + DB_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " text, " + KEY_EMAIL + " text, " + KEY_PASSWORD + " text, " + KEY_PHONE + " text, " + KEY_ADDRESS + " text)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
        String CREATE_TABLE_RATE="create table "+ DB_TABLE_NAME_RATE +"("+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_MSG+" text, "+ KEY_RATING+" text)";
        sqLiteDatabase.execSQL(CREATE_TABLE_RATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert_user(String name, String email, String password, String phone, String address) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_PHONE, phone);
        values.put(KEY_ADDRESS, address);
        sqLiteDatabase.insert(DB_TABLE_NAME, null, values);
    }

    public void update_user(String id,String name,String email,String password,String phone,String address)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_EMAIL,email);
        contentValues.put(KEY_PASSWORD,password);
        contentValues.put(KEY_PHONE,phone);
        contentValues.put(KEY_ADDRESS,address);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.update(DB_TABLE_NAME,contentValues, KEY_ID +"="+ id,null);
    }

    public void delete_user(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "DELETE FROM " + DB_TABLE_NAME + " WHERE ID IN(" + id + ")";
        sqLiteDatabase.execSQL(sql);
    }




    public  void insert_feedback(String rate,String msg)
    {
        ContentValues values=new ContentValues();
        values.put(KEY_RATING,rate);
        values.put(KEY_MSG,msg);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.insert(DB_TABLE_NAME_RATE,null,values);
    }

    public void delete_feedback(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "DELETE FROM " + DB_TABLE_NAME_RATE + " WHERE ID IN(" + id + ")";
        sqLiteDatabase.execSQL(sql);
    }
    public ArrayList<UserM> getUserData() {
        ArrayList<UserM> userMS = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                // UserM userM=new UserM(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                UserM userM = new UserM(cursor.getString(cursor.getColumnIndex(KEY_ID)), cursor.getString(cursor.getColumnIndex(KEY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_EMAIL)), cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)), cursor.getString(cursor.getColumnIndex(KEY_PHONE)), cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
                userMS.add(userM);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return userMS;
    }

public  ArrayList<RateM> getFeedbackdata()
{
  ArrayList<RateM> ratelist=new ArrayList();
SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
String sql="SELECT * FROM " + DB_TABLE_NAME_RATE;
Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
if(cursor.moveToFirst())
{
    do{
        RateM rateM=new RateM(cursor.getString(cursor.getColumnIndex(KEY_ID)),cursor.getString(cursor.getColumnIndex(KEY_RATING)),cursor.getString(cursor.getColumnIndex(KEY_MSG)));
        ratelist.add(rateM);
    }while (cursor.moveToNext());
}
    return ratelist;
}
}
