package com.example.nutritionanalyser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class sqLiteHelper extends SQLiteOpenHelper {

    private static final String databaseName = "Nutrition.db";
    private static final String tableName = "Nutrition_details";
    private static final String id = "_id";
    private static final String date = "Date";
    private static final String item = "Item";
    private static final String amount = "Amount";
    private static final String createTable = "CREATE TABLE "+tableName+"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+date+" VARCHAR(10), "+item+" VARCHAR(20), "+amount+" INTEGER);";
    private static final int versionNumber = 1;
    private static final String dropTable = "DROP TABLE IF EXISTS "+tableName;
    private static final String selectAll = "SELECT * FROM "+tableName;
    private Context context;


    public sqLiteHelper(@Nullable Context context) {
        super(context, databaseName, null, versionNumber);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            //Toast.makeText(context, "Table is created", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(createTable);
        }
        catch(Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            Toast.makeText(context, "Data cleared", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(dropTable);
            onCreate(sqLiteDatabase);
        }
        catch(Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(int currentDate, String itemGot, float amountGot)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(date, currentDate);
        contentValues.put(item, itemGot);
        contentValues.put(amount, amountGot);

        return sqLiteDatabase.insert(tableName, null, contentValues);
    }

    public Cursor displayAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectAll,null);
        return cursor;
    }
}
