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
    private Button tag4;
    private Button tag5;
    private Button tag6;
    private Button tag7;
    private Button tag8;
    private Button tag9;
    private Button tag10;
    private Button tag11;
    private Button tag12;
    private Button tag13;



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

                //Doppelte tags löschen

                int end = tags.length;
                
                for(int index = 0; index < end; i++){
                    for(int index2 = i + 1; index2 < end; index2++){
                        if(tags[index] == tags[index2]){
                            int shiftleft = index2;
                            for(int  index3 = index2 + +1; index3 < end; index3 ++){
                                tags[shiftleft] = tags[index3];
                            }
                            end--;
                            index2--;
                        }

                    }

                }




                tag1.setText(tags[0]);
                tag2.setText(tags[1]);
                tag3.setText(tags[2]);
                tag3.setText(tags[3]);
                tag4.setText(tags[4]);
                tag5.setText(tags[5]);
                tag6.setText(tags[6]);
                tag7.setText(tags[7]);
                tag8.setText(tags[8]);
                tag9.setText(tags[9]);
                tag10.setText(tags[10]);
                tag11.setText(tags[11]);
                tag12.setText(tags[12]);
                tag13.setText(tags[13]);
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
