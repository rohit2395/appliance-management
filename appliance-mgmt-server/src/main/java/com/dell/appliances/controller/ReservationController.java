package com.dell.appliances.controller;

import com.dell.appliances.common.ApplianceUtil;
import com.dell.appliances.common.UIMessages;
import com.dell.appliances.dto.APIResponse;
import com.dell.appliances.dto.ApplianceDetailsPayload;
import com.dell.appliances.dto.ApplianceReservationPayload;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.service.interfaces.IApplianceReservationService;
import com.dell.appliances.service.interfaces.IApplianceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
@RestController
@RequestMapping(APIConstants.RESERVATION)
public class ReservationController{

    public static final Logger LOG = LogManager.getLogger(ReservationController.class);

    @Autowired
    private IApplianceReservationService applianceReservationService;

    @PostMapping(APIConstants.RESERVE_APPLIANCE)
    public ResponseEntity<APIResponse> reserveAppliance(@RequestBody ApplianceReservationPayload applianceReservationPayload) {
        LOG.info("Received request to reserve an appliance..");
        try {
            applianceReservationService.reserveAppliance(applianceReservationPayload);
        } catch (ApplianceException e) {
            LOG.error("Failed to reserve appliance ",e.getErrorMessage());
            return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(UIMessages.APPLIANCE_RESERVED, HttpStatus.CREATED),HttpStatus.OK);
    }

    @PostMapping(APIConstants.RELEASE_APPLIANCE)
    public ResponseEntity<APIResponse> releasedAppliance(@RequestBody ApplianceReservationPayload applianceReservationPayload) {
        LOG.info("Received request to release an appliance..");
        try {
            applianceReservationService.releaseAppliance(applianceReservationPayload);
        } catch (ApplianceException e) {
            LOG.error("Failed to release appliance ",e.getErrorMessage());
            return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(UIMessages.APPLIANCE_RELEASED, HttpStatus.CREATED),HttpStatus.OK);
    }

}
