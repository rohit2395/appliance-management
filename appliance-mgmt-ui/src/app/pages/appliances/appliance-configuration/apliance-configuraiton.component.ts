import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { ApplianceData } from './appliance-data';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApplianceDetailsPayload } from 'app/models/appliance-details-payload';
import { FormGroup,FormBuilder, Validators} from '@angular/forms';
import { RegisterService } from 'app/services/register.service';
import { RegisterDetailsPayload } from 'app/models/register-details-payload';
import { NbComponentStatus, NbGlobalPhysicalPosition, NbToastrService } from '@nebular/theme';
import { ReservationService } from 'app/services/reservation.service';
import { LicenseManagerComponent } from './license-manager/license-manager.component';

@Component({
  selector: 'appliance-configuration',
  templateUrl: 'appliance-configuration.component.html',
  styleUrls: ['appliance-configuration.component.scss']
})
export class ApplianceConfiguration implements OnInit {

  isDataUpdated:boolean;
  isInProgress:boolean;
  textareaBg:string;
  textareaColor:string;
  isDisabled:boolean;
  updateConfigurationForm: FormGroup;
  applianceDetails: ApplianceDetailsPayload;
  allPurpose=[""];
  // configuration: string;

  constructor(private reservationService : ReservationService,@Inject(MAT_DIALOG_DATA) public data: ApplianceData,private formBuilder:FormBuilder,private registerService : RegisterService,private toastrService: NbToastrService,public dialog: MatDialog,private dialogRef: MatDialogRef<ApplianceConfiguration>) {
    this.isDataUpdated = false;
    this.applianceDetails = data.applianceDetails;
    console.log(this.applianceDetails);
    
    this.updateConfigurationForm = this.formBuilder.group({
      assignee:[{value:this.applianceDetails.assignee,disabled:this.isDisabled},Validators.required],
      email:[{value:this.applianceDetails.assigneeEmail,disabled:this.isDisabled},Validators.required],
      purpose:[{value:this.applianceDetails.purpose,disabled:this.isDisabled},Validators.required],
      configuration:[{value:this.applianceDetails.configuration,disabled:this.isDisabled},Validators.required]
    });

  }

  ngOnInit(): void {
    this.getAllPurpose();
    this.init();
  }

  getAllPurpose():void{
    this.reservationService.getAllPurpose().subscribe(data => {
      console.log('fetched all purpose');
      // console.log(data);
      this.allPurpose = data;
      // if(data.length != 0){
      //   this.updateConfigurationForm.patchValue({purpose:data[0]});
      // }
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance purpose');
      } else {
        console.error('failed to get all appliance purpose');
      }
    });
  }

  init():void{
    this.isInProgress=false;
    this.isDisabled = true;
    this.textareaBg='#F7F9FC';
    this.textareaColor='#C9D0DC';
    this.updateConfigurationForm.get('assignee').disable();
    this.updateConfigurationForm.get('email').disable();
    this.updateConfigurationForm.get('purpose').disable();
    this.updateConfigurationForm.get('configuration').disable();
  }

  makeConfigurationEditable(): void {
    console.log("You can update");
    this.isDisabled = false;
    this.updateConfigurationForm.get('assignee').enable();
    this.updateConfigurationForm.get('email').enable();
    this.updateConfigurationForm.get('purpose').enable();
    this.updateConfigurationForm.get('configuration').enable();
    this.textareaBg='white';
    this.textareaColor='black';
  }

  openLicenseManager():void{
    console.log("Opening license manager for"+this.applianceDetails.applianceName);
    
    const dialogRef = this.dialog.open(LicenseManagerComponent, {
      height: '95%',
      width: '65%',
      data: this.applianceDetails.applianceName
    });
    
    // dialogRef.afterClosed().subscribe(isDataUpdated => {
    //   console.log(isDataUpdated);
    //   if(isDataUpdated)
    //     this.applyFilter();
    // });
  }

  update(): void {
    this.isInProgress=true;
    this.isDataUpdated=true;
    // this.configuration=this.updateConfigurationForm.get("configuration").value;
    this.applianceDetails.assignee = this.updateConfigurationForm.get("assignee").value;
    this.applianceDetails.assigneeEmail = this.updateConfigurationForm.get("email").value;
    this.applianceDetails.purpose = this.updateConfigurationForm.get("purpose").value;
    this.applianceDetails.configuration = this.updateConfigurationForm.get("configuration").value;
    console.log(this.applianceDetails);
    let registerDetailsPayload = new RegisterDetailsPayload();
    registerDetailsPayload.applianceName = this.applianceDetails.applianceName;
    registerDetailsPayload.assignee = this.applianceDetails.assignee;
    registerDetailsPayload.assigneeEmail = this.applianceDetails.assigneeEmail;
    registerDetailsPayload.purpose = this.applianceDetails.purpose;
    registerDetailsPayload.configuration = this.applianceDetails.configuration;
    this.registerService.updateAppliance(registerDetailsPayload).subscribe(data => {
      if(data.status == '201'){
        console.log('appliance updated',data);
        this.showToast('primary',data.message,'Success');
        this.dialogRef.close(this.isDataUpdated);
      }
      else{
        console.error('failed to update',data);
        this.showToast('primary','Failed to update appliance!','Failure');
      }
    }, error => {
      console.log(error);
      this.showToast('danger',error.error.message,'Failure');
      if(error.status == 500){
        console.error('failed to update');
      }else{
        console.error('failed to update');
      }
    });
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