package com.dell.appliances.service;

import com.dell.appliances.dto.*;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.model.ActivityDetails;
import com.dell.appliances.model.ApplianceDetails;
import com.dell.appliances.model.UnitOfManagement;
import com.dell.appliances.model.enumerations.*;
import com.dell.appliances.repo.ActivityDetailsRepository;
import com.dell.appliances.repo.ApplianceDetailsRepository;
import com.dell.appliances.repo.AppliancePossessionRepository;
import com.dell.appliances.repo.UnitOfManagementRepository;
import com.dell.appliances.service.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Monday 12/7/2020
 *
 */
@Service
public class GeneralService implements IGeneralService {

    public static final Logger LOG = LogManager.getLogger(ApplianceService.class);

    @Autowired
    private ApplianceDetailsRepository applianceDetailsRepository;

    @Autowired
    private AppliancePossessionRepository appliancePossessionRepository;

    @Autowired
    private UnitOfManagementRepository unitOfManagementRepository;

    @Autowired
    private ActivityDetailsRepository activityDetailsRepository;

    @Autowired
    private IAlertingService alertingService;

    @Override
    public List<String> getAllApplianceModels() throws ApplianceException {
        List<String> allModels = new ArrayList<>();
        allModels.add(ApplianceModel.MODEL_4400S.getVal());
        allModels.add(ApplianceModel.MODEL_4400.getVal());
        allModels.add(ApplianceModel.MODEL_5300.getVal());
        allModels.add(ApplianceModel.MODEL_5800.getVal());
        allModels.add(ApplianceModel.MODEL_5900.getVal());
        allModels.add(ApplianceModel.MODEL_8300.getVal());
        allModels.add(ApplianceModel.MODEL_8400.getVal());
        allModels.add(ApplianceModel.MODEL_8800.getVal());
        allModels.add(ApplianceModel.MODEL_8900.getVal());
        allModels.add(ApplianceModel.LOCAL.getVal());
        return allModels;
    }

    @Override
    public List<String> getAllUomList() throws ApplianceException {
        List<UnitOfManagement> unitOfManagementList = unitOfManagementRepository.findAll();
        List<String> allUoms = new ArrayList<>();
        LOG.info("Getting UOM List");
        for(UnitOfManagement unitOfManagement : unitOfManagementList){
            allUoms.add(unitOfManagement.getName());
            LOG.info("UoM Name: "+unitOfManagement.getName());
        }
        return allUoms;
    }

    @Override
    public List<String> getAllApplianceNames() throws ApplianceException {
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        List<String> applianceNamesList = new ArrayList<>();
        LOG.info("Getting Appliacne names List");
        for(ApplianceDetails ad : applianceDetailsList){
            applianceNamesList.add(ad.getApplianceName());
            LOG.info("Appliance Name: " + ad.getApplianceName());
        }
        return applianceNamesList;
    }

    @Override
    public List<String> getAllPurposeList() throws ApplianceException {
        List<String> allPurposeList = new ArrayList<>();
        allPurposeList.add(Purpose.DEVELOPMENT.getVal());
        allPurposeList.add(Purpose.FEATURE_TESTING.getVal());
        allPurposeList.add(Purpose.UPGRADE_TESTING.getVal());
        allPurposeList.add(Purpose.FI_AUTOMATION.getVal());
        allPurposeList.add(Purpose.UPG_AUTOMATION.getVal());
        allPurposeList.add(Purpose.REGRESSION.getVal());
        allPurposeList.add(Purpose.SYSTEM_TESTING.getVal());
        allPurposeList.add(Purpose.PERFORMANCE.getVal());
        allPurposeList.add(Purpose.CFT.getVal());
        return allPurposeList;
    }
    @Override
    public List<String> getAllLocationList() throws ApplianceException {
        List<String> allLocationList = new ArrayList<>();
        allLocationList.add(Location.HOPKINTON.getVal());
        allLocationList.add(Location.BANGLORE.getVal());
        allLocationList.add(Location.DURHAM.getVal());
        allLocationList.add(Location.SANTA_CLARA.getVal());
        allLocationList.add(Location.CHINA.getVal());
        return allLocationList;
    }

    @Override
    public List<String> getAllGenerationList() throws ApplianceException {
        List<String> allGenerationList = new ArrayList<>();
        allGenerationList.add(Generation.GEN_13.getVal());
        allGenerationList.add(Generation.GEN_14.getVal());
        return allGenerationList;
    }

