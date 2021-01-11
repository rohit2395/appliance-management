package com.dell.appliances.dto;

import java.util.Date;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
public class ApplianceReservationPayload {

    private String assigneeName;
    private String assigneeEmail;
    private String purpose;
    private String applianceName;
    private String startDate;
    private String endDate;
    private String acmIp;
    private String acmPassword;

    public ApplianceReservationPayload() {
    }

    public ApplianceReservationPayload(String assigneeName,String assigneeEmail, String purpose, String applianceName) {
        this.assigneeName = assigneeName;
        this.assigneeEmail = assigneeEmail;
        this.purpose = purpose;
        this.applianceName = applianceName;
    }

    public ApplianceReservationPayload(String assigneeName,String assigneeEmail, String purpose, String applianceName, String dateTime,String startDate,String endDate) {
        this.assigneeName = assigneeName;
        this.assigneeEmail = assigneeEmail;
        this.purpose = purpose;
        this.applianceName = applianceName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
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

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public String getAcmPassword() {
        return acmPassword;
    }

    public void setAcmPassword(String acmPassword) {
        this.acmPassword = acmPassword;
    }

    public String getAcmIp() {
        return acmIp;
    }

    public void setAcmIp(String acmIp) {
        this.acmIp = acmIp;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }
}
