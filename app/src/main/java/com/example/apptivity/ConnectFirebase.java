package com.example.apptivity;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ConnectFirebase {
    private static final String TAG = ConnectFirebase.class.getSimpleName();
    private String jsonString = "{[";
    private String gson = "";
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public String getAllData(String collection)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection(collection);
        dbRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gson =  new Gson().toJson(document.getData());
                                jsonString += gson + ",";
                                Log.d("argl", jsonString);
                                Log.d("argl", "Test");
                            }
                            jsonString = jsonString.substring(0, jsonString.length()-1) + "]}";
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        Log.d("blarg", jsonString);
        return jsonString;
    }

    public JSONObject queryAll(String collection) throws JSONException
    {
        JSONObject reader = new JSONObject(this.getAllData(collection));
        return reader;
    }
}