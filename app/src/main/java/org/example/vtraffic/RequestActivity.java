package org.example.vtraffic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestActivity extends AppCompatActivity {

   // ImageView imageViewSouth;
    // ImageView imageViewEast;

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



        final ImageView tvBack = (ImageView) findViewById(R.id.ivBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK, null);
                finish();

            }
        });


      final  ImageButton ibArrowLeft= (ImageButton) findViewById(R.id.ibArrowLeft);
        final  ImageButton ibArrowForward= (ImageButton) findViewById(R.id.ibArrowForward);
        final  ImageButton ibArrowRight= (ImageButton) findViewById(R.id.ibArrowRight);
        ibArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               count=count+1;
                if(count==1){

                   // BackgroundTask backgroundTask=new BackgroundTask(this);
                    //backgroundTask.execute(count,ibArrowLeft,ibArrowForward,ibArrowRight);*/
                }
                /*BackgroundTask backgroundTask=new BackgroundTask(this);
                backgroundTask.execute(count,)
*/
            }
        });



    }
}
