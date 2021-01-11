package com.dell.appliances.model.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum Generation {


    GEN_13("Gen 13"),
    GEN_14("Gen 14");


    private final String gen;

    private static final Map<String,Generation> map = new HashMap<>();

    static{
        map.put("Gen 13",GEN_13);
        map.put("Gen 14",GEN_14);
    }

    Generation(String gen) {
        this.gen = gen;
    }

    public String getVal() {
        return this.gen;
    }

    public static Generation getGeneration(String gen){
        return map.get(gen);
    }
}
