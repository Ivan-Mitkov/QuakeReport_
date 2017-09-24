package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;
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
        //magitude

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.mag);
        double mag = currEartquake.getmMag();
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(mag);
        magnitudeView.setText(magnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currEartquake.getmMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
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
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
