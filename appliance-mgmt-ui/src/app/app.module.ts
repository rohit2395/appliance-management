/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CoreModule } from './@core/core.module';
import { 
  NbMenuModule,
  NbSidebarModule,
  NbToastrModule,
  NbDatepickerModule,
} from '@nebular/theme';

import { ThemeModule } from './@theme/theme.module';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

import { AppliancesService } from './services/appliances.service';
import { DashboardService } from './services/dashboard.service';
import { RegisterService } from './services/register.service';
import { ReservationService } from './services/reservation.service';
import { PagesModule } from './pages/pages.module';
import { LicenseService } from './services/license.service';
@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CoreModule.forRoot(),
    ThemeModule.forRoot(),
    NbSidebarModule.forRoot(),
    NbMenuModule.forRoot(),
    NbToastrModule.forRoot(),
    NbDatepickerModule.forRoot(),
    PagesModule,
  ],
  providers: [
    AppliancesService,
    DashboardService,
    RegisterService,
    ReservationService,
    LicenseService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
