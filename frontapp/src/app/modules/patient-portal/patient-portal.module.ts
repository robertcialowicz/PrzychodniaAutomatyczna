import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationComponent } from './navigation/navigation.component';
import {PatientPortalRoutingModule} from './patient-portal-routing.module';
import {AccountInfoDialogComponent, MainComponent} from './main/main.component';
import { ReserveVisitComponent } from './reserve-visit/reserve-visit.component';
import {MatStepperModule} from '@angular/material/stepper';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import { PlannedVisitsComponent } from './planned-visits/planned-visits.component';
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import { ReceiptsComponent } from './receipts/receipts.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatDialogModule} from '@angular/material/dialog';

@NgModule({
  declarations: [NavigationComponent, MainComponent, ReserveVisitComponent, PlannedVisitsComponent, ReceiptsComponent],
  imports: [
    CommonModule,
    PatientPortalRoutingModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatDatepickerModule,
    MatOptionModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatDialogModule
  ],
  entryComponents: []
})
export class PatientPortalModule { }
