import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationComponent } from './navigation/navigation.component';
import {PatientPortalRoutingModule} from './patient-portal-routing.module';
import { MainComponent } from './main/main.component';
import { ReserveVisitComponent } from './reserve-visit/reserve-visit.component';
import {MatStepperModule} from '@angular/material/stepper';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';



@NgModule({
  declarations: [NavigationComponent, MainComponent, ReserveVisitComponent],
  imports: [
    CommonModule,
    PatientPortalRoutingModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule

  ]
})
export class PatientPortalModule { }
