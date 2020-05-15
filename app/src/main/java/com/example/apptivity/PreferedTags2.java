package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;


public class PreferedTags2 extends AppCompatActivity {

    private ConnectFirebase a = new ConnectFirebase(this);
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String> listOfClickedTags = new ArrayList<>();

    private Button btPrefTags;
    private Button btHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefered_tags2);


        a.pullAllData("Tag", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                String r = "start";
                int tagCounter = 0;
                while(!r.equals("")){
                    r = pullProperty(value, tagCounter, "Name");
                    if(!r.equals(null) || !r.equals("")){
                        tags.add(r);
                    }
                    Log.d("tag", String.valueOf(tags));
                    tagCounter++;
                }
                tags.remove("");
                checkBoxes();

            }
        });

        btPrefTags = findViewById(R.id.btPrefTags);
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

    }

    private void checkBoxes() {
      //  String lastElement = Iterables.getLast(tags);
        for( int i = 0; i < tags.size(); i++ )
        {
            TableLayout tLayout = findViewById(R.id.matchLL);
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            CheckBox box = new CheckBox(this);
            CheckBox box2 = new CheckBox(this);

            box.setText(tags.get(i));
            box.setId(i);
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            box.setOnClickListener(onCheckboxClicked(box));

            i++;

           if(i >= tags.size()) {

               box.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
               tr.addView(box);
               tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                break;
           } else {

            box2.setText(tags.get(i));
            box2.setId(i);
            box2.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            box2.setOnClickListener(onCheckboxClicked(box2));

            box.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            box2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            tr.addView(box);
            tr.addView(box2);

            tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }}
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
        safeTags(listOfClickedTags);
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }

    public void openHome(){
        safeTags(listOfClickedTags);
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    View.OnClickListener onCheckboxClicked(final Button button){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                for(int index = 0; index < tags.size();index++){
                    String t = tags.get(index);
                    String d = (String) button.getText();

                    if(d.equals(t)){
                        if (checked) {
                            listOfClickedTags.add((String) tags.get(index));
                        } else
                            listOfClickedTags.remove((String) tags.get(index));
                        break;
                    }
                }
            }
        };
    }

    public String pullProperty(String string, int index, String property){
        JSONArray array = null;
        String information = "";
        try {
            array = new JSONArray(string);
            information = array.getJSONObject(index).get(property).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("testtest", information);
        return information;
    }

}
