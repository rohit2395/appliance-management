package com.dell.appliances.service.interfaces;

import com.dell.appliances.model.enumerations.Generation;

public interface ICountByGeneration {
    Generation getGeneration();
    int getTotalCount();
}
