package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Search2 extends AppCompatActivity {
    private Button btSwipe;
    protected static String town;
    protected static int postalCode;

    private EditText inputTown;
    private EditText inputPostal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        inputTown =  findViewById(R.id.inputTown);
        inputPostal =  findViewById(R.id.inputPostal);

        btSwipe =  findViewById(R.id.btSwipe);
        btSwipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(inputTown.getText().toString() != null && inputPostal.getText().toString() != null
                    && !inputTown.getText().toString().isEmpty() && !inputPostal.getText().toString().isEmpty()){
                    town = inputTown.getText().toString();
                    postalCode = Integer.parseInt(inputPostal.getText().toString());

                    openSwipe();
                }
            }

        });
    }
    public void openSwipe(){
        Intent intent = new Intent(this, Swiping.class);
        startActivity(intent);
    }
}
