package com.example.williams.asd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.williams.asd.RecordContract.Record;
/**
 * Created by USER on 21/3/2015.
 */
public class RecordSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "users.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + RecordContract.Record.TABLE_NAME + "(" +
                    RecordContract.Record.COLUMN_DATE + " TEXT," +
                    RecordContract.Record.COLUMN_FREQUENCY + " TEXT," +
                    RecordContract.Record.COLUMN_PROGRESS + " TEXT )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + RecordContract.Record.TABLE_NAME;

    public RecordSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //   This database is only a cache for online data, so its upgrade
        //   policy is to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);  }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {   onUpgrade(db, oldVersion, newVersion);  }
}
