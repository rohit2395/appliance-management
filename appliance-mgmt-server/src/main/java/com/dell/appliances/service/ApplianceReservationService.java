package com.dell.appliances.service;

import com.dell.appliances.common.StringUtils;
import com.dell.appliances.dto.ApplianceReservationPayload;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.exceptions.Error;
import com.dell.appliances.model.ActivityDetails;
import com.dell.appliances.model.ApplianceDetails;
import com.dell.appliances.model.AppliancePossession;
import com.dell.appliances.model.enumerations.AppliancePossessionStatus;
import com.dell.appliances.model.enumerations.Purpose;
import com.dell.appliances.repo.ActivityDetailsRepository;
import com.dell.appliances.repo.ApplianceDetailsRepository;
import com.dell.appliances.repo.AppliancePossessionRepository;
import com.dell.appliances.service.interfaces.IAlertingService;
import com.dell.appliances.service.interfaces.IApplianceReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
@Service
public class ApplianceReservationService implements IApplianceReservationService {

    public static final Logger LOG = LogManager.getLogger(ApplianceReservationService.class);

    @Autowired
    private StringUtils stringUtils;

    @Autowired
    private AppliancePossessionRepository appliancePossessionRepository;

    @Autowired
    private ApplianceDetailsRepository applianceDetailsRepository;

    @Autowired
    private ActivityDetailsRepository activityDetailsRepository;

    @Autowired
    private IAlertingService alertingService;

    @Override
    public Boolean reserveAppliance(ApplianceReservationPayload applianceReservationPayload) throws ApplianceException {
        LOG.info("Reserving an appliance");
        try {
            AppliancePossession appliancePossession = new AppliancePossession(applianceReservationPayload.getAssigneeName(),applianceReservationPayload.getAssigneeEmail(), Purpose.getPurpose(applianceReservationPayload.getPurpose()), applianceReservationPayload.getStartDate(),applianceReservationPayload.getEndDate());
            Optional<ApplianceDetails> applianceDetails = applianceDetailsRepository.findByApplianceName(applianceReservationPayload.getApplianceName());
            if (applianceDetails.get().getAppliancePossessionStatus().equals(AppliancePossessionStatus.AVAILABLE)) {
                AppliancePossession saved = appliancePossessionRepository.save(appliancePossession);
                LOG.info("Possession ID:" + saved.getId());
                if (applianceDetails.isPresent()) {
                    applianceDetails.get().setAppliancePossessionStatus(AppliancePossessionStatus.UNAVAILABLE);
                    applianceDetails.get().setAppliancePossession(saved);
                    if (applianceDetails.get().getCanBeShared()) {
                        applianceDetails.get().setAcmIP(applianceReservationPayload.getAcmIp());
                        applianceDetails.get().setAcmPassword(applianceReservationPayload.getAcmPassword());
                    }
                    applianceDetailsRepository.save(applianceDetails.get());
                    String activity = applianceDetails.get().getApplianceName()+" appliance [ Model:"+applianceDetails.get().getApplianceModel().getVal()+" ] is reserved by "+appliancePossession.getAssigneeName()+" for the purpose of "+appliancePossession.getPurpose();
                    activityDetailsRepository.save(new ActivityDetails(activity,new Date()));
                    alertingService.sendReservationEmail(applianceDetails.get());
                    LOG.info("Appliance reserved successfully with Name :" + applianceDetails.get().getApplianceName());
                }
            } else {
                LOG.error("Failed to reserve appliance with Name:" + applianceReservationPayload.getApplianceName());
                throw new ApplianceException(Error.FAILED_TO_RESERVE_APPLIANCE, new String[]{applianceReservationPayload.getApplianceName()}, null);
            }
            return true;
        } catch (Exception e) {
            LOG.error("Failed to reserve appliance with Name:" + applianceReservationPayload.getApplianceName(), e);
            throw new ApplianceException(Error.FAILED_TO_RESERVE_APPLIANCE, new String[]{applianceReservationPayload.getApplianceName()}, e);
        }
    }

    @Override
    public Boolean releaseAppliance(ApplianceReservationPayload applianceReservationPayload) throws ApplianceException {
        LOG.info("Releasing an appliance");
        try {
            Date dateTime = new Date();
            Optional<ApplianceDetails> applianceDetails = applianceDetailsRepository.findByApplianceName(applianceReservationPayload.getApplianceName());
            if(applianceDetails.isPresent()){
                AppliancePossession possession = new AppliancePossession();
                possession.setId(applianceDetails.get().getAppliancePossession().getId());
                applianceDetails.get().setAppliancePossessionStatus(AppliancePossessionStatus.AVAILABLE);
                applianceDetails.get().setAppliancePossession(null);
                applianceDetails.get().setAcmIP(null);
                applianceDetails.get().setAcmPassword(null);
                LOG.info("current appliance possession info:"+possession);
                applianceDetailsRepository.save(applianceDetails.get());
                appliancePossessionRepository.delete(possession);
                LOG.info("Possession deleted with ID:"+possession.getId());
                String activity = applianceDetails.get().getApplianceName()+" appliance [ Model:"+applianceDetails.get().getApplianceModel().getVal()+" ] is available for reservation. ";
                activityDetailsRepository.save(new ActivityDetails(activity,new Date()));
                alertingService.sendReleaseEmail(applianceDetails.get());
                LOG.info("Appliance released successfully with Name:"+applianceDetails.get().getApplianceName());
            }
            return true;
        }catch (Exception e){
            LOG.error("Failed to release appliance with Name:"+applianceReservationPayload.getApplianceName(),e);
            throw new ApplianceException(Error.FAILED_TO_RESERVE_APPLIANCE,new String[] {applianceReservationPayload.getApplianceName()},e);
        }
    }

}
