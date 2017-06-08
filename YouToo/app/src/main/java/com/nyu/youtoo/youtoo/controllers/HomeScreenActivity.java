package com.nyu.youtoo.youtoo.controllers;

/**
 * The MainActivity.java is the first page that will pop up
 * as long as the user has already logged in with their
 * username. There are four options available here.
 * User can choose any.
 *
 * @author  Ponpoorani Ravichandran
 * @version 1.0
 * @since   2015-03-29
 */

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nispok.snackbar.Snackbar;
import com.nyu.youtoo.youtoo.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends Activity {


    String username="user_default_name1";
    String password="user_default_password1";

    private HelperSharedPreferences _appPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home_screen);
        setContentView(R.layout.activity_detail);


        _appPrefs = new HelperSharedPreferences(getApplicationContext());
        // getting the string from prefs
        String prefUsername = _appPrefs.getUsername();
        Log.d("success", "In MainActivity: " + prefUsername);

        if(prefUsername.isEmpty() || prefUsername ==null)
        {
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);


        } else
        {
            Log.d("success", "Shared pref added in checkusername: " + prefUsername);
        }




        Intent intent = getIntent();
        Bundle b=intent.getExtras();
        String pwd = null;
        if(b!=null)
        {
            pwd=b.getString("Password");
            Log.d("Pwd",pwd);
        }

        password=pwd;
        username=prefUsername;

        TextView loginId = (TextView) findViewById(R.id.loginid);
        loginId.setText("Hello " + prefUsername + " !!!");









    }

    }