    @Override
    public ApplianceCount getApplianceCount() throws ApplianceException {
        List<ApplianceDetails> applianceDetailsList = applianceDetailsRepository.findAll();
        ApplianceCount applianceCount = new ApplianceCount();

        for(ApplianceDetails applianceDetails : applianceDetailsList){
            if(applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_4400S)) {
                applianceCount.setTotal4x00s(applianceCount.getTotal4x00s() + 1);
                if(applianceDetails.getAppliancePossessionStatus().equals(AppliancePossessionStatus.AVAILABLE))
                    applianceCount.setTotalAvailable4x00s(applianceCount.getTotalAvailable4x00s()+1);
            }
            else if(applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_4400)) {
                applianceCount.setTotal4x00(applianceCount.getTotal4x00() + 1);
                if(applianceDetails.getAppliancePossessionStatus().equals(AppliancePossessionStatus.AVAILABLE))
                    applianceCount.setTotalAvailable4x00(applianceCount.getTotalAvailable4x00()+1);
            }
            else if(applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_5300) || applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_5800) ||
                    applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_5900)) {
                applianceCount.setTotal5x00(applianceCount.getTotal5x00() + 1);
                if(applianceDetails.getAppliancePossessionStatus().equals(AppliancePossessionStatus.AVAILABLE))
                    applianceCount.setTotalAvailable5x00(applianceCount.getTotalAvailable5x00()+1);
            }
            else if(applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_8300) || applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_8400) ||
                    applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_8800) || applianceDetails.getApplianceModel().equals(ApplianceModel.MODEL_8900)) {
                applianceCount.setTotal8x00(applianceCount.getTotal8x00()+1);
                if(applianceDetails.getAppliancePossessionStatus().equals(AppliancePossessionStatus.AVAILABLE))
                    applianceCount.setTotalAvailable8x00(applianceCount.getTotalAvailable8x00()+1);
            }
            else if(applianceDetails.getApplianceModel().equals(ApplianceModel.LOCAL)) {
                applianceCount.setTotalLocal(applianceCount.getTotalLocal()+1);
            }
        }

        applianceCount.setTotalAppliances(applianceCount.getTotal4x00s()+applianceCount.getTotal4x00()+applianceCount.getTotal5x00()+applianceCount.getTotal8x00());
        applianceCount.setTotalAvailableAppliances(applianceCount.getTotalAvailable4x00()+applianceCount.getTotalAvailable5x00()+applianceCount.getTotalAvailable8x00());
        applianceCount.setTotalReservedAppliaces(Math.abs(applianceCount.getTotalAppliances() - applianceCount.getTotalAvailableAppliances()));

        applianceCount.setTotalReserved4x00(Math.abs(applianceCount.getTotal4x00()-applianceCount.getTotalAvailable4x00()));
        applianceCount.setTotalReserved5x00(Math.abs(applianceCount.getTotal5x00()-applianceCount.getTotalAvailable5x00()));
        applianceCount.setTotalReserved8x00(Math.abs(applianceCount.getTotal8x00()-applianceCount.getTotalAvailable8x00()));

        applianceCount.setCountByLoc(new ArrayList<>());
        applianceCount.setCountByGen(new ArrayList<>());
        applianceCount.setCountByLocEsxi(new ArrayList<>());
        for(ICountByLocation countByLocation : applianceDetailsRepository.getTotalCountByLocation()){
            applianceCount.getCountByLoc().add(new CountByLocation(countByLocation.getLocation().getVal(),countByLocation.getTotalCount()));
        }

        for(ICountByGeneration countByGeneration : applianceDetailsRepository.getTotalCountByGeneration()){
            applianceCount.getCountByGen().add(new CountByGeneration(countByGeneration.getGeneration().getVal(),countByGeneration.getTotalCount()));
        }
        for(ICountByLocation countByLocation : applianceDetailsRepository.getTotalCountByLocationEsxi()){
            applianceCount.getCountByLocEsxi().add(new CountByLocation(countByLocation.getLocation().getVal(),countByLocation.getTotalCount()));
        }

        return applianceCount;
    }

    @Override
    public ApplianceCountByModel getApplianceCountByModel() throws ApplianceException {
        ApplianceCountByModel applianceCountByModel = new ApplianceCountByModel();
        List<ICountByModel> list = applianceDetailsRepository.getTotalCountByModel();
        for(ICountByModel iCountByModel : list){
            LOG.info("Model: "+iCountByModel.getModel()+", Count: "+iCountByModel.getTotalCount());
            switch (iCountByModel.getModel()){
                case MODEL_4400S:
                    applianceCountByModel.setDP4400(iCountByModel.getTotalCount());
                    break;
                case MODEL_4400:
                    applianceCountByModel.setDP4400s(iCountByModel.getTotalCount());
                    break;
                case MODEL_5300:
                    applianceCountByModel.setDP5300(iCountByModel.getTotalCount());
                    break;
                case MODEL_5800:
                    applianceCountByModel.setDP5800(applianceCountByModel.getDP5800()+1);
                    break;
                case MODEL_5900:
                    applianceCountByModel.setDP5900(iCountByModel.getTotalCount());
                    break;
                case MODEL_8300:
                    applianceCountByModel.setDP8300(iCountByModel.getTotalCount());
                    break;
                case MODEL_8400:
                    applianceCountByModel.setDP8400(iCountByModel.getTotalCount());
                    break;
                case MODEL_8800:
                    applianceCountByModel.setDP8800(iCountByModel.getTotalCount());
                    break;
                case MODEL_8900:
                    applianceCountByModel.setDP8900(iCountByModel.getTotalCount());
                    break;
            }
        }
        return applianceCountByModel;
    }

    @Override
    public List<ActivityDetailsPayload> getApplianceActivity() throws ApplianceException {

        List<ActivityDetails> activityDetailsList = activityDetailsRepository.findAllByOrderByDateDesc();
        List<ActivityDetailsPayload> activityDetailsPayloadList = new ArrayList<>();
        for(ActivityDetails activity : activityDetailsList){
            String date = new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(activity.getDate());
            activityDetailsPayloadList.add(new ActivityDetailsPayload(activity.getActivity(),date));
        }
        return activityDetailsPayloadList;

    }

    @Override
    public void testEmail() throws ApplianceException {
        alertingService.sendEmail("Test Email","This is a test email.");
    }
}
