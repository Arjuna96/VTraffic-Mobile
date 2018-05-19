package org.example.vtraffic.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ConfirmReqActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView confirm_image;

    Button bConfirm;

    int position_no;
    int junctionId=1;
    TextView tvState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_req);






        Intent mIntent = getIntent();
        position_no = mIntent.getIntExtra("request_id", 0);

        //positionNo=(TextView) findViewById(R.id.positionNo);

        confirm_image=(ImageView) findViewById(R.id.confirm_image);
        if (position_no==0){
            confirm_image.setImageResource(R.drawable.sone);
        }
        else {
            if (position_no==1) {
                confirm_image.setImageResource(R.drawable.ssec);
            }


            else{
                if (position_no==2) {
                    confirm_image.setImageResource(R.drawable.sthrd);
                }

                else{
                    if (position_no==3) {
                        confirm_image.setImageResource(R.drawable.sfour);
                    }

                }

            }
        }




        final ImageView tvBack = (ImageView) findViewById(R.id.ivBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK, null);
                finish();

            }
        });


        bConfirm=(Button) findViewById(R.id.bConfirm);
       /* bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRequest(position_no);
            }
        });*/

       bConfirm.setOnClickListener(this);


    }










    private void createRequest(int id) {

       String userIDentity = PersistenceData.getUseremail();
        double lati=PersistenceData.getLati();
        double lonti=PersistenceData.getLonti();

        String url = "http://18.191.39.15:2000/api/goGreen";

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("trafficLightId", "1");
            jObj.put("routeId", id);
            jObj.put("userlongitude", lonti);
            jObj.put("userlatitude", lati);
            jObj.put("userEmail", userIDentity);

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

                                Toast ToastMessage = Toast.makeText(ConfirmReqActivity.this, "Request Successfully Sent!!", Toast.LENGTH_SHORT);
                                View toastView = ToastMessage.getView();
                                toastView.setBackgroundResource(R.drawable.toast_background_color);
                                ToastMessage.show();
                                bConfirm.setEnabled(false);
                                finish();
                            } else {
                                    bConfirm.setEnabled(false);
                                Toast ToastMessage = Toast.makeText(ConfirmReqActivity.this, "Something Went Wrong!!\n Try Again Later!!", Toast.LENGTH_SHORT);
                                View toastView = ToastMessage.getView();
                                toastView.setBackgroundResource(R.drawable.toast_background_color);
                                ToastMessage.show();
                                finish();
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

    @Override
    public void onClick(View v) {
        if(position_no==0){
            createRequest(0);
        }

        else if (position_no==1) {

                createRequest(1);

                }else if (position_no==2) {

                     createRequest(2);

                    }else if (position_no==3) {

                            createRequest(3);

                            }



    }







}

