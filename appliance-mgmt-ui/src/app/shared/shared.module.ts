import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { 
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbTabsetModule,
  NbListModule,
  NbIconModule,
  NbUserModule,
  NbCheckboxModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbSpinnerModule,
  NbPopoverModule,
  NbTooltipModule,
} from '@nebular/theme';


import {MatNativeDateModule} from '@angular/material/core';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import {MatDialogModule} from '@angular/material/dialog';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms'

import { HttpClientModule } from '@angular/common/http';

const MODULES = [
  
  CommonModule,
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbTabsetModule,
  NbListModule,
  NbIconModule,
  NbUserModule,
  NbCheckboxModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbSpinnerModule,
  NbPopoverModule,
  NbTooltipModule,
  
  HttpClientModule,

  MatNativeDateModule,
  ReactiveFormsModule,
  Ng2SmartTableModule,
  MatDialogModule,
  MatDatepickerModule,
  ScrollingModule,
  FormsModule,

];
@NgModule({
  declarations: [],
  imports: [...MODULES],
  exports: [...MODULES]
})
export class SharedModule { }
