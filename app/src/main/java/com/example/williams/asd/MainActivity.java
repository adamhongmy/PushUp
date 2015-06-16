package com.example.williams.asd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private RecordDataSource datasource;
    private MediaPlayer mainThemeSong;
    private MediaPlayer buttonClickSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainThemeSong = MediaPlayer.create(MainActivity.this, R.raw.dumbwaystodie);
        buttonClickSound = MediaPlayer.create(MainActivity.this, R.raw.button_click);
        mainThemeSong.start();
        mainThemeSong.setLooping(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void btnStart(View v){
        mainThemeSong.stop();
        buttonClickSound.start();
        SharedPreferences myPrefs = this.getSharedPreferences("PreviousPushup", MODE_WORLD_READABLE);
        String check = myPrefs.getString("previousPushup",null);
        Intent intent;
        if(check == null){
            intent = new Intent(MainActivity.this, Tutorial.class);
        }else{
            intent = new Intent(MainActivity.this, TodayGoalBeginner.class);
        }
        startActivity(intent);
        finish();
    }

    public void btnDailyRecords(View v){
        buttonClickSound.start();
        mainThemeSong.pause();
        Intent intent = new Intent(MainActivity.this, ShowRecordActivity.class);
        startActivity(intent);
        finish();
    }

    public void btnExit(View v){
        buttonClickSound.start();
        mainThemeSong.stop();
        finish();
        System.exit(1);

    }

    public void btnSettings(View v){
        buttonClickSound.start();
        Intent intent = new Intent(MainActivity.this, Notication.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {

        Toast.makeText(getApplicationContext(), "Back key was been disabled. Hit Exit button instead.",
                Toast.LENGTH_LONG).show();

      // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    protected void onPause() {
        mainThemeSong.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mainThemeSong.start();
        mainThemeSong.setLooping(true);
        super.onResume();
    }
}
