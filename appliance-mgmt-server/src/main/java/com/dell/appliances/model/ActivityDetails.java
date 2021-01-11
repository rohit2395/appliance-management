package com.dell.appliances.model;

import javax.persistence.*;
import java.util.Date;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Tuesday 12/8/2020
 *
 */
@Entity
@Table(name="activity_details")
public class ActivityDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity",nullable=false)
    private String activity;

    @Column(name = "date",nullable = false)
    private Date date;

    public ActivityDetails() {
    }

    public ActivityDetails(String activity, Date date) {
        this.activity = activity;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
