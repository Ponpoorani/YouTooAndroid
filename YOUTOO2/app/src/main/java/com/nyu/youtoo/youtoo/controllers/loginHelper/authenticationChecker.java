package com.nyu.youtoo.youtoo.controllers.loginHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.nyu.youtoo.youtoo.R;
import com.nyu.youtoo.youtoo.controllers.MainActivity;

public class authenticationChecker extends Activity{
    /** Called when the activity is first created. */
    public ProgressDialog myDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            public void run() {
                myDialog = ProgressDialog.show(authenticationChecker.this,"", "Loading", true);

                Intent intent=new Intent(authenticationChecker.this,MainActivity.class);
                authenticationChecker.this.startActivity(intent);
                myDialog.dismiss();
                authenticationChecker.this.finish();
            }

        }, 3000);// 3 Seconds
    }
};