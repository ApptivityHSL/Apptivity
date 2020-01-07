package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class PersonalInformation extends AppCompatActivity {

    private EditText inputName;
    private EditText inputAlter;
    private RadioButton inputMale;
    private RadioButton inputFemale;
    private Button btPInfo;
    

    public static final String SHARED_PREFS ="sharedPrefs";
    public static final String INPUT_NAME ="name";
    public static final String INPUT_ALTER = "alter";
    public static final String INPUT_MALE ="mann";
    public static final String INPUT_FEMALE ="frau";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        inputName =  findViewById(R.id.inputName);
        inputAlter =  findViewById(R.id.inputAlter);
        inputMale =  findViewById(R.id.inputMale);
        inputFemale =  findViewById(R.id.inputFemale);
        btPInfo =  findViewById(R.id.btPInfo);

        btPInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String namefuerfunc = "";
                int alterfuerfunc = 0;
                Boolean malefuerfunc = true;
                Boolean femalefuerfunc = false;

                namefuerfunc = inputName.getText().toString();
                alterfuerfunc = Integer.parseInt(inputAlter.getText().toString());
                malefuerfunc = inputMale.hasFocus();
                femalefuerfunc = inputFemale.hasFocus();

                storePersonalInfo(namefuerfunc, alterfuerfunc, malefuerfunc, femalefuerfunc);
                openPreferedTags();
            }

        });
    }

    public void openPreferedTags(){
        Intent intent = new Intent(this, PreferedTags.class);
        startActivity(intent);
    }

    private void storePersonalInfo(String name, int alter, boolean male, boolean female){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputName", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(INPUT_NAME, name);
        mEditor.apply();

        SharedPreferences mSharedPreferences1 = getSharedPreferences("inputAlter", MODE_PRIVATE);
        SharedPreferences.Editor mEditor1 = mSharedPreferences1.edit();
        mEditor1.putInt(INPUT_ALTER, alter);
        mEditor1.apply();

        SharedPreferences mSharedPreferences2 = getSharedPreferences("inputMale", MODE_PRIVATE);
        SharedPreferences.Editor mEditor2 = mSharedPreferences2.edit();
        mEditor2.putBoolean(INPUT_MALE, male);
        mEditor2.apply();

        SharedPreferences mSharedPreferences3 = getSharedPreferences("inputFemale", MODE_PRIVATE);
        SharedPreferences.Editor mEditor3 = mSharedPreferences3.edit();
        mEditor3.putBoolean(INPUT_FEMALE, female);
        mEditor3.apply();
    }

    public String getInputName(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputName", MODE_PRIVATE);
        return mSharedPreferences.getString(INPUT_NAME, "");

    }

    private int getInputAlter(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputAlter", MODE_PRIVATE);
       return  mSharedPreferences.getInt(INPUT_ALTER, 0);

    }

    private boolean getInputMale(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputMale", MODE_PRIVATE);
        return mSharedPreferences.getBoolean(INPUT_MALE, false);
    }

    private boolean getInputFemale(){
        SharedPreferences mSharedPreferences = getSharedPreferences("inputFemale", MODE_PRIVATE);
        return mSharedPreferences.getBoolean(INPUT_FEMALE, false);

    }



}
