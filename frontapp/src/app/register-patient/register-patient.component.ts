import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ApiService} from "../services/api.service";
import {TokenService} from "../services/token.service";
import {Router} from "@angular/router";

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
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.valid) {
      this.apiService.register(this.registerForm.value).subscribe((res:any)  => {
        this.success = true
        console.log(res)
      });
    }
  }

  redirectToLoginPage() {
    this.router.navigate(['/authorize'])
  }


}
