package com.dell.appliances.exceptions;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
public class ApplianceException extends Exception {
    private HttpStatus errorCode;
    private String errorMessage;
    private Exception e;

    public ApplianceException(Error error, Exception e) {
        super(error.getMsg());
        this.errorCode = error.getStatus();
        this.errorMessage = error.getMsg();
        this.e = e;
    }

    public ApplianceException(Error error, String[] params, Exception e) {
        super(error.getMsg());
        this.errorCode = error.getStatus();
        MessageFormat mf = new MessageFormat(error.getMsg());
        this.errorMessage = mf.format(params);
        this.e = e;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
