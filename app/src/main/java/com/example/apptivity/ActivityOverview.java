package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ActivityOverview extends AppCompatActivity {

    private Button btBack;
    private TextView stvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        btBack =  findViewById(R.id.btSwiping);
        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSwiping();
            }
        });

        stvName = (TextView) findViewById(R.id.stvName);



        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("choosenActivity");


        stvName.setText(id);

    }
    public void openSwiping(){
        Intent intent = new Intent(this, Swiping.class);
        startActivity(intent);
    }

}
