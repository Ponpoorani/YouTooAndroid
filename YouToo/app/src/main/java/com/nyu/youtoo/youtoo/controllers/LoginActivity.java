package com.nyu.youtoo.youtoo.controllers;

/**
 * Created by Ponpoorani on 3/29/2015.
 */

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.nyu.youtoo.youtoo.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends FragmentActivity {

    private HelperSharedPreferences _appPrefs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        _appPrefs = new HelperSharedPreferences(getApplicationContext());






        // String username = ;//getUsernameFromAccounts();
        EditText pwd=(EditText)findViewById(R.id.edit_pwd);
       final String password=pwd.getText().toString();


        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        // Listening to register new account link




        Button loginbtn = (Button) findViewById(R.id.btnLogin);
        // Listening to login button
        loginbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                login_details();

            }
        });



        Button fbloginbtn = (Button) findViewById(R.id.login_button);
        // Listening to login button
        fbloginbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getUserInfo();
            }
        });
    }

    public void login_details()
    {

        EditText uname=(EditText)findViewById(R.id.edituser);
        final String username=uname.getText().toString();
        _appPrefs.saveUsername(username);
        Log.d("username", username);
        EditText pwd=(EditText)findViewById(R.id.edit_pwd);
        final String password=pwd.getText().toString();

        Intent i = new Intent(getApplicationContext(), HomeScreenActivity.class);
        i.putExtra("Password",password);
        startActivity(i);

    }

    public void getUserInfo(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Key Hash", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        }
        catch(PackageManager.NameNotFoundException e){}
        catch(NoSuchAlgorithmException e){}
    }

    public void getUsername() {
        final Session session = Session.getActiveSession();
        Log.d("facebook", session + " Main");
        if (true) {
            Log.d("facebook", session+ " Inside");
            // If the session is open, make an API call to get user data
            // and define a new callback to handle the response
            Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    // If the response is successful
                    if (session == Session.getActiveSession()) {
                        if (user != null) {
                            String user_ID = user.getId();//user id
                            String profileName = user.getName();//user's profile name

                            Log.d("facebook", user_ID + "" + profileName);
                            _appPrefs.saveUsername(profileName);

                            Intent homeIntent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                            //homeIntent.putExtra("FbUserName",profileName);
                            startActivity(homeIntent);
                        }
                    }
                }
            });
            Request.executeBatchAsync(request);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
        Log.d("fb", "Done");
        getUsername();
    }

}
