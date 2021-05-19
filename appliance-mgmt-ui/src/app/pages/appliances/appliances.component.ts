import { Component, OnInit,Inject} from '@angular/core';
import { AppliancesService } from '../../services/appliances.service';
import {MatDialog} from '@angular/material/dialog';
import { ApplianceConfiguration } from './appliance-configuration/apliance-configuraiton.component';
import { Overlay } from '@angular/cdk/overlay';
import { FormControl } from '@angular/forms';
import { ApplianceFilter } from './appliance-filter';
import { RegisterService } from 'app/services/register.service';
import { filter } from 'rxjs/internal/operators/filter';

@Component({
  selector: 'ngx-appliances',
  templateUrl: './appliances.component.html',
  styleUrls: ['./appliances.component.scss']
})
export class AppliancesComponent implements OnInit {

  isSpinner = true;

  modelsOption:String[] = [];
  uomsOption:String[] = [];
  locationOption:String[] = [];
  genOption:String[] = [];
  purposeOption:String[] = [];

  selectedModels = new FormControl(this.modelsOption);
  selectedUoms = new FormControl(this.uomsOption);
  selectedLocations = new FormControl(this.locationOption);
  selectedGens = new FormControl(this.genOption);
  selectedPurposes = new FormControl(this.purposeOption);

  applianceFilter:ApplianceFilter;

  allApplianceSetting = {
    actions: true,
    columns: {
      // id: {
      //   title: 'ID',
      //   type: 'number',
      //   width:'5%',
      //   valuePrepareFunction:(value,row,cell) =>{
      //     return cell.row.index+1;
      //   }
      // },
      applianceName: {
        title: 'Appliance',
        type: 'string',
      },
      applianceModel: {
        title: 'Model',
        type: 'string',
      },
      uomName: {
        title: 'UoM/Team',
        type: 'string',
      },
      location: {
        title: 'Location',
        type: 'string',
      },
      // generation: {
      //   title: 'Generation',
      //   type: 'string',
      // },
      assignee: {
        title: 'Assignee',
        type: 'string',
      },
      // assigneeEmail: {
      //   title: 'Assignee Email',
      //   type: 'string',
      // },
      purpose: {
        title: 'Purpose',
        type: 'string',
      },
    },
    noDataMessage:'No data found!',
    pager:{
      perPage:100
    }
  };

  allAppliancedata = [];

  constructor(private appliancesService : AppliancesService,private registerService : RegisterService,public dialog: MatDialog,public overlay: Overlay) { 
    
    this.getAllModels();
    this.getAllUoms();
    this.getAllLocation();
    this.getAllGeneration();
    this.getAllPurpose();

    this.applianceFilter = {
      selectedModels:this.selectedModels.value,
      selectedUoms:this.selectedUoms.value,
      selectedLocations:this.selectedLocations.value,
      selectedGenerations:this.selectedGens.value,
      selectedPurposes:this.selectedPurposes.value
    };

    this.getAllApplances();
  }

  ngOnInit(): void {
  }

