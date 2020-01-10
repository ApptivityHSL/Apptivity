package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;

public class PreferedTags extends AppCompatActivity {

    private Button btPrefTags;
    private Button btHome;
    private ConnectFirebase a = new ConnectFirebase();
    private Button tag1;
    private Button tag2;
    private Button tag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefered_tags);
        btPrefTags =  findViewById(R.id.btPrefTags);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);

        btPrefTags.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonalInformation();
            }

        });

        btHome =  findViewById(R.id.btBackHome);
        btHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });

        a.pullAllData("Tag", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                String forTag1 = pullTag(value,0,"Name");
                tag1.setText(forTag1);
                String forTag2 = pullTag(value,1,"Name");
                tag2.setText(forTag2);
                String forTag3 = pullTag(value,2,"Name");
                tag3.setText(forTag3);
            }
        });


    }



    public void openPersonalInformation(){
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public String pullTag(String string, int index, String property){
        JSONArray array = null;
        String information = "";
        try {
            array = new JSONArray(string);                                      //"Bild" für Eigenschaft des Tags
            information = array.getJSONObject(index).get(property).toString(); //index für den Tag
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("testtest", information);
        return information;

    }
}
