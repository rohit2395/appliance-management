package com.dell.appliances.service;

import com.dell.appliances.common.Constants;
import com.dell.appliances.dto.ApplianceReservationPayload;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.model.ApplianceDetails;
import com.dell.appliances.model.enumerations.AppliancePossessionStatus;
import com.dell.appliances.repo.ApplianceDetailsRepository;
import com.dell.appliances.service.interfaces.IAlertingService;
import com.dell.appliances.service.interfaces.IApplianceReservationService;
import com.dell.appliances.service.interfaces.IScheduledService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Monday 12/21/2020
 *
 */
@Service
public class ScheduledService implements IScheduledService {

    public static final Logger LOG = LogManager.getLogger(ScheduledService.class);

    @Autowired
    private ApplianceDetailsRepository applianceDetailsRepository;

    @Autowired
    private IApplianceReservationService applianceReservationService;

    @Autowired
    private IAlertingService alertingService;

    @Override
    @Scheduled(cron = EVERY_DAY_AT_8)
    public void releaseExpiredReservation() throws ApplianceException {
        LOG.info("Cleaning expired reservation");
        LocalDate currentDate = LocalDate.now().minusDays(1);
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        for(ApplianceDetails ad : applianceDetailsList){
            if(ad.getAppliancePossessionStatus().equals(AppliancePossessionStatus.UNAVAILABLE)) {
                LocalDate endDate = LocalDate.parse(ad.getAppliancePossession().getEndDate());
                if(endDate.compareTo(currentDate) < 0){
                    LOG.info("Current Time:\t"+currentDate);
                    LOG.info("End date:\t"+endDate);
                    LOG.info("This appliance: "+ad.getApplianceName()+" has expired it's reservation!");
                    ApplianceReservationPayload applianceReservationPayload = new ApplianceReservationPayload();
                    applianceReservationPayload.setApplianceName(ad.getApplianceName());
                    applianceReservationService.releaseAppliance(applianceReservationPayload);
                }else{
                    LOG.info("Yet to expire reservation!: "+ad.getApplianceName());
                }
            }
        }
    }

    @Override
    @Scheduled(cron = EVERY_DAY_AT_9)
    public void notifyExpiration() throws ApplianceException {
        LOG.info("Notifying reservations about to expire");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME);
        LocalDate currentDate = LocalDate.now().plusDays(3);
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        for(ApplianceDetails ad : applianceDetailsList){
            if(ad.getAppliancePossessionStatus().equals(AppliancePossessionStatus.UNAVAILABLE)) {
                LocalDate endDate = LocalDate.parse(ad.getAppliancePossession().getEndDate(),formatter);
                if(endDate.compareTo(currentDate) < 0){
                    LOG.info("Current Time:\t"+currentDate);
                    LOG.info("End date:\t"+endDate);
                    LOG.info("This appliance: "+ad.getApplianceName()+" is about to expire it's reservation!");
                    alertingService.sendExpirationNotifyEmail(ad);
                }else{
                    LOG.info("Yet to expire reservation!: "+ad.getApplianceName());
                }
            }
        }
    }
}
