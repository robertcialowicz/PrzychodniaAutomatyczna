import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import * as _moment from 'moment';
// @ts-ignore
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
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
  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private router: Router) {
    this.periodForm = this.formBuilder.group({
      startDate: [moment()],
      endDate: [moment().add(5, 'days')]
    });
  }

  ngOnInit(): void {
    this.plannedVisits = this.route.snapshot.data['plannedVisits'];
  }

  showVisitDetails(visitId: any): void {
    this.router.navigate([`doctors/future-visits/${visitId}`]);
  }


}
