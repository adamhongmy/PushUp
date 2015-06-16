package com.example.williams.asd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import  com.example.williams.asd.RecordContract.Record;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TodayResult extends ActionBarActivity {
    private MediaPlayer everybodyWinsMusic;
    private MediaPlayer buttonClickSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_result);
        everybodyWinsMusic = MediaPlayer.create(TodayResult.this, R.raw.loading_screen_jingle);
        buttonClickSound = MediaPlayer.create(TodayResult.this, R.raw.button_click);
        everybodyWinsMusic.start();
        TextView txtResult = (TextView)findViewById(R.id.txtResult);

        Intent intent = getIntent();
        if (intent.hasExtra("todayResult")){
            String value = intent.getStringExtra("todayResult");
            txtResult.setText(value);
        }
        Intent intentCountUp = getIntent();
        if (intentCountUp.hasExtra("countUpResult")){
            int value = intentCountUp.getIntExtra("countUpResult", 0);
            txtResult.setText(String.valueOf(value));
        }
        TextView txtResult1;
        txtResult1 = (TextView) findViewById(R.id.txtResult);
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        String myDate = formatter.format(date);


        int txtProgress1;
        int currentFrequency=Integer.parseInt(txtResult1.getText().toString());

        if(noPreviousRecords()==true){
            txtProgress1=0;
        }else{
            txtProgress1=  currentFrequency - getFrequency();
        }

        txtResult1 = (TextView) findViewById(R.id.txtResult);
        UserRecord userRecord = new UserRecord();
        userRecord.setDate(myDate.toString());
        userRecord.setFrequency(currentFrequency);
        userRecord.setProgress(txtProgress1);
        RecordDataSource recordDataSource = new RecordDataSource(this);
        recordDataSource.insertUser(userRecord);

        //Rest.totalPushup=0;
        //Toast.makeText(this, "Record saved!", Toast.LENGTH_SHORT).show();

        String goalToday = txtResult.getText().toString();
        SharedPreferences sharedPreviousPushup = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreviousPushup.edit();
        editor.putString("previousPushup", goalToday);
        editor.commit();
        resetPushUp();

    }

    public void resetPushUp(){
        TotalPushUp.setClick(1);
        TotalPushUp.setCounter(1);
        TotalPushUp.setLastSet(0);
        TotalPushUp.setTargetSet(0);
        TotalPushUp.setTemp(0);

        Rest.setCounter(1);
        Rest.setTotalPushup(0);
        //Toast.makeText(this, "Push up Reset", Toast.LENGTH_SHORT).show();
    }

    public int getFrequency(){
        RecordSQLHelper dbH = new RecordSQLHelper(this);
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT frequency FROM records " , null);
        cursor.moveToLast();
        int frequency = cursor.getInt(0);
        cursor.close();
        db.close();
        return frequency;
    }


    public boolean noPreviousRecords(){

        boolean noRows= true;

        RecordSQLHelper dbH = new RecordSQLHelper(this);
        SQLiteDatabase db = dbH.getReadableDatabase();
        //   Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM records" , null);

        String countQuery = "SELECT  * FROM " + Record.TABLE_NAME;

        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        cursor.close();
        db.close();

        if(rowCount==0){
            noRows=true;
        }else
        {
            noRows=false;
        }

        return noRows;
    }

    public void retrieveRecord(View v)
    {
        buttonClickSound.start();
        everybodyWinsMusic.stop();
        Intent intent = new Intent(this, ShowRecordActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_today_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public void btnDailyRecord(View v){
        TextView txtResult = (TextView)findViewById(R.id.txtResult);
        String goalToday = txtResult.getText().toString();
        SharedPreferences sharedPreviousPushup = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreviousPushup.edit();
        editor.putString("previousPushup", goalToday);
        editor.commit();

        Intent intentDailyRecord = new Intent(TodayResult.this, DailyRecord.class);
        startActivity(intentDailyRecord);

    }
*/
    @Override
    public void onBackPressed()
    {
        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
    public void btnHomeTodayResult(View v)
    {   buttonClickSound.start();
        Intent intent = new Intent(TodayResult.this, MainActivity.class);
        intent.addCategory( Intent.CATEGORY_HOME );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        everybodyWinsMusic.stop();
        finish();
    }


}
