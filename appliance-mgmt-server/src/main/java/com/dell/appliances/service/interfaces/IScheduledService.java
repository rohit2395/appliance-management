package com.dell.appliances.service.interfaces;

import com.dell.appliances.exceptions.ApplianceException;

public interface IScheduledService {

    int SCHEDULED_RESERVATION_CLEANUP_ACTIVITY = 1000 * 60 * 60 * 12;
    String EVERY_DAY_AT_9 = "0 0 9 * * *";
    String EVERY_DAY_AT_8 = "0 0 8 * * *";


    void releaseExpiredReservation() throws ApplianceException;

    void notifyExpiration() throws ApplianceException;
}
