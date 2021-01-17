import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FutureVisitsComponent} from './future-visits/future-visits.component';
import {MainComponent} from './main/main.component';
import {MedicalsService, PlannedVisitsService, VisitsDetailsService} from './resolvers.service';
import {VisitDetailsComponent} from './visit-details/visit-details.component';


const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [{
      path: 'future-visits',
      component: FutureVisitsComponent,
      resolve: { plannedVisits: PlannedVisitsService }
    },
    {
      path: 'future-visits/:visitId',
      component: VisitDetailsComponent,
      resolve: { visitDetails: VisitsDetailsService, medicalsList: MedicalsService }
    }]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorPortalRoutingModule { }
