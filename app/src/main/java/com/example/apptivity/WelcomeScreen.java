package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WelcomeScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    private ConnectFirebase a = new ConnectFirebase(this);

    private Button btWelcome;
    public static final String DEFAULT_VALUE = "default";
    public static final String INPUT_NAME ="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);




      /*  a.pullAllData("Tag", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
            }
        });*/




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences nameOfPerson = getSharedPreferences("UserIn", MODE_PRIVATE);
                String username = nameOfPerson.getString(INPUT_NAME,DEFAULT_VALUE);
                Log.d("1298", username);
                if(username.equals(DEFAULT_VALUE)) {
                    openPersonalInformation();
                } else {
                    openPersonOverview();
                }
            }
        },SPLASH_TIME_OUT);

        /*btWelcome =  findViewById(R.id.btWelcome);
        btWelcome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                SharedPreferences nameOfPerson = getSharedPreferences("UserIn", MODE_PRIVATE);
                String username = nameOfPerson.getString(INPUT_NAME,DEFAULT_VALUE);
               Log.d("1298", username);
                if(username.equals(DEFAULT_VALUE)) {
                    openPersonalInformation();
                } else {
                    openPersonOverview();
                }
            }

        });*/

        ConnectFirebase cf = new ConnectFirebase(this);
        /*cf.pullAllData("Tag", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                    methode(value);
            }
        });*/
        cf.queryData("Tag", "Kategorie", "0", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                Log.d("argl", value);
                methode(value);
            }
        });

        cf.getImageURL("Tag/bild3.png", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                Log.d("argl", value);
            }
        });
    }

        public void openPersonalInformation(){
            Intent intent = new Intent(WelcomeScreen.this, PersonalInformation.class);
            startActivity(intent);
            finish();
        }

        public void openPersonOverview(){
         Intent intent = new Intent(WelcomeScreen.this, PersonOverview.class);
         startActivity(intent);
         finish();
    }

        public void methode(String string){
            JSONArray array = null;
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
