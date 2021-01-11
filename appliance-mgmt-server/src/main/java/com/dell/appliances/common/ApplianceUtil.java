package com.dell.appliances.common;

import com.dell.appliances.dto.APIResponse;
import com.dell.appliances.exceptions.ApplianceException;
import org.springframework.http.HttpStatus;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
public class ApplianceUtil {

    public static APIResponse buildApiResponse(String message , HttpStatus status) {
        return new APIResponse(status.value(),message);
    }
    public static APIResponse buildApiResponse(ApplianceException e) {
        return new APIResponse(e.getErrorCode().value(), e.getErrorMessage());
    }
}
