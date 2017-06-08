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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import com.nyu.youtoo.youtoo.R;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {

    private static final int SETTINGS_RESULT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserName();

        setContentView(R.layout.activity_main);

        Button btnSettings=(Button)findViewById(R.id.settings);
        // start the UserSettingActivity when user clicks on Button
        btnSettings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), UserSettingActivity.class);
                startActivityForResult(i, SETTINGS_RESULT);
            }
        });

        Button letsConnect=(Button)findViewById(R.id.letsConnect);
        // start the SendMessageActivity when user clicks on Button
        letsConnect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), SendMessageActivity.class);
                startActivity(i);
            }
        });

        Button receivedMessages=(Button)findViewById(R.id.receivedMessages);
        // start the ViewMsgReceivedActivity when user clicks on Button
        receivedMessages.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ViewMsgReceivedActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SETTINGS_RESULT)
        {
            // to-do
        }

    }

    /**
     * function to check whether user is already logged into shared preferences
     * by checking if the username is empty
     * @param {}
     */

    private void checkUserName()
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String username = sharedPrefs.getString("prefUsername","");
        Log.d("success", "Checkusername in MainActivity: " + username);
        if(username.isEmpty() || username ==null)
        {
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
        } else
        {
            Log.d("success", "Shared pref added in checkusername: " + username);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
