package com.whiterabbit.magudesh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.whiterabbit.magudesh.model.Employee;

import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";

    public static final String TABLE_NAME = "Employee";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_IMAGE = "IMAGE";

    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_NAME + " VARVHAR(255)," + COLUMN_EMAIL + " VARVHAR(255), "+ COLUMN_PHONE + " VARVHAR(255), " + COLUMN_IMAGE + " VARVHAR(255));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(Employee emp) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, emp.getName() );
        contentValues.put(COLUMN_EMAIL, emp.getEmail());
        contentValues.put(COLUMN_PHONE, emp.getPhone()==null ? "" : emp.getPhone());
        contentValues.put(COLUMN_IMAGE, emp.getProfileImage());

        long c = database.insert(TABLE_NAME, null, contentValues);
        Log.d("SASKEN", ""+c);
        database.close();
    }

    public ArrayList<Employee> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee empModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                empModel = new Employee();
                empModel.setName(cursor.getString(0));
                empModel.setEmail(cursor.getString(1));
                empModel.setPhone(cursor.getString(2));
                empModel.setProfileImage(cursor.getString(3));

                employees.add(empModel);
            }
        }
        cursor.close();
        database.close();

        return employees;
    }

    public void deleteAllRecords() {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }


}