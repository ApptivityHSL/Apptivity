package com.example.apptivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class ActivityOverview extends AppCompatActivity {

    private Button btBack;

    private TextView cName;
    private TextView cActID;
    private TextView cBudget;
    private TextView cClosed;
    private TextView cOpen;
    private TextView cDescription;
    private TextView cHouseNumber;
    private ImageView cURL;
    private TextView cWebsite;
    private TextView cStreet;
    private TextView cPostal;
    private TextView cMailAddress;
    private TextView cLocation;
    private TextView cPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        btBack =  findViewById(R.id.btSwiping);
        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSwiping();
            }
        });

        cName = (TextView) findViewById(R.id.cName);
        //cActID = (TextView) findViewById(R.id.cActID);
        cBudget = (TextView) findViewById(R.id.cBudget);
        cClosed = (TextView) findViewById(R.id.cClosed);
        //cOpen = (TextView) findViewById(R.id.cOpen);
        cDescription = (TextView) findViewById(R.id.cDescription);
        cHouseNumber = (TextView) findViewById(R.id.cHouseNumber);
        cURL = (ImageView) findViewById(R.id.actImage);
        cWebsite = (TextView) findViewById(R.id.cWebsite);
        //cStreet = (TextView) findViewById(R.id.cStreet);
        //cPostal = (TextView) findViewById(R.id.cPostal);
        cMailAddress = (TextView) findViewById(R.id.cMailAddress);
        cLocation = (TextView) findViewById(R.id.cLocation);
        cPhoneNumber = (TextView) findViewById(R.id.cPhoneNumber);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("cActID") == null){
            openSwiping();
        }

        try {
            JSONArray picArray = new JSONArray(bundle.getString("cURL"));
            String url = picArray.toString().replace("\"", "").replace("[", "").replace("]", "").replace("\\", "/").replace("//", "/");
            if(url.contains(",")) {
                url.substring(0, url.indexOf(","));
                String picaArray[] = url.split(",");
                Glide.with(this)
                        .load(picaArray[0])
                        .into(cURL);
            }else{
                Glide.with(this)
                        .load(url)
                        .into(cURL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }





        cName.setText(bundle.getString("cName"));
        //cActID.setText(bundle.getString("cActID"));
        cBudget.setText("Budget: "+bundle.getString("cBudget"));
        cClosed.setText("Geöffnet von "+bundle.getString("cOpen")+" bis "+bundle.getString("cClosed"));
        //cOpen.setText("Geöffnet von "+bundle.getString("cOpen"));
        cDescription.setText(bundle.getString("cDescription"));
        cHouseNumber.setText("Anschrift: "+bundle.getString("cStreet")+" "+bundle.getString("cHouseNumber"));
        cWebsite.setText("Website: "+bundle.getString("cWebsite"));
        //cStreet.setText("Anschrift: "+bundle.getString("cStreet"));
        //cPostal.setText("           "+bundle.getString("cPostal"));
        cMailAddress.setText("Email: "+bundle.getString("cMailAddress"));
        cLocation.setText(bundle.getString("cPostal")+" "+bundle.getString("cLocation"));
        cPhoneNumber.setText("Telefonnummer: "+bundle.getString("cPhoneNumber"));
    }

    public void openSwiping(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        boolean fromFavos = sharedPreferences.getBoolean("FROM_FAVOS",true);

        if(fromFavos){
            Intent intent1 = new Intent(this, Favourites.class);
            startActivity(intent1);
        } else {
            Intent intent2 = new Intent(this, Swiping.class);
            startActivity(intent2);
        }

    }

    public void maps(View view)
    {
        String loc = cHouseNumber.getText() + " " + cLocation.getText();
        loc = loc.replace("Anschrift: ", "");
        Log.d("maps", loc);
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+loc);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void call(View view)
    {
        String number = (String) cPhoneNumber.getText();
        number = number.replace("Telefonnummer: ", "");
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:"+number));
        startActivity(dialIntent);
    }

}
