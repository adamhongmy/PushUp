package com.example.williams.asd;

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

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class CountUpPushup extends ActionBarActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Button redButton;
    private RelativeLayout countUpPushupAct;
    private MediaPlayer pushUpSong;
    private MediaPlayer redButtonSound;
    private MediaPlayer restButtonSound;
    private static int click = 1;

    private static int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_up_pushup);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        redButton = (Button) findViewById(R.id.btnRedButton);
        countUpPushupAct = (RelativeLayout) findViewById(R.id.countUpPushup);
        redButtonSound = MediaPlayer.create(CountUpPushup.this, R.raw.coins_collect_01);
        restButtonSound = MediaPlayer.create(CountUpPushup.this, R.raw.collect_diamonds_01);
        pushUpSong = MediaPlayer.create(CountUpPushup.this, R.raw.dayofsagittarius);
        pushUpSong.start();
        pushUpSong.setLooping(true);
        TextView textView = (TextView)findViewById(R.id.txtCountUpPushup);
        textView.setText("0");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count_up_pushup, menu);
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

    public void btnPressMe(View v){
        countPushup();
    }

    public void countPushup(){
        redButtonSound.start();
        TextView txt = (TextView) findViewById(R.id.txtCountUpPushup);
        total++;
        txt.setText(String.valueOf(total));
        switch (click){
            case 1:  countUpPushupAct.setBackgroundColor(Color.CYAN);
                click++;
                break;
            case 2: countUpPushupAct.setBackgroundColor(Color.YELLOW);
                click++;
                break;
            case 3: countUpPushupAct.setBackgroundColor(Color.GREEN);
                click++;
                break;
            case 4: countUpPushupAct.setBackgroundColor(Color.MAGENTA);
                click++;
                break;
            case 5: countUpPushupAct.setBackgroundColor(Color.BLUE);
                click = 1;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void btnComplete(View v){
        restButtonSound.start();
        pushUpSong.stop();
        TextView textView = (TextView)findViewById(R.id.txtCountUpPushup);
        Intent intent = new Intent(CountUpPushup.this, TodayResult.class);
        intent.putExtra("countUpResult", Integer.parseInt(textView.getText().toString()));
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
     // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
}
