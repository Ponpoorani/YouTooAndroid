package com.nyu.youtoo.youtoo.controllers;

/**
 * The a class specially for shared preference and have other activities access it.
 *
 * @author  Ponpoorani Ravichandran
 * @version 1.0
 * @since   2015-04-05
 */

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.content.Context;
import android.app.Activity;

public class HelperSharedPreferences {
    public static final String PREFS_USERNAME_KEY = "prefUsername";
    private SharedPreferences _sharedPrefs;
    private static final String APP_SHARED_PREFS = HelperSharedPreferences.class.getSimpleName(); //  Name of the file -.xml
    private Editor _prefsEditor;

    public HelperSharedPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getUsername() {
        return _sharedPrefs.getString(PREFS_USERNAME_KEY, ""); // Get our string from prefs or return an empty string
    }

    public void saveUsername(String text) {
        _prefsEditor.putString(PREFS_USERNAME_KEY, text);
        _prefsEditor.commit();
    }

}
