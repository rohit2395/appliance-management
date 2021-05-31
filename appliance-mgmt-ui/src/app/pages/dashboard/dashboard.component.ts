import { Component, OnDestroy ,OnInit} from '@angular/core';
import { ApplianceCount } from './appliance-count';
import { DashboardService } from '../../services/dashboard.service';
import { Field } from './status-card/fields';
import { RegisterService } from 'app/services/register.service';

interface CardSettings {
  title: String;
  fields:Field[];
}

@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnDestroy,OnInit {

  TOTAL = "Total";
  DP4Xs = "4400s";
  DP4X = "4400";
  DP5x = "5x00";
  DP8x = "8x00";
  LocalEsxi = "Local-ESXi";
  private alive = true;

  activitySettings = {
    actions: false,
    hideHeader:true,
    hideSubHeader:true,
    columns: {
      activity: {
        title: 'Activity',
        type: 'string',
        width:'80%'
      },
      date: {
        title: 'Date',
        type: 'string',
        width:'20%'
      }
    },
    noDataMessage:'No data found!',
    pager:{
      perPage:12
    }
  };

  applianeCount:ApplianceCount;

  applianceCard: CardSettings = {
    title: 'All Appliances',
    fields:[],
  };


  location: CardSettings = {
    title: 'By Location - Appliance',
    fields:[],
  };

  generation: CardSettings = {
    title: 'By Generation',
    fields:[],
  };

  
  locationLocal: CardSettings = {
    title: 'By Location - Local ESXi',
    fields:[],
  };

  activityData = [];

  constructor(private dashboardService : DashboardService,private registerService:RegisterService) {
    this.applianeCount = {
      totalAppliances:0,
      totalAvailableAppliances:0,
      totalReservedAppliaces:0,
      total4x00s:0,
      totalAvailable4x00s:0,
      totalReserved4x00s:0,
      total4x00:0,
      totalAvailable4x00:0,
      totalReserved4x00:0,
      total5x00:0,
      totalAvailable5x00:0,
      totalReserved5x00:0,
      total8x00:0,
      totalAvailable8x00:0,
      totalReserved8x00:0,
      totalLocal:0,
      
      countByLoc:[],
      countByGen:[],
      countByLocEsxi:[]

    }    
  }
  ngOnInit(): void {
    
    this.getAllCount();
    this.getAllActivity();
  }

  ngOnDestroy() {
    this.alive = false;

  }

  getAllCount(): void {
    this.dashboardService.getAllApplianceCount().subscribe(data => {
      console.log('fetched all Appliance count');
      this.applianeCount = data;
      console.log(this.applianeCount);

      this.applianceCard.fields.push({key:this.TOTAL,value:this.applianeCount.totalAppliances});
      this.applianceCard.fields.push({key:this.DP4Xs,value:this.applianeCount.total4x00s});
      this.applianceCard.fields.push({key:this.DP4X,value:this.applianeCount.total4x00});
      this.applianceCard.fields.push({key:this.DP5x,value:this.applianeCount.total5x00});
      this.applianceCard.fields.push({key:this.DP8x,value:this.applianeCount.total8x00});
      // this.applianceCard.fields.push({key:this.LocalEsxi,value:this.applianeCount.totalLocal});

      for(let loc of this.applianeCount.countByLoc){
        this.location.fields.push({key:loc["locName"],value:loc["count"]});
      }

      for(let gen of this.applianeCount.countByGen){
        this.generation.fields.push({key:gen["gen"],value:gen["count"]});
      }

      this.locationLocal.fields.push({key:"Total",value:this.applianeCount.totalLocal});
      for(let loc of this.applianeCount.countByLocEsxi){
        this.locationLocal.fields.push({key:loc["locName"],value:loc["count"]});
      }
      
      
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all Appliance names');
      } else {
        console.error('failed to get all Appliance names');
      }
    });
  }

  getAllActivity(): void {
    this.dashboardService.getAllActivity().subscribe(data => {
      console.log('fetched all activity');
      // console.log(data);
      this.activityData = data;
    }, error => {
      console.log(error);
      if (error.status == 500) {
        console.error('failed to get all activity');
      } else {
        console.error('failed to get all activity');
      }
    });
  }

}
