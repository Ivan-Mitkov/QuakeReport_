package com.example.android.quakereport;

/**
 * Created by ivan on 23.9.2017 г..
 */

public class Eartquake {
    private String mMag;
    private String mLocation;
    private String mDate;

    public Eartquake(String mMag, String mLocation, String mDate) {
        this.mMag = mMag;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public String getmMag() {
        return mMag;
    }

    public void setmMag(String mMag) {
        this.mMag = mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
