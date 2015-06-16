package com.example.williams.asd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class Rest extends ActionBarActivity {

    private static final String FORMAT = "%02d";
    private TextView timer;
    private static int counter = 1;
    private static int totalPushup;
    private int recentPushup;
    private int set;
    private CountDownTimer countDownTimer;
    public final static String EXTRA_MESSAGE = "com.example.williams.MESSAGE";
    private MediaPlayer relaxMusic;
    private MediaPlayer btnContSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        btnContSound = MediaPlayer.create(Rest.this, R.raw.elixir_collect_02);
        relaxMusic = MediaPlayer.create(Rest.this, R.raw.harehareyukai);
        relaxMusic.start();
        Intent mIntent = getIntent();
        recentPushup = mIntent.getIntExtra("totalPushup", 0);
        set = mIntent.getIntExtra("targetSet", 0);
        totalPushup += recentPushup;
        if(set == counter) {
            proceedTodayResult();
        }else {
            countDownTimer = new CountDownTimer(30000, 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {
                    timer = (TextView) findViewById(R.id.timer);
                    timer.setText("" + String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                }

                public void onFinish() {
                    proceedTotalPushUp();
                }
            }.start();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rest, menu);
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

    public void btnContinue(View v){

        proceedTotalPushUp();
    }

    public void proceedTodayResult(){
        relaxMusic.stop();
        Intent intent = new Intent(Rest.this, TodayResult.class);
        String message = String.valueOf(totalPushup);
        intent.putExtra("todayResult", message);
        startActivity(intent);
    }

    public void proceedTotalPushUp(){
        relaxMusic.stop();
        btnContSound.start();
        countDownTimer.cancel();
        counter++;
        Intent intent = new Intent(Rest.this, TotalPushUp.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    public static void setTotalPushup(int totalPushup) {
        Rest.totalPushup = totalPushup;
    }

    public static void setCounter(int counter) {
        Rest.counter = counter;
    }
}
