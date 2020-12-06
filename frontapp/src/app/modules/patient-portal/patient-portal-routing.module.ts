import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MainComponent} from './main/main.component';
import {FutureVisitsComponent} from '../doctor-portal/future-visits/future-visits.component';
import {PlannedVisitsService, VisitsDetailsService} from '../doctor-portal/resolvers.service';
import {VisitDetailsComponent} from '../doctor-portal/visit-details/visit-details.component';
import {ReserveVisitComponent} from './reserve-visit/reserve-visit.component';


const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [{
      path: 'reserve-visit',
      component: ReserveVisitComponent,
      resolve: { plannedVisits: PlannedVisitsService }
    }]

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientPortalRoutingModule { }
