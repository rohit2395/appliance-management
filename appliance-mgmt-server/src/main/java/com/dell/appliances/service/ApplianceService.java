package com.dell.appliances.service;

import com.dell.appliances.common.StringUtils;
import com.dell.appliances.dto.ApplianceDetailsPayload;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.exceptions.Error;
import com.dell.appliances.model.ActivityDetails;
import com.dell.appliances.model.ApplianceDetails;
import com.dell.appliances.model.UnitOfManagement;
import com.dell.appliances.model.enumerations.*;
import com.dell.appliances.repo.ActivityDetailsRepository;
import com.dell.appliances.repo.ApplianceDetailsRepository;
import com.dell.appliances.repo.AppliancePossessionRepository;
import com.dell.appliances.repo.UnitOfManagementRepository;
import com.dell.appliances.service.interfaces.IApplianceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
@Service
public class ApplianceService implements IApplianceService {

    public static final Logger LOG = LogManager.getLogger(ApplianceService.class);

    @Autowired
    private StringUtils stringUtils;

    @Autowired
    private ApplianceDetailsRepository applianceDetailsRepository;

    @Autowired
    private UnitOfManagementRepository unitOfManagementRepository;

    @Autowired
    private ActivityDetailsRepository activityDetailsRepository;

    @Autowired
    private AppliancePossessionRepository appliancePossessionRepository;

    @Override
    public void addNewAppliance(ApplianceDetailsPayload applianceDetailsPayload) throws ApplianceException {
        LOG.info("Adding a new appliance to the system");
        try {
            ApplianceDetails applianceDetails = new ApplianceDetails(applianceDetailsPayload.getApplianceName(), ApplianceModel.getApplianceModel(applianceDetailsPayload.getApplianceModel()), applianceDetailsPayload.getCanBeShared());
            Optional<UnitOfManagement> unitOfManagement = unitOfManagementRepository.findByName(applianceDetailsPayload.getUomName());
            unitOfManagement.ifPresent(applianceDetails::setUnitOfManagement);
            applianceDetails.setLocation(Location.getLocation(applianceDetailsPayload.getLocation()));
            applianceDetails.setGeneration(Generation.getGeneration(applianceDetailsPayload.getGeneration()));
            applianceDetails.setOwnerName(applianceDetailsPayload.getAssignee());
            applianceDetails.setEmail(applianceDetailsPayload.getAssigneeEmail());
            applianceDetails.setPurpose(Purpose.getPurpose(applianceDetailsPayload.getPurpose()));
            applianceDetails.setConfiguration(applianceDetailsPayload.getConfiguration());
            applianceDetailsRepository.save(applianceDetails);
            activityDetailsRepository.save(new ActivityDetails("A new appliance - "+applianceDetails.getApplianceName()+" [ Model:"+applianceDetails.getApplianceModel().getVal()+", UoM Name:"+applianceDetails.getUnitOfManagement().getName()+" ]"+" is added.",new Date()));
        }catch (Exception e){
            LOG.error("Failed to add new appliance to the system",e);
            throw new ApplianceException(Error.FAILED_TO_ADD_NEW_APPLIANCE,e);
        }
        LOG.info("New appliance successfully added to the system");
    }

    @Override
    public void deleteAppliance(ApplianceDetailsPayload applianceDetailsPayload) throws ApplianceException {
        LOG.info("Delete an appliance from the system");
        try {
            Optional<ApplianceDetails> applianceDetailsOptional = applianceDetailsRepository.findByApplianceName(applianceDetailsPayload.getApplianceName());
            if(applianceDetailsOptional.isPresent()){
                if(Objects.nonNull(applianceDetailsOptional.get().getAppliancePossession()))
                    appliancePossessionRepository.delete(applianceDetailsOptional.get().getAppliancePossession());
                applianceDetailsRepository.delete(applianceDetailsOptional.get());
                activityDetailsRepository.save(new ActivityDetails("Appliance - "+applianceDetailsOptional.get().getApplianceName()+" [ Model:"+applianceDetailsOptional.get().getApplianceModel().getVal()+", UoM Name:"+applianceDetailsOptional.get().getUnitOfManagement().getName()+" ]"+" is deleted.",new Date()));
                LOG.info("Appliance successfully delete from the system");
            }else{
                LOG.error("Failed to delete the appliance details. The exisiting appliance not found");
                throw new ApplianceException(Error.FAILED_TO_UPDATE_APPLIANCE,null);
            }
        }catch (ApplianceException e){
            throw e;
        }catch (Exception e){
            LOG.error("Failed to add new appliance to the system",e);
            throw new ApplianceException(Error.FAILED_TO_UPDATE_APPLIANCE,e);
        }
    }

