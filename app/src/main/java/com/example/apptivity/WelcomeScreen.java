package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

        public void openPersonalInformation(){
            Intent intent = new Intent(this, PersonalInformation.class);
            startActivity(intent);
        }

}
