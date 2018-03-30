package org.example.vtraffic;

import android.location.Location;

/**
 * Created by Prabhashi on 1/17/2018.
 */
public interface LocationManagerInterface {

    String TAG = LocationManagerInterface.class.getSimpleName();

    void locationFetched(Location mLocation, Location oldLocation, String time, String locationProvider);
}
