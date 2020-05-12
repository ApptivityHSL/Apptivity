package com.example.apptivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.DirectAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Swiping extends  AppCompatActivity {



    private String IM_URL = "https://www.apptivity.com/Apptivity-WebApp/Apptivity-OG-Image.jpg";

    private Button btBackHome;
    private Button frame;

    String tags;
    Intent getName;
    Bundle extras;


    public static final String MATCHES ="match";
    private int matchnum = 0;

    private int i;
    private ConnectFirebase connection = new ConnectFirebase(this);
    private Set<String> matches = new HashSet<>();
    private int actAmount;
    private JSONArray countArray;

    private cards cards_data[];
    private arrayAdapter arrayAdapter;
    private BroadcastReceiver mReceiver;
    private ArrayList<String> listOfTags = new ArrayList<>();
    private ArrayList<String> listOfPeople = new ArrayList<>();
    private int money;
    private String town;
    private int postCode;
    private int aktuell = 0;

    List<cards> rowItems; //Für sortieren relevant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);


        //list of tags
        SharedPreferences sharedPreferences = getSharedPreferences("tags", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tags",null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        listOfTags = gson.fromJson(json, type);

        Log.d("sdafsdfsdaf", String.valueOf(listOfTags));

        //list of people
        SharedPreferences sharedPreferences2 = getSharedPreferences("people", MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences2.getString("people",null);
        Type type2 = new TypeToken<ArrayList>() {}.getType();
        listOfPeople = gson2.fromJson(json2, type2);

        //money
        SharedPreferences sharedPreferences3 = getSharedPreferences("money", MODE_PRIVATE);
        money = sharedPreferences3.getInt("money",0);

        //postcode
        SharedPreferences sharedPreferences4 = getSharedPreferences("postCode", MODE_PRIVATE);
        postCode = sharedPreferences4.getInt("postCode",0);

        //town
        SharedPreferences sharedPreferences5 = getSharedPreferences("town", MODE_PRIVATE);
        town = sharedPreferences5.getString("town","");



        Log.d("123986", String.valueOf(listOfTags));
        Log.d("123986", String.valueOf(listOfPeople));
        Log.d("123986", String.valueOf(money));
        Log.d("123986", String.valueOf(postCode));
        Log.d("123986", String.valueOf(town));



        populateCards();


       // cardFilter();



        rowItems = new ArrayList<>();

        //matches = new HashSet<String>();
        SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
        matches = mSharedPreferences.getStringSet(MATCHES, matches);

        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);


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
                matches.add(cardMatched.getActID());
                matchnum++;
                SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.clear();
                mEditor.putStringSet(MATCHES, matches);
                mEditor.apply();
            }


            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //if(!isFirst) {
                rowItems.add(new cards("XML ".concat(String.valueOf(i)), "Bitte weiterswipen!", "https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/ce2ece60-9b32-11e6-95ab-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg",
                        "Bla", "8:00", "20:00", "Landshut", "Schlumpfstraße", "wwww.website.de", "27", "275", "125987", "84034", "mail@address.com", new ArrayList<String>()));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
                //}
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                cards cardMatched1 = (cards) dataObject;

                String cName = cardMatched1.getName();
                String cActID = cardMatched1.getActID();
                String cBudget = cardMatched1.getBudget();
                String cClosed = cardMatched1.getClosed();
                String cOpen = cardMatched1.getOpen();
                String cDescription = cardMatched1.getDescription();
                String cHouseNumber = cardMatched1.getHouseNumber();
                String cURL = cardMatched1.getImURL();
                String cWebsite = cardMatched1.getWebsite();
                String cStreet = cardMatched1.getStreet();
                String cPostal = cardMatched1.getPostal();
                String cMailAddress = cardMatched1.getMailAddress();
                String cLocation = cardMatched1.getLocation();
                String cPhoneNumber = cardMatched1.getPhoneNumber();

                Intent intent = new Intent(Swiping.this, ActivityOverview.class);
                Bundle bundle = new Bundle();

                bundle.putString("cName", cName);
                bundle.putString("cActID", cActID);
                bundle.putString("cBudget", cBudget);
                bundle.putString("cClosed", cClosed);
                bundle.putString("cOpen", cOpen);
                bundle.putString("cDescription", cDescription);
                bundle.putString("cHouseNumber", cHouseNumber);
                bundle.putString("cURL", cURL);
                bundle.putString("cWebsite", cWebsite);
                bundle.putString("cStreet", cStreet);
                bundle.putString("cPostal", cPostal);
                bundle.putString("cMailAddress", cMailAddress);
                bundle.putString("cLocation", cLocation);
                bundle.putString("cPhoneNumber", cPhoneNumber);

                intent.putExtras(bundle);
                startActivity(intent);
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
/*
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<cards> cardFilter(List<cards> rowItems) {









        return ;
    }*/

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void ActivityOverview(){
        Intent intent = new Intent(this, ActivityOverview.class);
        startActivity(intent);
        }



    public void populateCards(){
        aktuell = 1;
        connection.queryData("Test", "Ort", "Landshut", new ConnectFirebaseCallback() {
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
                        ArrayList<String> ofTags = new ArrayList<>();
                        JSONArray jsonTags = countArray.getJSONObject(i).getJSONArray("Tags");
                        for(int j = 0; j < jsonTags.length(); j++){
                            ofTags.add(jsonTags.get(j).toString());
                        }
                        rowItems.add(new cards(countArray.getJSONObject(i).get("id").toString(),
                                countArray.getJSONObject(i).get("Name").toString(),
                                countArray.getJSONObject(i).get("Bild").toString(),
                                countArray.getJSONObject(i).get("Beschreibung").toString(),
                                countArray.getJSONObject(i).get("Offen").toString(),
                                countArray.getJSONObject(i).get("Geschlossen").toString(),
                                countArray.getJSONObject(i).get("Ort").toString(),
                                countArray.getJSONObject(i).get("Straße").toString(),
                                countArray.getJSONObject(i).get("Webseite").toString(),
                                countArray.getJSONObject(i).get("Hausnummer").toString(),
                                countArray.getJSONObject(i).get("Preis").toString(),
                                countArray.getJSONObject(i).get("Telefonnummer").toString(),
                                countArray.getJSONObject(i).get("PLZ").toString(),
                                countArray.getJSONObject(i).get("Mailadresse").toString(),
                                ofTags));
                        getFirstImage(countArray.getJSONObject(i).getJSONArray("Bild"), i);
                        Log.d("brgl", countArray.get(0).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }
    public void getFirstImage(JSONArray ImagePaths, final int indexed){
            List<String> ImPaStr = new ArrayList<>();
            try{
                for(int i = 0; i < ImagePaths.length(); i++){
                    Log.d("Imagetest", ImagePaths.get(i).toString());
                    if(!ImagePaths.get(i).toString().equals(null)){
                        ImPaStr.add(ImagePaths.get(i).toString());
                    }else {
                        ImPaStr.add(IM_URL);
                    }
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


    public void openActivityOverview(Object dataObject){
        Intent intent = new Intent(this, ActivityOverview.class);
        startActivity(intent);
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

