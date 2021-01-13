package com.dell.appliances.dto;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Tuesday 1/12/2021
 *
 */
public class CountByLocation {
    String locName;
    int count;

    public CountByLocation(){}

    public CountByLocation(String locName, int count) {
        this.locName = locName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }
}
