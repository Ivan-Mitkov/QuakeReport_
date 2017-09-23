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

        TextView locationView = (TextView)listItemView.findViewById(R.id.location);
        locationView.setText(currEartquake.getmLocation());

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
