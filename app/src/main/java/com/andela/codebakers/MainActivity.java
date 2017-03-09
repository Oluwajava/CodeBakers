package com.andela.codebakers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread splashTimer = new Thread(){
            public void run(){

                try{
                    sleep(1200);
                }catch (Exception exception) {
                    exception.printStackTrace();
                }finally {
                    Intent selectionActivityIntent = new Intent(MainActivity.this, SelectionActivity.class);
                    startActivity(selectionActivityIntent);
                }
            }
        };

        splashTimer.start();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
