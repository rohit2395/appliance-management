import { Injectable } from '@angular/core';
import { ReseveAppliancePayload } from '../models/reserve-appliance-payload';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApplianceApi  } from '../appliance-reservation-api';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private httpClient: HttpClient) { }
  
  reserveAppliance(reseveAppliancePayload:ReseveAppliancePayload):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_RESERVE,reseveAppliancePayload);
  }

  releaseAppliance(releaseAppliancePayload:ReseveAppliancePayload):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_RELEASE,releaseAppliancePayload);
  }
  
  getAllUoms():Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_UOM_ALL);
  }

  getAllAvailableAppliance():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_APPLIANCES_ALL_NAMES_AVAILABLE);
  }

  getAllReservedAppliance():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_APPLIANCES_ALL_NAMES_RESERVED);
  }

  getAllPurpose():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_PURPOSE_ALL);
  }

}