    @Override
    public void updateApplianceDetails(ApplianceDetailsPayload applianceDetailsPayload) throws ApplianceException {
        LOG.info("Update an appliance to the system");
        try {
            Optional<ApplianceDetails> applianceDetailsOptional = applianceDetailsRepository.findByApplianceName(applianceDetailsPayload.getApplianceName());
            if(applianceDetailsOptional.isPresent()){
                ApplianceDetails applianceDetails = applianceDetailsOptional.get();
                if(Objects.nonNull(applianceDetailsPayload.getApplianceName()))
                    applianceDetails.setApplianceName(applianceDetailsPayload.getApplianceName());
                if(Objects.nonNull(applianceDetailsPayload.getApplianceModel()))
                    applianceDetails.setApplianceModel(ApplianceModel.getApplianceModel(applianceDetailsPayload.getApplianceModel()));
                if(Objects.nonNull(applianceDetailsPayload.getLocation()))
                    applianceDetails.setLocation(Location.getLocation(applianceDetailsPayload.getLocation()));
                if(Objects.nonNull(applianceDetailsPayload.getGeneration()))
                    applianceDetails.setGeneration(Generation.getGeneration(applianceDetailsPayload.getGeneration()));
                if(Objects.nonNull(applianceDetailsPayload.getCanBeShared()))
                    applianceDetails.setCanBeShared( applianceDetailsPayload.getCanBeShared());
                if(Objects.nonNull(applianceDetailsPayload.getUomName())) {
                    Optional<UnitOfManagement> unitOfManagement = unitOfManagementRepository.findByName(applianceDetailsPayload.getUomName());
                    unitOfManagement.ifPresent(applianceDetails::setUnitOfManagement);
                }
                if(Objects.nonNull(applianceDetailsPayload.getAssignee()))
                    applianceDetails.setOwnerName(applianceDetailsPayload.getAssignee());
                if(Objects.nonNull(applianceDetailsPayload.getAssigneeEmail()))
                    applianceDetails.setEmail(applianceDetailsPayload.getAssigneeEmail());
                if(Objects.nonNull(applianceDetailsPayload.getPurpose()))
                    applianceDetails.setPurpose(Purpose.getPurpose(applianceDetailsPayload.getPurpose()));
                if(Objects.nonNull(applianceDetailsPayload.getConfiguration()))
                    applianceDetails.setConfiguration(applianceDetailsPayload.getConfiguration());
                applianceDetailsRepository.save(applianceDetails);
                activityDetailsRepository.save(new ActivityDetails("Appliance - "+applianceDetails.getApplianceName()+" [ Model:"+applianceDetails.getApplianceModel().getVal()+", UoM Name:"+applianceDetails.getUnitOfManagement().getName()+" ]"+" is updated.",new Date()));

            }else{
                LOG.error("Failed to update the appliance details. The exisiting appliance not found");
                throw new ApplianceException(Error.FAILED_TO_UPDATE_APPLIANCE,null);
            }
        }catch (ApplianceException e){
            throw e;
        }catch (Exception e){
            LOG.error("Failed to add new appliance to the system",e);
            throw new ApplianceException(Error.FAILED_TO_UPDATE_APPLIANCE,e);
        }
        LOG.info("New appliance successfully added to the system");
    }

    @Override
    public ApplianceDetailsPayload getApplianceDetails(Long applianceId) throws ApplianceException {
        LOG.info("Getting appliance details by ID:"+applianceId);
        try {
            Optional<ApplianceDetails> data = applianceDetailsRepository.findById(applianceId);
            if(data.isPresent()){
                return convertToPayLoad(data.get());
            }else{
                throw new ApplianceException(Error.APPLIANCE_NOT_FOUND_ID,new String[] {applianceId.toString()},null);
            }
        }catch (ApplianceException e){
            throw e;
        }catch (Exception e){
            LOG.error("Failed to add new appliance to the system",e);
            throw new ApplianceException(Error.FAILED_TO_ADD_NEW_APPLIANCE,e);
        }
    }

    @Override
    public ApplianceDetailsPayload getApplianceDetails(String applianceName) throws ApplianceException {
        LOG.info("Getting appliance details by Name:"+applianceName);
        try {
            Optional<ApplianceDetails> data = applianceDetailsRepository.findByApplianceName(applianceName);
            if(data.isPresent()){
                return convertToPayLoad(data.get());
            }else{
                throw new ApplianceException(Error.APPLIANCE_NOT_FOUND_NAME,new String[] {applianceName},null);
            }
        }catch (ApplianceException e){
            throw e;
        }catch (Exception e){
            LOG.error("Failed to fetch appliance details",e);
            throw new ApplianceException(Error.APPLIANCE_NOT_FOUND_NAME,e);
        }
    }

