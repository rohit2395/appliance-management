package com.dell.appliances.model.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum Purpose {

    FEATURE_TESTING("Feature_Testing"),
    UPGRADE_TESTING("Upgrade_Testing"),
    AUTOMATION("CI_Automation"),
    REGRESSION("Regression"),
    DEVELOPMENT("Development"),
    SYSTEM_TESTING("System_Testing"),
    PERFORMANCE("Performance");


    private final String purpose;

    private static Map<String,Purpose> map = new HashMap<>();

    static{
        map.put("Feature_Testing",FEATURE_TESTING);
        map.put("Upgrade_Testing",UPGRADE_TESTING);
        map.put("CI_Automation",AUTOMATION);
        map.put("Regression",REGRESSION);
        map.put("Development",DEVELOPMENT);
        map.put("System_Testing",SYSTEM_TESTING);
        map.put("Performance",PERFORMANCE);
    }

    Purpose(String purpose) {
        this.purpose = purpose;
    }

    public String getVal() {
        return this.purpose;
    }

    public static Purpose getPurpose(String purpose){
        return map.get(purpose);
    }
}
