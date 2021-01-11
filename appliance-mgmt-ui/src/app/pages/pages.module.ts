import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { 
  NbDatepickerModule
} from '@nebular/theme';
import { ApplianceConfiguration } from './appliances/appliance-configuration/apliance-configuraiton.component';
import { AppliancesComponent } from './appliances/appliances.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StatusCardComponent } from './dashboard/status-card/status-card.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import { ReservationComponent } from './reservation/reservation.component';



@NgModule({
  declarations: [
    AppliancesComponent,
    ApplianceConfiguration,
    DashboardComponent,
    StatusCardComponent,
    RegisterComponent,
    ReservationComponent,
    NotFoundComponent,
  ],
  imports: [
    SharedModule,
    NbDatepickerModule,
  ]
})
export class PagesModule { }
