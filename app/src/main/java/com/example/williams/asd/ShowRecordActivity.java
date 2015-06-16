package com.example.williams.asd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;


public class ShowRecordActivity extends Activity {

    private RecordDataSource datasource;

    public void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);
        datasource = new RecordDataSource(this);
        datasource.open();
        final List<UserRecord> values = datasource.getAllUsers();
        final ListView listview = (ListView) findViewById(R.id.ListView1);

        RecordsAdapter adapter = new RecordsAdapter(this,values);
        listview.setAdapter(adapter);


    }
    public void homePage(View v)
    {
        Intent intent = new Intent(ShowRecordActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        datasource.open();
        super.onResume();
    }
    protected void onPause(){
        datasource.close();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}