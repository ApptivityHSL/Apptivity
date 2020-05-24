package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import com.google.gson.Gson;


import static com.example.apptivity.PersonalInformation.INPUT_NAME;

public class Search1 extends AppCompatActivity {
    private Button btSearch2;
    protected static boolean alone;
    protected static boolean partner;
    protected static boolean family;
    protected static boolean friends;
    protected static int budget;
    private TextView greetings2;
    private SeekBar budgetBar;
    private TextView money;

    ArrayList<String> peopleArray = new ArrayList<String>();

    private CheckBox inputAlone;
    private CheckBox inputPartner;
    private CheckBox inputFamily;
    private CheckBox inputFriend;
    private SeekBar inputBudget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);

        insertNameintoTextView();
        seekbar();



        inputAlone =  findViewById(R.id.inputAlone);
        inputPartner =  findViewById(R.id.inputPartner);
        inputFamily =  findViewById(R.id.inputFamily);
        inputFriend =  findViewById(R.id.inputFriend);
        inputBudget =  findViewById(R.id.inputBudget);

        btSearch2 =  findViewById(R.id.btSearch2);
        btSearch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                int value = inputBudget.getProgress();
                safeMoney(value);

                safePeople(peopleArray);

                Log.d("123981", String.valueOf(peopleArray));

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

    private void seekbar(){
        budgetBar = findViewById(R.id. inputBudget);
        money = findViewById(R.id. money);
        money.setText(budgetBar.getProgress() + "€" + " / " + budgetBar.getMax() + "€");


        budgetBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        budget = progress;
                        money.setText(progress + "€" + " / " + budgetBar.getMax() + "€");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        money.setText(budget + "€" + " / " + budgetBar.getMax() + "€");
                    }
                }
        );
    };


    private void insertNameintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        String text = "Hallo "+mSharedPreferences.getString(INPUT_NAME, "")+"!";
        greetings2 =  findViewById(R.id.tvGreetings2);
        greetings2.setText(text);
    }

    private void safePeople(ArrayList<String> people) {
        SharedPreferences sharedPreferences = getSharedPreferences("people", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String Json = gson.toJson(people);
        editor.putString("people", Json);
        editor.apply();
    }

    private void safeMoney(int i) {
        SharedPreferences sharedPreferences = getSharedPreferences("money", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("money", i);
        editor.apply();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.inputAlone:
                if (checked){
                    peopleArray.add((String) inputAlone.getText() );
                }
                else
                    peopleArray.remove((String) inputAlone.getText() );
                break;

            case R.id.inputPartner:
                if (checked){
                    peopleArray.add((String) inputPartner.getText() );
                }
                else
                    peopleArray.remove((String) inputPartner.getText() );
                break;

            case R.id.inputFamily:
                if (checked){
                    peopleArray.add((String) inputFamily.getText() );
                }
                else
                    peopleArray.remove((String) inputFamily.getText() );
                break;

            case R.id.inputFriend:
                if (checked){
                    peopleArray.add((String) inputFriend.getText() );
                }
                else
                    peopleArray.remove((String) inputFriend.getText() );
                break;

        }



    }


}
