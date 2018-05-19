package org.example.vtraffic.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.example.vtraffic.activity.MapsActivity;
import org.example.vtraffic.activity.PersistenceData;
import org.example.vtraffic.adapter.LoginDataBaseAdapter;
import org.example.vtraffic.R;
import org.example.vtraffic.activity.RegisterActivity;
import org.example.vtraffic.activity.UserLogActivity;

//import android.support.v4.app.Fragment;


/**
 * Created by Admin on 3/12/2018.
 */

public class LoginFragment extends Fragment{

    private LoginDataBaseAdapter loginDataBaseAdapter;
    private EditText etEmail;
    private EditText etPassword;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static String userEmail;




    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,
                container, false);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);

        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity()); // data base adapter for login
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        View loginButton = rootView.findViewById (R.id.bLogin); //for login button click
        loginButton.setOnClickListener(new View.OnClickListener () {
            public void onClick (View view) {
                login();
            }
        });

        View signupButton = rootView.findViewById (R.id.bSignUp); // for signup button click
        signupButton.setOnClickListener(new View.OnClickListener () {
            public void onClick (View view) {
                Intent signupIntent = new Intent (getActivity(),
                        RegisterActivity.class );
                getActivity().startActivity (signupIntent);
            }
        });





        return rootView;

    }



    private void login() { //validating email and password
        if (etEmail.getText().toString().trim().length() == 0) {
            etEmail.setError(getString(R.string.required));
            return;
        }
        if (!etEmail.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            etEmail.setError("Invalid Email Address");
        }

        if (etPassword.getText().toString().trim().length() == 0) {
            etPassword.setError(getString(R.string.required));
            return;
        }

        loginUser();
    }

    private void loginUser() { // checking a successful login
        String username = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(username);

        if (storedPassword.equals(password)) {
            PersistenceData.setUseremail(username);
            Toast ToastMessage = Toast.makeText(getActivity(), "Login success", Toast.LENGTH_SHORT);
            View toastView = ToastMessage.getView();
            toastView.setBackgroundResource(R.drawable.toast_background_color);
            ToastMessage.show();
            startActivity(new Intent(getActivity(), MapsActivity.class));

            //User has successfully logged in, save this information
            // We need an Editor object to make preference changes.
            SharedPreferences settings = this.getActivity().getSharedPreferences(LoginFragment.PREFS_NAME, 0); // 0 - for private mode
            SharedPreferences.Editor editor = settings.edit();

            //Setting "hasLoggedIn" to true
            editor.putBoolean("hasLoggedIn", true);

            // Commit the edits!
            editor.commit();
            getActivity().finish();
        }
        else{
            Toast ToastMessage = Toast.makeText(getActivity(), " Incorrect Username or Password", Toast.LENGTH_SHORT);
            View toastView = ToastMessage.getView();
            toastView.setBackgroundResource(R.drawable.toast_background_color);
            ToastMessage.show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

}
