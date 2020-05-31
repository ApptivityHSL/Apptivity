package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class loadingFromDatabase extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private String IM_URL = "https://www.apptivity.com/Apptivity-WebApp/Apptivity-OG-Image.jpg";
    private ConnectFirebase connection = new ConnectFirebase(this);
    private JSONArray countArray;
    private int actAmount;
    private int aktuell = 0;
    public static List<cards> rowItems; //Für sortieren relevant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_from_database);

        rowItems = new ArrayList<>();






        startAsyncTask();
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(loadingFromDatabase.this, Swiping.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);*/
    }

    public void startAsyncTask(){
        AsyncTaskLoad task = new AsyncTaskLoad(this);
        task.execute();
    }

    private static class AsyncTaskLoad extends android.os.AsyncTask<Integer, Void, String>{
        private WeakReference<loadingFromDatabase> activityWeakReference;

        AsyncTaskLoad(loadingFromDatabase activity){
            activityWeakReference = new WeakReference<loadingFromDatabase>(activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("doing?", "doing1");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            final loadingFromDatabase activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return null;
            }
            activity.aktuell = 0;
            Log.d("doing?", "doing2");
            activity.connection.queryData("Test", "Ort", "Landshut", new ConnectFirebaseCallback() {
                @Override
                public void onCallback(String value) {
                    try {
                        activity.countArray = new JSONArray(value);
                        activity.actAmount = activity.countArray.length();
                        Log.d("ObjektZahl", activity.actAmount + "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("anzahl", activity.actAmount + "");
                    for (int i = 0; i < activity.actAmount; i++) {
                        try {
                            ArrayList<String> ofTags = new ArrayList<>();
                            JSONArray jsonTags = activity.countArray.getJSONObject(i).getJSONArray("Tags");
                            for (int j = 0; j < jsonTags.length(); j++) {
                                ofTags.add(jsonTags.get(j).toString());
                            }
                            /*String s = activity.countArray.getJSONObject(i).get("Bild").toString();
                            s = s.replace("\\", "/").replace("//", "/");
                            Log.d("BildS", s);*/
                            rowItems.add(new cards(activity.countArray.getJSONObject(i).get("id").toString(),
                                    activity.countArray.getJSONObject(i).get("Name").toString(),
                                    //s,
                                    activity.countArray.getJSONObject(i).get("Bild").toString().replace("\\", "/").replace("//", "/"),
                                    //activity.countArray.getJSONObject(i).getJSONArray("Bild").getJSONObject(0).toString(),
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
                            //Log.d("BildS", activity.countArray.getJSONObject(i).get("Bild").toString().replace("\\", "/").replace("//", "/"));
                            //Log.d("BildSoll", activity.countArray.getJSONObject(i).get("Bild").toString());
                            //Log.d("BildSoll", activity.countArray.getJSONObject(i).getJSONArray("Bild").getJSONObject(0).toString());
                            //getFirstImage(activity.countArray.getJSONObject(i).getJSONArray("Bild"), i);
                            //Log.d("brgl", activity.countArray.get(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            /*
            for (int i = 0; i < activity.actAmount; i++) {
                JSONArray ImagePaths = null;
                try {
                    ImagePaths = activity.countArray.getJSONObject(i).getJSONArray("Bild");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<String> ImPaStr = new ArrayList<>();
                try {
                    for (int j = 0; j < ImagePaths.length(); j++) {
                        Log.d("Imagetest", ImagePaths.get(i).toString());
                        if (!ImagePaths.get(j).toString().equals(null)) {
                            ImPaStr.add(ImagePaths.get(j).toString());
                        } else {
                            ImPaStr.add(activity.IM_URL);
                        }
                        Log.d("Imageteststr", ImPaStr.get(j));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("nixFunc", String.valueOf(ImPaStr));
                String im = ImPaStr.get(0);
                Log.d("testingerIM", im);
                activity.connection.getImageURL(im, new ConnectFirebaseCallback() {
                    @Override
                    public void onCallback(String value) {
                        Log.d("Whyno2cardStillNothing", rowItems.get(1).getImURL());
                        Log.d("testinger", value);
                        Log.d("aktuell", activity.aktuell + "");
                        rowItems.get(activity.aktuell).setImURL(value);
                        Log.d("Whyno2cardLinkPls", rowItems.get(1).getImURL());
                        activity.aktuell++;
                    }
                });
            }*/
            return null;
        }
        /*
        public void getFirstImage(JSONArray ImagePaths, final int indexed){
            final loadingFromDatabase activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return;
            }
            List<String> ImPaStr = new ArrayList<>();
            try{
                for(int i = 0; i < ImagePaths.length(); i++){
                    Log.d("Imagetest", ImagePaths.get(i).toString());
                    if(!ImagePaths.get(i).toString().equals(null)){
                        ImPaStr.add(ImagePaths.get(i).toString());
                    }else {
                        ImPaStr.add(activity.IM_URL);
                    }
                    Log.d("Imageteststr", ImPaStr.get(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String im = ImPaStr.get(0);
            Log.d("testingerIM", im);
            activity.connection.getImageURL(im, new ConnectFirebaseCallback() {
                @Override
                public void onCallback(String value) {
                    Log.d("Whyno2cardStillNothing", rowItems.get(1).getImURL());
                    Log.d("testinger", value);
                    Log.d("aktuell", activity.aktuell+"");
                    rowItems.get(activity.aktuell).setImURL(value);
                    Log.d("Whyno2cardLinkPls", rowItems.get(activity.aktuell).getImURL());
                    activity.aktuell++;
                }
            });
        }*/

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("doing?", "doing");
            loadingFromDatabase activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()){
                return;
            }
            Intent intent = new Intent(activity, Swiping.class);
            activity.startActivity(intent);
        }
    }
}
