package com.example.apptivity;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
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

import java.util.Objects;

/**
 * The type Connect firebase.
 */
public class ConnectFirebase {
    private String gson = "";
    private FirebaseAuth mAuth;

    /**
     * Instantiates a new Connect firebase.
     *
     * @param current the current
     */
    public ConnectFirebase(final Activity current) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously()
                .addOnCompleteListener(current, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
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

    /**
     * Pull all data.
     *
     * @param collection the collection
     * @param callback   the callback
     */
    public void pullAllData(final String collection, final ConnectFirebaseCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection(collection);

        dbRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                        StringBuilder jsonString = new StringBuilder("[");
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                gson =  new Gson().toJson(document.getData());
                                jsonString.append(gson).append(",");
                            }
                        } else {
                            Log.d("argl", "Error getting documents: ",
                                    task.getException());
                        }
                        jsonString = new StringBuilder(jsonString.substring(0, jsonString.length() - 1) + "]");
                        callback.onCallback(jsonString.toString());
                    }
                });
    }
    /**
     * Query data.
     *
     * @param collection     the collection
     * @param condition      the condition
     * @param conditionValue the condition value
     * @param callback       the callback
     */
    public void queryData(final String collection, final String condition,
                          final String conditionValue, final ConnectFirebaseCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection(collection);

        dbRef.whereEqualTo(condition, conditionValue)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                        StringBuilder jsonString = new StringBuilder("[");
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                gson =  new Gson().toJson(document.getData());
                                jsonString.append(gson).append(",");
                            }
                        } else {
                            Log.d("argl", "Error getting documents: ",
                                    task.getException());
                        }
                        Log.d("gnarfl", jsonString.toString());
                        jsonString = new StringBuilder(jsonString.substring(0, jsonString.length() - 1) + "]");
                        callback.onCallback(jsonString.toString());
                    }
                });
    }

    /**
     * Gets image url.
     *
     * @param bild     the bild
     * @param callback the callback
     */
    public void getImageURL(final String bild, final ConnectFirebaseCallback callback) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        storageRef.child(bild).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(final Uri uri) {
                callback.onCallback(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull final Exception exception) {
                Log.d("argl", "getImageError");
            }
        });
    }
}