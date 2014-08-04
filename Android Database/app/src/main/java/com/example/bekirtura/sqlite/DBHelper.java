package com.example.bekirtura.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bekirtura on 17/07/14.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Sensors";
    private static final String TABLE_SENSORS="sensors_name";

    public Cursor cursor;





    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sq1="CREATE TABLE "+TABLE_SENSORS+"(id INTEGER PRIMARY KEY AUTOINCREMENT,Rate REAL,DEGER_X TEXT,DEGER_Y TEXT, DEGER_Z TEXT"+")";
        Log.d("DBHelper", "SQL : " + sq1);

        sqLiteDatabase.execSQL(sq1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSORS);
        onCreate(sqLiteDatabase);

    }

}
