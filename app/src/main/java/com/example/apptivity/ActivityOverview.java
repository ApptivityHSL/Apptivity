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

    private TextView cName;
    private TextView cActID;
    private TextView cBudget;
    private TextView cClosed;
    private TextView cOpen;
    private TextView cDescription;
    private TextView cHouseNumber;
    private TextView cURL;
    private TextView cWebsite;
    private TextView cStreet;
    private TextView cPostal;
    private TextView cMailAddress;
    private TextView cLocation;
    private TextView cPhoneNumber;


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

        cName = (TextView) findViewById(R.id.cName);
        cActID = (TextView) findViewById(R.id.cActID);
        cBudget = (TextView) findViewById(R.id.cBudget);
        cClosed = (TextView) findViewById(R.id.cClosed);
        cOpen = (TextView) findViewById(R.id.cOpen);
        cDescription = (TextView) findViewById(R.id.cDescription);
        cHouseNumber = (TextView) findViewById(R.id.cHouseNumber);
        cURL = (TextView) findViewById(R.id.cURL);
        cWebsite = (TextView) findViewById(R.id.cWebsite);
        cStreet = (TextView) findViewById(R.id.cStreet);
        cPostal = (TextView) findViewById(R.id.cPostal);
        cMailAddress = (TextView) findViewById(R.id.cMailAddress);
        cLocation = (TextView) findViewById(R.id.cLocation);
        cPhoneNumber = (TextView) findViewById(R.id.cPhoneNumber);

        Bundle bundle = getIntent().getExtras();

       cName.setText(bundle.getString("cName"));
        cActID.setText(bundle.getString("cActID"));
        cBudget.setText(bundle.getString("cBudget"));
        cClosed.setText(bundle.getString("cClosed"));
        cOpen.setText(bundle.getString("cOpen"));
        cDescription.setText(bundle.getString("cDescription"));
        cHouseNumber.setText(bundle.getString("cHouseNumber"));
        cURL.setText(bundle.getString("cURL"));
        cWebsite.setText(bundle.getString("cWebsite"));
        cStreet.setText(bundle.getString("cStreet"));
        cPostal.setText(bundle.getString("cPostal"));
        cMailAddress.setText(bundle.getString("cMailAddress"));
        cLocation.setText(bundle.getString("cLocation"));
        cPhoneNumber.setText(bundle.getString("cPhoneNumber"));

    }
    public void openSwiping(){
        Intent intent = new Intent(this, Swiping.class);
        startActivity(intent);
    }

}
