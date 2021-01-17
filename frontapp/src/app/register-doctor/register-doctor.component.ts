import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ApiService} from '../services/api.service';
import {ActivatedRoute, Router} from '@angular/router';

class List<T> {
}

@Component({
  selector: 'app-register-doctor',
  templateUrl: './register-doctor.component.html',
  styleUrls: ['./register-doctor.component.scss']
})
export class RegisterDoctorComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  success = false;
  error = '';

  selectable = true;
  removable = true;
  addOnBlur = true;
  specializatons: any;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private formBuilder: FormBuilder,
    private apiService: ApiService,
    public router: Router,
    private route: ActivatedRoute
  ) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      isDoctor: ['true'],
      doctorData: this.formBuilder.group({
        name: ['', Validators.required],
        surname: ['', Validators.required],
        pesel: ['', Validators.required],
        specialization: ['', Validators.required]
      })
    });
  }

  ngOnInit(): void {
    this.specializatons = this.route.snapshot.data['specializations'];
  }

  get f(): List<FormControl> { return this.registerForm.controls; }

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
