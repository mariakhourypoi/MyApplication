package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sarah on 9/20/2017.
 * this page contains the custom galley that extends
 */

public class CustomAdapter3 extends ArrayAdapter<Emergancy> {

    private int resourceLayout;
    private Context mContext;

    public CustomAdapter3(Context context, int resource, List<Emergancy> items) {
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


        Emergancy p = getItem(position);

        if (p != null) {
            TextView name = (TextView) v.findViewById(R.id.textViewEmergancyName);
            name.setText(p.getEmergancyName());

        }

        return v;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }

}
