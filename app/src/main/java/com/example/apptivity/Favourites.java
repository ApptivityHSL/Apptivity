package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Set;

public class Favourites extends AppCompatActivity {

    public static final String MATCHES ="match";
    private Button btBackToHome = findViewById(R.id.btBackToHome);
    private Button btResetMatches = findViewById(R.id.btResetMatches);
    private Set<String> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        SharedPreferences mSharedPreferences1 = getSharedPreferences("activity_swiping", MODE_PRIVATE);
        Log.d("FavoritenAnzeigen", mSharedPreferences1.getStringSet("match", matches).toString());

        SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
        matches = mSharedPreferences.getStringSet(MATCHES, matches);
        String[] matchesToView = new String[matches.size()];
        int index = 0;
        for (String str : matches)
            matchesToView[index++] = str;

        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for( int i = 0; i < matchesToView.length; i++ )
        {
            TextView textView = new TextView(this);
            textView.setText(matchesToView[i]);
            linearLayout.addView(textView);
        }


        btBackToHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });

        btResetMatches.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

    private void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
