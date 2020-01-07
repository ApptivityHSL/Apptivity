package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.apptivity.PersonalInformation.INPUT_ALTER;
import static com.example.apptivity.PersonalInformation.INPUT_MALE;
import static com.example.apptivity.PersonalInformation.INPUT_NAME;

public class PersonOverview extends AppCompatActivity {

    private Button btBackHome;
    private Button btNewPI;
    private TextView name;
    private TextView age;
    private TextView gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_overview);

       insertNameintoTextView();
       insertGenderintoTextView();
       insertAgeintoTextView();

        btBackHome =  findViewById(R.id.btBackHome);
        btBackHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });

        btNewPI =  findViewById(R.id.btNewPI);
        btNewPI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonalInformation();
            }

        });

     /*   btNewPI =  findViewById(R.id.btNewPI);
        btNewPI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ openPersonalInformation();
            }

        });*/
    }



    private void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void openPersonalInformation(){
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }
    private void insertNameintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputName", MODE_PRIVATE);
        String text = mSharedPreferences.getString(INPUT_NAME, "");
        name =  findViewById(R.id.tvNameOverview);
        name.setText(text);
    }
    private void insertAgeintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputAlter", MODE_PRIVATE);
        int alter =  mSharedPreferences.getInt(INPUT_ALTER, 0);
        age =  findViewById(R.id.tvAgeOverview);
        String alter2 = String.valueOf(alter);
        age.setText(alter2);
    }

    private void insertGenderintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputMale", MODE_PRIVATE);
        boolean text1 = mSharedPreferences.getBoolean(INPUT_MALE, false);
        if(text1 == false){
            gender =  findViewById(R.id.tvGenderOverview);
            gender.setText("text1");
        } else {
        gender =  findViewById(R.id.tvGenderOverview);
        gender.setText("Männlich");}
    }
}

