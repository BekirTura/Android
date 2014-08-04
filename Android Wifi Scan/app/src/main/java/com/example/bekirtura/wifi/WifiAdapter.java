package com.example.bekirtura.wifi;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bekirtura on 21/07/14.
 */
public class WifiAdapter extends BaseAdapter {
    Activity activity;
    List<ScanResult>data;
    LayoutInflater inflater;
    public WifiAdapter(Activity a, List<ScanResult> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder{
        TextView ssid,bssid,capabilities,freq,level,timestamp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ScanResult scanResult=data.get(i);
        ViewHolder viewHolder;
        if(view==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.wifi_list_item, null);
            viewHolder.ssid = (TextView) view.findViewById(R.id.ssidView);
            viewHolder.bssid = (TextView) view.findViewById(R.id.bssidView);
            viewHolder.capabilities = (TextView) view.findViewById(R.id.capabilitiesView);
            viewHolder.freq = (TextView) view.findViewById(R.id.frequencyView);
            viewHolder.level = (TextView) view.findViewById(R.id.levelView);
            viewHolder.timestamp = (TextView) view.findViewById(R.id.timestampView);
            view.setTag(viewHolder);
        }else {
           viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ssid.setText(scanResult.SSID);
        viewHolder.bssid.setText(scanResult.BSSID);
        viewHolder.capabilities.setText(scanResult.capabilities);
        viewHolder.freq.setText(String.valueOf(scanResult.frequency));
        viewHolder.level.setText(String.valueOf(scanResult.level));
       // viewHolder.timestamp.setText(String.valueOf(scanResult.timestamp));
        return view;
    }
}
