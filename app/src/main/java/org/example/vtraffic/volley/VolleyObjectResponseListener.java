package org.example.vtraffic.volley;

/**
 * Created by DELL on 4/2/2018.
 */

public interface VolleyObjectResponseListener {
    void onError(Object error);

    void onResponse(Object response);
}
