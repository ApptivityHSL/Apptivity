package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityOverview extends AppCompatActivity {
    Bundle receiveTag = getIntent().getExtras();
    String activityID = receiveTag.getString("choosenActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }
}
