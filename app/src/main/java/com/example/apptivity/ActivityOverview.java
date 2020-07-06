package com.example.apptivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * The type Activity overview.
 */
public class ActivityOverview extends AppCompatActivity {
    private TextView tvHouseNumber;
    private TextView tvLocation;
    private TextView tvPhoneNumber;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Button btBack = findViewById(R.id.btSwiping);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openSwiping();
            }
        });

        TextView tvName = findViewById(R.id.cName);
        TextView tvBudget = findViewById(R.id.cBudget);
        TextView tvClosed = findViewById(R.id.cClosed);
        TextView tvDescription = findViewById(R.id.cDescription);
        tvHouseNumber = findViewById(R.id.cHouseNumber);
        ImageView imURL = findViewById(R.id.actImage);
        TextView tvWebsite = findViewById(R.id.cWebsite);
        TextView tvMailAddress = findViewById(R.id.cMailAddress);
        tvLocation = findViewById(R.id.cLocation);
        tvPhoneNumber = findViewById(R.id.cPhoneNumber);

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        if (bundle.getString("cActID") == null) {
            openSwiping();
        }

        try {
            JSONArray picArray = new JSONArray(bundle.getString("cURL"));
            String url = picArray.toString().replace("\"", "")
                    .replace("[", "").replace("]", "")
                    .replace("\\", "/").replace("//", "/");
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

        Spannable spanHouseNumber = new SpannableString(String.format("Anschrift: %s %s", bundle.getString("cStreet"),
                bundle.getString("cHouseNumber")));
        spanHouseNumber.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.Logotürkis)), 11, spanHouseNumber.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanHouseNumber.setSpan(new UnderlineSpan(), 11, spanHouseNumber.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Spannable spanPhoneNumber = new SpannableString(String.format("Telefonnummer: %s",
                bundle.getString("cPhoneNumber")));
        spanPhoneNumber.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.Logotürkis)), 15, spanPhoneNumber.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanPhoneNumber.setSpan(new UnderlineSpan(), 15, spanPhoneNumber.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvName.setText(bundle.getString("cName"));
        //cActID.setText(bundle.getString("cActID"));
        tvBudget.setText(String.format("Budget: %s", bundle.getString("cBudget") + "€"));
        tvClosed.setText(String.format("Geöffnet von %s bis %s", bundle.getString("cOpen"),
                bundle.getString("cClosed")));
        //cOpen.setText("Geöffnet von "+bundle.getString("cOpen"));
        tvDescription.setText(bundle.getString("cDescription"));
        tvHouseNumber.setText(spanHouseNumber);
        tvWebsite.setText(String.format("Website: %s", bundle.getString("cWebsite")));
        //cStreet.setText("Anschrift: "+bundle.getString("cStreet"));
        //cPostal.setText("           "+bundle.getString("cPostal"));
        tvMailAddress.setText(String.format("Email: %s", bundle.getString("cMailAddress")));
        tvLocation.setText(String.format("%s %s", bundle.getString("cPostal"),
                bundle.getString("cLocation")));
        tvPhoneNumber.setText(spanPhoneNumber);

        tvLocation.setPaintFlags(tvLocation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    /**
     * Open swiping.
     */
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

    /**
     * Maps.
     *
     * @param view the view
     */
    public void maps(final View view) {
        String loc = tvHouseNumber.getText() + " " + tvLocation.getText();
        loc = loc.replace("Anschrift: ", "");
        Log.d("maps", loc);
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + loc);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    /**
     * Call.
     *
     * @param view the view
     */
    public void call(final View view) {
        String number = (String) tvPhoneNumber.getText();
        number = number.replace("Telefonnummer: ", "");
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + number));
        startActivity(dialIntent);
    }

}
