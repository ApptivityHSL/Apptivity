package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;

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
                    openPersonOverview();
                }
            }
        }, SPLASH_TIME_OUT);

        ConnectFirebase cf = new ConnectFirebase(this);
        cf.queryData("Tag", "Kategorie", "0", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(final String value) {
                Log.d("argl", value);
                methode(value);
            }
        });

        cf.getImageURL("Tag/bild3.png", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(final String value) {
                Log.d("argl", value);
            }
        });
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
    public void openPersonOverview() {
         Intent intent = new Intent(WelcomeScreen.this, PersonOverview.class);
         startActivity(intent);
         finish();
    }

    /**
     * Methode.
     *
     * @param string the string
     */
    public void methode(final String string) {
            JSONArray array;
            String information = "";
            try {
                array = new JSONArray(string);
                information = array.getJSONObject(0).get("Bild").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("argl", information);

        }
}
