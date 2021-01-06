import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {forkJoin, Observable, of} from 'rxjs';
import {ApiService} from "./api.service";
import {map} from "rxjs/operators";
import {TokenService} from "../../services/token.service";
import * as _moment from 'moment';
// @ts-ignore
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;
@Injectable({
  providedIn: 'root'
})
export class PatientPortalSpecializationsResolver implements Resolve<boolean> {

  constructor(private apiService: ApiService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    return this.apiService.getSpecializations();
  }
}

@Injectable({
  providedIn: 'root'
})
export class PlannedVisitsResolver implements Resolve<boolean> {

  constructor(private apiService: ApiService, private tokenService: TokenService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    let visits = this.apiService.getVisits();
    let users = this.apiService.getUsers();
    let specializations = this.apiService.getSpecializations();

    return forkJoin([visits, users, specializations]).pipe(
      map((res: any) => res[0]
        .filter((v:any) => {
          const token = this.tokenService.getStoredJwtToken();
          const currentUserID = this.tokenService.decodeToken(token).sub;
          return v.patientID === currentUserID && moment().diff(v.date) <=0;
        })
        .map((el:any) => {
          const doctor = res[1].find((u: any) => u.id === el.doctorID).doctorData;
          const specialization = res[2].find((s: any) => s.id === el.specializationID);
          el.doctorName = doctor.name;
          el.doctorSurname = doctor.surname;
          el.specializationName = specialization.name;
          el.date = el.date.slice(0, -5);
          return el;
        })
      ));

  }
}

