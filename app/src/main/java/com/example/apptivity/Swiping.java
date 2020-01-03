package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Swiping extends AppCompatActivity {
    private Button btBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);

        btBackHome =  findViewById(R.id.btBackHome);
        btBackHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
