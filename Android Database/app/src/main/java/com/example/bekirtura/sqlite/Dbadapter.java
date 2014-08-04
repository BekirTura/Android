package com.example.bekirtura.sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by bekirtura on 17/07/14.
 */
public class Dbadapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Sensors> sensorsList;
    public Dbadapter(Activity activity,List<Sensors> sensorses){
        layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sensorsList=sensorses;
    }
    @Override
    public int getCount() {
        return sensorsList.size();
    }

    @Override
    public Object getItem(int i) {
        return sensorsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class Holder {
        TextView textView;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view= convertView;
        Holder holder = null;
        if(convertView==null){
            view= layoutInflater.inflate(R.layout.adabter_data, null);
            holder = new Holder();
            holder.textView=(TextView) view.findViewById(R.id.row_textview);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

            Sensors sensors= sensorsList.get(i);
            holder.textView.setText(sensors.getCOLUMNS_X().toString()+"|"+sensors.getCOLUMNS_Y().toString()+"|"+sensors.getCOLUMNS_Z().toString());
         return view;


    }
}
