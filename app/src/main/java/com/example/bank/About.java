package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class About extends AppCompatActivity {

    ProgressBar pb;
    TextView showEr;
    int count = 0;
    Timer timer;
    TimerTask timerTask;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("About us");

        pb = findViewById(R.id.progressBar);
        showEr = findViewById(R.id.showEr);

        timer = new Timer();

        showEr.setVisibility(View.INVISIBLE);

                 timerTask = new TimerTask() {
                    @Override
                    public void run() {



                        count++;

                        pb.setProgress(count);

                        if(count==100){

                            pb.setVisibility(View.INVISIBLE);
                            System.out.println(pb.getVisibility());
                          try{

                              if(pb.getVisibility()==View.INVISIBLE) {
                                  showEr.setVisibility(View.VISIBLE);
                              }
                          }
                          catch (Exception e){

                              System.out.println(e);

                          }

                            timer.cancel();


                        }


                    }
                };

                timer.schedule(timerTask,0,100);


        if(pb.getVisibility()==View.INVISIBLE) {
            showEr.setVisibility(View.VISIBLE);
        }

        System.out.println(pb.getVisibility());



        System.out.println("hello  running");


    }

    public void openGmail(View view){

        for (int i = 0; i < 10; i++) {
            System.out.println("hello");
        }

        Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.camera");

        try {
            if (intent != null)
                startActivity(intent);
        }
        catch (Exception e){

            System.out.println("Exception: " + e);
        }

    }
}