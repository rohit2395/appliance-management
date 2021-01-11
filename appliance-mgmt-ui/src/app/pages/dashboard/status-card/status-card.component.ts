import { Component, Input, OnInit, } from '@angular/core';

@Component({
  selector: 'ngx-status-card',
  styleUrls: ['./status-card.component.scss'],
  templateUrl: './status-card.component.html'
  ,
})

export class StatusCardComponent implements OnInit {
     
  @Input() title: string;
  @Input() type: string;
  @Input() total: number;
  @Input() available: number;
  @Input() reserved: number;
  
  
  ngOnInit(): void {
    // console.log(this.title);
    // console.log(this.type);
    // console.log(this.total);
    // console.log(this.available);
    // console.log(this.reserved);
  }
 
  navigate(){
  }

  

}
