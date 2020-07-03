package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class loadingFromDatabase extends AppCompatActivity {

    private ConnectFirebase connection = new ConnectFirebase(this);
    private JSONArray countArray;
    private int actAmount;
    private int aktuell = 0;
    public static List<cards> rowItems; //Für sortieren relevant
    private static boolean warten = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_from_database);

        rowItems = new ArrayList<>();

        new AsyncTaskLoad(this).execute();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, Search2.class);
        startActivity(intent);
    }

    private static class AsyncTaskLoad extends android.os.AsyncTask<Integer, Integer, String>{
        private WeakReference<loadingFromDatabase> activityWeakReference;

        AsyncTaskLoad(loadingFromDatabase activity){
            activityWeakReference = new WeakReference<loadingFromDatabase>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            final loadingFromDatabase activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return null;
            }
            activity.aktuell = 0;
            activity.connection.queryData("Activities", "Ort", "Landshut", new ConnectFirebaseCallback() {
                @Override
                public void onCallback(String value) {
                    try {
                        activity.countArray = new JSONArray(value);
                        activity.actAmount = activity.countArray.length();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < activity.actAmount; i++) { //
                        try {
                            ArrayList<String> ofTags = new ArrayList<>();
                            JSONArray jsonTags = activity.countArray.getJSONObject(i).getJSONArray("Tags");
                            for (int j = 0; j < jsonTags.length(); j++) {
                                ofTags.add(jsonTags.get(j).toString());
                            }
                            rowItems.add(new cards(activity.countArray.getJSONObject(i).get("id").toString(),
                                    activity.countArray.getJSONObject(i).get("Name").toString(),
                                    activity.countArray.getJSONObject(i).get("Bild").toString().replace("\\", "/").replace("//", "/"),
                                    activity.countArray.getJSONObject(i).get("Beschreibung").toString(),
                                    activity.countArray.getJSONObject(i).get("Offen").toString(),
                                    activity.countArray.getJSONObject(i).get("Geschlossen").toString(),
                                    activity.countArray.getJSONObject(i).get("Ort").toString(),
                                    activity.countArray.getJSONObject(i).get("Straße").toString(),
                                    activity.countArray.getJSONObject(i).get("Webseite").toString(),
                                    activity.countArray.getJSONObject(i).get("Hausnummer").toString(),
                                    activity.countArray.getJSONObject(i).get("Preis").toString(),
                                    activity.countArray.getJSONObject(i).get("Telefonnummer").toString(),
                                    activity.countArray.getJSONObject(i).get("PLZ").toString(),
                                    activity.countArray.getJSONObject(i).get("Mailadresse").toString(),
                                    ofTags));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    warten = false;
                }
            });
            while(warten){}
            warten = true;
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loadingFromDatabase activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return;
            }
            Intent intent = new Intent(activity, Swiping.class);
            activity.startActivity(intent);
        }
    }
}
