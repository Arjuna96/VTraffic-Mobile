package org.example.vtraffic.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.Timer;
import java.util.TimerTask;

public class ReqActivity extends AppCompatActivity {

    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    TextView tvState;
    private Timer myTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);


        viewPager=(ViewPager) findViewById(R.id.view_pager);
        adapter=new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

//        new android.os.Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(), "sending.. ", Toast.LENGTH_SHORT).show();
//                getCurrentstatus(1);
//            }
//        },2000);

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                getCurrentstatus(1);
//                Toast.makeText(getApplicationContext(), "sending.. ", Toast.LENGTH_SHORT).show();
//
//            }
//        }, 2000);

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    getCurrentstatus(1);
//                }
//
//            }, 2000);




        //getCurrentstatus(1);

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                TimerMethod();
            }

        }, 0, 2000);






        final ImageView tvBack = (ImageView) findViewById(R.id.ivBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK, null);
                finish();

            }
        });



        tvState=(TextView) findViewById(R.id.tvState);


    }

    private void getCurrentstatus(int trafficLightId) {



        String url = "http://18.191.39.15:2000/api/getCurrentState";

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("trafficLightId", "1");


            Log.i("JasonObject", jObj.toString());
        } catch (JSONException jex) {
            jex.printStackTrace();
        }


        VolleyObjectUtils.post_jObjRequestTimeSet(getApplicationContext(), url, jObj,
                new VolleyObjectResponseListener() {

                    @Override
                    public void onResponse(Object response) {


                        Log.i("sendRequest", response.toString());

                        JSONObject jObj = (JSONObject) response;


                        try {
//                            jObj.getString("Current_state");
                            tvState.setText(jObj.getString("Current_state"));
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


    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here
            getCurrentstatus(1);

        }
    };



}
