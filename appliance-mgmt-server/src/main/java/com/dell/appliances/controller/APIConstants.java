package com.dell.appliances.controller;

public interface APIConstants {

    String ROOT = "/api";
    String APPLIANCE = ROOT + "/appliance";
    String GENERAL = ROOT + "/general";
    String AUTH = ROOT + "/auth";
    String RESERVATION = ROOT + "/reservation";
    String FILES = ROOT + "/files";

    //ApplianceController APIs
    String ADD_APPLIANCE = "/add-appliance";
    String UPDATE_APPLIANCE = "/update-appliance";
    String DELETE_APPLIANCE = "/delete-appliance";
    String GET_APPLIANCE_ID = "/get/id/{id}";
    String GET_APPLIANCE_NAME = "/get/name/{name}";
    String GET_APPLIANCES_UOM = "/get/uom/{uomId}";
    String GET_APPLIANCES_ASSIGNEE = "/get/assignee/{assigneeName}";
    String GET_APPLIANCES_ALL = "/getAll";
    String GET_ALL_APPLIANCE_BY_FILTER = "/getAllByFilter";

    //ReservationController APIs
    String RESERVE_APPLIANCE = "/reserve";
    String RELEASE_APPLIANCE = "/release";

    //General APIs
    String GET_ALL_APPLIANCE_MODELS = "/get/models/all";
    String GET_ALL_UOM = "/get/uom/all";
    String GET_ALL_APPLIANCE_NAMES = "/get/appliance-name-all";
    String GET_ALL_APPLIANCE_NAMES_AVAIL = "/get/appliance-name-all/available";
    String GET_ALL_APPLIANCE_NAMES_UNAVAIL = "/get/appliance-name-all/unavailable";
    String GET_ALL_PURPOSE = "/get/purpose/all";
    String GET_ALL_LOCATION = "/get/location/all";
    String GET_ALL_GENERATION = "/get/generation/all";
    String GET_ALL_APPLIANCE_COUNT = "/get/count";
    String GET_ALL_APPLIANCE_COUNT_MODEL = "/get/count/model";
    String GET_ALL_ACTIVITY = "/get/activity/all";
    String TEST_EMAIL = "/test-mail";


    //files APIs
    String UPLOAD_FILES = "/upload";
    String DOWNLOAD_SINGLE_FILE = "/download/{applianceName}/{fileName}";
    String DOWNLOAD_FILES = "/download/{applianceName}";
    String GET_LIST = "/all/{applianceName}";

}
