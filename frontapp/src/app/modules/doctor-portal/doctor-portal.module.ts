import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FutureVisitsComponent } from './future-visits/future-visits.component';
import {DoctorPortalRoutingModule} from './doctor-portal-routing.module';
import { NavigationComponent } from './navigation/navigation.component';
import {MatIconModule} from '@angular/material/icon';
import { MainComponent } from './main/main.component';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule,} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import { VisitDetailsComponent } from './visit-details/visit-details.component';
import {MatSelectModule} from '@angular/material/select';
import {MatMenuModule} from '@angular/material/menu';


@NgModule({
  declarations: [FutureVisitsComponent, NavigationComponent, MainComponent, VisitDetailsComponent],
    imports: [
        CommonModule,
        DoctorPortalRoutingModule,
        MatIconModule,
        MatCardModule,
        MatFormFieldModule,
        MatDatepickerModule,
        FormsModule,
        ReactiveFormsModule,
        MatInputModule,
        MatDividerModule,
        MatSelectModule,
        MatMenuModule
    ],
  exports: [

  ]
})
export class DoctorPortalModule { }
