package com.dell.appliances.controller;

import com.dell.appliances.common.ApplianceUtil;
import com.dell.appliances.dto.*;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.model.ActivityDetails;
import com.dell.appliances.service.interfaces.ICountByModel;
import com.dell.appliances.service.interfaces.IGeneralService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
@RestController
@RequestMapping(APIConstants.GENERAL)
public class GeneralController {

    public static final Logger LOG = LogManager.getLogger(GeneralController.class);

    @Autowired
    private IGeneralService generalService;

    @GetMapping(APIConstants.GET_ALL_APPLIANCE_MODELS)
    public ResponseEntity<?> getAllModels() {
        LOG.info("Received request to get all appliance models..");
        List<String> allModels = new ArrayList<>();
        try {
            allModels = generalService.getAllApplianceModels();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all appliance models ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allModels,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_UOM)
    public ResponseEntity<?> getAllUOMs() {
        LOG.info("Received request to get all UoMs..");
        List<String> allUoms = new ArrayList<>();
        try {
            allUoms = generalService.getAllUomList();
        } catch (ApplianceException e) {
            LOG.error("Failed to to get all UoMs ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allUoms,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_APPLIANCE_NAMES)
    public ResponseEntity<?> getAllApplianceNames() {
        LOG.info("Received request to get all Appliance names..");
        List<String> allAppliances = new ArrayList<>();
        try {
            allAppliances = generalService.getAllApplianceNames();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all Appliance names ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allAppliances,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_PURPOSE)
    public ResponseEntity<?> getAllPurposes() {
        LOG.info("Received request to get all purpose..");
        List<String> allPurpose = new ArrayList<>();
        try {
            allPurpose = generalService.getAllPurposeList();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all purpose ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allPurpose,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_LOCATION)
    public ResponseEntity<?> getAllLocations() {
        LOG.info("Received request to get all locations..");
        List<String> allLocations = new ArrayList<>();
        try {
            allLocations = generalService.getAllLocationList();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all locations ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allLocations,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_GENERATION)
    public ResponseEntity<?> getAllGenerations() {
        LOG.info("Received request to get all generations..");
        List<String> allGenerations = new ArrayList<>();
        try {
            allGenerations = generalService.getAllGenerationList();
        } catch (ApplianceException e) {
            LOG.error("Failed to get all generation ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(allGenerations,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_APPLIANCE_COUNT_MODEL)
    public ResponseEntity<?> getAllCountByModel() {
        LOG.info("Received request to get all appliance count by model..");
        ApplianceCountByModel applianceCountByModel = new ApplianceCountByModel();

        try {
            applianceCountByModel = generalService.getApplianceCountByModel();
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance count ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(applianceCountByModel,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_APPLIANCE_COUNT)
    public ResponseEntity<?> getAllCount() {
        LOG.info("Received request to get all appliance count..");
        ApplianceCount applianceCount = new ApplianceCount();
        try {
            applianceCount = generalService.getApplianceCount();
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance count ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(applianceCount,HttpStatus.OK);
    }

    @GetMapping(APIConstants.GET_ALL_ACTIVITY)
    public ResponseEntity<?> getAllActivity() {
        LOG.info("Received request to get all activity..");
        List<ActivityDetailsPayload> activityDetailsList = new ArrayList<>();
        try {
            activityDetailsList = generalService.getApplianceActivity();
        } catch (ApplianceException e) {
            LOG.error("Failed to get appliance activity ",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(activityDetailsList,HttpStatus.OK);
    }

    @PostMapping(APIConstants.TEST_EMAIL)
    public ResponseEntity<?> testEmail() {
        LOG.info("Received request to send a test mail..");
        try {
            generalService.testEmail();
        } catch (ApplianceException e) {
            LOG.error("Failed to send test email",e.getErrorMessage());
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e),e.getErrorCode());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
