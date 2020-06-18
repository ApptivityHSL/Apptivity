package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.apptivity.PersonalInformation.INPUT_NAME;


public class Home extends AppCompatActivity {
    private TextView greetings;
    private Button btFav;
    private Button btSearch;
    private Button btOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        insertNameintoTextView();

        btFav =  findViewById(R.id.btFav);
        btFav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openFavos();
            }

        });

        btSearch =  findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPrefTags2();
            }

        });

        btOptions =  findViewById(R.id.btOptions);
        btOptions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonOverview();
            }

        });


     /*   btOptions =  findViewById(R.id.btOptions);
        btOptions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonOverview();
            }

        });*/
    }

    private void insertNameintoTextView(){
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        String text = "Hallo "+mSharedPreferences.getString(INPUT_NAME, "")+"!";
        greetings =  findViewById(R.id.greetings);
        greetings.setText(text);
    }
    public void openSearch(){

        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("FROM_FAVOS", false);
        mEditor.apply();

        Intent intent = new Intent(this, Search1.class);
        startActivity(intent);
    }

    public void openFavos(){

        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("FROM_FAVOS", true);
        mEditor.apply();

        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    public void openPersonOverview(){
        Intent intent = new Intent(this, PersonOverview.class);
        startActivity(intent);
    }

    public void openPrefTags2(){
        Intent intent = new Intent(this, PreferedTags2.class);
        startActivity(intent);
    }

}

