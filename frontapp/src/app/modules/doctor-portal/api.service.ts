import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { delay } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  getPlannedVisits(): Observable<any> {
    return of([
      {
        "id": "047a9587-05ef-4c9e-b3b9-3f5fcf450915",
        "date": "2022-12-22 22:22:00.0",
        "notes": "robertmadwakoty",
        "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
        "patientID": "43211111-1234-4321-4444-ac023f2f6ba3",
        "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
        "medicalsID": "5a2039e7-a12a-4d95-4321-aaaaaabbbfff"
      },
      {
        "id": "a5b0f1e1-c600-4508-9db0-8f30651c102a",
        "date": "2020-02-12 13:00:00.0",
        "notes": "alfa",
        "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
        "patientID": "5a2039e7-a12a-4d95-9e14-ac023f2f6ba3",
        "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
        "medicalsID": "5a2039e7-a12a-4d95-9e14-ac023f2f6ba3"
      },
      {
        "id": "a8d7e0f8-50dc-48c1-81dc-d072a8c4e4f5",
        "date": "2022-12-22 22:22:00.0",
        "notes": "robertmadwakoty",
        "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
        "patientID": "43211111-1234-4321-4444-ac023f2f6ba3",
        "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
        "medicalsID": "5a2039e7-a12a-4d95-4321-aaaaaabbbfff"
      },
      {
        "id": "a8dcbce9-b0c5-4a4d-b035-b66a3b7bbeec",
        "date": "2011-11-11 11:11:00.0",
        "notes": "alamakotaaaaaaa",
        "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
        "patientID": "11111111-2222-3333-4444-ac023f2f6ba3",
        "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
        "medicalsID": "5a2039e7-a12a-4d95-9e14-aaaaaabbbfff"
      },
      {
        "id": "ad901dba-0ac0-4d7e-bd3e-97b3a143dd97",
        "date": "2020-02-12 17:00:00.0",
        "notes": null,
        "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
        "patientID": "5a2039e7-a12a-4d95-9e14-ac023f2f6ba3",
        "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
        "medicalsID": null
      },
      {
        "id": "b86fcce7-b664-47bc-8c0e-692f7292832c",
        "date": "2020-02-12 13:00:00.0",
        "notes": null,
        "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
        "patientID": "5a2039e7-a12a-4d95-9e14-ac023f2f6ba3",
        "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
        "medicalsID": null
      }
    ]);
  }

  getVisitDetails():Observable<any> {
    return of({
      "id": "b86fcce7-b664-47bc-8c0e-692f7292832c",
      "date": "2020-02-12 13:00:00.0",
      "notes": null,
      "doctorID": "bc796d30-aa85-43fc-9bd9-65aa39f53cf0",
      "patientID": "5a2039e7-a12a-4d95-9e14-ac023f2f6ba3",
      "specializationID": "5d16c66a-db11-4a1e-b864-78b35f245699",
      "medicalsID": null
    });
  }

}
