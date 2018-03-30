package org.example.vtraffic;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by DELL on 3/26/2018.
 */

public class BackgroundTask extends AsyncTask<Integer,Void,Void> {
    Context ctx;
    View.OnClickListener ibArrowLeft;

    BackgroundTask(View.OnClickListener ibArrowLeft){
        this.ibArrowLeft=ibArrowLeft;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Integer... params) {
        String count_url="";
        int count=params[0];
        if(count==1){





        }




        return null;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


}