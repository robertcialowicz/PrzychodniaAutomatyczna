import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from './welcome/welcome.component';
import {RegisterDoctorComponent} from './register-doctor/register-doctor.component';
import {RegisterPatientComponent} from './register-patient/register-patient.component';
import {AuthorizeComponent} from './authorize/authorize.component';
import {PatientPortalSpecializationsResolver} from './modules/patient-portal/patient-portal.resolver';
const routes: Routes = [
  {
    path: 'doctors',
    loadChildren: () => import('./modules/doctor-portal/doctor-portal.module').then(m => m.DoctorPortalModule)
  },
  {
    path: 'patients',
    loadChildren: () => import('./modules/patient-portal/patient-portal.module').then(m => m.PatientPortalModule)
  },
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'register-doctor',
    component: RegisterDoctorComponent,
    resolve: { specializations: PatientPortalSpecializationsResolver }
  },
  {
    path: 'register-patient',
    component: RegisterPatientComponent
  },
  {
      path: 'authorize',
      component: AuthorizeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