  getAllPurpose():void{
    this.registerService.getAllPurpose().subscribe(data => {
      console.log('fetched all purpose');
      // console.log(data);
      this.purposeOption.push("All");
      this.purposeOption = this.purposeOption.concat(data);
      this.selectedPurposes.setValue(this.purposeOption);
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
      this.modelsOption.push("All");
      this.modelsOption = this.modelsOption.concat(data);
      this.selectedModels.setValue(this.modelsOption);
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
      this.uomsOption.push("All");
      this.uomsOption = this.uomsOption.concat(data);
      this.selectedUoms.setValue(this.uomsOption);
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
      this.locationOption.push("All");
      this.locationOption = this.locationOption.concat(data);
      this.selectedLocations.setValue(this.locationOption);
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
      this.genOption.push("All");
      this.genOption = this.genOption.concat(data);
      this.selectedGens.setValue(this.genOption);
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliance generations');
      } else {
        console.error('failed to get all appliance generations');
      }
    });
  }

  getAllApplances(): void {
    
    this.appliancesService.getAllAppliances().subscribe(appliances => {
      console.log('fetched all appliances');
      this.allAppliancedata = appliances;
      this.isSpinner = false;
    }, error => {
      this.isSpinner = false;
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliances');
      } else {
        console.error('failed to get all appliances');
      }
    });
  }

  getDetails(applianceData):void{
    console.log(applianceData);
    
    const dialogRef = this.dialog.open(ApplianceConfiguration, {
      height: '99%',
      width: '65%',
      data: {
        applianceDetails: applianceData
      }
    });

    dialogRef.afterClosed().subscribe(isDataUpdated => {
      console.log(isDataUpdated);
      if(isDataUpdated)
        this.applyFilter();
    });

  }


  modelsSelected(selectedModel):void{
    if(selectedModel === "All"){
      if(!this.selectedModels.value.includes("All"))
        this.selectedModels.setValue([]);
      else
        this.selectedModels.setValue(this.modelsOption);
    }else{
      this.selectedModels.setValue(this.selectedModels.value.filter(function(item) { return item !== "All"; }));
      var allSelected = true;
      for(let i=1;i<this.modelsOption.length;i++){
        if(!this.selectedModels.value.includes(this.modelsOption[i])){
          allSelected = false;
          break;
        }
      }
      if(allSelected)
        this.selectedModels.setValue(this.modelsOption);
      
    }
    // console.log(this.selectedModels.value.filter(function(item) { return item !== "All"; }));
  }

  uomSelected(selectedUom):void{
    if(selectedUom === "All"){
      if(!this.selectedUoms.value.includes("All"))
        this.selectedUoms.setValue([]);
      else
        this.selectedUoms.setValue(this.uomsOption);
    }else{
      this.selectedUoms.setValue(this.selectedUoms.value.filter(function(item) { return item !== "All"; }));
      var allSelected = true;
      for(let i=1;i<this.uomsOption.length;i++){
        if(!this.selectedUoms.value.includes(this.uomsOption[i])){
          allSelected = false;
          break;
        }
      }
      if(allSelected)
        this.selectedUoms.setValue(this.uomsOption);
      
    }
    // console.log(this.selectedUoms.value.filter(function(item) { return item !== "All"; }));
  }

  locationSelected(selectedLocation):void{
    if(selectedLocation === "All"){
      if(!this.selectedLocations.value.includes("All"))
        this.selectedLocations.setValue([]);
      else
        this.selectedLocations.setValue(this.locationOption);
    }else{
      this.selectedLocations.setValue(this.selectedLocations.value.filter(function(item) { return item !== "All"; }));
      var allSelected = true;
      for(let i=1;i<this.locationOption.length;i++){
        if(!this.selectedLocations.value.includes(this.locationOption[i])){
          allSelected = false;
          break;
        }
      }
      if(allSelected)
        this.selectedLocations.setValue(this.locationOption);
      
    }
    // console.log(this.selectedLocations.value.filter(function(item) { return item !== "All"; }));
  }

  genSelected(selectedGen):void{
    if(selectedGen === "All"){
      if(!this.selectedGens.value.includes("All"))
        this.selectedGens.setValue([]);
      else
        this.selectedGens.setValue(this.genOption);
    }else{
      this.selectedGens.setValue(this.selectedGens.value.filter(function(item) { return item !== "All"; }));
      var allSelected = true;
      for(let i=1;i<this.genOption.length;i++){
        if(!this.selectedGens.value.includes(this.genOption[i])){
          allSelected = false;
          break;
        }
      }
      if(allSelected)
        this.selectedGens.setValue(this.genOption);
      
    }
    // console.log(this.selectedGens.value.filter(function(item) { return item !== "All"; }));
  }

  purposesSelected(selectedPurpose):void{
    if(selectedPurpose === "All"){
      if(!this.selectedPurposes.value.includes("All"))
        this.selectedPurposes.setValue([]);
      else
        this.selectedPurposes.setValue(this.purposeOption);
    }else{
      this.selectedPurposes.setValue(this.selectedPurposes.value.filter(function(item) { return item !== "All"; }));
      var allSelected = true;
      for(let i=1;i<this.purposeOption.length;i++){
        if(!this.selectedPurposes.value.includes(this.purposeOption[i])){
          allSelected = false;
          break;
        }
      }
      if(allSelected)
        this.selectedPurposes.setValue(this.purposeOption);
      
    }
    // console.log(this.selectedPurposes.value.filter(function(item) { return item !== "All"; }));
  }

  applyFilter():void{
    this.isSpinner = true;
    console.log(this.selectedModels.value.filter(function(item) { return item !== "All"; }));
    console.log(this.selectedUoms.value.filter(function(item) { return item !== "All"; }));
    console.log(this.selectedLocations.value.filter(function(item) { return item !== "All"; }));
    console.log(this.selectedGens.value.filter(function(item) { return item !== "All"; }));
    console.log(this.selectedPurposes.value.filter(function(item) { return item !== "All"; }));
    
    this.applianceFilter.selectedModels = this.selectedModels.value.filter(function(item) { return item !== "All"; });
    this.applianceFilter.selectedUoms = this.selectedUoms.value.filter(function(item) { return item !== "All"; });
    this.applianceFilter.selectedLocations = this.selectedLocations.value.filter(function(item) { return item !== "All"; });
    this.applianceFilter.selectedGenerations = this.selectedGens.value.filter(function(item) { return item !== "All"; });
    this.applianceFilter.selectedPurposes = this.selectedPurposes.value.filter(function(item) { return item !== "All"; });
    
    this.appliancesService.getAllAppliancesByFilter(this.applianceFilter).subscribe(appliances => {
      console.log('fetched all appliances');
      this.allAppliancedata = appliances;
      this.isSpinner = false;
    }, error => {
      this.isSpinner = false;
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all appliances');
      } else {
        console.error('failed to get all appliances');
      }
    });
  }

  clearAllFilters():void{
    this.selectedModels.setValue([]);
    this.selectedUoms.setValue([]);
    this.selectedLocations.setValue([]);
    this.selectedGens.setValue([]);
    this.selectedPurposes.setValue([]);
    
    this.applianceFilter.selectedModels = this.selectedModels.value;
    this.applianceFilter.selectedUoms = this.selectedUoms.value;
    this.applianceFilter.selectedLocations = this.selectedLocations.value;
    this.applianceFilter.selectedGenerations = this.selectedGens.value;
    this.applianceFilter.selectedPurposes = this.selectedPurposes.value;

  }
}
