package com.dell.appliances.service.interfaces;

import com.dell.appliances.model.enumerations.ApplianceModel;

public interface ICountByModel {

    ApplianceModel getModel();
    int getTotalCount();
}
