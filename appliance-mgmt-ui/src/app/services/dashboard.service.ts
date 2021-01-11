import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApplianceApi  } from '../appliance-reservation-api';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private httpClient: HttpClient) { }

  getAllApplianceCount():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_COUNT);
  }
  getAllActivity():Observable<any>{
    return this.httpClient.get(ApplianceApi.API_GET_ACTIVITY);
  }
}
