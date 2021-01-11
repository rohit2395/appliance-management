import { Component, OnInit } from '@angular/core';

import { AbstractControl,FormBuilder,Validators,FormGroup } from '@angular/forms';


import {
  NbComponentStatus,
  NbGlobalPhysicalPosition,
  NbToastrService,
} from '@nebular/theme';


import { RegisterService } from '../../services/register.service';
import { RegisterDetailsPayload } from '../../models/register-details-payload';

@Component({
  selector: 'ngx-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})


export class RegisterComponent implements OnInit {

  isInProgress = false;

  registerDetailsPayload : RegisterDetailsPayload;

  allModels: [""];

  allUoms: [""];
  allLocations: [""];
  allGenerations: [""];
  allApplianceNames: [""];
  allPurpose: [""];
  selectedAppliance:"";

  addApplianceForm:FormGroup;
  updateApplianceForm:FormGroup;
  deleteApplianceForm:FormGroup;


  constructor(private registerService : RegisterService,private formBuilder: FormBuilder,private toastrService: NbToastrService) { 
   
    this.registerDetailsPayload = {
      applianceName:'',
      applianceModel:'',
      location:'',
      generation:'',
      canBeShared:'',
      uomName:'',
      assignee:'',
      purpose:'',
      configuration:''
    }


  }

  ngOnInit(): void {

    this.addApplianceForm = this.formBuilder.group({
      applianceName:[ null, Validators.required ],
      applianceModel:[ null, Validators.required ],
      location:[ null, Validators.required ],
      generation:[ null, Validators.required ],
      canBeShared:[ true, Validators.required ],
      uomName:[ null, Validators.required ],
      assignee:[ null, Validators.required ],
      purpose:[ null, Validators.required ],
      configuration:[ "", Validators.required ]
    });

    this.updateApplianceForm = this.formBuilder.group({
      applianceName:[ null, Validators.required ],
      applianceModel:[ null, Validators.required ],
      location:[ null, Validators.required ],
      generation:[ null, Validators.required ],
      canBeShared:[ true, Validators.required ],
      uomName:[ null, Validators.required ],
      assignee:[ null, Validators.required ],
      purpose:[ null, Validators.required ],
      configuration:[ "", Validators.required ]
    });

    
    this.deleteApplianceForm = this.formBuilder.group({
      applianceName:[ null, Validators.required ],
      applianceModel:[ null, Validators.required ],
      location:[ null, Validators.required ],
      generation:[ null, Validators.required ],
      canBeShared:[ true, Validators.required ],
      uomName:[ null, Validators.required ],
      configuration:[ "", Validators.required ],
      assignee:[ "", Validators.required ]
    });

    
    this.getAllModels();
    this.getAllUoms();
    this.getAllLocation();
    this.getAllGeneration();
    this.getAllAppliances();
    this.getAllPurpose();
  }

  get canBeShared(): AbstractControl {
    return this.addApplianceForm.get('canBeShared');
  }

  getAllPurpose():void{
    this.registerService.getAllPurpose().subscribe(data => {
      console.log('fetched all purpose');
      // console.log(data);
      this.allPurpose = data;
      if(data.length != 0){
        this.addApplianceForm.patchValue({purpose:data[0]});
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

  getAllModels(): void {
    this.registerService.getAllModels().subscribe(data => {
      console.log('fetched all appliance models');
      // console.log(data);
      this.allModels = data;
      this.addApplianceForm.patchValue({applianceModel:data[0]});
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance models');
      } else {
        console.error('failed to get all appliance models');
      }
    });
  }

  getAllUoms(): void {
    this.registerService.getAllUoms().subscribe(data => {
      console.log('fetched all appliance names');
      // console.log(data);
      this.allUoms = data;
      this.addApplianceForm.patchValue({uomName:data[0]});
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all UoMs');
      } else {
        console.error('failed to get all UoMs');
      }
    });
  }

  getAllAppliances(): void {
    this.registerService.getAllAppliancesNames().subscribe(data => {
      console.log('fetched all appliance names');
      // console.log(data);
      this.allApplianceNames = data;
      this.selectedAppliance = data[0];
      this.updateUpdateForm(data[0]);
      this.updateDeleteForm(data[0]);
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all UoMs');
      } else {
        console.error('failed to get all UoMs');
      }
    });
  }

  getAllLocation(): void {
    this.registerService.getAllLocation().subscribe(data => {
      console.log('fetched all appliance locations');
      // console.log(data);
      this.allLocations = data;
      this.addApplianceForm.patchValue({location:data[0]});
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance locations');
      } else {
        console.error('failed to get all appliance locations');
      }
    });
  }

  getAllGeneration(): void {
    this.registerService.getAllGeneration().subscribe(data => {
      console.log('fetched all appliance generations');
      // console.log(data);
      this.allGenerations = data;
      this.addApplianceForm.patchValue({generation:data[0]});
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance generations');
      } else {
        console.error('failed to get all appliance generations');
      }
    });
  }

  register():void{
    this.isInProgress = true;
    this.registerDetailsPayload = new RegisterDetailsPayload();
    this.registerDetailsPayload.applianceName = this.addApplianceForm.get('applianceName').value;
    this.registerDetailsPayload.applianceModel = this.addApplianceForm.get('applianceModel').value;
    this.registerDetailsPayload.location = this.addApplianceForm.get('location').value;
    this.registerDetailsPayload.generation = this.addApplianceForm.get('generation').value;
    this.registerDetailsPayload.canBeShared = this.addApplianceForm.get('canBeShared').value;
    this.registerDetailsPayload.uomName = this.addApplianceForm.get('uomName').value;
    this.registerDetailsPayload.assignee = this.addApplianceForm.get('assignee').value;
    this.registerDetailsPayload.purpose = this.addApplianceForm.get('purpose').value;
    this.registerDetailsPayload.configuration = this.addApplianceForm.get('configuration').value;
    
    console.log(this.registerDetailsPayload);

    this.registerService.addAppliance(this.registerDetailsPayload).subscribe(data => {
      if(data.status == '201'){
        console.log('appliance added',data);
        this.showToast('primary',data.message,'Success');
        this.ngOnInit();
      }
      else{
        console.error('failed to add',data);
        this.showToast('primary','Failed to add new appliance!','Success');
      }
      this.isInProgress = false;
    }, error => {
      console.log(error);
      this.showToast('danger',error.error.message,'Failure');
      if(error.status == 500){
        console.error('failed to add');
      }else{
        console.error('failed to add');
      }
      this.isInProgress = false;
    });
  }

  update():void{
    this.isInProgress = true;
    this.registerDetailsPayload = new RegisterDetailsPayload();
    this.registerDetailsPayload.applianceName = this.updateApplianceForm.get('applianceName').value;
    this.registerDetailsPayload.applianceModel = this.updateApplianceForm.get('applianceModel').value;
    this.registerDetailsPayload.location = this.updateApplianceForm.get('location').value;
    this.registerDetailsPayload.generation = this.updateApplianceForm.get('generation').value;
    this.registerDetailsPayload.canBeShared = this.updateApplianceForm.get('canBeShared').value;
    this.registerDetailsPayload.uomName = this.updateApplianceForm.get('uomName').value;
    this.registerDetailsPayload.assignee = this.updateApplianceForm.get('assignee').value;
    this.registerDetailsPayload.purpose = this.updateApplianceForm.get('purpose').value;
    this.registerDetailsPayload.configuration = this.updateApplianceForm.get('configuration').value;
    
    console.log(this.registerDetailsPayload);

    this.registerService.updateAppliance(this.registerDetailsPayload).subscribe(data => {
      if(data.status == '201'){
        console.log('appliance updated',data);
        this.showToast('primary',data.message,'Success');
        this.ngOnInit();
      }
      else{
        console.error('failed to update',data);
        this.showToast('primary','Failed to update appliance!','Success');
      }
      this.isInProgress = false;
    }, error => {
      console.log(error);
      this.showToast('danger',error.error.message,'Failure');
      if(error.status == 500){
        console.error('failed to update');
      }else{
        console.error('failed to update');
      }
      this.isInProgress = false;
    });
  }

  delete():void{
    this.isInProgress = true;
    this.registerDetailsPayload = new RegisterDetailsPayload();
    this.registerDetailsPayload.applianceName = this.deleteApplianceForm.get('applianceName').value;
    console.log(this.registerDetailsPayload);
    console.log(this.deleteApplianceForm);

    this.registerService.deleteAppliance(this.registerDetailsPayload).subscribe(data => {
      if(data.status == '201'){
        console.log('appliance deleted',data);
        this.showToast('primary',data.message,'Success');
        this.ngOnInit();
      }
      else{
        console.error('failed to delete',data);
        this.showToast('primary','Failed to delete appliance!','Success');
      }
      this.isInProgress = false;
    }, error => {
      console.log(error);
      this.showToast('danger',error.error.message,'Failure');
      if(error.status == 500){
        console.error('failed to delete');
      }else{
        console.error('failed to delete');
      }
      this.isInProgress = false;
    });
  }

  updateUpdateForm(applianceName):void{
    //updateing upadte form
    console.log('Updaing update form',applianceName);
    this.registerService.getApplianceDetail(applianceName).subscribe(data => {
      console.log('appliance details:',data);
      this.updateApplianceForm.patchValue({applianceName:data.applianceName})
      this.updateApplianceForm.patchValue({applianceModel:data.applianceModel})
      this.updateApplianceForm.patchValue({location:data.location})
      this.updateApplianceForm.patchValue({generation:data.generation})
      this.updateApplianceForm.patchValue({canBeShared:data.canBeShared})
      this.updateApplianceForm.patchValue({uomName:data.uomName})
      this.updateApplianceForm.patchValue({assignee:data.assignee})
      this.updateApplianceForm.patchValue({purpose:data.purpose})
      this.updateApplianceForm.patchValue({configuration:data.configuration})
    }, error => {
      console.log(error);
      if(error.status == 500){
        console.error('failed to get appliance details');
      }else{
        console.error('failed to get appliance details');
      }
    });

  }

  updateDeleteForm(applianceName):void{
    //updateing delete form
    console.log('Updating delete form',applianceName);
    this.deleteApplianceForm.patchValue({applianceName:applianceName})
    this.registerService.getApplianceDetail(applianceName).subscribe(data => {
      console.log('appliance details:',data);
      this.deleteApplianceForm.patchValue({applianceModel:data.applianceModel})
      this.deleteApplianceForm.patchValue({location:data.location})
      this.deleteApplianceForm.patchValue({generation:data.generation})
      this.deleteApplianceForm.patchValue({canBeShared:data.canBeShared})
      this.deleteApplianceForm.patchValue({uomName:data.uomName})
      this.deleteApplianceForm.patchValue({assignee:data.assignee})
    }, error => {
      console.log(error);
      if(error.status == 500){
        console.error('failed to get appliance details');
      }else{
        console.error('failed to get appliance details');
      }
    });
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
