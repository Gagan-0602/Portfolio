package com.gagankaushal.portfolio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelperProject extends SQLiteOpenHelper {
    public static String DB_NAME = "portfolio";
    public static String DB_TABLE_NAME = "register";
    public static String DB_TABLE_NAME_CONTACT = "contact";
    public static String DB_TABLE_NAME_RATEUS = "rateus";
    public static String DB_TABLE_NAME_PROJECT = "projecttable";
    public static String DB_TABLE_NAME_CONTACT_US = "contactus";
    public static String KEY_SUBJECT = "subject";
    public static String KEY_MSG = "msg";
    public static String KEY_USER_ID = "userid";
    public static String KEY_ID = "id";
    public static String KEY_NAME = "name";
    public static String KEY_EMAIL = "email";
    public static String KEY__PASSWORD = "password";
    public static String KEY_ADDRESS = "address";
    public static String KEY_PHONE = "phone";
    public static String DB_VERSION = "1";
    public static String KEY_RATING = "rating";
    public static String KEY_PROJECT_DESC = "projectdescripition";
    public static String KEY_TITLE = "title";
    public static String KEY_IMG = "image";


    public DBHelperProject(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "create table IF NOT EXISTS " + DB_TABLE_NAME + "(" + KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " text," + KEY_EMAIL + " text," + KEY__PASSWORD + " text," + KEY_ADDRESS + " text," + KEY_PHONE + " text )";
        sqLiteDatabase.execSQL(CREATE_TABLE);
        String CREATE_TABLE_CONTACT = "create table IF NOT EXISTS " + DB_TABLE_NAME_CONTACT + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_ID + " INTEGER, " + KEY_SUBJECT + " text," + KEY_MSG + " text)";
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT);
        String CREATE_TABLE_RATEUS = "create table IF NOT EXISTS " + DB_TABLE_NAME_RATEUS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_ID + " INTEGER, " + KEY_MSG + " text," + KEY_RATING + " text)";
        Log.e("rate_query", CREATE_TABLE_RATEUS);
        sqLiteDatabase.execSQL(CREATE_TABLE_RATEUS);
        String CREATE_TABLE_PROJECT = "create table " + DB_TABLE_NAME_PROJECT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_ID + " INTEGER," + KEY_TITLE + " TEXT," + KEY_PROJECT_DESC + " TEXT," + KEY_IMG + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_PROJECT);
        String CREATE_TABLE_CONTACTUS = "create table  " + DB_TABLE_NAME_CONTACT_US + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SUBJECT + " text," + KEY_MSG + " text)";
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACTUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String name, String email, String password, String address, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY__PASSWORD, password);
        contentValues.put(KEY_ADDRESS, address);
        contentValues.put(KEY_PHONE, phone);
        sqLiteDatabase.insert(DB_TABLE_NAME, null, contentValues);
    }

    public void insert1(String subject, String msg, String userid) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_ID, userid);
        contentValues.put(KEY_SUBJECT, subject);
        contentValues.put(KEY_MSG, msg);
        sqLiteDatabase.insert(DB_TABLE_NAME_CONTACT, null, contentValues);
    }

    public void insert2(String userid, String msg, String rating) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_ID, userid);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_MSG, msg);
        sqLiteDatabase.insert(DB_TABLE_NAME_RATEUS, null, contentValues);
    }

    public void insert3(String userid, String img, String title, String projects) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, userid);
        values.put(KEY_IMG, img);
        values.put(KEY_TITLE, title);
        values.put(KEY_PROJECT_DESC, projects);
        sqLiteDatabase.insert(DB_TABLE_NAME_PROJECT, null, values);

    }

    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "DELETE FROM " + DB_TABLE_NAME_PROJECT + " WHERE ID IN(" + id + ")";
        sqLiteDatabase.execSQL(sql);
    }

    public void update(String id, String title, String projects) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_PROJECT_DESC, projects);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(DB_TABLE_NAME_PROJECT, values, KEY_ID + "=" + id, null);


    }

    public void contact_insert(String subject, String msg) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SUBJECT, subject);
        contentValues.put(KEY_MSG, msg);
        sqLiteDatabase.insert(DB_TABLE_NAME_CONTACT_US, null, contentValues);
    }

    public void contact_update(String id,String subject,String msg)
    {
       ContentValues values=new ContentValues();
       values.put(KEY_ID,id);
       values.put(KEY_SUBJECT,subject);
       values.put(KEY_MSG,msg);
       SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       sqLiteDatabase.update(DB_TABLE_NAME_CONTACT_US,values, KEY_ID +"="+ id,null);


    }
    public void contact_delete(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "DELETE FROM " + DB_TABLE_NAME_CONTACT_US + " WHERE ID IN(" + id + ")";
        sqLiteDatabase.execSQL(sql);
    }
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public boolean login(String mail1, String pass1) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + KEY_EMAIL + "=? AND " + KEY__PASSWORD + " =? ", new String[]{mail1, pass1});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }


    public ArrayList<PortfolioM> getAllData() {
        ArrayList<PortfolioM> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = " SELECT * FROM " + DB_TABLE_NAME_PROJECT;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                PortfolioM portfolioM = new PortfolioM(cursor.getString(0), cursor.getString(4), cursor.getString(2), cursor.getString(2));
                arrayList.add(portfolioM);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return arrayList;

    }

    public ArrayList<PortfolioM> getProjectdata() {
        ArrayList<PortfolioM> arrayList1 = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_NAME_PROJECT;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {

            do {
                PortfolioM portfolioM = new PortfolioM(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                arrayList1.add(portfolioM);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();
        return arrayList1;

    }

    public ArrayList<ContactM> getContactdata() {
        ArrayList<ContactM> list1 = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql1 = "SELECT * FROM " + DB_TABLE_NAME_CONTACT_US;
        Cursor cursor = sqLiteDatabase.rawQuery(sql1, null);
        if (cursor.moveToFirst()) {

            do {
                ContactM contactM = new ContactM(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                list1.add(contactM);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();
        return list1;


    }
}

