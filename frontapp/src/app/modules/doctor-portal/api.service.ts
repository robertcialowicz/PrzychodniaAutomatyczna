import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { delay } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  userServiceUrl = "http://172.25.0.11:9092/api/user-authentication-system";
  visitsServiceUrl = "http://localhost:9092/api/visit";

  constructor(private httpClient: HttpClient) { }

  getVisits() {
    return this.httpClient.get(`${this.visitsServiceUrl}/api/visit`);
  }

  updateVisit(visitID: string, visitDetails: any) {
    return this.httpClient.put(`${this.visitsServiceUrl}/api/visit/${visitID}`, visitDetails);
  }


  getVisitDetails(visitID: any):Observable<any> {
      return this.httpClient.get(`${this.visitsServiceUrl}/api/visit/${visitID}`)
  }

  getUsers() {
    return this.httpClient.get(`${this.userServiceUrl}/api/user`)
  }

  getSpecializations() {
    return this.httpClient.get(`${this.userServiceUrl}/api/specializations`);
  }

}
