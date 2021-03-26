import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApplianceApi  } from '../appliance-reservation-api';
import { ApplianceDetailsPayload } from 'app/models/appliance-details-payload';
import { ApplianceFilter } from 'app/pages/appliances/appliance-filter';

@Injectable({
  providedIn: 'root'
})
export class AppliancesService {

  constructor(private httpClient: HttpClient) { }

  getAllAppliances():Observable<ApplianceDetailsPayload[]>{
    return this.httpClient.get<ApplianceDetailsPayload[]>(ApplianceApi.APPLIANCE_API_GET_APPLIANCES_ALL);
  }

  getAllAppliancesByFilter(applianceFilter:ApplianceFilter):Observable<ApplianceDetailsPayload[]>{
    return this.httpClient.post<ApplianceDetailsPayload[]>(ApplianceApi.APPLIANCE_API_GET_APPLIANCES_ALL_BY_FILTER,applianceFilter);
  }

}
