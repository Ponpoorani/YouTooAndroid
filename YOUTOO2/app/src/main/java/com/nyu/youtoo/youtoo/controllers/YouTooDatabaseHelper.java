package com.nyu.youtoo.youtoo.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nyu.youtoo.youtoo.models.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakshith on 4/4/2015.
 */
public class YouTooDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "YouToo";
    private static final String TABLE_TOPIC = "Topic";
    private static final String COLUMN_TOPIC_ID = "TopicID";
    private static final String COLUMN_TOPIC_NAME = "TopicName";
    private static final String COLUMN_TOPIC_DESCRIPTION = "TopicDescription";

    public YouTooDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table" + TABLE_TOPIC + "(" + COLUMN_TOPIC_ID + "integer primary key autoincrement,"+
                COLUMN_TOPIC_NAME + "varchar(50),"+ COLUMN_TOPIC_DESCRIPTION + "varchar(1000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists" + TABLE_TOPIC);
        onCreate(db);
    }

    public long insertTopic(Topic topic){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TOPIC_NAME, topic.getTopicName());
        cv.put(COLUMN_TOPIC_DESCRIPTION, topic.getTopicDescription());
        return getWritableDatabase().insert(TABLE_TOPIC,null,cv);
    }

    public List<Topic> getTopics(){
        List<Topic> topicList = new ArrayList<Topic>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from" + TABLE_TOPIC,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Topic topic = new Topic();
            topic.setTopicName(cursor.getString(0));
            topic.setTopicDescription(cursor.getString(1));
            topicList.add(topic);
        }
        return topicList;
    }
}
