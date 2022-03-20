package com.example.sqlitenotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper databaseHelper;
    private final Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public void insert(String subject, String desc, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, subject);
        contentValues.put(DatabaseHelper.DESC, desc);
        contentValues.put(DatabaseHelper.DATE, date);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                DatabaseHelper.ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC, DatabaseHelper.DATE
        };
        return sqLiteDatabase.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null
        , null);
    }

    public void update(long id, String subject, String desc, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, subject);
        contentValues.put(DatabaseHelper.DESC, desc);
        contentValues.put(DatabaseHelper.DATE, date);
        sqLiteDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + id, null);
    }

    public void delete(long id) {
        sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + " = " +id, null);
    }
}
