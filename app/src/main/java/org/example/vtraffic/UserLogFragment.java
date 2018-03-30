package org.example.vtraffic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 3/12/2018.
 */

public class UserLogFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user,
                container, false);

        View driverButton = rootView.findViewById (R.id.bDriver);
        driverButton.setOnClickListener(new View.OnClickListener () {
            public void onClick (View view) {
                Intent driverIntent = new Intent (getActivity() ,
                        MapsActivity.class );
                getActivity().startActivity (driverIntent);
            }
        });

        View pedestrianButton = rootView.findViewById (R.id.bPedestrian);
        pedestrianButton.setOnClickListener(new View.OnClickListener () {
            public void onClick (View view) {
                Intent pedestrianIntent = new Intent (getActivity() ,
                        PedMapsActivity.class );
                getActivity().startActivity (pedestrianIntent);
            }
        });


        return rootView;
    }

}
