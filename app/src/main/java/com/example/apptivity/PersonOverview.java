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
    private Button btPersonalInformation;
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
    private void insertNameintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputName", MODE_PRIVATE);
        String text = mSharedPreferences.getString(INPUT_NAME, "");
        name =  findViewById(R.id.tvNameOverview);
        name.setText(text);
    }
    private void insertAgeintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputAlter", MODE_PRIVATE);
        String text = mSharedPreferences.getString(INPUT_ALTER, "");
        age =  findViewById(R.id.tvAgeOverview);
        age.setText(text);
    }

    private void insertGenderintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputFemale", MODE_PRIVATE);
        String text1 = mSharedPreferences.getString(INPUT_MALE, "");
        if(text1.equals(false)){
            gender =  findViewById(R.id.tvGenderOverview);
            gender.setText("Weiblich");
        } else {
        gender =  findViewById(R.id.tvGenderOverview);
        gender.setText("Mänchich");}
    }
}

