package com.example.bekirtura.sqlite;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ActionBarActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private SensorEventListener listener;
    DBHelper dbHelper;
    File dbfile = new File("/sdcard/Your_db_File.db" );

    private static final String TABLE_SENSORS="sensors_name";

    private static final String DATABASE_NAME="Sensors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button button=(Button)findViewById(R.id.button);
        final ListView customListView=(ListView)findViewById(R.id.listView);
        dbHelper= new DBHelper(getApplicationContext());
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                insertSensors(new Sensors(1,37.0,String.valueOf(sensorEvent.values[0]),String.valueOf(sensorEvent.values[1]),String.valueOf(sensorEvent.values[2])));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        //SharedPreferences sharedPreferences= getSharedPreferences("SQL",0);
        //boolean firstTime=sharedPreferences.getBoolean("firstTime",true);
        //Uygulama çalıştığında yeni bir DBHelper yaratılarak veritabanı bağlantısı oluşturuluyor.
        // Uygulamanın ilk defa çalışması ise (Google Play’den ilk yükleme)
        // SharedPreferences üzerinden bir değişkenle kontrol ediliyor.
        // Burada firstTime adında bir değerin cihazda daha önce bulunup bulunmadığı sorgulanıyor.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sensorManager.unregisterListener(listener);
                List<Sensors>sensorses = getAllSensors();
                Dbadapter dbadapter = new Dbadapter(MyActivity.this,sensorses);
                customListView.setAdapter(dbadapter);
                Log.d("durdu","durdu");
            }
        });

}

    public void insertSensors(Sensors sensors){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Rate",sensors.getRate());
        values.put("DEGER_X",sensors.getCOLUMNS_X());
        values.put("DEGER_Y",sensors.getCOLUMNS_Y());
        values.put("DEGER_Z",sensors.getCOLUMNS_Z());
        sqLiteDatabase.insert(TABLE_SENSORS,null,values);
        sqLiteDatabase.close();
    }
    public List<Sensors> getAllSensors(){
        List<Sensors> sensorses= new ArrayList<Sensors>();
        SQLiteDatabase  db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
        db= dbHelper.getWritableDatabase();
        Cursor cursor;
        cursor= db.query(TABLE_SENSORS,new String[]{"id","Rate","DEGER_X","DEGER_Y","DEGER_Z"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Sensors sensors= new Sensors();
            sensors.setId(cursor.getInt(0));
            sensors.setRate(cursor.getDouble(1));
            sensors.setCOLUMNS_X(cursor.getString(2));
            sensors.setCOLUMNS_Y(cursor.getString(3));
            sensors.setCOLUMNS_Z(cursor.getString(4));
            sensorses.add(sensors);
        }
        return sensorses;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
