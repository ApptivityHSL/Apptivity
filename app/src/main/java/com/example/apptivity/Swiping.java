package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Swiping extends AppCompatActivity {
    private Button btBackHome;
    private Button btSwipeUp;
    private Button btSwipeLeft;
    private Button btSwipeRight;
    private ConnectFirebase connection = new ConnectFirebase();
    private JSONObject activities = new Gson().fromJson(connection.getAllData("Test"), JSONObject.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);

        nextActivity();

        btBackHome =  findViewById(R.id.btBackHome);
        btBackHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });

        btSwipeUp =  findViewById(R.id.btSwipeUp);
        btSwipeUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                swipeUp();
            }

        });

        btSwipeLeft =  findViewById(R.id.btSwipeLeft);
        btSwipeLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                swipeLeft();
                nextActivity();
            }

        });

        btSwipeRight =  findViewById(R.id.btSwipeRight);
        btSwipeRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                swipeRight();
                nextActivity();
            }

        });
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void swipeLeft(){

    }
    public void swipeRight(){

    }
    public void swipeUp(){

    }
    public void nextActivity(){
    }
}
