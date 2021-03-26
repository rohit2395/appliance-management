package com.dell.appliances.controller;

import com.dell.appliances.common.ApplianceUtil;
import com.dell.appliances.common.UIMessages;
import com.dell.appliances.dto.APIResponse;
import com.dell.appliances.dto.ApplianceDetailsPayload;
import com.dell.appliances.dto.ApplianceFilter;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.service.interfaces.IApplianceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
@RestController
@RequestMapping(APIConstants.APPLIANCE)
public class ApplianceController {

    public static final Logger LOG = LogManager.getLogger(ApplianceController.class);

    @Autowired
    private IApplianceService applianceService;

    @PostMapping(APIConstants.ADD_APPLIANCE)
    public ResponseEntity<APIResponse> addAppliance(@RequestBody ApplianceDetailsPayload applianceDetailsPayload) {
        LOG.info("Received request to add an appliance..");
        try {
            applianceService.addNewAppliance(applianceDetailsPayload);
        } catch (ApplianceException e) {
            LOG.error("Failed to add appliance ",e.getErrorMessage());
            return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(UIMessages.APPLIANCE_ADDED, HttpStatus.CREATED),HttpStatus.OK);
    }

    @PostMapping(APIConstants.UPDATE_APPLIANCE)
    public ResponseEntity<APIResponse> updateAppliance(@RequestBody ApplianceDetailsPayload applianceDetailsPayload) {
        LOG.info("Received request to update an appliance..");
        try {
            applianceService.updateApplianceDetails(applianceDetailsPayload);
        } catch (ApplianceException e) {
            LOG.error("Failed to update appliance ",e.getErrorMessage());
            return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(UIMessages.APPLIANCE_UPDATED, HttpStatus.CREATED),HttpStatus.OK);
    }

    @PostMapping(APIConstants.DELETE_APPLIANCE)
    public ResponseEntity<APIResponse> deleteAppliance(@RequestBody ApplianceDetailsPayload applianceDetailsPayload) {
        LOG.info("Received request to delete an appliance..");
        try {
            applianceService.deleteAppliance(applianceDetailsPayload);
        } catch (ApplianceException e) {
            LOG.error("Failed to add appliance ",e.getErrorMessage());
            return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<APIResponse>(ApplianceUtil.buildApiResponse(UIMessages.APPLIANCE_DELETED, HttpStatus.CREATED),HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_APPLIANCE_ID)
    public ResponseEntity<?> getAppliance(@PathVariable @RequestBody Long id) {
        LOG.info("Received request to get appliance details..");
        try {
            ApplianceDetailsPayload applianceDetailsPayload = applianceService.getApplianceDetails(id);
            return new ResponseEntity<>(applianceDetailsPayload,HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance details",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
    }

    @GetMapping(APIConstants.GET_APPLIANCE_NAME)
    public ResponseEntity<?> getAppliance(@PathVariable @RequestBody String name) {
        LOG.info("Received request to get appliance details..");
        try {
            ApplianceDetailsPayload applianceDetailsPayload = applianceService.getApplianceDetails(name);
            return new ResponseEntity<>(applianceDetailsPayload,HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance details",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
    }

    @GetMapping(APIConstants.GET_APPLIANCES_ALL)
    public ResponseEntity<?> getAllAppliances() {
        LOG.info("Received request to get all appliance details..");
        try {
            List<ApplianceDetailsPayload> applianceDetailsPayloads = applianceService.getAllAppDetailsList();
            return new ResponseEntity<>(applianceDetailsPayloads,HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get all appliance details",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
    }

    @PostMapping(APIConstants.GET_ALL_APPLIANCE_BY_FILTER)
    public ResponseEntity<?> getAllAppliancesByFilter(@RequestBody ApplianceFilter applianceFilter) {
        LOG.info("Received request to get all appliance details by filter..");
        try {
            List<ApplianceDetailsPayload> applianceDetailsPayloads = applianceService.getAllAppDetailsList(applianceFilter);
            return new ResponseEntity<>(applianceDetailsPayloads,HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get all appliance details by filter",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
    }

    @GetMapping(APIConstants.GET_ALL_APPLIANCE_NAMES_AVAIL)
    public ResponseEntity<?> getAllAvailableApplianceNames() {
        LOG.info("Received request to get all Appliance names..");
        List<ApplianceDetailsPayload> allAppliances = new ArrayList<>();
        try {
            allAppliances = applianceService.getAllAvailableApplianceDetails();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all Appliance names ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allAppliances,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_APPLIANCE_NAMES_UNAVAIL)
    public ResponseEntity<?> getAllUnAvailableApplianceNames() {
        LOG.info("Received request to get all Appliance names..");
        List<ApplianceDetailsPayload> allAppliances = new ArrayList<>();
        try {
            allAppliances = applianceService.getAllUnAvailableApplianceDetails();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all Appliance names ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allAppliances,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_APPLIANCES_UOM)
    public ResponseEntity<?> getAllAppliancesByUom(@PathVariable @RequestBody Long uomId) {
        LOG.info("Received request to get all appliance details by UOM..");
        try {
            List<ApplianceDetailsPayload> applianceDetailsPayloads = applianceService.getAllAppDetailsListByUom(uomId);
            return new ResponseEntity<>(applianceDetailsPayloads,HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance details",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
    }

    @GetMapping(APIConstants.GET_APPLIANCES_ASSIGNEE)
    public ResponseEntity<?> getAllAppliancesByUom(@PathVariable @RequestBody String assigneeName) {
        LOG.info("Received request to get all appliance details by UOM..");
        try {
            List<ApplianceDetailsPayload> applianceDetailsPayloads = applianceService.getAllAppDetailsListByAssignee(assigneeName);
            return new ResponseEntity<>(applianceDetailsPayloads,HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance details",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
    }

}
