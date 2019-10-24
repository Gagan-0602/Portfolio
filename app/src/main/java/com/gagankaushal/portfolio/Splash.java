package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        imageView = (ImageView) findViewById(R.id.logo);
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    LocalSession localSession = new LocalSession(Splash.this);
                    if (localSession.isLogin()) {
                        Intent intent = new Intent(Splash.this, Home.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Splash.this, Login.class);
                        startActivity(intent);
                    }
                    finish();


                    finish();
                } catch (Exception e) {

                }

            }
        };
        thread.start();

    }
}
