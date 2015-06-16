package com.example.williams.asd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TodayGoalBeginner extends ActionBarActivity {
    private MediaPlayer buttonClickSound;
    private static int totalGoal;
    private int counter = 1;
    private Button btnAddOne;
    private Button btnAddTwo;
    private Button btnAddThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_goal_beginner);

        btnAddOne = (Button) findViewById(R.id.btnAddOne);
        btnAddTwo = (Button) findViewById(R.id.btnAddTwo);
        btnAddThree = (Button) findViewById(R.id.btnAddThree);
        buttonClickSound = MediaPlayer.create(TodayGoalBeginner.this,R.raw.button_click);

        SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        String previousPushup = sharedPreferences.getString("previousPushup", null);

        TextView txtGoal = (TextView) findViewById(R.id.textViewLastPushup);
        txtGoal.setText(previousPushup);

        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        txtTodayGoal.setText(previousPushup);
        btnAddOne.setPressed(false);
        btnAddTwo.setPressed(false);
        btnAddThree.setPressed(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_today_goal, menu);
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
        }else if(id == R.id.action_begineer){
            Intent intent = new Intent(this, TodayGoalBeginner.class);
            startActivity(intent);
        }else if(id == R.id.action_advance){
            Intent intent = new Intent(this, TodayGoal.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnAddOne(View v){
        int addTodayGoal;
        buttonClickSound.start();
        SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        String previousPushup = sharedPreferences.getString("previousPushup", null);
        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        addTodayGoal = Integer.parseInt(previousPushup) + 1;
        totalGoal = addTodayGoal;
        txtTodayGoal.setText(String.valueOf(totalGoal));
        btnAddOne.setPressed(true);
        btnAddTwo.setPressed(false);
        btnAddThree.setPressed(false);

    }

    public void btnAddTwo(View v){
        int addTodayGoal;
        buttonClickSound.start();
        SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        String previousPushup = sharedPreferences.getString("previousPushup", null);
        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        addTodayGoal = Integer.parseInt(previousPushup) + 3;
        totalGoal = addTodayGoal;
        txtTodayGoal.setText(String.valueOf(totalGoal));
        counter ++;
        btnAddOne.setPressed(false);
        btnAddTwo.setPressed(true);
        btnAddThree.setPressed(false);
    }


    public void btnAddThree(View v){
        int addTodayGoal;
        buttonClickSound.start();
        SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        String previousPushup = sharedPreferences.getString("previousPushup", null);
        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        addTodayGoal = Integer.parseInt(previousPushup) + 5;
        totalGoal = addTodayGoal;
        txtTodayGoal.setText(String.valueOf(totalGoal));
        counter ++;
        btnAddOne.setPressed(false);
        btnAddTwo.setPressed(false);
        btnAddThree.setPressed(true);
    }

    public void btnStart(View v){
        buttonClickSound.start();
        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        String target = txtTodayGoal.getText().toString();
        String key = "GOAL";
        Intent intent = new Intent(TodayGoalBeginner.this, TotalPushUp.class);
        intent.putExtra("target", target);
        intent.putExtra("key", key);
        startActivity(intent);
        finish();
    }

    public void btnBack(View v){
        buttonClickSound.start();
        Intent intent = new Intent(TodayGoalBeginner.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
}
