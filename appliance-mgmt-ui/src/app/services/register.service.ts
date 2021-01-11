import { Injectable } from '@angular/core';
import { RegisterDetailsPayload } from '../models/register-details-payload';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApplianceApi  } from '../appliance-reservation-api';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) { }

  addAppliance(applianceDetailsPayload: RegisterDetailsPayload):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_ADD,applianceDetailsPayload);
  }

  updateAppliance(applianceDetailsPayload: RegisterDetailsPayload):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_UPDATE,applianceDetailsPayload);
  }

  deleteAppliance(applianceDetailsPayload: RegisterDetailsPayload):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_DELETE,applianceDetailsPayload);
  }

  getAllModels():Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_MODELS_ALL);
  }

  getAllUoms():Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_UOM_ALL);
  }

  getAllPurpose():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_PURPOSE_ALL);
  }
  
  getAllLocation():Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_LOC_ALL);
  }

  getAllGeneration():Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_GEN_ALL);
  }

  getAllAppliancesNames():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_APPLIANCES_ALL_NAMES_ALL);
  }

  getApplianceDetail(applianceName:string):Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_NAME+applianceName);
  }
}
