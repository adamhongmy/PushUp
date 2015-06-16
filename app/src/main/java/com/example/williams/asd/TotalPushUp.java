package com.example.williams.asd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class TotalPushUp extends ActionBarActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int i = 10;
    private int target;
    private int set = 10;
    private static int targetSet;//
    private static int counter = 1;//
    private static int lastSet;//
    private static int temp;//
    private static int click = 1;//
    private RelativeLayout totalPushUpLayout;
    private Button redButton;
    private TextView txt;
    private MediaPlayer pushUpSong;
    private MediaPlayer redButtonSound;
    private MediaPlayer restButtonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_push_up);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        totalPushUpLayout = (RelativeLayout) findViewById(R.id.totalPushUp);
        redButton = (Button) findViewById(R.id.btnRedButton);

        txt = (TextView) findViewById(R.id.textView99);
        Intent intent = getIntent();
        if (intent.hasExtra("target")){
            String value = intent.getStringExtra("target");
            target = Integer.parseInt(value);
            targetSet = target / 10;
            if((target %10) != 0){
                lastSet = target % 10;
                targetSet+=1;
            }else{
                lastSet = 10;
            }
            temp = lastSet;
        }

        redButtonSound = MediaPlayer.create(TotalPushUp.this, R.raw.coins_collect_01);
        restButtonSound = MediaPlayer.create(TotalPushUp.this, R.raw.collect_diamonds_01);
        pushUpSong = MediaPlayer.create(TotalPushUp.this, R.raw.dayofsagittarius);
        pushUpSong.start();
        pushUpSong.setLooping(true);

        if(targetSet == counter){
            TextView finalSet = (TextView) findViewById(R.id.textView99);
            finalSet.setText(String.valueOf(lastSet));
        }else{
            TextView finalSet = (TextView) findViewById(R.id.textView99);
            finalSet.setText(String.valueOf(set));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_total_push_up, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        //if (event.values[0] == 0) {
   if(event.values[0] < event.sensor.getMaximumRange()) {
        redButton.setEnabled(false);
        redButton.setPressed(true);
        countPushup();
        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        redButton.setEnabled(true);
                        redButton.setPressed(false);
                    }
                });
            }
        }, 1000);
      }

    }

    public void countPushup(){
        redButtonSound.start();
        if (targetSet != counter) {
            i--;
            txt.setText(String.valueOf(i));
            if (i == 0) {
                Intent intent = new Intent(TotalPushUp.this, Rest.class);
                intent.putExtra("totalPushup", 10);
                intent.putExtra("targetSet", targetSet);
                counter++;
                pushUpSong.pause();
                startActivity(intent);
            }
        }else if(targetSet == counter){
            lastSet--;
             txt.setText(String.valueOf(lastSet));
            if (lastSet == 0) {
                Intent intent = new Intent(TotalPushUp.this, Rest.class);
                intent.putExtra("totalPushup", temp);
                intent.putExtra("targetSet", targetSet);
                pushUpSong.stop();
                startActivity(intent);
            }

        }
        switch (click){
            case 1: totalPushUpLayout.setBackgroundColor(Color.CYAN);
                click++;
                break;
            case 2: totalPushUpLayout.setBackgroundColor(Color.YELLOW);
                click++;
                break;
            case 3: totalPushUpLayout.setBackgroundColor(Color.GREEN);
                click++;
                break;
            case 4: totalPushUpLayout.setBackgroundColor(Color.MAGENTA);
                click++;
                break;
            case 5: totalPushUpLayout.setBackgroundColor(Color.BLUE);
                click = 1;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        pushUpSong.start();
        pushUpSong.setLooping(true);
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        pushUpSong.pause();
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void btnRest(View v){
        restButtonSound.start();
        Intent intent = new Intent(TotalPushUp.this, Rest.class);
        TextView txt = (TextView)findViewById(R.id.textView99);
        String rest = txt.getText().toString();
        if(targetSet == counter){
            int total = lastSet - Integer.parseInt(rest);
            intent.putExtra("totalPushup", total);
            intent.putExtra("targetSet", targetSet);
            counter++;
            startActivity(intent);
        }else{
            int total = 10 - Integer.parseInt(rest);
            intent.putExtra("totalPushup", total);
            intent.putExtra("targetSet", targetSet);
            counter++;
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(), "Don't give up!",
                Toast.LENGTH_SHORT).show();
       // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    public void btnPressMe(View v){
        countPushup();
    }


    public static void setTargetSet(int targetSet) {
        TotalPushUp.targetSet = targetSet;
    }

    public static void setCounter(int counter) {
        TotalPushUp.counter = counter;
    }

    public static void setLastSet(int lastSet) {
        TotalPushUp.lastSet = lastSet;
    }

    public static void setTemp(int temp) {
        TotalPushUp.temp = temp;
    }

    public static void setClick(int click) {
        TotalPushUp.click = click;
    }

}
