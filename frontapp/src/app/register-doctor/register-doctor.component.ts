import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ApiService} from "../services/api.service";
import {Router} from "@angular/router";

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
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private formBuilder: FormBuilder,
    private apiService: ApiService,
    public router: Router
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
        specialization: this.formBuilder.array([])
      })
    });
  }

  ngOnInit(): void {

  }

  get f(): List<FormControl> { return this.registerForm.controls; }


  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    console.log(this.registerForm.get('doctorData.specialization'))
    if ((value || '').trim()) {
      (this.registerForm.get('doctorData.specialization') as FormArray).push(this.formBuilder.group({specialization: value}));
    }
    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(index: any): void {
    (this.registerForm.get('doctorData.specialization') as FormArray).removeAt(index);
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.valid) {
      this.registerForm.value.doctorData.specialization = this.registerForm.value.doctorData.specialization.map((el:any) => el.specialization)
      this.apiService.register(this.registerForm.value).subscribe((res:any)  => {
        this.success = true
        console.log(res)
      });
    }
  }

  redirectToLoginPage() {
    this.router.navigate(['/authorize'])
  }

  get specializations () {
    return this.registerForm.get('doctorData.specialization') as FormArray
  }
}
