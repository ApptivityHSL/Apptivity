package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The type Favourites.
 */
public class Favourites extends AppCompatActivity {

    /**
     * The constant MATCHES.
     */
    public static final String MATCHES = "match";
    private Set<String> matches;
    private ConnectFirebase connection = new ConnectFirebase(this);
    private final int magicSeventyFive = 75;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        SharedPreferences mSharedPreferences =
                getSharedPreferences("activity_swiping", MODE_PRIVATE);
        matches = mSharedPreferences.getStringSet(MATCHES, matches);
        if (matches != null) {
        String[] matchesToView = new String[matches.size()];
        int index = 0;
        for (String str : matches) {
            matchesToView[index++] = str;
        }

        TableLayout tLayout = findViewById(R.id.matchLL);
        final Bundle[] bundle = new Bundle[matchesToView.length];
            for (int i = 0; i < matchesToView.length; i++) {
                TableRow tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                final Button mButton = new Button(this);
                mButton.setTag(matchesToView[i]);
                final String matchToView = matchesToView[i];
                final int indeXX = i;
                bundle[i] = new Bundle();
                fillBundle(bundle[i], matchToView);
                mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent intent = new Intent(
                            Favourites.this, ActivityOverview.class);
                    intent.putExtras(bundle[indeXX]);
                    startActivity(intent);
                }

                });
            Button delButton = new Button(this);
            final String matchKey = matchesToView[i];
            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    SharedPreferences mSharedPreferences =
                            getSharedPreferences("activity_swiping", MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    matches = mSharedPreferences.getStringSet(MATCHES, matches);
                    matches.remove(matchKey);
                    mEditor.clear();
                    mEditor.putStringSet(MATCHES, matches);
                    mEditor.apply();
                    refreshFav();
                }

            });
            connection.queryData("Activities", "id", matchToView,
                    new ConnectFirebaseCallback() {
                @Override
                public void onCallback(final String value) {  //Gibt Collection und nicht Dokument
                    try {
                        JSONArray name1 = new JSONArray(value);         //Fehler ???
                        JSONObject name = name1.getJSONObject(0);  //Nja Fast !!!
                        mButton.setText(name.getString("Name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

                SystemClock.sleep(magicSeventyFive); //hmm

            mButton.setText(matchesToView[i]);
            delButton.setText("X");
            mButton.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            delButton.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            mButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
                    mButton.setBackgroundResource(R.drawable.rounded_button);
            delButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            delButton.setBackgroundResource(R.drawable.rounded_button);
            tr.addView(mButton);
            tr.addView(delButton);
            tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }
        Button btBackToHome = findViewById(R.id.btBackToHome);
        btBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openHome();
            }

        });
        Button btResetMatches = findViewById(R.id.btResetMatches);
        btResetMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                SharedPreferences mSharedPreferences =
                        getSharedPreferences("activity_swiping", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putStringSet(MATCHES, new HashSet<String>());
                mEditor.apply();
                refreshFav();
            }
        });
    }

    private void refreshFav() {
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void fillBundle(final Bundle bundle, final String matchToView) {
        connection.queryData("Activities", "id", matchToView, new ConnectFirebaseCallback() {
            @Override
            public void onCallback(final String value) {
                try {
                    JSONArray activity1 = new JSONArray(value);
                    JSONObject activity = activity1.getJSONObject(0);

                    String ccName = activity.get("Name").toString();
                    String ccActID = activity.get("id").toString();
                    String ccBudget = activity.get("Preis").toString();
                    String ccClosed = activity.get("Geschlossen").toString();
                    String ccOpen = activity.get("Offen").toString();
                    String ccDescription = activity.get("Beschreibung").toString();
                    String ccHouseNumber = activity.get("Hausnummer").toString();
                    String ccURL = activity.get("Bild").toString();
                    String ccWebsite = activity.get("Webseite").toString();
                    String ccStreet = activity.get("Straße").toString();
                    String ccPostal = activity.get("PLZ").toString();
                    String ccMailAddress = activity.get("Mailadresse").toString();
                    String ccLocation = activity.get("Ort").toString();
                    String ccPhoneNumber = activity.get("Telefonnummer").toString();


                    bundle.putString("cName", ccName);
                    bundle.putString("cActID", ccActID);
                    bundle.putString("cBudget", ccBudget);
                    bundle.putString("cClosed", ccClosed);
                    bundle.putString("cOpen", ccOpen);
                    bundle.putString("cDescription", ccDescription);
                    bundle.putString("cHouseNumber", ccHouseNumber);
                    bundle.putString("cURL", ccURL);
                    bundle.putString("cWebsite", ccWebsite);
                    bundle.putString("cStreet", ccStreet);
                    bundle.putString("cPostal", ccPostal);
                    bundle.putString("cMailAddress", ccMailAddress);
                    bundle.putString("cLocation", ccLocation);
                    bundle.putString("cPhoneNumber", ccPhoneNumber);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
