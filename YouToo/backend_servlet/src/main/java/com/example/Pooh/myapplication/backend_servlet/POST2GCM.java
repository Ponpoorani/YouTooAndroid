package com.example.Pooh.myapplication.backend_servlet;

/**
 * Created by Pooh on 27-04-2015.
 */


import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
public class POST2GCM {
    JSONObject json=new JSONObject();
    String apiKey="AIzaSyBCCD_1M4HaECwoFM1xvH9lGRAWMQ34XsE";


    public POST2GCM(JSONObject sendtogcm) {
        json=sendtogcm;
    }

    public String post(){




           String response;
            String url="https://android.googleapis.com/gcm/send";
            URL object= null;
            try {
                object = new URL(url);
            } catch (MalformedURLException e) {
                return "Url malformed---"+"---------"+e.getMessage();
            }

            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) object.openConnection();
            } catch (IOException e) {
               return "open connection---"+"---------" + e.getMessage();
            }

            con.setDoOutput(true);

            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json");

            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "key="+apiKey);

            try {
                con.setRequestMethod("POST");
            } catch (ProtocolException e) {
                return"Post request---"+ "---------" + e.getMessage();
            }

            OutputStreamWriter wr=null;
            try {
                wr= new OutputStreamWriter(con.getOutputStream());
            } catch (IOException e) {
               return "out put stream---"+ "---------" + e.getMessage();
            }


            try {
                wr.write(json.toString());
                wr.flush(); wr.close();
            } catch (IOException e) {
                return "write stream---"+"---------" + e.getMessage();
            }

            StringBuilder sb = new StringBuilder();


            int HttpResult = 0;
            try {
                HttpResult = con.getResponseCode();
            } catch (IOException e) {
                return "response code-gcm----"+  e.getMessage();
            }

            if(HttpResult ==HttpURLConnection.HTTP_OK){

                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
                } catch (IOException e) {
                    return "InputStream---"+ "---------" + e.getMessage();
                }

                String line = null;

                try {
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                   return "br.readLine---"+"---------" + e.getMessage();
                }

                try {
                    br.close();
                } catch (IOException e) {
                    return "br.close---"+ "---------" + e.getMessage();
                }
                response="response String:"+ sb.toString();
                return response;

            }else {
                try {
                    return "response String:"+ con.getResponseMessage();
                } catch (IOException e) {
                    return "getResponseMesg---"+ "---------" + e.getMessage();
                }
            }
            }



}
