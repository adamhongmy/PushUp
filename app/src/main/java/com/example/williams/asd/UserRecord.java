package com.example.williams.asd;
import com.example.williams.asd.RecordContract.Record;
/**
 * Created by USER on 21/3/2015.
 */
public class UserRecord {
    private String date;
    private int frequency;
    private int progress;

    public String getDate()
    {
        return this.date;
    }


    public void setDate(String date)
    {
        this.date = date;
    }


    public int getFrequency()
    {
        return this.frequency;
    }


    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }
    public int getProgress()
    {
        return  this.progress;
    }


    public void setProgress(int progress)
    {
        this.progress = progress;
    }
    public String toString()
    {   return RecordContract.Record.COLUMN_DATE + ":" + this.date +
            "," + RecordContract.Record.COLUMN_FREQUENCY + ":" + this.frequency +
            ","+ RecordContract.Record.COLUMN_PROGRESS + ":" + this.progress;  }
}

