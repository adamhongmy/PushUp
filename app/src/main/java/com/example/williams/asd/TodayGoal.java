package com.example.williams.asd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class TodayGoal extends ActionBarActivity {

    static int totalGoal;
    static int counter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_goal);

        SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
        String previousPushup = sharedPreferences.getString("previousPushup", null);
        TextView txtGoal = (TextView) findViewById(R.id.goal);
        txtGoal.setText("Your last push up is : " + previousPushup);

        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        txtTodayGoal.setText(previousPushup);
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
        if(counter == 1) {
            SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
            String previousPushup = sharedPreferences.getString("previousPushup", null);
            TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
            addTodayGoal = Integer.parseInt(previousPushup) + 1;
            totalGoal = addTodayGoal;
            txtTodayGoal.setText(String.valueOf(totalGoal));
            counter ++;
        }else{
            totalGoal ++;
            TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
            txtTodayGoal.setText(String.valueOf(totalGoal));
        }
    }

    public void btnAddTwo(View v){
        int addTodayGoal;
        if(counter == 1) {
            SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
            String previousPushup = sharedPreferences.getString("previousPushup", null);
            TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
            addTodayGoal = Integer.parseInt(previousPushup) + 3;
            totalGoal = addTodayGoal;
            txtTodayGoal.setText(String.valueOf(totalGoal));
            counter ++;
        }else{
            totalGoal += 3;
            TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
            txtTodayGoal.setText(String.valueOf(totalGoal));
        }
    }

    public void btnAddThree(View v){
        int addTodayGoal;
        if(counter == 1) {
            SharedPreferences sharedPreferences = getSharedPreferences("PreviousPushup", Context.MODE_PRIVATE);
            String previousPushup = sharedPreferences.getString("previousPushup", null);
            TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
            addTodayGoal = Integer.parseInt(previousPushup) + 5;
            totalGoal = addTodayGoal;
            txtTodayGoal.setText(String.valueOf(totalGoal));
            counter ++;
        }else{
            totalGoal += 5;
            TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
            txtTodayGoal.setText(String.valueOf(totalGoal));
        }
    }

    public void btnStart(View v){
        TextView txtTodayGoal = (TextView) findViewById(R.id.todayGoal);
        String target = txtTodayGoal.getText().toString();
        Intent intent = new Intent(TodayGoal.this, TotalPushUp.class);
        intent.putExtra("target", target);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
}
