package com.nyu.youtoo.youtoo.controllers;

/**
 * The Login Activity is the basic login page where users can
 * enter their username and password to log in to the application.
 * LoginActivity will get displayed only if the user does not
 * have a username already.
 *
 * @author  Ponpoorani Ravichandran
 * @version 1.0
 * @since   2015-03-29
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import com.nyu.youtoo.youtoo.controllers.loginHelper.authenticationChecker;

import com.nyu.youtoo.youtoo.R;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        final EditText userName = (EditText) findViewById(R.id.Username_edittext);
        /**
         * Listening to registration button
         *
         */

        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Registration Screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
               startActivity(i);
            }
        });


        Button btnSettings=(Button)findViewById(R.id.btnLogin);
        //set the username in Sharedpref by calling the setUserSettings() function
        // start the invokeMainActivity() when user clicks on Button
        btnSettings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setUserSettings(userName.getText().toString());
                invokeAuthCheckActivity();
            }
        });

    }
    /**
     * function to assign or set user information into shared preferences
     * @param {String}
     */

    private void setUserSettings(String username)
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("prefUsername", username);
        Boolean flag = editor.commit();
        if(flag==true)
        {
            Log.d("success", "Shared pref added in setUserSettings: " + sharedPrefs.getString("prefUsername",""));
        }
    }

    /**
     * function to invoke the main activity
     *
     */
    void invokeAuthCheckActivity(){
        Intent mainActivityIntent = new Intent(getApplicationContext(), authenticationChecker.class);
        startActivity(mainActivityIntent);
        Log.d("success", "Invoked Main Activity");
    }

}
