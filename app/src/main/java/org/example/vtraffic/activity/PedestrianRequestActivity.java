package org.example.vtraffic.activity;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.android.volley.NetworkResponse;
        import com.android.volley.VolleyError;

        import org.example.vtraffic.R;
        import org.example.vtraffic.volley.VolleyObjectResponseListener;
        import org.example.vtraffic.volley.VolleyObjectUtils;
        import org.json.JSONException;
        import org.json.JSONObject;

public class PedestrianRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bRequestGreen;
    private double lan;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedestrian_request);

        lan = getIntent().getDoubleExtra("locationlan",0);
        lon = getIntent().getDoubleExtra("locationlon",0);
        bRequestGreen = (Button) findViewById(R.id.bRequestGreen);


        bRequestGreen.setOnClickListener(this);

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
        createRequest(4);
    }

    private void createRequest(int id) {
        //sending junction id, user location, button requested; to server

        String url = "http://18.221.95.10:2000/api/Location";

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("locationId", "1");
            jObj.put("Latitude", lan);
            jObj.put("Longitude", lon);
            jObj.put("ButtonId", id);

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
                            if (jObj.getBoolean("Success")) {
                                Toast.makeText(PedestrianRequestActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(PedestrianRequestActivity.this,"Error Occured!!",Toast.LENGTH_LONG).show();
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

                                if (!jObj.getBoolean("Success")) {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, 30000);
    }
}

