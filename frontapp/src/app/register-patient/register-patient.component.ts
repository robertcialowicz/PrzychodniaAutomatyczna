import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-register-patient',
  templateUrl: './register-patient.component.html',
  styleUrls: ['./register-patient.component.scss']
})
export class RegisterPatientComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  error = '';
  loading = false;
  constructor(private formBuilder: FormBuilder) {
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
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
  }

}
