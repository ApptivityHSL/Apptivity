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

            box.setText(tags.get(i));
            box.setId(Integer.parseInt(tags.get(i)));
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            box.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(box);
            tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }










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


    /*public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            for (int i = 0; i <= tags.size()) {
                case R.id. tags.get(i):
                    if (checked) {
                        listOfClickedTags.add((String) tags.get(i).getText());
                    } else
                        listOfClickedTags.remove((String) tags.get(i).getText());
                    break;
            }
        }
    }*/


}
