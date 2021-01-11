package com.dell.appliances.model.enumerations;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
public enum AppliancePossessionStatus {

    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    private final String appliancePossessionStatus;

    AppliancePossessionStatus(String appliancePossessionStatus) {
        this.appliancePossessionStatus = appliancePossessionStatus;
    }

    public String getVal() {
        return this.appliancePossessionStatus;
    }
}
