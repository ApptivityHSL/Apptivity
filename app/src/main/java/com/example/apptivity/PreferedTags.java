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
    public String [] tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefered_tags);
        btPrefTags =  findViewById(R.id.btPrefTags);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);

        a.pullAllData("Test", new ConnectFirebaseCallback(){
            @Override
            public void onCallback(String value){
                String r = "start";
                String all = "";
                int i = 0;
                String property = "Tags";
                while(!r.equals("")){
                    r = pullProperty(value, i, "Tags");
                    all += r;
                    i++;
                }
                Log.d("1234", all);
                String ph1;
                String ph2;
                String ph3;
                ph1 = getString(R.string.test12);
                ph2 = getString(R.string.test2);
                ph3 = getString(R.string.test3);

                String e = all.replace('"', '|');
                String f = e.replace('[', ' ');
                String g = f.replace(']', ' ');
                String h = g.replace('|', ' ');
                String j = h.replace(',', ' ');

                tags = j.split("   ");

                Log.d("3456", e);
                Log.d("3456", j);

                tag1.setText(tags[0]);
                tag2.setText(tags[1]);
                tag3.setText(tags[2]);
            }

        });




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
/*
        a.pullAllData("Test", new ConnectFirebaseCallback() {

            @Override
            public void onCallback(String value) {
                String forTag1 = pullProperty(value,0,"Tags");
                tag1.setText(forTag1);
                String forTag2 = pullProperty(value,1,"Tags");
                tag2.setText(forTag2);
                String forTag3 = pullProperty(value,2,"Tags");
                tag3.setText(forTag3);
            }
        });
        */


    }



    public void openPersonalInformation(){
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public String pullProperty(String string, int index, String property){
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

 /*   private void getAllTags( ConnectFirebase all){
        all.pullAllData("Test", new ConnectFirebaseCallback(){
            @Override
            public void onCallback(String value){
                String r = "start";
                String all = "";
                int i = 0;
                String property = "Tags";
               while(!r.equals("")){
                    r = pullProperty(value, i, "Tags");
                    all += r;
                    i++;
               }


                int a = all.length();
                all.replace('[', '#');
                all.replace(']', ' ');
                all.replace('"', '#');
                all.replace(',', ' ');
                all.replaceAll("#","");

                 tags = all.split(" ");
                }

        });

    }*/



}
