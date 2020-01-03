package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class Search1 extends AppCompatActivity {
    private Button btSearch2;
    protected static boolean alone;
    protected static boolean partner;
    protected static boolean family;
    protected static boolean friends;
    protected static int budget;

    private CheckBox inputAlone;
    private CheckBox inputPartner;
    private CheckBox inputFamily;
    private CheckBox inputFriend;
    private SeekBar inputBudget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);

        inputAlone =  findViewById(R.id.inputAlone);
        inputPartner =  findViewById(R.id.inputPartner);
        inputFamily =  findViewById(R.id.inputFamily);
        inputFriend =  findViewById(R.id.inputFriend);
        inputBudget =  findViewById(R.id.inputBudget);

        btSearch2 =  findViewById(R.id.btSearch2);
        btSearch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                alone = inputAlone.hasFocus();
                partner = inputPartner.hasFocus();
                family = inputFamily.hasFocus();
                friends = inputFriend.hasFocus();
                budget = 0;

                openSearch2();
            }

        });
    }

    public void openSearch2(){
        Intent intent = new Intent(this, Search2.class);
        startActivity(intent);
    }
}
