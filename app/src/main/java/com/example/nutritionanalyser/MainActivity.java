package com.example.nutritionanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mButton, vButton, dButton, gButton, fButton, wButton;
    sqLiteHelper sqLiteHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Left icon on action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icons_nutrition_resized);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Database creating
        sqLiteHelper = new sqLiteHelper(this);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        //clearing database :
        //sqLiteHelper.onUpgrade(sqLiteDatabase, 1, 1);

        mButton = (ImageButton) findViewById(R.id.meatID);
        vButton = (ImageButton) findViewById(R.id.vegetableID);
        dButton = (ImageButton) findViewById(R.id.dairyID);
        gButton = (ImageButton) findViewById(R.id.grainsID);
        fButton = (ImageButton) findViewById(R.id.fruitsID);
        wButton = (ImageButton) findViewById(R.id.waterID);


        mButton.setOnClickListener(this);
        vButton.setOnClickListener(this);
        dButton.setOnClickListener(this);
        gButton.setOnClickListener(this);
        fButton.setOnClickListener(this);
        wButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int flag = 0;

        if(v.getId()==R.id.meatID) flag=1;
        if(v.getId()==R.id.vegetableID) flag=2;
        if(v.getId()==R.id.dairyID) flag=3;
        if(v.getId()==R.id.grainsID) flag=4;
        if(v.getId()==R.id.fruitsID) flag=5;
        if(v.getId()==R.id.waterID) flag=6;

        //passing button clicked info
        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
        intent.putExtra("item", flag);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.statusId){

            Intent intent = new Intent(MainActivity.this, Summery_Actvity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.clearId){

            sqLiteHelper = new sqLiteHelper(this);
            SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
            sqLiteHelper.onUpgrade(sqLiteDatabase, 1, 1);

        }
        else this.finish();

        return super.onOptionsItemSelected(item);
    }
}