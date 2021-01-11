import { Component, OnInit, } from '@angular/core';
import {DatePipe} from '@angular/common';
import { FormBuilder,Validators,FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

import { ReservationService } from '../../services/reservation.service';
import { ReseveAppliancePayload } from '../../models/reserve-appliance-payload';
import { NbDateService } from '@nebular/theme';

import {
  NbComponentStatus,
  NbGlobalPhysicalPosition,
  NbToastrService,
} from '@nebular/theme';


@Component({
  selector: 'ngx-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {

  isInProgress = false;
  minDate: Date;
  minEndDate: Date;
  reseveAppliancePayload:ReseveAppliancePayload;
  releaseAppliancePayload:ReseveAppliancePayload;
  
  allPurpose=new Array();

  allUoms=new Array();
  selectedUom="";

  allAvailableAppliance=new Array();
  allAvailableApplianceByUom=new Array();

  allReservedAppliance=new Array();
  allReservedApplianceByUom=new Array();

  askForAcm=false;

  reserveApplianceForm:FormGroup;
  releaseApplianceForm:FormGroup;

  constructor(private reservationService : ReservationService,private formBuilder: FormBuilder,private toastrService: NbToastrService,private router: Router,protected dateService: NbDateService<Date>,public datepipe: DatePipe) { 

    this.minDate = this.dateService.addDay(this.dateService.today(),0);
    this.minEndDate =  this.minDate;
    this.reseveAppliancePayload = {
      assigneeName:'',
      assigneeEmail:'',
      purpose:'',
      applianceName:'',
      startDate:'',
      endDate:'',
      acmIp:'',
      acmPassword:''
    }

    this.releaseAppliancePayload = {
      assigneeName:'',
      assigneeEmail:'',
      purpose:'',
      applianceName:'',
      startDate:'',
      endDate:'',
      acmIp:'',
      acmPassword:''
    }

  }

  ngOnInit(): void {
    
    
    this.getAllPurpose();
    this.getAllUoms();
    this.getAllAvailableAppliance();
    this.getAllReservedAppliance();

    this.reserveApplianceForm = this.formBuilder.group({
      assigneeName:[ null, Validators.required ],
      assigneeEmail:[ null, Validators.required ],
      purpose:[null, Validators.required ],
      uomName:["", Validators.required ],
      availableApplianceName:['', Validators.required ],
      startDate:[new Date(), Validators.required],
      endDate:[new Date(), Validators.required],
      acmIp:[ null, Validators.required ],
      acmPassword:[ null, Validators.required ],
      
   });

   this.releaseApplianceForm = this.formBuilder.group({
    reservedApplianceName:[ "", Validators.required ],
    uomName:["", Validators.required ],
    assigneeName:[ null, Validators.required ],
    assigneeEmail:[ null, Validators.required ],
    purpose:[null, Validators.required ],
    startDate:[new Date(), Validators.required],
    endDate:[new Date(), Validators.required],
   });


  }


  getAllUoms(): void {
    this.reservationService.getAllUoms().subscribe(data => {
      console.log('fetched all appliance UoMs');
      // console.log(data);
      this.allUoms = data;
      if(data.length != 0){
        this.reserveApplianceForm.patchValue({uomName:data[0]});
        this.releaseApplianceForm.patchValue({uomName:data[0]});
        this.selectedUom = data[0];
      }
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance UoMs');
      } else {
        console.error('failed to get all appliance UoMs');
      }
    });
  }

  getAllPurpose(): void {
    this.reservationService.getAllPurpose().subscribe(data => {
      console.log('fetched all purpose');
      // console.log(data);
      this.allPurpose = data;
      if(data.length != 0){
        this.reserveApplianceForm.patchValue({purpose:data[0]});
      }
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance purpose');
      } else {
        console.error('failed to get all appliance purpose');
      }
    });
  }

  getAllReservedAppliance(): void {
    this.reservationService.getAllReservedAppliance().subscribe(data => {
      console.log('fetched all reserved Appliances ');
      // console.log(data);
      this.allReservedAppliance = data;
      this.uomChangedReserved(this.selectedUom)
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all reserved Appliances');
      } else {
        console.error('failed to get all reserved Appliances');
      }
    });
  }

  getAllAvailableAppliance(): void {
    this.reservationService.getAllAvailableAppliance().subscribe(data => {
      console.log('fetched all available Appliances ');
      // console.log(data);
      this.allAvailableAppliance = data;
      for (let i = 0; i < data.length; i++) {
        if(data[i]["uomName"] === this.selectedUom){
          this.allAvailableApplianceByUom.push(data[i]);
        }
      }
      this.uomChanged(this.selectedUom)
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all avialable Appliances');
      } else {
        console.error('failed to get all avialable Appliances');
      }
    });
  }

  
  reserve():void{
    this.isInProgress = true;
    this.reseveAppliancePayload.assigneeName = this.reserveApplianceForm.get('assigneeName').value;
    this.reseveAppliancePayload.assigneeEmail = this.reserveApplianceForm.get('assigneeEmail').value;
    this.reseveAppliancePayload.purpose = this.reserveApplianceForm.get('purpose').value;
    this.reseveAppliancePayload.applianceName = this.reserveApplianceForm.get('availableApplianceName').value;
    this.reseveAppliancePayload.startDate = this.datepipe.transform(this.reserveApplianceForm.get('startDate').value, 'yyyy-MM-dd');
    this.reseveAppliancePayload.endDate = this.datepipe.transform(this.reserveApplianceForm.get('endDate').value, 'yyyy-MM-dd');
    this.reseveAppliancePayload.acmIp = this.reserveApplianceForm.get('acmIp').value;
    this.reseveAppliancePayload.acmPassword = this.reserveApplianceForm.get('acmPassword').value;
    
    console.log(this.reseveAppliancePayload);

    this.reservationService.reserveAppliance(this.reseveAppliancePayload).subscribe(data => {
        console.log('appliance reserved');
        console.log(data);
        this.showToast('primary',data.message,'Success');
        this.isInProgress = false;
        this.ngOnInit();
        // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        //   this.router.navigate(['reservation']);
        // }); 
    }, error => {
      console.log(error);
      this.showToast('danger',error.error.message,'Failure');
        console.error('failed to reserve an appliance');
        this.isInProgress = false;
    });
  }

  release():void{
    this.isInProgress = true;
    this.releaseAppliancePayload.applianceName = this.releaseApplianceForm.get('reservedApplianceName').value;
    console.log(this.releaseAppliancePayload);
    this.reservationService.releaseAppliance(this.releaseAppliancePayload).subscribe(data => {
      console.log('appliance released',data);
      this.showToast('primary',data.message,'Success');
      this.isInProgress = false;
      this.ngOnInit();
      // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      //   this.router.navigate(['reservation']);
      // }); 
    }, error => {
      console.log(error);
      this.showToast('danger',error.error.message,'Failure');
      console.error('failed to release an appliance');
      this.isInProgress = false;
    });
  }

  uomChanged(event):void{
    console.log("uom changed");
    this.reserveApplianceForm.get("availableApplianceName").setValue("");
    this.askForAcm=false;
    this.allAvailableApplianceByUom = new Array();
    for (let i = 0; i < this.allAvailableAppliance.length; i++) {
      if(this.allAvailableAppliance[i]["uomName"] === event){
        this.allAvailableApplianceByUom.push(this.allAvailableAppliance[i]);
      }
    }
    if(this.allAvailableApplianceByUom.length > 0){
      setTimeout(() => { 
        this.reserveApplianceForm.get("availableApplianceName").setValue(this.allAvailableApplianceByUom[0]["applianceName"]); 
      },0);
      
      this.askForAcm = this.allAvailableApplianceByUom[0]["canBeShared"]
    }
    
  }
  uomChangedReserved(event):void{
    console.log("reserved uom changed");
    // console.log(event);
    this.releaseApplianceForm.reset();
    this.releaseApplianceForm.patchValue({uomName:event});
    this.allReservedApplianceByUom = new Array();
    for (let i = 0; i < this.allReservedAppliance.length; i++) {
      if(this.allReservedAppliance[i]["uomName"] == event.trim()){
        this.allReservedApplianceByUom.push(this.allReservedAppliance[i]);
      }
      
    }
    if(this.allReservedApplianceByUom.length > 0){
      var appliance = this.allReservedApplianceByUom[0];
      // console.log(appliance);
      setTimeout(() => {
        this.releaseApplianceForm.patchValue({reservedApplianceName:appliance["applianceName"]});
      }, 0);

      this.releaseApplianceForm.patchValue({assigneeName:appliance["assignee"]});
      this.releaseApplianceForm.patchValue({assigneeEmail:appliance["assigneeEmail"]});
      this.releaseApplianceForm.patchValue({purpose:appliance["purpose"]});
      this.releaseApplianceForm.patchValue({startDate:appliance["startDate"]});
      this.releaseApplianceForm.patchValue({endDate:appliance["endDate"]});
    }
    
  }

  applianceChangedReservation(event):void{
    console.log(event);
    for (let i = 0; i < this.allAvailableAppliance.length; i++) {
      if(this.allAvailableAppliance[i]["applianceName"] == event){
        this.askForAcm = this.allAvailableAppliance[i]["canBeShared"];
      }
    }
  }

  applianceChangedRelease(event):void{
    console.log(event);
    for (let i = 0; i < this.allReservedApplianceByUom.length; i++) {
      if(this.allReservedApplianceByUom[i]["applianceName"] == event){
        this.releaseApplianceForm.patchValue({assigneeName:this.allReservedApplianceByUom[i]["assignee"]});
        this.releaseApplianceForm.patchValue({assigneeEmail:this.allReservedApplianceByUom[i]["assigneeEmail"]});
        this.releaseApplianceForm.patchValue({purpose:this.allReservedApplianceByUom[i]["purpose"]});
        this.releaseApplianceForm.patchValue({startDate:this.allReservedApplianceByUom[i]["startDate"]});
        this.releaseApplianceForm.patchValue({endDate:this.allReservedApplianceByUom[i]["endDate"]});
      }
    }
  }

  startDateChanged(event):void{
    console.log(event);
    this.minEndDate= new Date(event);
    
    this.reserveApplianceForm.get("endDate").setValue(this.reserveApplianceForm.get("startDate").value);

  }

  private showToast(type: NbComponentStatus, body: string,title:string) {
    const config = {
      status: type,
      destroyByClick: true,
      duration: 5000,
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
