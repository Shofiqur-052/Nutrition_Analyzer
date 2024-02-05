package com.example.nutritionanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ItemActivity extends AppCompatActivity {

    String[] listName, itemList;
    private Spinner spinner;
    private Button button;
    private TextView textView, unitView;
    private EditText editText;
    sqLiteHelper sqLiteHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //adding back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Database creating
        sqLiteHelper = new sqLiteHelper(this);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        spinner = (Spinner) findViewById(R.id.spinnerID);
        button = (Button) findViewById(R.id.buttonID);
        textView = (TextView) findViewById(R.id.titleID);
        unitView = (TextView) findViewById(R.id.unitId);
        editText = (EditText) findViewById(R.id.editTextID);

        //get clicked item info
        Bundle bundle = getIntent().getExtras();
        int flag = bundle.getInt("item");

        if(flag==1) listName = getResources().getStringArray(R.array.meat_array);
        if(flag==2) listName = getResources().getStringArray(R.array.vegetable_array);
        if(flag==3) listName = getResources().getStringArray(R.array.dairy_array);
        if(flag==4) listName = getResources().getStringArray(R.array.grains_array);
        if(flag==5) listName = getResources().getStringArray(R.array.fruits_array);
        if(flag==6) listName = getResources().getStringArray(R.array.water_array);

        itemList = getResources().getStringArray(R.array.main_item);
        textView.setText(itemList[flag-1]);

        //setting array list to spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.sampleview, R.id.sampletextID, listName);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String unitItem = spinner.getSelectedItem().toString();

                if(unitItem.equals("Red meat")) unitView.setText("gm");
                else if(unitItem.equals("Chicken")) unitView.setText("gm");
                else if(unitItem.equals("Greens")) unitView.setText("gm");
                else if(unitItem.equals("Potato")) unitView.setText("piece");
                else if(unitItem.equals("Tomato")) unitView.setText("piece");
                else if(unitItem.equals("Papaya")) unitView.setText("gm");
                else if(unitItem.equals("Lemon")) unitView.setText("piece");
                else if(unitItem.equals("Egg")) unitView.setText("piece");
                else if(unitItem.equals("Milk")) unitView.setText("ml");
                else if(unitItem.equals("Rice")) unitView.setText("gm");
                else if(unitItem.equals("Bread")) unitView.setText("gm");
                else if(unitItem.equals("Cake")) unitView.setText("gm");
                else if(unitItem.equals("Banana")) unitView.setText("piece");
                else if(unitItem.equals("Mango")) unitView.setText("piece");
                else if(unitItem.equals("Guava")) unitView.setText("piece");
                else if(unitItem.equals("Pine Apple")) unitView.setText("gm");
                else if(unitItem.equals("Watermelon")) unitView.setText("gm");
                else if(unitItem.equals("Water")) unitView.setText("ml");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                unitView.setText("");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = editText.getText().toString();

                if(TextUtils.isEmpty(value)){
                    Toast.makeText(getApplicationContext(), "Please enter some amount", Toast.LENGTH_SHORT).show();
                }
                else{
                    float amount=Float.parseFloat(value);
                    String item = spinner.getSelectedItem().toString();

                    // Get the current date
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                    String currentDate = dateFormat.format(calendar.getTime());
                    int dd=Integer.parseInt(currentDate);

                    long rowId = sqLiteHelper.insertData(dd, item, amount);

                    if(rowId==-1){
                        Toast.makeText(getApplicationContext(), "Unsuccessful :( Try again", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //back button
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}