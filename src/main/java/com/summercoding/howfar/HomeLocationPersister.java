package com.summercoding.howfar;

import android.content.SharedPreferences;
import android.util.Log;

public class HomeLocationPersister {

    private final static String TAG = HomeLocationPersister.class.getSimpleName();

    private final static String HOME_LATITUDE = "homeLatitude";
    private final static String HOME_LONGITUDE = "homeLongitude";

    private final SharedPreferences sharedPreferences;

    private double latitude = 0.0;
    private double longitude = 0.0;

    public HomeLocationPersister(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;

        initFields();
    }

    public boolean isSet() {
        return latitude != 0.0 || longitude != 0.0;
    }

    public void store(double latitude, double longitude) {
        Log.d(TAG, String.format("Store home location: %f, %f", latitude, longitude));

        storeInSharedPreferences(latitude, longitude);
        initFields();
    }

    private void storeInSharedPreferences(double latitude, double longitude) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(HOME_LATITUDE, Double.doubleToRawLongBits(latitude));
        editor.putLong(HOME_LONGITUDE, Double.doubleToRawLongBits(longitude));
        editor.commit();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private void initFields() {
        latitude = Double.longBitsToDouble(sharedPreferences.getLong(HOME_LATITUDE, 0L));
        longitude = Double.longBitsToDouble(sharedPreferences.getLong(HOME_LONGITUDE, 0L));
    }
}
