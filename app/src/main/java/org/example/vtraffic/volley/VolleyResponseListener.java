package org.example.vtraffic.volley;

/**
 * Created by DELL on 4/2/2018.
 */

public interface VolleyResponseListener {

    void onError(String message);

    void onResponse(Object response);
}
