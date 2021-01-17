import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import * as _moment from 'moment';
// @ts-ignore
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {forkJoin} from 'rxjs';
import {map} from 'rxjs/operators';
import {ApiService} from '../api.service';
import {TokenService} from '../../../services/token.service';
export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

@Component({
  selector: 'app-future-visits',
  templateUrl: './future-visits.component.html',
  styleUrls: ['./future-visits.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ]

})
export class FutureVisitsComponent implements OnInit {
  plannedVisits: any;
  periodForm: FormGroup;
  constructor(private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router,
              private apiService: ApiService,
              private tokenService: TokenService) {
    this.periodForm = this.formBuilder.group({
      startDate: [moment()],
      endDate: [moment().add(5, 'days')]
    });

    this.periodForm.valueChanges.subscribe(res1 => {
      const visits = this.apiService.getVisits();
      const users = this.apiService.getUsers();
      const specializations = this.apiService.getSpecializations();

      const a = forkJoin([visits, users, specializations]).pipe(
        map((res: any) => res[0]
          .filter((v: any) => {
            const token = this.tokenService.getStoredJwtToken();
            const currentUserID = this.tokenService.decodeToken(token).sub;
            const specialization = res[2].find((s: any) => s.id === v.specializationID);
            return v.doctorID === currentUserID &&
              moment(res1.endDate).diff(v.date) >= 0 &&
              moment(res1.startDate).diff(v.date) <= 0 &&
              specialization;
          })
          .map((el: any) => {
            const patient = res[1].find((u: any) => u.id === el.patientID).patientData;
            const specialization = res[2].find((s: any) => s.id === el.specializationID);
            el.patientName = patient.name;
            el.patientSurname = patient.surname;
            el.specializationName = specialization.name;
            el.date = el.date.slice(0, -5);
            return el;
          })
        )).subscribe(vis => {
          this.plannedVisits = vis;
      });
    });
  }

  ngOnInit(): void {
    this.plannedVisits = this.route.snapshot.data['plannedVisits'];
  }

  showVisitDetails(visitId: any): void {
    this.router.navigate([`doctors/future-visits/${visitId}`]);
  }
}
