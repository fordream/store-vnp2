package com.hoatieu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hoatieu.db.FeedReaderContract.FeedEntry;
import com.hoatieu.db.FeedReaderContract.StormEntry;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hoatieu.db";
    private static final String VARCHAR_TYPE = " VARCHAR";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
    	    "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
    	    FeedEntry.COLUMN_NAME_LOCALNAME +VARCHAR_TYPE+ COMMA_SEP +
    	    FeedEntry.COLUMN_NAME_LOCALTYPE + VARCHAR_TYPE + COMMA_SEP +
    	    FeedEntry.COLUMN_NAME_STATUS + VARCHAR_TYPE + COMMA_SEP +
    	    FeedEntry.COLUMN_NAME_LONGITUDE + VARCHAR_TYPE + COMMA_SEP +
    	    FeedEntry.COLUMN_NAME_LATITUDE + VARCHAR_TYPE + COMMA_SEP +
    	    FeedEntry.COLUMN_NAME_MOBILE + VARCHAR_TYPE + COMMA_SEP +
    	    FeedEntry.COLUMN_NAME_UPDATETIME + VARCHAR_TYPE +  
    	    " )";
    
    private static final String SQL_CREATE_STORM =
    	    "CREATE TABLE " + StormEntry.TABLE_NAME + " (" +
    	    StormEntry.COLUMN_NAME_LONGITUDE + VARCHAR_TYPE + COMMA_SEP +
    	    StormEntry.COLUMN_NAME_LATITUDE + VARCHAR_TYPE + COMMA_SEP +
    	    StormEntry.COLUMN_NAME_NAME + VARCHAR_TYPE + COMMA_SEP +
    	    StormEntry.COLUMN_NAME_DESC + VARCHAR_TYPE + COMMA_SEP +
    	    StormEntry.COLUMN_NAME_TIME + VARCHAR_TYPE +  
    	    " )";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
    	 db.execSQL(SQL_CREATE_ENTRIES);
    	 db.execSQL(SQL_CREATE_STORM);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