    @Override
    public List<ApplianceDetailsPayload> getAllAppDetailsList() throws ApplianceException {
        LOG.info("Getting all appliance details ");
        try {
            List<ApplianceDetails> data = applianceDetailsRepository.findAll();
            List<ApplianceDetailsPayload> applianceDetailsPayloads = new ArrayList<>();
            for(ApplianceDetails app : data){
                applianceDetailsPayloads.add(convertToPayLoad(app));
            }
            LOG.info("Appliance details fetched successfully : {}",data);
            return applianceDetailsPayloads;
        }catch (Exception e){
            LOG.error("Failed to fetch appliance details",e);
            throw new ApplianceException(Error.APPLIANCES_NOT_FOUND,e);
        }
    }


    @Override
    public List<ApplianceDetailsPayload> getAllAvailableApplianceDetails() throws ApplianceException {
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        List<ApplianceDetailsPayload> applianceDetailsPayloadList = new ArrayList<>();
        LOG.info("Getting Appliacne names List");
        for(ApplianceDetails ad : applianceDetailsList){
            if(ad.getAppliancePossessionStatus().equals(AppliancePossessionStatus.AVAILABLE)) {
                applianceDetailsPayloadList.add(convertToPayLoad(ad));
                LOG.info("Appliance Name: " + ad.getApplianceName());
            }
        }
        return applianceDetailsPayloadList;
    }

    @Override
    public List<ApplianceDetailsPayload> getAllUnAvailableApplianceDetails() throws ApplianceException {
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        List<ApplianceDetailsPayload> applianceDetailsPayloadList = new ArrayList<>();
        LOG.info("Getting Appliance names List");
        for(ApplianceDetails ad : applianceDetailsList){
            if(ad.getAppliancePossessionStatus().equals(AppliancePossessionStatus.UNAVAILABLE)) {
                applianceDetailsPayloadList.add(convertToPayLoad(ad));
                LOG.info("Appliance Name: " + ad.getApplianceName());
            }
        }
        return applianceDetailsPayloadList;
    }

    @Override
    public List<ApplianceDetailsPayload> getAllAppDetailsListByUom(Long uomId) throws ApplianceException {
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        List<ApplianceDetailsPayload> applianceDetailsPayloadList = new ArrayList<>();
        LOG.info("Getting Appliance names List");
        for (ApplianceDetails ad : applianceDetailsList) {
            if(ad.getUnitOfManagement().getId().equals(uomId)) {
                applianceDetailsPayloadList.add(convertToPayLoad(ad));
                LOG.info("Appliance Name: " + ad.getApplianceName());
            }
        }
        return applianceDetailsPayloadList;
    }

    @Override
    public List<ApplianceDetailsPayload> getAllAppDetailsListByAssignee(String assigneeName) throws ApplianceException {
        return null;
    }

    private ApplianceDetailsPayload convertToPayLoad(ApplianceDetails applianceDetails){
        ApplianceDetailsPayload applianceDetailsPayload = new ApplianceDetailsPayload(applianceDetails.getId(),applianceDetails.getApplianceName(),applianceDetails.getApplianceModel().getVal(),applianceDetails.getCanBeShared(),applianceDetails.getUnitOfManagement().getName());
        if(Objects.nonNull(applianceDetails.getLocation()))
            applianceDetailsPayload.setLocation(applianceDetails.getLocation().getVal());
        if(Objects.nonNull(applianceDetails.getGeneration()))
            applianceDetailsPayload.setAppliancePossessionStatus(applianceDetails.getAppliancePossessionStatus().getVal());
        if(Objects.nonNull(applianceDetails.getPurpose()))
            applianceDetailsPayload.setPurpose(applianceDetails.getPurpose().getVal());
        applianceDetailsPayload.setGeneration(applianceDetails.getGeneration().getVal());
        applianceDetailsPayload.setConfiguration(applianceDetails.getConfiguration());
        applianceDetailsPayload.setAssignee(applianceDetails.getOwnerName());
        applianceDetailsPayload.setAssigneeEmail(applianceDetails.getEmail());
        if(Objects.nonNull(applianceDetails.getAppliancePossession())){
//            applianceDetailsPayload.setPurpose(applianceDetails.getAppliancePossession().getPurpose().getVal());
//            applianceDetailsPayload.setAssignee(applianceDetails.getAppliancePossession().getAssigneeName());
            applianceDetailsPayload.setAssigneeEmail(applianceDetails.getAppliancePossession().getAssigneeEmail());
            applianceDetailsPayload.setStartDate(StringUtils.extractDate(applianceDetails.getAppliancePossession().getStartDate()));
            applianceDetailsPayload.setEndDate(StringUtils.extractDate(applianceDetails.getAppliancePossession().getEndDate()));
        }
        return applianceDetailsPayload;
    }

}
