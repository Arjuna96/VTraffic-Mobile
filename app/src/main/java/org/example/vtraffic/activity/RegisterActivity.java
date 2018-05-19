package org.example.vtraffic.activity;

/**
 * Created by Admin on 3/12/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.example.vtraffic.adapter.LoginDataBaseAdapter;
import org.example.vtraffic.R;

public class RegisterActivity extends AppCompatActivity {


    private LoginDataBaseAdapter loginDataBaseAdapter;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etReEnterPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //for fields to fill by user
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etReEnterPassword = (EditText) findViewById(R.id.etReEnterPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);


        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });




    }

    private void register() { //validating the fields

        if (etFirstName.getText().toString().trim().length() == 0) {
            etFirstName.setError(getString(R.string.required));
            return;
        }

        if (etLastName.getText().toString().trim().length() == 0) {
            etLastName.setError(getString(R.string.required));
            return;
        }

        if (etEmail.getText().toString().trim().length() == 0) {
            etEmail.setError(getString(R.string.required));
            return;
        }
        if (!etEmail.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            etEmail.setError("Invalid Email Address");
            return;
        }
        if (etPassword.getText().toString().trim().length() == 0) {
            etPassword.setError(getString(R.string.required));
            return;
        }
        if (etReEnterPassword.getText().toString().trim().length() == 0) {
            etReEnterPassword.setError(getString(R.string.required));
            return;
        }
        if (!etPassword.getText().toString().trim().equals(etReEnterPassword.getText().toString().trim())) {
            etPassword.setError(getString(R.string.mismatch));
            etReEnterPassword.setError(getString(R.string.mismatch));
            return;
        }

        registerUser();
    }


    private void registerUser(){//creating account and store data
        String username = etEmail.getText().toString().trim();

        //  settings.setUserName(uname);
        String password = etPassword.getText().toString().trim();
        // settings.setPassword(pass);

        loginDataBaseAdapter.insertEntry(username, password);
        Toast ToastMessage = Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT);
        View toastView = ToastMessage.getView();
        toastView.setBackgroundResource(R.drawable.toast_background_color);
        ToastMessage.show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

}
