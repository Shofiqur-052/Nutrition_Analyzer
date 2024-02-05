package com.example.nutritionanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class Summery_Actvity extends AppCompatActivity {

    TextView calorie, carbohydrate, protein, fat, water, conditionText;
    float cl=0, ch=0, pr=0, ft=0, wt=0;
    sqLiteHelper sqLiteHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summery_actvity);

        //adding back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calorie = (TextView) findViewById(R.id.calorieId);
        carbohydrate = (TextView) findViewById(R.id.carbohydrateId);
        protein = (TextView) findViewById(R.id.proteinId);
        fat = (TextView) findViewById(R.id.fatId);
        water = (TextView) findViewById(R.id.waterId);
        conditionText = (TextView) findViewById(R.id.conditionId);

        sqLiteHelper = new sqLiteHelper(this);
        Cursor cursor = sqLiteHelper.displayAllData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
            return ;
        }
        else {
            Set<Integer> diff = new HashSet<>();

            while(cursor.moveToNext())
            {
                String st = cursor.getString(2);
                int cnt = cursor.getInt(3);
                diff.add(cursor.getInt(1));
                done(st, cnt);
            }
            int totalDate=diff.size();

            float res1= (float) (cl/totalDate);
            calorie.setText("Calories : "+ (int) res1 +" Kcal per day");

            float res2= (float) ((ch*100.0)/(275*totalDate));
            carbohydrate.setText("Carbohydrate : "+ Math.min(100, (int) res2) +"% in average");

            float res3= (float) ((pr*100.0)/(50*totalDate));
            protein.setText("Protein : "+ Math.min(100, (int) res3) +"% in average");

            float res4= (float) ((ft*100.0)/(60*totalDate));
            fat.setText("Fat : "+ Math.min(100, (int) res4) +"% in average");

            float res5= (float) ((wt*100.0)/(2000*totalDate));
            water.setText("Water : "+ Math.min(100, (int) res5) +"% in average");

            int total = (int)res2+(int)res3+(int)res4+(int)res5;
            total = total / 4;

            if(total >= 90)
            {
                conditionText.setText("GOOD");
                conditionText.setTextColor(Color.parseColor("#17851B"));
            }
            else if(total >= 50)
            {
                conditionText.setText("AVERAGE");
                conditionText.setTextColor(Color.parseColor("#1F80CD"));
            }
            else if(total >= 1) {
                conditionText.setText("POOR");
                conditionText.setTextColor(Color.parseColor("#F64C16"));
            }
        }
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {   //back button

        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void done(String st, int cnt)    // calculating nutrition
    {
        if(st.equals("Red meat"))
        {
            cl+=3*cnt;
            pr+=0.3*cnt;
            ft+=0.2*cnt;
        }
        else if(st.equals("Chicken"))
        {
            cl+=1.65*cnt;
            pr+=0.31*cnt;
            ft+=0.036*cnt;
        }
        else if(st.equals("Greens"))
        {
            cl+=0.2*cnt;
            ch+=0.04*cnt;
            pr+=0.03*cnt;
            ft+=0.01*cnt;
        }
        else if(st.equals("Potato"))
        {
            cl+=140*cnt;
            ch+=33*cnt;
            pr+=4*cnt;
            ft+=0.5*cnt;
        }
        else if(st.equals("Tomato"))
        {
            cl+=30*cnt;
            ch+=6*cnt;
            pr+=2*cnt;
            ft+=0.4*cnt;
        }
        else if(st.equals("Papaya"))
        {
            cl+=0.43*cnt;
            ch+=0.11*cnt;
            pr+=0.005*cnt;
            ft+=0.004*cnt;
        }
        else if(st.equals("Lemon"))
        {
            cl+=17*cnt;
            ch+=5*cnt;
            pr+=0.6*cnt;
            ft+=0.2*cnt;
        }
        else if(st.equals("Egg"))
        {
            cl+=75*cnt;
            ch+=0.6*cnt;
            pr+=13*cnt;
            ft+=5*cnt;
        }
        else if(st.equals("Milk"))
        {
            cl+=0.70*cnt;
            ch+=0.047*cnt;
            pr+=0.032*cnt;
            ft+=0.036*cnt;
        }
        else if(st.equals("Rice"))
        {
            cl+=1.4*cnt;
            ch+=0.28*cnt;
            pr+=0.027*cnt;
            ft+=0.003*cnt;
        }
        else if(st.equals("Bread"))
        {
            cl+=2.6*cnt;
            ch+=0.5*cnt;
            pr+=0.09*cnt;
            ft+=0.025*cnt;
        }
        else if(st.equals("Cake"))
        {
            cl+=3.5*cnt;
            ch+=0.6*cnt;
            pr+=0.06*cnt;
            ft+=0.15*cnt;
        }
        else if(st.equals("Banana"))
        {
            cl+=95*cnt;
            ch+=23*cnt;
            pr+=1.3*cnt;
            ft+=0.3*cnt;
        }
        else if(st.equals("Mango"))
        {
            cl+=170*cnt;
            ch+=38*cnt;
            pr+=2*cnt;
            ft+=0.8*cnt;
        }
        else if(st.equals("Guava"))
        {
            cl+=70*cnt;
            ch+=15*cnt;
            pr+=2.6*cnt;
            ft+=1*cnt;
        }
        else if(st.equals("Pine Apple"))
        {
            cl+=0.52*cnt;
            ch+=0.13*cnt;
            pr+=0.005*cnt;
            ft+=0.001*cnt;
        }
        else if(st.equals("Watermelon"))
        {
            cl+=0.35*cnt;
            ch+=0.07*cnt;
            pr+=0.006*cnt;
            ft+=0.002*cnt;
        }
        else
        {
            wt+=cnt;
        }
    }


}