import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ApiService} from '../api.service';
import {debounceTime} from 'rxjs/operators';


export class Medicaments {
  id?: string;
  name?: string;
}

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.scss']
})

export class VisitDetailsComponent implements OnInit {
  notes = '';
  medicaments: Medicaments[] = [];
  visit: any;
  medicalList: any;
  constructor(private route: ActivatedRoute,
              private apiService: ApiService) { }

  ngOnInit(): void {
    this.visit = this.route.snapshot.data['visitDetails'];
    this.medicalList = this.route.snapshot.data['medicalsList'];

    const medicals = this.visit.medicalsID ? this.visit.medicalsID.split(',') : [];
    medicals.forEach((el: any) => {
      this.medicalList.forEach((m: any) => {
        if (m.id === el) {
          // @ts-ignore
          this.medicaments.push(m);
        }
      });
    });

    this.notes = this.visit.notes;
  }


  updateVisitDetails(): void {
    const medicaments = this.medicaments.map((el: any) => el.id);
    const medicamentsString = medicaments.join(',');
    this.visit.notes = this.notes;
    this.visit.medicalsID = medicamentsString;
    this.apiService.updateVisit(this.visit.id, this.visit).pipe(
      debounceTime(3500)
    ).subscribe(res => {
      this.notes = this.visit.notes;
    });
  }


}
