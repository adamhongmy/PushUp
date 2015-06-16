package com.example.williams.asd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import  com.example.williams.asd.RecordContract.Record;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 21/3/2015.
 */
public class RecordDataSource {
    private SQLiteDatabase database;
    private RecordSQLHelper dbHelper;
    private String[] allColumn = {
            RecordContract.Record.COLUMN_DATE,
            RecordContract.Record.COLUMN_FREQUENCY,
            RecordContract.Record.COLUMN_PROGRESS
    };
    public RecordDataSource(Context context){
        dbHelper = new RecordSQLHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public void insertUser(UserRecord userRecord){
        ContentValues values = new ContentValues();
        values.put(RecordContract.Record.COLUMN_DATE, userRecord.getDate());
        values.put(RecordContract.Record.COLUMN_FREQUENCY, userRecord.getFrequency());
        values.put(RecordContract.Record.COLUMN_PROGRESS, userRecord.getProgress());
        database = dbHelper.getWritableDatabase();
        database.insert(RecordContract.Record.TABLE_NAME, null, values);
        database.close();  }

    public List<UserRecord> getAllUsers(){
        List<UserRecord> records = new ArrayList<UserRecord>();
        Cursor cursor = database.query(RecordContract.Record.TABLE_NAME, allColumn , null,  null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            UserRecord userRecord = new UserRecord();
            userRecord.setDate(cursor.getString(0));
            userRecord.setFrequency((cursor.getString(1)==null? 0:cursor.getInt(1)));
            userRecord.setProgress((cursor.getString(2)==null? 0:cursor.getInt(2)));
            records.add(userRecord);
            cursor.moveToNext();
        }
        cursor.close();
        return records;

    }
}