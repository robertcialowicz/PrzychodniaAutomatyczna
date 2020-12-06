import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlannedVisitsService {
  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    return this.apiService.getPlannedVisits();
  }
}

@Injectable({
  providedIn: 'root'
})
export class VisitsDetailsService {
  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    return this.apiService.getVisitDetails();
  }
}


