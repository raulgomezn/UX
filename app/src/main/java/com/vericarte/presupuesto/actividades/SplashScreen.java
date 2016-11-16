package com.vericarte.presupuesto.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

/**
 * Created by raul on 16/11/16.
 */

public class SplashScreen extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        FacebookSdk.sdkInitialize(getApplicationContext());


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);

                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    if(accessToken != null){
                        Intent intent = new Intent(SplashScreen.this, SampleActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
