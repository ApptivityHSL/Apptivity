package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;

public class ActivityOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Button btBack;
        TextView tvName;
        TextView tvBudget;
        TextView tvClosed;
        TextView tvDescription;
        TextView tvHouseNumber;
        ImageView imURL;
        TextView tvWebsite;
        TextView tvMailAddress;
        TextView tvLocation;
        TextView tvPhoneNumber;

        btBack =  findViewById(R.id.btSwiping);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSwiping();
            }
        });

        tvName = (TextView) findViewById(R.id.cName);
        tvBudget = (TextView) findViewById(R.id.cBudget);
        tvClosed = (TextView) findViewById(R.id.cClosed);
        tvDescription = (TextView) findViewById(R.id.cDescription);
        tvHouseNumber = (TextView) findViewById(R.id.cHouseNumber);
        imURL = (ImageView) findViewById(R.id.actImage);
        tvWebsite = (TextView) findViewById(R.id.cWebsite);
        tvMailAddress = (TextView) findViewById(R.id.cMailAddress);
        tvLocation = (TextView) findViewById(R.id.cLocation);
        tvPhoneNumber = (TextView) findViewById(R.id.cPhoneNumber);

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        if (bundle.getString("cActID") == null) {
            openSwiping();
        }

        try {
            JSONArray picArray = new JSONArray(bundle.getString("cURL"));
            String url = picArray.toString().replace("\"", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\\", "/")
                    .replace("//", "/");
            if (url.contains(",")) {
                url.substring(0, url.indexOf(","));
                String[] picaArray = url.split(",");
                Glide.with(this)
                        .load(picaArray[0])
                        .into(imURL);
            } else {
                Glide.with(this)
                        .load(url)
                        .into(imURL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvName.setText(bundle.getString("cName"));
        //cActID.setText(bundle.getString("cActID"));
        tvBudget.setText(String.format("Budget: %s", bundle.getString("cBudget")));
        tvClosed.setText(String.format("Geöffnet von %s bis %s",
                bundle.getString("cOpen"), bundle.getString("cClosed")));
        //cOpen.setText("Geöffnet von "+bundle.getString("cOpen"));
        tvDescription.setText(bundle.getString("cDescription"));
        tvHouseNumber.setText(String.format("Anschrift: %s %s",
                bundle.getString("cStreet"), bundle.getString("cHouseNumber")));
        tvWebsite.setText(String.format("Website: %s", bundle.getString("cWebsite")));
        //cStreet.setText("Anschrift: "+bundle.getString("cStreet"));
        //cPostal.setText("           "+bundle.getString("cPostal"));
        tvMailAddress.setText(String.format("Email: %s", bundle.getString("cMailAddress")));
        tvLocation.setText(String.format("%s %s", bundle.getString("cPostal"),
                bundle.getString("cLocation")));
        tvPhoneNumber.setText(String.format("Telefonnummer: %s",
                bundle.getString("cPhoneNumber")));
    }

    public void openSwiping() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        boolean fromFavos = sharedPreferences.getBoolean("FROM_FAVOS", true);

        if (fromFavos) {
            Intent intent1 = new Intent(this, Favourites.class);
            startActivity(intent1);
        } else {
            Intent intent2 = new Intent(this, Swiping.class);
            startActivity(intent2);
        }

    }

}
