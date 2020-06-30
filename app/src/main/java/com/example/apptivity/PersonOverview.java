package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.apptivity.PersonalInformation.INPUT_ALTER;
import static com.example.apptivity.PersonalInformation.INPUT_FEMALE;
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
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        String text = mSharedPreferences.getString(INPUT_NAME, "");
        name =  findViewById(R.id.tvNameOverview);
        name.setText(text);
    }
    private void insertAgeintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        int alter =  mSharedPreferences.getInt(INPUT_ALTER, 0);
        age =  findViewById(R.id.tvAgeOverview);
        String alter2 = String.valueOf(alter);
        age.setText(alter2);
    }

    private void insertGenderintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences mSharedPreferences1 = getSharedPreferences("UserIn", MODE_PRIVATE);
        boolean text1 = mSharedPreferences.getBoolean(INPUT_MALE, true);
        boolean text2 = mSharedPreferences1.getBoolean(INPUT_FEMALE, true);
        gender =  findViewById(R.id.tvGenderOverview);
        if(text2 == true && text1 == false){
            gender.setText("Weiblich");
        } else {
            if(text1 == true && text2 == false){
                gender.setText("Männlich");
            } else { gender.setText("Fehler");}
        }
    }
}

