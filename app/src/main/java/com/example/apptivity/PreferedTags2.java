package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;


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
import android.widget.ScrollView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;


public class PreferedTags2 extends AppCompatActivity {

    private ConnectFirebase a = new ConnectFirebase();
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
                    tags.add(r);
                    tagCounter++;
                }
            }
        });

        TableLayout tLayout = findViewById(R.id.matchLL);

        for( int i = 0; i < tags.size(); i++ )
        {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            CheckBox box = new CheckBox(this);
            CheckBox box2 = new CheckBox(this);

            box.setText(tags.get(i));
            box.setId(Integer.parseInt(tags.get(i)));
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            box.setOnClickListener(onCheckboxClicked(box));

            i++;

            box2.setText(tags.get(i));
            box2.setId(Integer.parseInt(tags.get(i)));
            box2.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            box2.setOnClickListener(onCheckboxClicked(box2));

            box.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            box2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            tr.addView(box);
            tr.addView(box2);

            tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }







    }

    View.OnClickListener onCheckboxClicked(final Button button){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                for(int index = 0; index < tags.size();index++){
                    String t = tags.get(index);
                    String d = String.valueOf(button.getId());

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
