package org.example.vtraffic.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.example.vtraffic.R;
import org.example.vtraffic.volley.VolleyObjectResponseListener;
import org.example.vtraffic.volley.VolleyObjectUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    //handling sending requests to server, from the buttons in the app

   // ImageView imageViewSouth;
    // ImageView imageViewEast;

    private ImageButton ibArrowLeft;
    private ImageButton ibArrowForward;
    private ImageButton ibArrowRight;
    private double lan;
    private double lon;



    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        //imageViewSouth = (ImageView) findViewById(R.id.ivLocationSouth);
        //imageViewEast = (ImageView) findViewById(R.id.ivLocationEast);
        //String title = getIntent().getStringExtra("title");

       /* if(title.equals("Wellawatta Junction South")){
            imageViewSouth.setVisibility(View.VISIBLE);
            //imageViewEast.setVisibility(View.GONE);
        }else {

            imageViewSouth.setVisibility(View.GONE);
            // imageViewEast.setVisibility(View.VISIBLE);
        }*/

        lan = getIntent().getDoubleExtra("locationlan",0);
        lon = getIntent().getDoubleExtra("locationlon",0);
        ibArrowLeft = (ImageButton) findViewById(R.id.ibArrowLeft);
        ibArrowForward = (ImageButton) findViewById(R.id.ibArrowForward);
        ibArrowRight = (ImageButton) findViewById(R.id.ibArrowRight);

        ibArrowLeft.setOnClickListener(this);
        ibArrowForward.setOnClickListener(this);
        ibArrowRight.setOnClickListener(this);

        final ImageView tvBack = (ImageView) findViewById(R.id.ivBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK, null);
                finish();

            }
        });
    }



    @Override
    public void onClick(View v) {



        if (v == ibArrowLeft) {

            createRequest(1);

        }else if (v == ibArrowForward) {

            createRequest(2);

        }else if (v == ibArrowRight) {

            createRequest(3);

        }

    }


    private void createRequest(int id) {

        String url = "http://18.221.95.10:2000/api/goGreen";

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("trafficLightid", "1");
            jObj.put("userlatitude", lan);
            jObj.put("userlongitude", lon);
            jObj.put("routeid", id);

            Log.i("JasonObject", jObj.toString());
        } catch (JSONException jex) {
            jex.printStackTrace();
        }


        VolleyObjectUtils.post_jObjRequestTimeSet(getApplicationContext(), url, jObj,
                new VolleyObjectResponseListener() {

                    @Override
                    public void onResponse(Object response) {


                        Log.i("createRequest", response.toString());

                        JSONObject jObj = (JSONObject) response;

                        try {
                            if (jObj.getString("Status").equals("success")) {
                                finish();
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Object error) {

                        VolleyError volleyErr = (VolleyError) error;
                        NetworkResponse response = volleyErr.networkResponse;
                        if (response != null && response.data != null) {
                            String json = new String(response.data);

                            // loading.dismiss();

                            Log.i("Create Response", new String(response.data).toString());
                            try {
                                JSONObject jObj = new JSONObject(json);

                               /* if (!jObj.getBoolean("Success")) {

                                }*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, 30000);
    }
}
