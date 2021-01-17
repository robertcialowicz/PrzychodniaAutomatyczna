import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../api.service";

@Component({
  selector: 'app-planned-visits',
  templateUrl: './planned-visits.component.html',
  styleUrls: ['./planned-visits.component.scss']
})
export class PlannedVisitsComponent implements OnInit {

  visits: any;
  constructor(private route: ActivatedRoute,
              private apiService: ApiService) { }

  ngOnInit(): void {
    this.visits = this.route.snapshot.data['visits'];
  }

  cancelVisit(visitID: string): void {
    this.apiService.cancelVisit(visitID).subscribe(res => {
      this.visits = this.visits.filter((el: any) => el.id  !== visitID)
    });
  }

}
