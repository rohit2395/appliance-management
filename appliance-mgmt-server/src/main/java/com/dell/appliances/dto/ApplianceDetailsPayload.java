package com.dell.appliances.dto;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
public class ApplianceDetailsPayload {

    private Long id;
    private String applianceName;
    private String applianceModel;
    private String location;
    private String generation;
    private Boolean canBeShared;
    private String appliancePossessionStatus;
    private String applianceState;
    private String uomName;
    private String purpose;
    private String assignee;
    private String assigneeEmail;
    private String configuration;
    private String startDate;
    private String endDate;

    public ApplianceDetailsPayload(){}
    public ApplianceDetailsPayload(Long id,String applianceName, String applianceModel, Boolean canBeShared,String uomName) {
        this.id = id;
        this.applianceName = applianceName;
        this.applianceModel = applianceModel;
        this.canBeShared = canBeShared;
        this.uomName = uomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public String getApplianceModel() {
        return applianceModel;
    }

    public void setApplianceModel(String applianceModel) {
        this.applianceModel = applianceModel;
    }

    public Boolean getCanBeShared() {
        return canBeShared;
    }

    public void setCanBeShared(Boolean canBeShared) {
        this.canBeShared = canBeShared;
    }

    public String getAppliancePossessionStatus() {
        return appliancePossessionStatus;
    }

    public void setAppliancePossessionStatus(String appliancePossessionStatus) {
        this.appliancePossessionStatus = appliancePossessionStatus;
    }

    public String getApplianceState() {
        return applianceState;
    }

    public void setApplianceState(String applianceState) {
        this.applianceState = applianceState;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }
}
