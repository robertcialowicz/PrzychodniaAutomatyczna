import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../services/api.service";
import {TokenService} from "../services/token.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  styleUrls: ['./authorize.component.scss']
})
export class AuthorizeComponent implements OnInit {
  authorizeForm: FormGroup;
  submitted = false;
  loading = false;
  // returnUrl: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiService,
              private tokenService: TokenService,
              public router: Router) {
    this.authorizeForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });

  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.submitted = true;
    if(this.authorizeForm.valid) {
      this.apiService.logIn(this.authorizeForm.value).subscribe((res:any) => {
        this.tokenService.storeJwtToken(res.jwtToken)
        const userInfo = this.tokenService.decodeToken(res.jwtToken);
        console.log(userInfo.roles)
        if(userInfo.roles.includes("doctor")) {
          this.router.navigate(['/doctors']);
        } else {
          this.router.navigate(['/patients']);
        }
      })
    }
  }

}
