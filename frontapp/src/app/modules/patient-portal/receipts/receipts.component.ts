import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-receipts',
  templateUrl: './receipts.component.html',
  styleUrls: ['./receipts.component.scss']
})
export class ReceiptsComponent implements OnInit {

  receipts: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.receipts = this.route.snapshot.data['receipts'];
    const reduced: any[] = [];
    this.receipts.forEach((el: any) => {
      const medicament = {
        id: el.medicalIds,
        name: el.medicamentName
      };
      if (!reduced.find(r => r.visitId === el.visitId)) {
        el.medicalIds = [];
        el.medicalIds.push(medicament);
        reduced.push(el);
      } else {
        reduced.map((elem: any) => {
          if (elem.visitId === el.visitId) {
            elem.medicalIds.push(medicament);
          }
        });
      }
    });

    this.receipts = reduced;
    console.log(this.receipts)
  }

}
