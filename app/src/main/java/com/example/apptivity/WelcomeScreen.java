package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        cf.pullAllData("Tag", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(JSONArray value) {
                try {
                    methode(value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


        public void openPersonalInformation(){
            Intent intent = new Intent(this, PersonalInformation.class);
            startActivity(intent);
        }

        public void methode(JSONArray array) throws JSONException {
            Log.d("argl", array.getJSONObject(0).get("Name").toString());
        }

}
