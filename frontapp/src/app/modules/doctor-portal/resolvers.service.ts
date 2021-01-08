import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {forkJoin, Observable} from 'rxjs';
import {map} from "rxjs/operators";
import {TokenService} from "../../services/token.service";
import * as _moment from 'moment';
// @ts-ignore
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;

@Injectable({
  providedIn: 'root'
})
export class PlannedVisitsService {
  constructor(private apiService: ApiService, private tokenService: TokenService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
      let visits = this.apiService.getVisits();
      let users = this.apiService.getUsers();
      let specializations = this.apiService.getSpecializations();

      return forkJoin([visits, users, specializations]).pipe(
        map((res: any) => res[0]
          .filter((v:any) => {
            const token = this.tokenService.getStoredJwtToken();
            const currentUserID = this.tokenService.decodeToken(token).sub;
            return v.doctorID === currentUserID && moment().diff(v.date) <=0;
          })
          .map((el:any) => {
            const patient = res[1].find((u: any) => u.id === el.patientID).patientData;
            const specialization = res[2].find((s: any) => s.id === el.specializationID);
            el.patientName = patient.name;
            el.patientSurname = patient.surname;
            el.specializationName = specialization.name;
            el.date = el.date.slice(0, -5);
            return el;
          })
        ));
    }
}

@Injectable({
  providedIn: 'root'
})
export class VisitsDetailsService {
  constructor(private apiService: ApiService, private tokenService: TokenService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    let visit = this.apiService.getVisitDetails(route.params.visitId);
    let users = this.apiService.getUsers();
    let specializations = this.apiService.getSpecializations();

    let transformVisit = (res: any) => {
      const patient = res[1].find((u: any) => u.id === res[0].patientID).patientData;
      const specialization = res[2].find((s: any) => s.id === res[0].specializationID);
      res[0].patientData = patient;
      res[0].specializationName = specialization.name;
      res[0].date = res[0].date.slice(0, -5);
      return res[0];
    }

    return forkJoin([visit, users, specializations]).pipe(
      map((res: any) => transformVisit(res)
    ));
  }
}


