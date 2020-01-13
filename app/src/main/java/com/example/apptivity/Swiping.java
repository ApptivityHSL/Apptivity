package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Swiping extends AppCompatActivity {

    private Button btBackHome;

    public static final String MATCHES ="match";
    private int matchnum = 0;

    //private ArrayList<String> al;
    //private ArrayList<String> imArray;
    private int i;
    private ConnectFirebase connection = new ConnectFirebase();
    //private ArrayList<String> actNames;
    private ArrayList<cards> matches;
    //private boolean isFirst = true;
    private int actAmount;
    private JSONArray countArray;

    private cards cards_data[];
    private arrayAdapter arrayAdapter;

    private int aktuell = 0;

    ListView listView;
    List<cards> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);
    /*
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
    */
        populateCards();

        rowItems = new ArrayList<cards>();

        matches = new ArrayList<cards>();

        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                /*Log.d("LIST", "removed object!");
                if(isFirst) {
                    for (String s : actNames) {
                        al.add(s);
                    }
                    isFirst = false;
                }*/
                rowItems.remove(0);
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
                cards cardMatched = (cards) dataObject;
                matches.add(cardMatched);
                /*matchnum++;
                SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putStringSet(MATCHES, matched);
                mEditor.apply();*/
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //if(!isFirst) {
                    rowItems.add(new cards("XML ".concat(String.valueOf(i)), "Bitte weiterswipen!", "https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/ce2ece60-9b32-11e6-95ab-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg"));
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("LIST", "notified");
                    i++;
                //}
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
    public void populateCards(){
        aktuell = 1;
        connection.pullAllData("Test", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                try{
                    countArray = new JSONArray(value);
                    actAmount = countArray.length();
                    Log.d("ObjektZahl",actAmount+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("anzahl", actAmount+"");
                for(int i = 0; i < actAmount; i++) {
                    try {
                        rowItems.add(new cards("" + i,
                                countArray.getJSONObject(i).get("Name").toString(),
                                countArray.getJSONObject(i).get("Bild").toString()));
                                getFirstImage(countArray.getJSONObject(i).getJSONArray("Bild"), i);
                                Log.d("brgl", countArray.get(0).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                arrayAdapter.notifyDataSetChanged();
                Log.d("Whyno2cardLinkPlsNOW", rowItems.get(2).getImURL());
            }
        });

    }
    public void getFirstImage(JSONArray ImagePaths, final int indexed){
            List<String> ImPaStr = new ArrayList<String>();
            try{
                for(int i = 0; i < ImagePaths.length(); i++){
                    Log.d("Imagetest", ImagePaths.get(i).toString());
                    ImPaStr.add(ImagePaths.get(i).toString());
                    Log.d("Imageteststr", ImPaStr.get(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String im = ImPaStr.get(0);
            Log.d("testingerIM", im);
            connection.getImageURL(im, new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                Log.d("Whyno2cardStillNothing", rowItems.get(2).getImURL());
                Log.d("testinger", value);
                Log.d("aktuell", aktuell+"");
                rowItems.get(aktuell).setImURL(value);
                arrayAdapter.notifyDataSetChanged();
                Log.d("Whyno2cardLinkPls", rowItems.get(2).getImURL());
                aktuell++;
            }
        });
    }
    /*
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

    }*/
}

