package com.nyu.youtoo.youtoo.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rakshith on 4/6/2015.
 */
public class YouTooDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "YouToo";
    public static final String TABLE_TOPIC = "Topic";
    public static final String COLUMN_TOPIC_ID = "TopicID";
    public static final String COLUMN_TOPIC_NAME = "TopicName";
    public static final String COLUMN_TOPIC_DESCRIPTION = "TopicDescription";

    public YouTooDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_TOPIC + "(" + COLUMN_TOPIC_ID + " integer primary key autoincrement,"+
                COLUMN_TOPIC_NAME + " varchar(50),"+ COLUMN_TOPIC_DESCRIPTION + " varchar(1000))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_TOPIC);
        onCreate(db);
    }
}
