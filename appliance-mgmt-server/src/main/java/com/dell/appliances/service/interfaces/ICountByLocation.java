package com.dell.appliances.service.interfaces;

import com.dell.appliances.model.enumerations.Location;

public interface ICountByLocation {
    Location getLocation();
    int getTotalCount();
}
