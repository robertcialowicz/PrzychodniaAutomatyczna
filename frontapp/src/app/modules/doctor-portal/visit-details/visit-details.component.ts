import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../api.service";
import {debounceTime} from "rxjs/operators";

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.scss']
})
export class VisitDetailsComponent implements OnInit {
  notes = '';
  medicaments = '';
  visit: any;
  constructor(private route: ActivatedRoute,
              private apiService: ApiService) { }

  ngOnInit(): void {
    this.visit = this.route.snapshot.data['visitDetails'];
    console.log(this.visit);
  }

  updateVisitDetails() {
    this.apiService.updateVisit(this.visit.id, this.visit).pipe(
      debounceTime(3500)
    ).subscribe(res => {
      console.log(res)
    })
  }

}
