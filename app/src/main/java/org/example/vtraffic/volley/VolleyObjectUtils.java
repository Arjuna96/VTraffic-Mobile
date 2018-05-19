package org.example.vtraffic.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.example.vtraffic.controller.AppController;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 4/2/2018.
 */

public class VolleyObjectUtils {

    public static void post_jObjRequest(final Context context, String url, JSONObject jObjPost, final VolleyObjectResponseListener listener){

        Log.i("Post URL", url);

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.POST, url, jObjPost,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Call Object Response", response.toString());

                        listener.onResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                        listener.onError(error);

                        // NetworkResponse response = error.networkResponse;

                        if( error instanceof NetworkError) {
                            Log.i("Call Error", "NetworkError");
                        }else if( error instanceof ServerError) {
                            Log.i("Call Error", "ServerError");
                        } else if( error instanceof AuthFailureError) {
                            Log.i("Call Error", "AuthFailureError");
                        } else if( error instanceof ParseError) {
                            Log.i("Call Error", "ParseError");
                        } else if( error instanceof NoConnectionError) {
                            Log.i("Call Error", "NoConnectionError");
                        } else if( error instanceof TimeoutError) {
                            Log.i("Call Error", "TimeoutError");
                        }


                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

			/*	headers.put("Authorization", context.getResources().getString(R.string.auth_join_key) + " "
						   + AppController.getPrefVal(context.getResources().
									getString(R.string.tag_mi_token), context));
				//headers.put("Content-Type", "json");
				headers.put("Content-Type", "application/json; charset=utf-8");*/

                return headers;
            }


        };

       /* objRequest.setRetryPolicy(new DefaultRetryPolicy(9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        AppController.getInstance().addToRequestQueue(objRequest);

    }

    public static void post_jObjRequestTimeSet(final Context context, String url, JSONObject jObjPost, final VolleyObjectResponseListener listener, int TIMEOUT_MS){

        Log.i("Post URL", url);

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.POST, url, jObjPost,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Call Object Response", response.toString());

                        listener.onResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                        listener.onError(error);

                        // NetworkResponse response = error.networkResponse;

                        if( error instanceof NetworkError) {
                            Log.i("Call Error", "NetworkError");
                        }else if( error instanceof ServerError) {
                            Log.i("Call Error", "ServerError");
                        } else if( error instanceof AuthFailureError) {
                            Log.i("Call Error", "AuthFailureError");
                        } else if( error instanceof ParseError) {
                            Log.i("Call Error", "ParseError");
                        } else if( error instanceof NoConnectionError) {
                            Log.i("Call Error", "NoConnectionError");
                        } else if( error instanceof TimeoutError) {
                            Log.i("Call Error", "TimeoutError");
                        }


                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

			/*	headers.put("Authorization", context.getResources().getString(R.string.auth_join_key) + " "
						+ AppController.getPrefVal(context.getResources().
						getString(R.string.tag_mi_token), context));
				//headers.put("Content-Type", "json");
				headers.put("Content-Type", "application/json; charset=utf-8");*/

                return headers;
            }


        };

        objRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(objRequest);

    }

}
