package com.example.android.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 26.9.2017 г..
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if(mUrl==null){
            return null;
        }
        ArrayList<Earthquake> earthquakes= QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
