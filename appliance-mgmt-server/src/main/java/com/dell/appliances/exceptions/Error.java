package com.dell.appliances.exceptions;

import org.springframework.http.HttpStatus;

public enum Error {

    GENERAL_ERROR("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_FAILED("Failed to send the email alert!", HttpStatus.INTERNAL_SERVER_ERROR),


    FAILED_TO_ADD_NEW_APPLIANCE("Failed to add new appliance!", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_UPDATE_APPLIANCE("Failed to update appliance!", HttpStatus.INTERNAL_SERVER_ERROR),
    APPLIANCE_NOT_FOUND_ID("Appliance not found with ID:{0}!", HttpStatus.NOT_FOUND),
    APPLIANCE_NOT_FOUND_NAME("Appliance not found with Name:{0}!", HttpStatus.NOT_FOUND),
    APPLIANCES_NOT_FOUND("Appliances not found!", HttpStatus.NOT_FOUND),

    FAILED_TO_RESERVE_APPLIANCE("Failed to reserve appliance with Name:{0}!", HttpStatus.INTERNAL_SERVER_ERROR),
    ;


    private String msg;
    private HttpStatus status;

    Error(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

}
