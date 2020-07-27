package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

import static com.example.apptivity.PersonalInformation.INPUT_FEMALE;
import static com.example.apptivity.PersonalInformation.INPUT_MALE;

/**
 * The type Loading from database.
 */
public class LoadingFromDatabase extends AppCompatActivity {

    private ConnectFirebase connection = new ConnectFirebase(this);
    private JSONArray countArray;
    private int actAmount;
    private int aktuell = 0;
    private String conditionTown;
    /**
     * The constant rowItems.
     */
    public static List<cards> rowItems; //Für sortieren relevant
    private static volatile boolean warten = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_from_database);

        SharedPreferences mSharedPreferences = getSharedPreferences("town", MODE_PRIVATE);
        conditionTown = mSharedPreferences.getString("town", "Dorfen");

        Log.d("Tags987654", conditionTown);

        rowItems = new ArrayList<>();

        new AsyncTaskLoad(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, Search2.class);
        startActivity(intent);
    }

    private class AsyncTaskLoad extends android.os.AsyncTask<Integer, Integer, String> {
        private WeakReference<LoadingFromDatabase> activityWeakReference;

        /**
         * Instantiates a new Async task load.
         *
         * @param activity the activity
         */
        AsyncTaskLoad(final LoadingFromDatabase activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(final Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(final Integer... integers) {
            final LoadingFromDatabase activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }

            activity.aktuell = 0;

            activity.connection.queryData("Activities", "Ort",
                    conditionTown, new ConnectFirebaseCallback() {
                @Override
                public void onCallback(final String value) {
                    try {
                        activity.countArray = new JSONArray(value);
                        activity.actAmount = activity.countArray.length();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < activity.actAmount; i++) { //
                        try {
                            ArrayList<String> ofTags = new ArrayList<>();
                            JSONArray jsonTags = activity.countArray.getJSONObject(i)
                                    .getJSONArray("Tags");
                            for (int j = 0; j < jsonTags.length(); j++) {
                                ofTags.add(jsonTags.get(j).toString());
                            }
                            rowItems.add(new cards(activity.countArray.getJSONObject(i).get("id")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Name")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Bild")
                                            .toString().replace("\\", "/")
                                            .replace("//", "/"),
                                    activity.countArray.getJSONObject(i).get("Beschreibung")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Offen")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Geschlossen")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Ort")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Straße")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Webseite")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Hausnummer")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Preis")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Telefonnummer")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("PLZ")
                                            .toString(),
                                    activity.countArray.getJSONObject(i).get("Mailadresse")
                                            .toString(),
                                    ofTags));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    warten = false;
                }
            });
            while (warten) { }
            warten = true;
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            LoadingFromDatabase activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            Intent intent = new Intent(activity, Swiping.class);
            activity.startActivity(intent);
        }
    }

    public static List<cards> getRowItems() {
        return rowItems;
    }
}
