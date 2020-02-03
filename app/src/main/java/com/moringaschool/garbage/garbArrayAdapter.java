package com.moringaschool.garbage;

import android.content.Context;
import android.widget.ArrayAdapter;

public class garbArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mTrucks;
    private String[] mZones;

    public garbArrayAdapter (Context mContext, int resource, String[] mTrucks, String[] mZones){
        super(mContext, resource);
        this.mContext = mContext;
        this.mZones = mZones;
        this.mTrucks = mTrucks;
    }

    @Override
    public Object getItem(int position){
        String trucks = mTrucks[position];
        String zones = mZones[position];
        return String.format("%s \n Operates: %s", trucks, zones);
    }

    @Override
    public int getCount(){
        return mTrucks.length;
    }
}