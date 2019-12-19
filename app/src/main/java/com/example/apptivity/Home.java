package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.apptivity.PersonalInformation.INPUT_NAME;


public class Home extends AppCompatActivity {
    private TextView greetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        insertNameintoTextView();
    }

    private void insertNameintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputName", MODE_PRIVATE);
        String text = "Hallo "+mSharedPreferences.getString(INPUT_NAME, "")+"!";
        greetings =  findViewById(R.id.greetings);
        greetings.setText(text);
    }

}

