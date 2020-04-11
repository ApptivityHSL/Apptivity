package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class WelcomeScreen extends AppCompatActivity {

    private Button btWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        btWelcome =  findViewById(R.id.btWelcome);
        btWelcome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonalInformation();
            }

        });

        ConnectFirebase cf = new ConnectFirebase();
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
            Intent intent = new Intent(this, PersonalInformation.class);
            startActivity(intent);
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
