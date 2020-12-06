import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';

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
  // returnUrl: string;
  error = '';

  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private formBuilder: FormBuilder
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
  onSubmit(): void {

  }
  get f(): List<FormControl> { return this.registerForm.controls; }


  add(event: MatChipInputEvent): void {
  }

  remove(index: any): void {
  }
}
