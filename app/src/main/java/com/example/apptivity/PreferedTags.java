package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.gms.common.images.WebImage;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;


public class PreferedTags<ListSpecificCars> extends AppCompatActivity {

    private Button btPrefTags;
    private Button btHome;
    private ConnectFirebase a = new ConnectFirebase();
    private CheckBox cbtag1;
    private CheckBox cbtag2;
    private CheckBox cbtag3;
    private CheckBox cbtag4;
    private CheckBox cbtag5;
    private CheckBox cbtag6;
    private CheckBox cbtag7;
    private CheckBox cbtag8;
    private CheckBox cbtag9;
    private CheckBox cbtag10;
    private CheckBox cbtag11;
    private CheckBox cbtag12;
    private CheckBox cbtag13;



    ArrayList<String> listOfClickedTags = new ArrayList<String>();
    public String [] tags;
    public String [] tagsPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefered_tags);


        btPrefTags =  findViewById(R.id.btPrefTags);
        cbtag1 = (CheckBox) findViewById(R.id.tag1);
        cbtag2 = (CheckBox) findViewById(R.id.tag2);
        cbtag3 = (CheckBox) findViewById(R.id.tag3);
        /*cbtag4 = (CheckBox) findViewById(R.id.tag4);
        cbtag5 = (CheckBox) findViewById(R.id.tag5);
        cbtag6 = (CheckBox) findViewById(R.id.tag6);
        cbtag7 = (CheckBox) findViewById(R.id.tag7);
        cbtag8 = (CheckBox) findViewById(R.id.tag8);
        cbtag9 = (CheckBox) findViewById(R.id.tag9);
        cbtag10 = (CheckBox) findViewById(R.id.tag10);
        cbtag11 = (CheckBox) findViewById(R.id.tag11);
        cbtag12 = (CheckBox) findViewById(R.id.tag12);
        cbtag13 = (CheckBox) findViewById(R.id.tag13);*/



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
                String k = h.replace(',', ' ');



                String j = k.replaceAll("( )+", " ");

                Log.d("123prefer", k);

                tags = j.split(" ");


                LinkedHashSet<String> htags = new LinkedHashSet<String>(Arrays.asList(tags));

                tagsPT = htags.toArray(new String[ htags.size() ]);



                cbtag1.setText(tagsPT[1]);
                cbtag2.setText(tagsPT[2]);
                cbtag3.setText(tagsPT[3]);
/*              cbtag4.setText(tagsPT[4]);
                cbtag5.setText(tagsPT[5]);
                cbtag6.setText(tagsPT[6]);
                cbtag7.setText(tagsPT[7]);
                cbtag8.setText(tagsPT[8]);
                cbtag9.setText(tagsPT[9]);
                cbtag10.setText(tagsPT[10]);
                cbtag11.setText(tagsPT[11]);
                cbag12.setText(tagsPT[12]);
                cbtag13.setText(tagsPT[13]);*/

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

                safeTags(listOfClickedTags);
                Log.d("123prefer", String.valueOf(listOfClickedTags));

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

    private void safeTags(ArrayList<String> listOfClickedTags) {
        SharedPreferences sharedPreferences = getSharedPreferences("tags", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String Json = gson.toJson(listOfClickedTags);
        editor.putString("tags", Json);
        editor.apply();
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




    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.tag1:
                if (checked){
                    listOfClickedTags.add((String) cbtag1.getText() );
                }
            else
                    listOfClickedTags.remove((String) cbtag1.getText() );
                break;

            case R.id.tag2:
                if (checked){
                    listOfClickedTags.add((String) cbtag2.getText() );
                }
                else
                    listOfClickedTags.remove((String) cbtag2.getText() );
                break;

            case R.id.tag3:
                if (checked){
                    listOfClickedTags.add((String) cbtag3.getText() );
                }
                else
                    listOfClickedTags.remove((String) cbtag3.getText() );
                break;

        }



    }








}
