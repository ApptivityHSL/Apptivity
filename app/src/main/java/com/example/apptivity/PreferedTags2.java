package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;


public class PreferedTags2 extends AppCompatActivity {

    private ConnectFirebase aauth = new ConnectFirebase(this);
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String> listOfClickedTags = new ArrayList<>();


    private ProgressBar progressPullAllData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefered_tags2);

        progressPullAllData = findViewById(R.id.progressPullAllData);

        progressPullAllData.setVisibility(View.VISIBLE);

        aauth.queryData("Tag", "Kategorie",
                "0", new ConnectFirebaseCallback() {
            @Override
            public void onCallback(String value) {
                String r = "start";
                int tagCounter = 0;
                while (!r.equals("")) {
                    r = pullProperty(value, tagCounter, "Name");
                    assert r != null;
                    tags.add(r);
                    tagCounter++;
                }
                tags.remove("");
                checkBoxes();
                progressPullAllData.setVisibility(View.GONE);
            }
        });

        Button btPrefTags = findViewById(R.id.btPrefTags);
        btPrefTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        Button btHome = findViewById(R.id.btBackHome);
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safeTags(listOfClickedTags);
                openSearch1();
            }
        });

    }


    private void checkBoxes() {
        for (int i = 0; i < tags.size(); i++) {
            TableLayout tLayout = findViewById(R.id.tagsLL);

            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            CheckBox box = new CheckBox(this);
            CheckBox box2 = new CheckBox(this);

            box.setText(tags.get(i));
            box.setId(i);
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            box.setOnClickListener(onCheckboxClicked(box));
            box.setGravity(Gravity.CENTER);

            i++;

           if (i >= tags.size()) {

               box.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                       TableRow.LayoutParams.WRAP_CONTENT));
               tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                       TableLayout.LayoutParams.WRAP_CONTENT));
                break;
           } else {

            box2.setText(tags.get(i));
            box2.setId(i);
            box2.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            box2.setOnClickListener(onCheckboxClicked(box2));
            box2.setGravity(Gravity.CENTER);

            box.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            box.setPaddingRelative(25, 0, 100, 0);
            box2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            box2.setPaddingRelative(25, 0, 0, 0);

            tr.addView(box);
            tr.addView(box2);
            tr.setGravity(Gravity.CENTER_HORIZONTAL);
            tLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        }
    }


    private void safeTags(ArrayList<String> listOfClickedTags) {
        SharedPreferences sharedPreferences = getSharedPreferences("tags", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String Json = gson.toJson(listOfClickedTags);
        editor.putString("tags", Json);
        editor.apply();
    }


    public void openPersonalInformation() {
        safeTags(listOfClickedTags);
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }

    public void openHome() {
        safeTags(listOfClickedTags);
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openSearch1() {
        safeTags(listOfClickedTags);
        Intent intent = new Intent(this, Search1.class);
        startActivity(intent);
    }

    View.OnClickListener onCheckboxClicked(final Button button) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                for (int index = 0; index < tags.size(); index++) {
                    String t = tags.get(index);
                    String d = (String) button.getText();

                    if (d.equals(t)) {
                        if (checked) {
                            listOfClickedTags.add(tags.get(index));
                        } else {
                            listOfClickedTags.remove(tags.get(index));
                        }
                        break;
                    }
                }
            }
        };
    }

    public String pullProperty(String string, int index, String property) {
        JSONArray array;
        String information = "";
        try {
            array = new JSONArray(string);
            information = array.getJSONObject(index).get(property).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("testtest", information);
        return information;
    }

    public void onAuthStateChanged(FirebaseAuth auth) {

    }

}
