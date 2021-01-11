package com.dell.appliances.service.interfaces;

import com.dell.appliances.dto.ApplianceReservationPayload;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.model.ApplianceDetails;

import java.util.List;
public interface IAlertingService {

    void sendEmail(String subject,String content) throws ApplianceException;
    void sendEmail(String subject,String content,List<String> emailList) throws ApplianceException;
    void sendReservationEmail(ApplianceDetails applianceDetails) throws ApplianceException;
    void sendReleaseEmail(ApplianceDetails applianceDetails) throws ApplianceException;
    void sendExpirationNotifyEmail(ApplianceDetails applianceDetails) throws ApplianceException;
    void sendApplianceAddEmail(ApplianceReservationPayload applianceReservationPayload,ApplianceDetails applianceDetails) throws ApplianceException;
}
