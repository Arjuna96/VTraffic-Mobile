package org.example.vtraffic.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.example.vtraffic.R;

public class SplashActivity extends AppCompatActivity {
 //for a splash before enter to the app
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        SharedPreferences settings = getSharedPreferences(SplashActivity.PREFS_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        final boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                // do something
                if(hasLoggedIn)
                {
                    //Go directly to main activity.
                    startActivity(new Intent(SplashActivity.this, MapsActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 3000);

    }
}
