package com.dell.appliances.dto;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Tuesday 12/8/2020
 *
 */
public class ActivityDetailsPayload {

    private String activity;
    private String date;

    public ActivityDetailsPayload() {
    }

    public ActivityDetailsPayload(String activity, String date) {
        this.activity = activity;
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
