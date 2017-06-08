package com.nyu.youtoo.youtoo.controllers;

/**
 * Created by Ponpoorani on 3/30/2015.
 */

/**
 * The RegisterActivity.java displays the registration page.
 * First time users of the application can register with
 * their username, passowrd and their email address.
 *
 *
 * @author  Ponpoorani Ravichandran
 * @version 1.0
 * @since   2015-03-30
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nyu.youtoo.youtoo.R;

public class RegisterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);

        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
    }
}
