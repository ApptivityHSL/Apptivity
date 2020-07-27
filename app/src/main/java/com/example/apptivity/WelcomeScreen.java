package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Welcome screen.
 */
public class WelcomeScreen extends AppCompatActivity {

    /**
     * The constant DEFAULT_VALUE.
     */
    public static final String DEFAULT_VALUE = "default";
    /**
     * The constant INPUT_NAME.
     */
    public static final String INPUT_NAME = "name";
    /**
     * The constant SPLASH_TIME_OUT.
     */
    public static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences nameOfPerson = getSharedPreferences("UserIn", MODE_PRIVATE);
                String username = nameOfPerson.getString(INPUT_NAME, DEFAULT_VALUE);
                if (username.equals(DEFAULT_VALUE)) {
                    openPersonalInformation();
                } else {
                    openHome();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    /**
     * Open personal information.
     */
    public void openPersonalInformation() {
            Intent intent = new Intent(WelcomeScreen.this, PersonalInformation.class);
            startActivity(intent);
            finish();
        }

    /**
     * Open person overview.
     */
    public void openHome() {
         Intent intent = new Intent(WelcomeScreen.this, Home.class);
         startActivity(intent);
         finish();
    }
}
