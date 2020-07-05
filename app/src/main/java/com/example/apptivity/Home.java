package com.example.apptivity;

import static com.example.apptivity.PersonalInformation.INPUT_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Home.
 */
public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        insertNameintoTextView();

        Button btFav = findViewById(R.id.btFav);
        btFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openFavos();
            }

        });

        Button btSearch = findViewById(R.id.btSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openPrefTags2();
            }

        });

        Button btOptions = findViewById(R.id.btOptions);
        btOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openPersonOverview();
            }

        });

    }

    private void insertNameintoTextView() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        String text = "Hallo " + mSharedPreferences.getString(INPUT_NAME, "") + "!";
        TextView greetings = findViewById(R.id.greetings);
        greetings.setText(text);
    }

    /**
     * Open search.
     */
    public void openSearch() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("FROM_FAVOS", false);
        mEditor.apply();

        Intent intent = new Intent(this, Search1.class);
        startActivity(intent);
    }

    /**
     * Open favos.
     */
    public void openFavos() {

        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("FROM_FAVOS", true);
        mEditor.apply();

        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    /**
     * Open person overview.
     */
    public void openPersonOverview() {
        Intent intent = new Intent(this, PersonOverview.class);
        startActivity(intent);
    }

    /**
     * Open pref tags 2.
     */
    public void openPrefTags2() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("FROM_FAVOS", false);
        mEditor.apply();
        Intent intent = new Intent(this, PreferedTags2.class);
        startActivity(intent);
    }

}

