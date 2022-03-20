package com.example.sqlitenotesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Table name
    public static final String TABLE_NAME = "NOTES";

    //Table columns
    public static final String ID = "id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";
    public static final String DATE = "date";

    //Database name
    static final String DB_NAME = "Master_sqlite";

    //Database version
    static final int DB_VERSION = 2;

    private static final String DATABASE_ADD_NEW_COLUMN = "ALTER TABLE "
            + TABLE_NAME + " ADD COLUMN " + "new_column" + " string;";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SUBJECT + " TEXT, "
                + DESC + " TEXT, "+ DATE +" TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        if (newVersion == 2) {
            sqLiteDatabase.execSQL(DATABASE_ADD_NEW_COLUMN);
        }
    }
}
