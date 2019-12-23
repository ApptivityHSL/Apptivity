package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonOverview extends AppCompatActivity {

    private Button btBackHome;
    private Button btPersonalInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_overview);
        btBackHome =  findViewById(R.id.btBackHome);
        btBackHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });

        btPersonalInformation =  findViewById(R.id.btNewPI);
        btPersonalInformation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonalInformation();
            }

        });
    }



    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openPersonalInformation(){
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }
}
