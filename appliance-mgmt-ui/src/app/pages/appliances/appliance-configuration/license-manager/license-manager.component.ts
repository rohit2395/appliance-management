import { HttpResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NbComponentStatus, NbGlobalPhysicalPosition, NbToastrService } from '@nebular/theme';
import { LicenseService } from 'app/services/license.service';

@Component({
  selector: 'ngx-license-manager',
  templateUrl: './license-manager.component.html',
  styleUrls: ['./license-manager.component.scss']
})
export class LicenseManagerComponent implements OnInit {

  uploadForm: FormGroup;
  allLic:String[];
  licensesAvail:boolean;
  selectedLic:String[];

  isInProgress:boolean;

  filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
  
  constructor(private formBuilder: FormBuilder,@Inject(MAT_DIALOG_DATA) public applianceName: String,private dialogRef: MatDialogRef<LicenseManagerComponent>,private licenseService:LicenseService,private toastrService: NbToastrService) {
    console.log(this.applianceName);
    this.getAllLic();
    this.allLic = [];
    this.selectedLic = [];
    this.licensesAvail = true;
    this.isInProgress = false;
   }

  ngOnInit(): void {
    this.uploadForm = this.formBuilder.group({
      licenses: [''],
      applianceName:this.applianceName
    });
  }



  toggle(event,item){
    if(event){
      console.log("Checked file:"+item);
      this.selectedLic.push(item);
    }else{
      console.log("Unchecked file:"+item);
      const index = this.selectedLic.indexOf(item, 0);
      if (index > -1) {
        this.selectedLic.splice(index, 1);
      }
    }
    console.log("Licenses can be downloaded",this.selectedLic);
  }


  extractFilenameFromHeader(disposition):String{
    if (disposition && disposition.indexOf('attachment') !== -1) {
        var matches = this.filenameRegex.exec(disposition);
        if (matches != null && matches[1]) { 
          var filename = matches[1].replace(/['"]/g, '');
        }
    }
    return filename;
  }

  downloadFile(data,fileName) {
    console.log(data);
    
    const blob = new Blob([data], { type: data.type });
    const url= window.URL.createObjectURL(blob);
    console.log(url);
    var anchor = document.createElement("a");
    anchor.download = fileName;
    anchor.href = url;
    anchor.click();
    // window.open(url);
  }

  downloadAll() {
    console.log("Downloading all Licenses for"+this.applianceName);
    this.isInProgress = true;
    this.licenseService.downloadAllLicenses(this.applianceName).subscribe((response:HttpResponse<any>) => {
      this.isInProgress = false;
      console.log(response);
      console.log(response.headers.getAll('Content-Disposition'));
      var filename = this.extractFilenameFromHeader(response.headers.getAll('Content-Disposition')[0]);
      console.log(filename);
    
      this.downloadFile(response.body,filename);
    },error => {
      this.isInProgress = false;
      console.log(error);
      console.error('failed to download license');
    });
  }

  downloadSelected() {
    console.log("Downloading License(s) for"+this.applianceName);
    this.isInProgress = true;
    if(this.selectedLic.length == 0){
      this.showToast('danger','Please select at least one license to download','Failure');
      this.isInProgress = false;
    }else if(this.selectedLic.length > 1){
      this.licenseService.downloadMultipleLicenses(this.applianceName,this.selectedLic).subscribe((response:HttpResponse<any>) => {
        this.isInProgress = false;
        console.log(response);
        var filename = this.extractFilenameFromHeader(response.headers.getAll('Content-Disposition')[0]);
        console.log(filename);
        this.downloadFile(response.body,filename);
      }, error => {
        this.isInProgress = false;
        console.log(error);
        console.error('failed to upload license');
      });
    }else{
      this.licenseService.downloadSingleLicenses(this.applianceName,this.selectedLic[0]).subscribe((response:HttpResponse<any>) => {
        this.isInProgress = false;
        console.log(response);
        var filename = this.extractFilenameFromHeader(response.headers.getAll('Content-Disposition')[0]);
        console.log(filename);
        this.downloadFile(response.body,filename);
      }, error => {
        this.isInProgress = false;
        console.log(error);
        console.error('failed to upload license');
      });
    }
  }

  upload() {
    console.log("Uploading selected licenses to the server");
    this.isInProgress = true;
    const formData = new FormData();
    for (let i = 0; i < this.uploadForm.get('licenses').value.length; i++) {
      // formData.append(files[i].name, files[i])
      formData.append('files', this.uploadForm.get('licenses').value[i]);
    }
    formData.append('directory',this.uploadForm.get('applianceName').value)
    this.licenseService.uploadLicense(formData).subscribe(async data => {
      this.isInProgress = false;
      console.log(data);
      this.getAllLic();
      this.showToast('primary',data.message,'Success');
    }, error => {
      this.isInProgress = false;
      console.log(error);
      console.error('failed to upload license');
      this.showToast('danger',error.error.message,'Failure');
    });

  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  } 

  getAllLic(){
    console.log("Getting all licenses");
    this.isInProgress = true;
    this.licenseService.getAvailableLicenses(this.applianceName).subscribe(data => {
      this.isInProgress = false;
      console.log(data);
      this.allLic = data;
      this.licensesAvail = true;
      if(data.length <= 0){
        this.licensesAvail = false;
        console.log("License not available!");
      }
    }, error => {
      this.isInProgress = false;
      this.licensesAvail = false;
      console.log(error);
      console.error('failed to get licenses');
    });
    
  }

  onFileSelect(event) {
    if (event.target.files.length > 0) {
        this.uploadForm.get('licenses').setValue(event.target.files);
      }
    }
  

  close():void{
    this.dialogRef.close();
  }

  private showToast(type: NbComponentStatus, body: string,title:string) {
    const config = {
      status: type,
      destroyByClick: true,
      duration: 2000,
      hasIcon: true,
      position: NbGlobalPhysicalPosition.TOP_RIGHT,
      preventDuplicates: false,
    };
    this.toastrService.show(
      body,
      title,
      config);
  }

}
