package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

public class Swiping extends AppCompatActivity {

    private Button btBackHome;
    /*
    private JSONObject activities = new Gson().fromJson(connection.getAllData("Test"), JSONObject.class);
    */

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private ConnectFirebase connection = new ConnectFirebase();
    private ArrayList<String> actNames;
    private ArrayList<JSONObject> matches;
    private int actAmount;
    private boolean isFirst = true;
    private JSONArray countArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);

        connection.pullAllData("Test", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                try{
                    countArray = new JSONArray(value);
                    actAmount = countArray.length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                actNames = new ArrayList<>();
                for(int i = 0; i < actAmount; i++) {
                    Log.d("actMount", actAmount+"");
                    actNames.add(pullStringFromData(value, i, "Name"));
                    Log.d("namtest", actNames.get(i));
                }
            }
        });

        al = new ArrayList<>();
        al.add("Zum Start einmal Swipen!");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                if(isFirst) {
                    for (String s : actNames) {
                        al.add(s);
                    }
                    isFirst = false;
                }
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(Swiping.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(Swiping.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                if(!isFirst) {
                    al.add("XML ".concat(String.valueOf(i)));
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("LIST", "notified");
                    i++;
                }
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(Swiping.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        btBackHome = findViewById(R.id.btBackHome);
        btBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }

        });
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public String pullStringFromData(String string, int index, String property){
        JSONArray array = null;
        String information = "";
        try {
            array = new JSONArray(string);
            information = array.getJSONObject(index).get(property).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("namtestPull", information);
        return information;

    }
}

