import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ɵangular_packages_platform_browser_platform_browser_g } from '@angular/platform-browser';
import { ApplianceApi } from 'app/appliance-reservation-api';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class LicenseService {

  constructor(private httpClient: HttpClient) { }

  uploadLicense(formData:FormData):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_UPLOAD_LICENSE,formData);
  }

  getAvailableLicenses(applianceName:String):Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_GET_LICENSE_LIST+applianceName);
  }

  downloadAllLicenses(applianceName:String):Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_DOWNLOAD_LICENSE+applianceName,{
      observe: "response" as 'body',
      responseType: 'blob'
    });
  }

  downloadSingleLicenses(applianceName:String,fileName:String):Observable<any>{
    return this.httpClient.get(ApplianceApi.APPLIANCE_API_DOWNLOAD_LICENSE+applianceName+'/'+fileName,{
      observe: "response" as 'body',
      responseType: 'blob'
    });
  }

  downloadMultipleLicenses(applianceName:String,fileNames:String[]):Observable<any>{
    return this.httpClient.post(ApplianceApi.APPLIANCE_API_DOWNLOAD_LICENSE+applianceName,fileNames,{
      observe: "response" as 'body',
      responseType: 'blob'
    });
  }

}
