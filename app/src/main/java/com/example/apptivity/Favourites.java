package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class Favourites extends AppCompatActivity {

    public static final String MATCHES ="match";
    private Button btBackToHome;
    private Button btResetMatches;
    private Set<String> matches;
    private ConnectFirebase connection = new ConnectFirebase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
        matches = mSharedPreferences.getStringSet(MATCHES, matches);
        if (matches != null) {
        String[] matchesToView = new String[matches.size()];
        int index = 0;
        for (String str : matches)
            matchesToView[index++] = str;

        TableLayout tLayout = findViewById(R.id.matchLL);
        final Bundle[] bundle = new Bundle[matchesToView.length];
            for (int i = 0; i < matchesToView.length; i++) {
                TableRow tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                final Button mButton = new Button(this);
                mButton.setTag(matchesToView[i]);
                final String matchToView = matchesToView[i];
                final int indeXX = i;
                bundle[i] = new Bundle();
                fillBundle(bundle[i], matchToView);
                mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Favourites.this, ActivityOverview.class);
                    intent.putExtras(bundle[indeXX]);
                    startActivity(intent);
                }

                });
            Button dButton = new Button(this);
            final String matchKey = matchesToView[i];
            dButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    matches = mSharedPreferences.getStringSet(MATCHES, matches);
                    matches.remove(matchKey);
                    mEditor.clear();
                    mEditor.putStringSet(MATCHES, matches);
                    mEditor.apply();
                    refreshFav();
                }

            });
            connection.queryData("Activities", "id", matchToView, new ConnectFirebaseCallback() {
                @Override
                public void onCallback(String value) {                  //Gibt Collection und nicht Dokument
                    try {
                        JSONArray name1 = new JSONArray(value);         //Fehler ???
                        JSONObject name = name1.getJSONObject(0);  //Nja Fast !!!
                        mButton.setText(name.getString("Name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

                SystemClock.sleep(75); //hmm

            mButton.setText(matchesToView[i]);
            dButton.setText("X");
            mButton.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dButton.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            mButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            dButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(mButton);
            tr.addView(dButton);
            tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }
        btBackToHome = findViewById(R.id.btBackToHome);
        btBackToHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHome();
            }

        });
        btResetMatches = findViewById(R.id.btResetMatches);
        btResetMatches.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences mSharedPreferences = getSharedPreferences("activity_swiping", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putStringSet(MATCHES, new HashSet<String>());
                mEditor.apply();
                refreshFav();
            }
        });
    }
    private void refreshFav(){
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    private void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void fillBundle(final Bundle bundle, String matchToView){
        connection.queryData("Test", "id", matchToView, new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                try {
                    JSONArray activity1 = new JSONArray(value);
                    JSONObject activity = activity1.getJSONObject(0);

                    String cName = activity.get("Name").toString();
                    String cActID = activity.get("id").toString();
                    String cBudget = activity.get("Preis").toString();
                    String cClosed = activity.get("Geschlossen").toString();
                    String cOpen = activity.get("Offen").toString();
                    String cDescription = activity.get("Beschreibung").toString();
                    String cHouseNumber = activity.get("Hausnummer").toString();
                    String cURL = activity.get("Bild").toString();
                    String cWebsite = activity.get("Webseite").toString();
                    String cStreet = activity.get("Straße").toString();
                    String cPostal = activity.get("PLZ").toString();
                    String cMailAddress = activity.get("Mailadresse").toString();
                    String cLocation = activity.get("Ort").toString();
                    String cPhoneNumber = activity.get("Telefonnummer").toString();


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

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
