import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.scss']
})
export class VisitDetailsComponent implements OnInit {
  notes = '';
  medicaments = '';
  constructor() { }

  ngOnInit(): void {
  }

}
