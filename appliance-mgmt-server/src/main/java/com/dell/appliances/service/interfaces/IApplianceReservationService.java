package com.dell.appliances.service.interfaces;

import com.dell.appliances.dto.ApplianceReservationPayload;
import com.dell.appliances.exceptions.ApplianceException;

import java.util.Date;


public interface IApplianceReservationService {

    public Boolean reserveAppliance(ApplianceReservationPayload applianceReservationPayload) throws ApplianceException;

    public Boolean releaseAppliance(ApplianceReservationPayload applianceReservationPayload) throws ApplianceException;

}
