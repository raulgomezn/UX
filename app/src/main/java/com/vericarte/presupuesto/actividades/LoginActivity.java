package com.vericarte.presupuesto.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by rgomezn on 01/11/2016.
 */

public class LoginActivity extends Activity {

    private LoginButton loginButton;

    private Button loginButtonNormal;
    private EditText email;
    private EditText password;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.login);

        loginButtonNormal = (Button) findViewById(R.id.buttonLogin);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextContrasena);

        loginButtonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().length() > 5 &&
                        password.getText().length() > 5)
                {
                    Intent myIntent = new Intent(LoginActivity.this, SampleActivity.class);
                    startActivity(myIntent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Error =(",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Login", "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken());
                Intent myIntent = new Intent(LoginActivity.this, SampleActivity.class);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onCancel() {
                Log.d("Login", "Login attempt canceled.");
                Toast.makeText(getApplicationContext(), "Error Login attempt canceled =(",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("Login", "Login attempt failed.");
                Toast.makeText(getApplicationContext(), "Error con Facebook =(",
                        Toast.LENGTH_LONG).show();
                 //Log.d("Login", "Error: " + e.getMessage().toString());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
