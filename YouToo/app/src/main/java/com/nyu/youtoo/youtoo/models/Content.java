package com.nyu.youtoo.youtoo.models;

/**
 * Created by Pooh on 27-04-2015.
 */
public class Content {
        String deviceId;
        String regid;
        String description;
        String username;
        Content record;
    public Content(){

    }
        public Content(String d,String r,String de,String u){
            deviceId=d;
            regid=r;
            description=de;
            username=u;
            getContent();
        }
        public void getContent(){
            record.deviceId=deviceId;
            record.regid=deviceId;
            record.description=description;
            record.username=username;

        }
    public String getUsername(){
        return record.username;
    }
}
