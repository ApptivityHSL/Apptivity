package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search1 extends AppCompatActivity {
    private Button btSearch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);

        btSearch2 =  findViewById(R.id.btSearch2);
        btSearch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSearch2();
            }

        });
    }

    public void openSearch2(){
        Intent intent = new Intent(this, Search2.class);
        startActivity(intent);
    }
}
