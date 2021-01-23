import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MainComponent} from './main/main.component';
import {ReserveVisitComponent} from './reserve-visit/reserve-visit.component';
import {
  PatientPortalReceiptsResolver,
  PatientPortalRemindersResolver,
  PatientPortalSpecializationsResolver,
  PlannedVisitsResolver
} from './patient-portal.resolver';
import {PlannedVisitsComponent} from './planned-visits/planned-visits.component';
import {ReceiptsComponent} from './receipts/receipts.component';


const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    resolve: { reminders: PatientPortalRemindersResolver },
    children: [
      {
      path: 'reserve-visit',
      component: ReserveVisitComponent,
      resolve: { specializations:  PatientPortalSpecializationsResolver}
      },
      {
        path: 'planned-visits',
        component: PlannedVisitsComponent,
        resolve: { visits:  PlannedVisitsResolver}
      },
      {
        path: 'receipts',
        component: ReceiptsComponent,
        resolve: { receipts: PatientPortalReceiptsResolver}
      }
    ]

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientPortalRoutingModule { }
