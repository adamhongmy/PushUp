package com.example.williams.asd;

/**
 * Created by USER on 21/3/2015.
 */

import android.provider.BaseColumns;


import android.provider.BaseColumns;
/**
 * Created by USER on 15/3/2015.
 */
public final class RecordContract {

    public RecordContract() {
    }

    public static abstract class Record implements BaseColumns {
        public static final String TABLE_NAME = "records";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_FREQUENCY = "frequency";
        public static final String COLUMN_PROGRESS = "progress";
    }
}

