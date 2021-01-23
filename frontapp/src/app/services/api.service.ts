import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  userServiceUrl = 'http://localhost:9092/api/user-authentication-system';
  // userServiceUrl = 'http://zuulgwsystem:9092/api/user-authentication-system';
  constructor(private httpClient: HttpClient) {}


  logIn(credentials: any) {
    return this.httpClient.post(`${this.userServiceUrl}/api/authenticate`, credentials);
  }

  register(registrationData: any) {
    return this.httpClient.post(`${this.userServiceUrl}/api/user/register`, registrationData);
  }

}

