package com.emre.androbooster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by emre on 25.04.2016.
 */
public class EnterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String PREFS_NAME = "splash_boolean";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("a", true)) {
            settings.edit().putBoolean("a", false).commit();
            Intent myIntent = new Intent(EnterActivity.this, Splash.class);
            EnterActivity.this.startActivity(myIntent);
            finish();
        }else {
            Intent myIntent = new Intent(EnterActivity.this, MainActivity.class);
            EnterActivity.this.startActivity(myIntent);
            finish();
        }
    }
}
