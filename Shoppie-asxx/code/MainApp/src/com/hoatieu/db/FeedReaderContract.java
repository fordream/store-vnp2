package com.hoatieu.db;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "ListShip";
        public static final String COLUMN_NAME_LOCALNAME = "localname";
        public static final String COLUMN_NAME_LOCALTYPE = "localtype";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_MOBILE = "mobile";
        public static final String COLUMN_NAME_UPDATETIME = "updatetime";
    }
    
    
    public static abstract class StormEntry implements BaseColumns {
        public static final String TABLE_NAME = "Storm";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_NAME = "eventName";
        public static final String COLUMN_NAME_TIME = "eventTimeStamp";
        public static final String COLUMN_NAME_DESC = "eventDesc";
    }
    
    
}