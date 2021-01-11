export class ApplianceApi{
	
   public static API_ENDPOINT='api';

    // public static API_ENDPOINT='http://10.241.189.110:8090/api';
    // public static API_ENDPOINT='http://localhost:8090/api';



    //Add new Appliance
    public static APPLIANCE_API_ADD = ApplianceApi.API_ENDPOINT + '/appliance' + "/add-appliance";
    public static APPLIANCE_API_UPDATE = ApplianceApi.API_ENDPOINT + '/appliance' + "/update-appliance";
    public static APPLIANCE_API_DELETE = ApplianceApi.API_ENDPOINT + '/appliance' + "/delete-appliance";
    public static APPLIANCE_API_GET_ID = ApplianceApi.API_ENDPOINT + '/appliance' + "/get/id/";
    public static APPLIANCE_API_GET_NAME = ApplianceApi.API_ENDPOINT + '/appliance' + "/get/name/";
    public static APPLIANCE_API_GET_APPLIANCES_ALL = ApplianceApi.API_ENDPOINT + '/appliance' + "/getAll";
    public static API_GET_APPLIANCES_ALL_NAMES_AVAILABLE = ApplianceApi.API_ENDPOINT + '/appliance' + "/get/appliance-name-all/available";
    public static API_GET_APPLIANCES_ALL_NAMES_RESERVED = ApplianceApi.API_ENDPOINT + '/appliance' + "/get/appliance-name-all/unavailable";

    //General APIs
    public static APPLIANCE_API_GET_MODELS_ALL = ApplianceApi.API_ENDPOINT + '/general' + "/get/models/all";
    public static APPLIANCE_API_GET_UOM_ALL = ApplianceApi.API_ENDPOINT + '/general' + "/get/uom/all";
    public static APPLIANCE_API_GET_LOC_ALL = ApplianceApi.API_ENDPOINT + '/general' + "/get/location/all";
    public static APPLIANCE_API_GET_GEN_ALL = ApplianceApi.API_ENDPOINT + '/general' + "/get/generation/all";
    public static API_GET_APPLIANCES_ALL_NAMES_ALL = ApplianceApi.API_ENDPOINT + '/general' + "/get/appliance-name-all";
    public static API_GET_PURPOSE_ALL = ApplianceApi.API_ENDPOINT + '/general' + "/get/purpose/all";
    public static API_GET_COUNT = ApplianceApi.API_ENDPOINT + '/general' + "/get/count";
    public static API_GET_ACTIVITY = ApplianceApi.API_ENDPOINT + '/general' + "/get/activity/all";

    //Appliance reservation
    public static APPLIANCE_API_RESERVE = ApplianceApi.API_ENDPOINT + '/reservation' + '/reserve';
    public static APPLIANCE_API_RELEASE = ApplianceApi.API_ENDPOINT + '/reservation' + '/release';
}