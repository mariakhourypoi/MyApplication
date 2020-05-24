package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 9/20/2017.
 * this page contains the custom galley that extends
 */

public class CustomAdapter2 extends ArrayAdapter<Schedule> {

    private int resourceLayout;
    private Context mContext;

    public CustomAdapter2(Context context, int resource, List<Schedule> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    /*
    getView() method. This view is called when a listItem needs to be created and populated with the data.In this method first the View is inflated using the LayoutInflator.inflate() method. It is important that you check that if the view you are trying to inflate is new or reused
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null)
            v = LayoutInflater.from(mContext).inflate(resourceLayout, parent, false);


        Schedule p = getItem(position);

        if (p != null) {
            TextView day = (TextView) v.findViewById(R.id.textViewDay);
            day.setText(p.getDay());

            TextView timeStart =(TextView) v.findViewById(R.id.textViewTimeStart);
            timeStart.setText(p.getTimeScheduleStart().getHour()+":"+p.getTimeScheduleStart().getMinutes());

            TextView timeEnd =(TextView) v.findViewById(R.id.textViewTimeEnd);
            timeEnd.setText(p.getTimeScheduleStart().getHour()+":"+p.getTimeScheduleEnd().getMinutes());

        }
        return v;
    }




}
