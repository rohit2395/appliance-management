package com.dell.appliances.common;

import com.dell.appliances.controller.ApplianceController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Saturday 12/5/2020
 *
 */
@Component
public class StringUtils {

    public static final Logger LOG = LogManager.getLogger(StringUtils.class);

    public String allCaps(String string){
        return string.toUpperCase();
    }

    public String allSmall(String string){
        return string.toLowerCase();
    }

    public static String extractDate(String dateTime){
        try {
            DateFormat f = new SimpleDateFormat(Constants.DATE_TIME);
            Date d = f.parse(dateTime);
            DateFormat date = new SimpleDateFormat(Constants.DATE);
            return date.format(d);
        }catch(ParseException e){
            LOG.error("Failed to parse string date time",e);
            return "";
        }
    }

}
