package com.dell.appliances.service.interfaces;

import com.dell.appliances.dto.*;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.model.ActivityDetails;

import java.util.List;

public interface IGeneralService {

    List<String> getAllApplianceModels() throws ApplianceException;
    List<String> getAllUomList() throws ApplianceException;

    List<String> getAllApplianceNames() throws ApplianceException;
    List<String> getAllPurposeList() throws ApplianceException;
    public List<String> getAllLocationList() throws ApplianceException;
    public List<String> getAllGenerationList() throws ApplianceException;

    ApplianceCount getApplianceCount() throws ApplianceException;
    ApplianceCountByModel getApplianceCountByModel() throws ApplianceException;
    List<ActivityDetailsPayload> getApplianceActivity() throws ApplianceException;

    void testEmail() throws ApplianceException;

}
