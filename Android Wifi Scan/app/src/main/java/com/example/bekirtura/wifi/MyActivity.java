package com.example.bekirtura.wifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;


public class MyActivity extends ActionBarActivity {
    public WifiManager wifiManager;
    private Button scanButton;
    private ToggleButton wifitoggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        scanButton=(Button)findViewById(R.id.scanButton);
        wifitoggleButton=(ToggleButton)findViewById(R.id.wifiToggleButton);
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled()){
            scanButton.setEnabled(true);
            wifitoggleButton.setChecked(true);
        }else{
            scanButton.setEnabled(false);
            wifitoggleButton.setChecked(false);
        }
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!scanWifi()){
                    Toast.makeText(getBaseContext(),"wifi bulunamadi",Toast.LENGTH_SHORT).show();
                }
            }
        });
        wifitoggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!toggleWifi()){
                        Toast.makeText(getBaseContext(), "Oh snap! Can't turn wifi on.",
                                Toast.LENGTH_SHORT).show();
                        wifitoggleButton.setChecked(false);
                    }
                }else{
                    if(!toggleWifi()){
                        Toast.makeText(getBaseContext(), "Oh snap! Can't turn wifi off.",
                                Toast.LENGTH_SHORT).show();
                        wifitoggleButton.setChecked(true);
                    }

                }
            }
        });
    }
    private boolean toggleWifi(){
        if(wifiManager.isWifiEnabled()){
            if(wifiManager.setWifiEnabled(false)){
                wifitoggleButton.setChecked(false);
                scanButton.setEnabled(false);
            }else {
                return false;
            }
        }else{
            if(wifiManager.setWifiEnabled(true)){
                wifitoggleButton.setChecked(true);
                scanButton.setEnabled(true);
            }else{
                return false;
            }
        }
        return true;
    }
    private boolean scanWifi(){
        final List<ScanResult>scanResultList;
        WifiAdapter wifiAdapter;
        ListView listView=(ListView)findViewById(R.id.list);
        if(wifiManager.startScan()){
            scanResultList=wifiManager.getScanResults();
            wifiAdapter=new WifiAdapter(this,scanResultList);
            listView.setAdapter(wifiAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    boolean connectSuccess;
                   // connectSuccess = addNetworkAndActivate(scanResultList.get(i));

                }
            });
            return true;
        }else {
            return false;
        }
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
}
