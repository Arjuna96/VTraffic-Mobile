package org.example.vtraffic.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.example.vtraffic.R;

/**
 * Created by DELL on 5/7/2018.
 */

public class CustomSwipeAdapter extends PagerAdapter {

    private Button bState;
     String roadId;
    private int[] image_resources={R.drawable.sone,R.drawable.ssec,R.drawable.sthrd,R.drawable.sfour};


    private Context context;
    private LayoutInflater layoutInflater;


    public CustomSwipeAdapter(Context context){
        this.context=context;



    }






    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }



    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        //tvState=(TextView) findViewById(R.id.tvState);



        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imgView=(ImageView) item_view.findViewById(R.id.image_view);
        TextView txtView=(TextView) item_view.findViewById(R.id.image_no);
        imgView.setImageResource(image_resources[position]);
        txtView.setText("state no : " + position);
        txtView.setVisibility(View.GONE);

        roadId=txtView.getText().toString();
         bState=(Button) item_view.findViewById(R.id.bState);
        bState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You select : " + roadId, Toast.LENGTH_LONG).show();
               // context.createRequest(position);

                Intent intent = new Intent(context, ConfirmReqActivity.class);
                intent.putExtra("request_id", position);
                context.startActivity(intent);




            }
        });







        container.addView(item_view);



        return item_view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {



        container.removeView((LinearLayout) object);








    }








}
