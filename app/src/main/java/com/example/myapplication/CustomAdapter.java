package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 9/20/2017.
 * this page contains the custom galley that extends
 */

public class CustomAdapter extends ArrayAdapter<Notification> {

    private int resourceLayout;
    private Context mContext;

    public CustomAdapter(Context context, int resource, List<Notification> items) {
        super(context, resource,items );
        this.resourceLayout = resource;
        this.mContext = context;
    }

    /*
    getView() method. This view is called when a listItem needs to be created and populated with the data.In this method first the View is inflated using the LayoutInflator.inflate() method. It is important that you check that if the view you are trying to inflate is new or reused
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null)
            v = LayoutInflater.from(mContext).inflate(resourceLayout,parent,false);


        Notification p = getItem(position);

        if (p != null) {
            TextView notificationCount = (TextView) v.findViewById(R.id.noificationTextView);
            notificationCount.setText("0");

            TextView childName = v.findViewById(R.id.textViewChildName1);
            childName.setText(p.getName());
            TextView birthday = v.findViewById(R.id.textViewBir);
            birthday.setText(p.getBirthday());
            TextView homeAdress = v.findViewById(R.id.textViewHomeAdress);
            homeAdress.setText(p.getHomeAdress());
            TextView schoolAdress = v.findViewById(R.id.textViewSchoolAdress);
            schoolAdress.setText(p.getSchoolAdress());

        }

        return v;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }

}
