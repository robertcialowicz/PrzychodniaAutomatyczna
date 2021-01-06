import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {filter, map} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  userServiceUrl = "http://172.25.0.11:9092/api/user-authentication-system";
  visitsServiceUrl = "http://localhost:9092/api/visit"

  constructor(private httpClient: HttpClient) {}

  getSpecializations() {
    return this.httpClient.get(`${this.userServiceUrl}/api/specializations`);
  }

  getDoctorsBySpecialization(specializationID: string){
    return this.httpClient.get(`${this.userServiceUrl}/api/user`).pipe(
      map((res: any) => res
        .filter((u:any) => u.doctorData && u.doctorData.specialization.includes(specializationID))
        .map((el:any) => {
          return {...{id: el.id}, ...el.doctorData}
        }))
    );
  }

  getPossibleVisitsBySpecialization(specializationID: string, fromDate: string, toDate: string) {
    return this.httpClient.get(`${this.visitsServiceUrl}/api/visit/search/freeBySpec/${specializationID}/${fromDate}/${toDate}`);
  }

  getPossibleVisitsByDoctor(doctorID: string, fromDate: string, toDate: string) {
    return this.httpClient.get(`${this.visitsServiceUrl}/api/visit/search/freeByDoc/${doctorID}/${fromDate}/${toDate}`);
  }

  reserveVisit(visitData: any) {
    return this.httpClient.post(`${this.visitsServiceUrl}/api/visit`, visitData);
  }

  getVisits() {
    return this.httpClient.get(`${this.visitsServiceUrl}/api/visit`)
  }

  getUsers() {
    return this.httpClient.get(`${this.userServiceUrl}/api/user`)
  }

  cancelVisit(visitID:  string) {
    return this.httpClient.delete(`${this.visitsServiceUrl}/api/visit/${visitID}`)
  }
}
