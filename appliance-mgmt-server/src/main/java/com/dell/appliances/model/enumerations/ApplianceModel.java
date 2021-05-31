package com.dell.appliances.model.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum ApplianceModel {


    MODEL_4400S("DP4400S"),
    MODEL_4400("DP4400"),
    MODEL_5300("DP5300"),
    MODEL_5800("DP5800"),
    MODEL_5900("DP5900"),
    MODEL_8300("DP8300"),
    MODEL_8400("DP8400"),
    MODEL_8800("DP8800"),
    MODEL_8900("DP8900"),
    LOCAL("Local Setup ESXi");

    private final String model;
    private static Map<String,ApplianceModel> map = new HashMap<>();

    static{
        map.put("DP4400S",MODEL_4400S);
        map.put("DP4400",MODEL_4400);
        map.put("DP5300",MODEL_5300);
        map.put("DP5800",MODEL_5800);
        map.put("DP5900",MODEL_5900);
        map.put("DP8300",MODEL_8300);
        map.put("DP8400",MODEL_8400);
        map.put("DP8800",MODEL_8800);
        map.put("DP8900",MODEL_8900);
        map.put("Local Setup ESXi",LOCAL);
    }
    ApplianceModel(String model) {
        this.model = model;
    }

    public String getVal() {
        return this.model;
    }

    public static ApplianceModel getApplianceModel(String model){
        return map.get(model);
    }
}
