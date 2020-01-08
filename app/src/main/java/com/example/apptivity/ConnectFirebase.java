package com.example.apptivity;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;

public class ConnectFirebase {
    private String jsonString = "{[";
    private String gson = "";
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public void pullAllData(String collection, final ConnectFirebaseCallback callback)
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
                            }
                        } else {
                            Log.d("argl", "Error getting documents: ", task.getException());
                        }
                        jsonString = jsonString.substring(0, jsonString.length()-1) + "]}";
                        callback.onCallback(jsonString);
                    }
                });
    }
}