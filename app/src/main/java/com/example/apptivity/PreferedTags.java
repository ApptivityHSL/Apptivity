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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefered_tags);
        btPrefTags =  findViewById(R.id.btPrefTags);
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

        a.pullAllData("Test", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                String f =pullTag(value);
                Log.d("test1",value);
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

    public String pullTag(String string){
        JSONArray array = null;
        String information = "";
        try {
            array = new JSONArray(string);                                      //"Bild" für Eigenschaft des Tags
            information = array.getJSONObject(0).get("Bild").toString(); //index für den Tag
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("argl", information);
        return information;

    }
}
