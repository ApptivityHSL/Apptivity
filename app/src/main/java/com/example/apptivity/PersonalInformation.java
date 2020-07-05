package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Personal information.
 */
public class PersonalInformation extends AppCompatActivity {

    private EditText inputName;
    private EditText inputAlter;
    private RadioButton inputMale;
    private RadioButton inputFemale;


    /**
     * The constant SHARED_PREFS.
     */
    public static final String SHARED_PREFS = "sharedPrefs";
    /**
     * The constant INPUT_NAME.
     */
    public static final String INPUT_NAME = "name";
    /**
     * The constant INPUT_ALTER.
     */
    public static final String INPUT_ALTER = "alter";
    /**
     * The constant INPUT_MALE.
     */
    public static final String INPUT_MALE = "mann";
    /**
     * The constant INPUT_FEMALE.
     */
    public static final String INPUT_FEMALE = "frau";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        inputName =  findViewById(R.id.inputName);
        inputAlter =  findViewById(R.id.inputAlter);
        inputMale =  findViewById(R.id.inputMale);
        inputFemale =  findViewById(R.id.inputFemale);
        Button btPInfo = findViewById(R.id.btPInfo);

        btPInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                inputName.getText().toString();
                inputAlter.getText().toString();
                if (!inputName.getText().toString().isEmpty() && !inputAlter.getText().toString()
                        .isEmpty() && (inputMale.isChecked() || inputFemale.isChecked())) {
                    String namefuerfunc;
                    int alterfuerfunc;
                    boolean malefuerfunc;
                    boolean femalefuerfunc;

                    namefuerfunc = inputName.getText().toString();
                    alterfuerfunc = Integer.parseInt(inputAlter.getText().toString());
                    malefuerfunc = inputMale.isChecked();
                    femalefuerfunc = inputFemale.isChecked();

                    storePersonalInfo(namefuerfunc, alterfuerfunc, malefuerfunc, femalefuerfunc);
                    openHome();
                }
            }

        });
    }

    private void openPreferredTags() {
        Intent intent = new Intent(this, PreferedTags2.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    private void storePersonalInfo(final String name, final int alter, final boolean male, final boolean female) {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(INPUT_NAME, name);
        mEditor.apply();

        SharedPreferences mSharedPreferences1 = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor1 = mSharedPreferences1.edit();
        mEditor1.putInt(INPUT_ALTER, alter);
        mEditor1.apply();

        SharedPreferences mSharedPreferences2 = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor2 = mSharedPreferences2.edit();
        mEditor2.putBoolean(INPUT_MALE, male);
        mEditor2.apply();

        SharedPreferences mSharedPreferences3 = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor3 = mSharedPreferences3.edit();
        mEditor3.putBoolean(INPUT_FEMALE, female);
        mEditor3.apply();
    }

    /**
     * Gets input name.
     *
     * @return the input name
     */
    public String getInputName() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        return mSharedPreferences.getString(INPUT_NAME, "");

    }

    private int getInputAlter() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
       return  mSharedPreferences.getInt(INPUT_ALTER, 0);

    }

    private boolean getInputMale() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        return mSharedPreferences.getBoolean(INPUT_MALE, false);
    }

    private boolean getInputFemale() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        return mSharedPreferences.getBoolean(INPUT_FEMALE, false);

    }



}
