package org.example.vtraffic.utills;

import android.location.Location;

/**
 * Created by Dell on 1/17/2018.
 */
public interface LocationManagerInterface {

    String TAG = LocationManagerInterface.class.getSimpleName();

    void locationFetched(Location mLocation, Location oldLocation, String time, String locationProvider);
}
