package com.nyu.youtoo.youtoo.controllers;

/**
 * Created by Ponpoorani on 4/5/2015.
 */

/**
 * The UserSettingActivity.java displays the settings page which includes
 * options to change user name, turn on/off notifications, set search radius,
 * set update frequency etc.
 *
 *
 * @author  Ponpoorani Ravichandran
 * @version 1.0
 * @since   2015-04-29
 */

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.nyu.youtoo.youtoo.R;

public class UserSettingActivity extends PreferenceActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // add the xml resource
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }
    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.layout.settings);
        }
    }

}