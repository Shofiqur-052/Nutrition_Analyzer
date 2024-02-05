package com.example.nutritionanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    private TextView textView;
    private Typeface typeface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_screen);

        progressBar = (ProgressBar) findViewById(R.id.progressBarID);
        textView = (TextView) findViewById(R.id.welcomeID);

        typeface = Typeface.createFromAsset(getAssets(), "font/RichtmanPlain.ttf");
        textView.setTypeface(typeface);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doProgress();
                startNext();
            }
        });
        thread.start();
    }
    public void doProgress(){

        for(progress=0; progress<=110; progress+=1){
            try {
                Thread.sleep(30);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void startNext(){

        Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}