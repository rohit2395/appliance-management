package com.dell.appliances.model.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum Location {


    HOPKINTON("Hopkinton"),
    BANGLORE("Banglore"),
    SANTA_CLARA("Santa Clara");



    private final String loc;

    private static final Map<String,Location> map = new HashMap<>();

    static{
        map.put("Hopkinton",HOPKINTON);
        map.put("Banglore",BANGLORE);
        map.put("Santa Clara",SANTA_CLARA);
    }

    Location(String loc) {
        this.loc = loc;
    }

    public String getVal() {
        return this.loc;
    }

    public static Location getLocation(String loc){
        return map.get(loc);
    }
}
