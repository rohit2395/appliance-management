package com.dell.appliances.service.interfaces;

import com.dell.appliances.dto.ApplianceDetailsPayload;
import com.dell.appliances.dto.ApplianceFilter;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.model.ApplianceDetails;

import java.util.List;

public interface IApplianceService {

    public void addNewAppliance(ApplianceDetailsPayload applianceDetailsPayload) throws ApplianceException;

    public void deleteAppliance(ApplianceDetailsPayload applianceDetails) throws ApplianceException;

    public void updateApplianceDetails(ApplianceDetailsPayload applianceDetails) throws ApplianceException;

    public ApplianceDetailsPayload getApplianceDetails(Long applianceId) throws ApplianceException;
    public ApplianceDetailsPayload getApplianceDetails(String applianceName) throws ApplianceException;

    public List<ApplianceDetailsPayload> getAllAppDetailsList() throws ApplianceException;
    public List<ApplianceDetailsPayload> getAllAppDetailsList(ApplianceFilter applianceFilter) throws ApplianceException;
    List<ApplianceDetailsPayload> getAllAvailableApplianceDetails() throws ApplianceException;
    List<ApplianceDetailsPayload> getAllUnAvailableApplianceDetails() throws ApplianceException;
    public List<ApplianceDetailsPayload> getAllAppDetailsListByUom(Long uomId) throws ApplianceException;
    public List<ApplianceDetailsPayload> getAllAppDetailsListByAssignee(String assigneeName) throws ApplianceException;
}
