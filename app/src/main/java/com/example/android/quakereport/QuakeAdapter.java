package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.android.quakereport.R.id.location;

/**
 * Created by ivan on 23.9.2017 г..
 */

public class QuakeAdapter extends ArrayAdapter<Earthquake>{



    public QuakeAdapter ( Activity context,  List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Earthquake currEartquake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.mag);

        magnitudeView.setText(currEartquake.getmMag());
        //location
        String loc = currEartquake.getmLocation();
        int st = loc.indexOf(",");
        int of = loc.indexOf("of");

        String country ="";
        String place="";
        if(st!=-1){
            place = loc.substring(0,st);
            country=loc.substring(st+2);
        }

        else {
            place="Near the";
            country=loc.substring(st+1);
        }


        TextView locationView = (TextView)listItemView.findViewById(location);
        locationView.setText(place);

        TextView countrView = (TextView)listItemView.findViewById(R.id.country);
        countrView.setText(country);
        //date
        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        Date tm = new Date(currEartquake.getmDate());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, YYYY");
        String toDispllay = dateFormat.format(tm);
        dateView.setText(toDispllay);

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH: mm");
        String hourToDispllay = hourFormat.format(tm);
        timeView.setText(hourToDispllay);

        return listItemView;
    }

}
