import { Component, OnInit,Inject} from '@angular/core';
import { AppliancesService } from '../../services/appliances.service';
import {MatDialog} from '@angular/material/dialog';
import { ApplianceConfiguration } from './appliance-configuration/apliance-configuraiton.component';
import { Overlay } from '@angular/cdk/overlay';

@Component({
  selector: 'ngx-appliances',
  templateUrl: './appliances.component.html',
  styleUrls: ['./appliances.component.scss']
})
export class AppliancesComponent implements OnInit {

  allApplianceSetting = {
    actions: false,
    columns: {
      id: {
        title: 'ID',
        type: 'number',
        width:'5%',
        valuePrepareFunction:(value,row,cell) =>{
          return cell.row.index+1;
        }
      },
      applianceName: {
        title: 'Appliance Name',
        type: 'string',
      },
      applianceModel: {
        title: 'Appliance Model',
        type: 'string',
        width:'15%',
      },
      uomName: {
        title: 'UoM Name',
        type: 'string',
      },
      location: {
        title: 'Location',
        type: 'string',
      },
      generation: {
        title: 'Generation',
        type: 'string',
      },
      // appliancePossessionStatus: {
      //   title: 'Availability',
      //   type: 'string',
      //   width:'5%',
      // },
      // canBeShared:{
      //   title: 'Can be shared?',
      //   type: 'string',
      //   width:'5%',
      //   valuePrepareFunction: (canBeShared) => {
      //     if (canBeShared)
      //       return "YES";
      //     else
      //       return "NO";
      //   }
      // },
      
      assignee: {
        title: 'Assignee',
        type: 'number',
        width:'8%',
      },
      purpose: {
        title: 'Purpose',
        type: 'string',
        width:'8%',
      },
    },
    noDataMessage:'No data found!',
    pager:{
      perPage:10
    }
  };

  reservedApplianceSetting = {
    actions: false,
    columns: {
      id: {
        title: 'ID',
        type: 'number',
        width:'5%',
        valuePrepareFunction:(value,row,cell) =>{
          return cell.row.index+1;
        }
      },
      applianceName: {
        title: 'Appliance Name',
        type: 'string',
      },
      applianceModel: {
        title: 'Appliance Model',
        type: 'string',
        width:'8%',
      },
      uomName: {
        title: 'UoM Name',
        type: 'string',
      },
      canBeShared:{
        title: 'Can be shared?',
        type: 'string',
        width:'5%',
        valuePrepareFunction: (canBeShared) => {
          if (canBeShared)
            return "YES";
          else
            return "NO";
        }
      },
      purpose: {
        title: 'Purpose',
        type: 'string',
      },
      assignee: {
        title: 'Assignee',
        type: 'string',
      },
      assigneeEmail: {
        title: 'Assignee Email',
        type: 'string',
      },
      startDate: {
        title: 'Start Date',
        type: 'string',
      },
      endDate: {
        title: 'End Date',
        type: 'string',
      },
    },
    noDataMessage:'No data found!'
  };

  availableApplianceSetting = {
    actions: false,
    columns: {
      id: {
        title: 'ID',
        type: 'number',
        width:'5%',
        valuePrepareFunction:(value,row,cell) =>{
          return cell.row.index+1;
        }
      },
      applianceName: {
        title: 'Appliance Name',
        type: 'string',
      },
      applianceModel: {
        title: 'Appliance Model',
        type: 'string',
      },
      uomName: {
        title: 'UoM Name',
        type: 'string',
      },
      canBeShared:{
        title: 'Can be shared?',
        type: 'string',
        width:'8%',
        valuePrepareFunction: (canBeShared) => {
          if (canBeShared)
            return "YES";
          else
            return "NO";
          }
      }
    },
    noDataMessage:'No data found!'
  };

  allAppliancedata = [];
  availableAppliancedata = [];
  reservedAppliancedata = [];

  constructor(private appliancesService : AppliancesService,public dialog: MatDialog,public overlay: Overlay) { 
    
  }

  ngOnInit(): void {
    this.getAllApplances();
  }

  getAllApplances(): void {
    this.appliancesService.getAllAppliances().subscribe(appliances => {
      console.log('fetched all appliances');
      // console.log(appliances);
      // var a = [];
      // var r = [];
      this.allAppliancedata = appliances;
      this.availableAppliancedata = [];
      this.reservedAppliancedata = [];
      for(let appliance of appliances){
        if(appliance.appliancePossessionStatus === "Available"){
          // a.push(appliance);
          this.availableAppliancedata.push(appliance);
        }else{
          // r.push(appliance);
          this.reservedAppliancedata.push(appliance)
        }
      }
      // for (let i = 0; i < data.length; i++) {
        
      //   if(data[i].appliancePossessionStatus === "Available"){
      //     a.push(data[i]);
      //   }else{
      //     r.push(data[i]);
      //   }
      // }
      
      // this.allAppliancedata = appliances;
      // this.availableAppliancedata = a;
      // this.reservedAppliancedata = r;
    }, error => {
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
      height: 'auto',
      width: '65%',
      data: {
        applianceDetails: applianceData
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllApplances();
    });
  }

}
