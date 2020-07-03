package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Swiping extends  AppCompatActivity {

    private Button btBackHome;

    public static final String MATCHES ="match";
    private int matchnum = 0;

    private Set<String> matches = new HashSet<>();

    private cards cards_data[];
    private MyArrayAdapter arrayAdapter;
    private ArrayList<String> listOfTags = new ArrayList<>();
    private ArrayList<String> listOfPeople = new ArrayList<>();
    private ArrayList<cards> filterdCards = new ArrayList<cards>();
    private int money;
    private String town;
    private int postCode;

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

        //list of people
        SharedPreferences sharedPreferences2 = getSharedPreferences("people", MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences2.getString("people",null);
        Type type2 = new TypeToken<ArrayList>() {}.getType();
        listOfPeople = gson2.fromJson(json2, type2);

        //money
        SharedPreferences sharedPreferences3 = getSharedPreferences("money", MODE_PRIVATE);
        money = sharedPreferences3.getInt("money",0);
        Double moneyDouble = Double.parseDouble(String.valueOf(money));

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



        filterdCards = cardFilter(listOfTags,listOfPeople,money,loadingFromDatabase.rowItems);
        //matches = new HashSet<String>();



        SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
        matches = mSharedPreferences.getStringSet(MATCHES, matches);
        arrayAdapter = new MyArrayAdapter(this, R.layout.item, filterdCards);

        SystemClock.sleep(250); //hmm

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
                filterdCards.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                //Toast.makeText(Swiping.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
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

    public void openActivityOverview(Object dataObject){
        Intent intent = new Intent(this, ActivityOverview.class);
        startActivity(intent);
    }

    public ArrayList<cards> cardFilter(ArrayList<String> listOfTags, ArrayList<String> listOfPeople,double money, List<cards> rowItems){

        ArrayList<cards> result = new ArrayList<cards>();

        SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
        matches = mSharedPreferences.getStringSet(MATCHES, matches);

        String[] matchesFav = convert(matches);
        Log.d("matches", String.valueOf(matches));

        boolean sameTag = false;
        boolean samePeople = false;
        boolean stillInFav = false;

        if(money == 0){
            money = 50;
        }

        for (int i = 0; i < rowItems.size(); i++) {
             sameTag = false;
             samePeople = false;
            stillInFav = false;

            if(listOfPeople.isEmpty()){
                samePeople = true;
            };

            if(listOfTags.isEmpty()){
                sameTag = true;
            };

            if(matches.isEmpty()){
                stillInFav = false;
            };

            cards item = rowItems.get(i);


            for(int i3 = 0; i3 < matches.size(); i3++){
                if(item.getActID().contains(matchesFav[i3])){
                    stillInFav = true;
                }
            }

            for(int i1 = 0; i1 < listOfTags.size(); i1++){
               if(item.getTags().contains(listOfTags.get(i1))){
                   sameTag = true;
               }
            }

            for(int i2 = 0; i2 < listOfPeople.size(); i2++){
                if(item.getTags().contains(listOfPeople.get(i2))){
                    samePeople = true;
                }
            }

            double budget = Double.parseDouble(item.getBudget().replace(",","."));

            if(budget < money && sameTag  && samePeople && !stillInFav)
                result.add(item);
            }
        
        return result;
    }

    public static String[] convert(Set<String> setOfString)
    {
        String[] arrayOfString = new String[setOfString.size()];

        int index = 0;
        for (String str : setOfString)
            arrayOfString[index++] = str;
        return arrayOfString;
    }


}

