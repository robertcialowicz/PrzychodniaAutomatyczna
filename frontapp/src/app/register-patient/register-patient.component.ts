import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ApiService} from '../services/api.service';
import {Router} from '@angular/router';
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
  selector: 'app-register-patient',
  templateUrl: './register-patient.component.html',
  styleUrls: ['./register-patient.component.scss'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ]
})
export class RegisterPatientComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  error = '';
  loading = false;
  success = false;

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiService,
              public router: Router) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      isDoctor: ['false'],
      patientData: this.formBuilder.group({
        name: ['', Validators.required],
        surname: ['', Validators.required],
        pesel: ['', Validators.required],
        birthDate: ['', Validators.required]
      })
    });

    this.registerForm.valueChanges.subscribe(res => {
      this.registerForm.value.patientData.birthDate = moment(this.registerForm.value.patientData.birthDate).format('YYYY-MM-DD');
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.valid) {
      this.apiService.register(this.registerForm.value).subscribe((res: any)  => {
        this.success = true;
      });
    }
  }

  redirectToLoginPage(): void {
    this.router.navigate(['/authorize']);
  }


}
