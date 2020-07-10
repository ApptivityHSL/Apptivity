package com.example.apptivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

/**
 * The type Search 2.
 */
public class Search2 extends AppCompatActivity {
    /**
     * The constant town.
     */
    protected static String town = "";
    /**
     * The constant postalCode.
     */
    protected static int postalCode;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private double longitude;
    private double latitude;

    private EditText inputTown;
    private EditText inputPostal;
    private ProgressBar progressGPS;
    private ResultReceiver resultReceiver;

    private final int magicTenThousand = 10000;
    private final int magicThreeThousand = 3000;

    /**
     * Instantiates a new Search 2.
     */
    public Search2() {
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);





        inputTown =  findViewById(R.id.inputTown);
        inputPostal =  findViewById(R.id.inputPostal);
        Button getLocation = findViewById(R.id.getLocation);
        progressGPS = findViewById(R.id.progressBarGps);

        progressGPS.setVisibility(View.INVISIBLE);

        resultReceiver = new AddressResultReceiver(new Handler());

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        Search2.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION

                    );
                } else {
                    getCurrentLocation();
                }
            }
        });

        Button btSwipe = findViewById(R.id.btSwipe);
        btSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                inputTown.getText().toString();
                inputPostal.getText().toString();
                if (!inputTown.getText().toString().isEmpty() && !inputPostal
                        .getText().toString().isEmpty()) {
                    town = inputTown.getText().toString();
                    postalCode = Integer.parseInt(inputPostal.getText().toString());
                    safeTown(town);
                    safePostCode(postalCode);
                    openSwipe();
                }
            }

        });
    }

    /**
     * Open swipe.
     */
    public void openSwipe() {
        Intent intent = new Intent(this, LoadingFromDatabase.class);
        startActivity(intent);
    }

    private void safeTown(final String i) {
        SharedPreferences sharedPreferences = getSharedPreferences("town", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("town", i);
        editor.apply();
    }

    private void safePostCode(final int i) {
        SharedPreferences sharedPreferences = getSharedPreferences("postCode", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("postCode", i);
        editor.apply();
    }

    private void fetchAddressFromLatLong(final Location location) {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);


    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        progressGPS.setVisibility(View.VISIBLE);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(magicTenThousand);
        locationRequest.setFastestInterval(magicThreeThousand);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(Search2.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @SuppressLint("MissingPermission")
                    @Override
                    public void onLocationResult(final LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(Search2.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            latitude =
                                    locationResult.getLocations()
                                            .get(latestLocationIndex).getLatitude();
                            longitude =
                                    locationResult.getLocations()
                                            .get(latestLocationIndex).getLongitude();

                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            fetchAddressFromLatLong(location);
                        } else {
                            progressGPS.setVisibility(View.GONE);
                        }

                        Log.d("gps", String.valueOf(latitude));
                        Log.d("gps", String.valueOf(longitude));
                    }
                }, Looper.getMainLooper());

    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION & grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Zugang verweigert!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class AddressResultReceiver extends ResultReceiver {


        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler the handler
         */
        AddressResultReceiver(final Handler handler) {
            super(handler);
        }

        protected void onReceiveResult(final int resultCode, final Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Constants.SUCCESS_RESULT) {
                String result  = resultData.getString(Constants.RESULT_DATA_KEY);
                assert result != null;
                String[] parts = result.split(", ");
                String city = parts[1];


                String[] postalAndCity = city.split(" ");
                int x = postalAndCity.length;
                String postalCodeString;

                Log.d("gps", String.valueOf(x));
                Log.d("gps", postalAndCity[0]);
                Log.d("gps", postalAndCity[1]);


                postalCodeString = postalAndCity[0];
                postalCode = Integer.parseInt(postalAndCity[0]);

                town = "";

                for (int i = 1; i < x; i++) {

                    town = town + " " + postalAndCity[i];
                }
                town = town.trim();

                inputTown.setText(town);
                inputPostal.setText(postalCodeString);
            }
            progressGPS.setVisibility(View.GONE);
        }


    }

}
