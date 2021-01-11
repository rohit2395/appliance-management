package com.dell.appliances.model;

import com.dell.appliances.common.Constants;
import com.dell.appliances.model.enumerations.AppliancePossessionStatus;
import com.dell.appliances.model.enumerations.Purpose;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
@Entity
@Table(name="appliance_possession_details")
public class AppliancePossession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="assignee_name",nullable=false)
    private String assigneeName;

    @Column(name="assignee_email",nullable=false)
    private String assigneeEmail;

    @Column(name="purpose")
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name="reservation_date",nullable=false)
    private String startDate;

    @Column(name="reservation_end_date",nullable=false)
    private String endDate;

    @OneToMany(mappedBy = "appliancePossession",cascade=CascadeType.ALL)
    private List<ApplianceDetails> applianceDetails;

    public AppliancePossession() {
    }


    public AppliancePossession(String assigneeName,String assigneeEmail, Purpose purpose, String startDate, String endDate) {
        this.assigneeName = assigneeName;
        this.assigneeEmail = assigneeEmail;
        this.purpose = purpose;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<ApplianceDetails> getApplianceDetails() {
        return applianceDetails;
    }

    public void setApplianceDetails(List<ApplianceDetails> applianceDetails) {
        this.applianceDetails = applianceDetails;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }

    @Override
    public String toString() {
        return "AppliancePossession{" +
                ", assigneeName='" + assigneeName + '\'' +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", purpose=" + purpose +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", applianceDetails=" + applianceDetails +
                '}';
    }

}
