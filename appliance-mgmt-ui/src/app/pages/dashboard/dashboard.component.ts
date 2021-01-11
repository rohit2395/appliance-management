import { Component, OnDestroy ,OnInit} from '@angular/core';
import { ApplianceCount } from './appliance-count';
import { DashboardService } from '../../services/dashboard.service';

interface CardSettings {
  title: string;
  type: string;
  total:number;
  available:number;
  reserved:number;
}

@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnDestroy,OnInit {

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
    type: 'fi',
    total:0,
    available:0,
    reserved:0
  };

  model4x: CardSettings = {
    title: '4400 & 4400s',
    type: 'fi',
    total:0,
    available:0,
    reserved:0
  };
  model5x: CardSettings = {
    title: '5x00',
    type: 'fi',
    total:0,
    available:0,
    reserved:0
  };
  model8x: CardSettings = {
    title: '8x00',
    type: 'fi',
    total:0,
    available:0,
    reserved:0
  };

  activityData = [];

  constructor(private dashboardService : DashboardService) {
    this.applianeCount = {
      totalAppliances:0,
        totalAvailableAppliances:0,
        totalReservedAppliaces:0,
        total4x00:0,
        totalAvailable4x00:0,
        totalReserved4x00:0,
        total5x00:0,
        totalAvailable5x00:0,
        totalReserved5x00:0,
        total8x00:0,
        totalAvailable8x00:0,
        totalReserved8x00:0
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

      this.applianceCard.total = this.applianeCount.totalAppliances;
      this.applianceCard.available = this.applianeCount.totalAvailableAppliances;
      this.applianceCard.reserved = this.applianeCount.totalReservedAppliaces;
      
      this.model4x.total = this.applianeCount.total4x00;
      this.model4x.available = this.applianeCount.totalAvailable4x00;
      this.model4x.reserved = this.applianeCount.totalReserved4x00;

      this.model5x.total = this.applianeCount.total5x00;
      this.model5x.available = this.applianeCount.totalAvailable5x00;
      this.model5x.reserved = this.applianeCount.totalReserved5x00;

      this.model8x.total = this.applianeCount.total8x00;
      this.model8x.available = this.applianeCount.totalAvailable8x00;
      this.model8x.reserved = this.applianeCount.totalReserved8x00;
      
      
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
