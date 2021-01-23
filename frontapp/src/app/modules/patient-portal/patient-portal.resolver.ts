import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {forkJoin, Observable, of} from 'rxjs';
import {ApiService} from './api.service';
import {map} from 'rxjs/operators';
import {TokenService} from '../../services/token.service';
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
    const visits = this.apiService.getVisits();
    const users = this.apiService.getUsers();
    const specializations = this.apiService.getSpecializations();

    return forkJoin([visits, users, specializations]).pipe(
      map((res: any) => res[0]
        .filter((v: any) => {
          const token = this.tokenService.getStoredJwtToken();
          const currentUserID = this.tokenService.decodeToken(token).sub;

          const specialization = res[2].find((s: any) => s.id === v.specializationID);
          return v.patientID === currentUserID && moment().diff(v.date) <= 0 && specialization;
        })
        .map((el: any) => {
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

@Injectable({
  providedIn: 'root'
})
export class PatientPortalRemindersResolver implements Resolve<boolean> {

  constructor(private apiService: ApiService, private tokenService: TokenService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    const token = this.tokenService.getStoredJwtToken();
    const currentUserID = this.tokenService.decodeToken(token).sub;

    const reminders = this.apiService.getReminders(currentUserID);
    const users = this.apiService.getUsers();
    const specializations = this.apiService.getSpecializations();

    return forkJoin([reminders, users, specializations]).pipe(
      map((res: any) => res[0]
        .filter((v: any) => moment().diff(v.datetime) <= 0)
        .map((el: any) => {
          const doctor = res[1].find((u: any) => u.id === el.doctorID).doctorData;
          // const specialization = res[2].find((s: any) => s.id === el.specializationID);
          el.doctorName = doctor.name;
          el.doctorSurname = doctor.surname;
          // el.specializationName = specialization.name;
          el.datetime = el.datetime.slice(0, -5);
          return el;
        })
      ));
  }
}

@Injectable({
  providedIn: 'root'
})
export class PatientPortalReceiptsResolver implements Resolve<boolean> {

  constructor(private apiService: ApiService, private tokenService: TokenService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    const token = this.tokenService.getStoredJwtToken();
    const currentUserID = this.tokenService.decodeToken(token).sub;

    const receipts = this.apiService.getReceipts(currentUserID);
    const medicaments = this.apiService.getMedicals();
    const visits = this.apiService.getVisits();

    return forkJoin([receipts, medicaments, visits]).pipe(
      map((res: any) => res[0]
        .map((el: any) => {
          el.medicamentName  = res[1].find((m: any) => m.id === el.medicalIds) ? res[1].find((m: any) => m.id === el.medicalIds).name : '';

          el.visitDate = res[2].find((v: any) => v.id === el.visitId).date.slice(0, -5);
          return el;
        })
      ));
  }
}

