package com.example.android.quakereport;

/**
 * Created by ivan on 23.9.2017 г..
 */

public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mDate;

    public Earthquake(double mMag, String mLocation, long mDate) {

        this.mMag = mMag;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public double getmMag() {
        return mMag;
    }

    public void setmMag(double mMag) {
        this.mMag = mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public long getmDate() {
       return mDate;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }
}
