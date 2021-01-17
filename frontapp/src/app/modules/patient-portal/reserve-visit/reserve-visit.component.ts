import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import * as _moment from 'moment';
// @ts-ignore
import {default as _rollupMoment, Moment} from 'moment';

const moment = _rollupMoment || _moment;
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {ActivatedRoute} from '@angular/router';
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
  selector: 'app-reserve-visit',
  templateUrl: './reserve-visit.component.html',
  styleUrls: ['./reserve-visit.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ]
})
export class ReserveVisitComponent implements OnInit {
  firstFormGroup: FormGroup;
  doctrorFormGroup: FormGroup;
  periodForm: FormGroup;
  thirdFormGroup: FormGroup;
  specializations: any;
  doctors: any;
  posssibleVisits = null;
  dateFormat = 'YYYY-MM-DD';
  reservationSucceed = false;
  visitDetails: any = null;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private apiService: ApiService,
              private tokenService: TokenService) {

    this.firstFormGroup = this.formBuilder.group({
      firstCtrl: ['', Validators.required]
    });

    this.doctrorFormGroup = this.formBuilder.group({
      doctorCtrl: ['', Validators.required]
    });

    this.periodForm = this.formBuilder.group({
      startDate: [moment().add(1, 'days')],
      endDate: [moment().add(14, 'days')]
    });

    this.thirdFormGroup = this.formBuilder.group({
      thirdCtrl: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.specializations = this.route.snapshot.data['specializations'];
  }

  getPossibleVisits(): void {
    this.apiService.getPossibleVisitsByDoctor(
      this.doctrorFormGroup.value.doctorCtrl,
      moment(this.periodForm.value.startDate, "x").format(this.dateFormat),
      moment(this.periodForm.value.endDate, "x").format(this.dateFormat)).subscribe((res: any) => {
        this.posssibleVisits = res;
    });
  }

  getDoctorsBySpecialization(): void {
    this.apiService.getDoctorsBySpecialization(this.firstFormGroup.value.firstCtrl).subscribe((res: any) => {
      this.doctors = res;
    });
  }

  reserveVisit(): void {
    const token = this.tokenService.getStoredJwtToken();
    const currentUserID = this.tokenService.decodeToken(token).sub;

    const visit = {
      date: this.thirdFormGroup.value.thirdCtrl,
      notes: null,
      doctorID: this.doctrorFormGroup.value.doctorCtrl,
      patientID: currentUserID,
      specializationID: this.firstFormGroup.value.firstCtrl,
      medicalsID: null
    };

    this.apiService.reserveVisit(visit).subscribe(res => {
      this.reservationSucceed = true;
      this.visitDetails = res;

    }, error => {
      this.reservationSucceed = false;
    });
  }



}
