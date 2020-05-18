package com.example.apptivity;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;


public class ConnectFirebase {
    private String gson = "";
    private FirebaseAuth mAuth;

    public ConnectFirebase(){}

    public ConnectFirebase(Activity current)
    {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously()
                .addOnCompleteListener(current, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("cfb_auth", "FirebaseAuthErfolg");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w("cfb_auth", "FirebaseAuthFehler", task.getException());
                        }

                        // ...
                    }
                });
    }

    public void pullAllData(String collection, final ConnectFirebaseCallback callback)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection(collection);

        dbRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String jsonString = "[";
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gson =  new Gson().toJson(document.getData());
                                jsonString += gson + ",";
                            }
                        } else {
                            Log.d("argl", "Error getting documents: ", task.getException());
                        }
                        jsonString = jsonString.substring(0, jsonString.length()-1) + "]";
                        callback.onCallback(jsonString);
                    }
                });
    }

    public void queryData(String collection, String condition, String conditionValue, final ConnectFirebaseCallback callback)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection(collection);

        dbRef.whereEqualTo(condition, conditionValue)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String jsonString = "[";
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                gson =  new Gson().toJson(document.getData());
                                jsonString += gson + ",";
                            }
                        } else {
                            Log.d("argl", "Error getting documents: ", task.getException());
                        }
                        Log.d("gnarfl", jsonString);
                        jsonString = jsonString.substring(0, jsonString.length()-1) + "]";
                        callback.onCallback(jsonString);
                    }
                });
    }

    public void getImageURL(String bild, final ConnectFirebaseCallback callback)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        storageRef.child(bild).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                callback.onCallback(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("argl", "getImageError");
            }
        });
    }

}