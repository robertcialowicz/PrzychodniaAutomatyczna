import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {TokenService} from '../../../services/token.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ApiService} from '../api.service';
export class Reminder {
  datetime?: string;
  doctorID?: string;
  doctorName?: string;
  doctorSurname?: string;
  id?: string;
  patientID?: string;
  visitID?: string;
}

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent implements OnInit {
  reminders: Reminder[] = [];
  visible = false;
  user: any;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private tokenService: TokenService,
              public dialog: MatDialog,
              public apiService: ApiService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.visible = this.route.snapshot._routerState.url === '/patients' ? true : false;
    this.router.events.pipe(filter(e => e instanceof NavigationEnd)).subscribe((s: any) => {
      this.visible = (s.url) === '/patients' ? true : false;
    });
    this.reminders = this.route.snapshot.data['reminders'];

    this.apiService.getUsers().subscribe(res => {
      this.user = res.find((u: any) => u.id === this.tokenService.decodeToken(this.tokenService.getStoredJwtToken()).sub);
      Object.keys(this.user.patientData).forEach((key) => {
        this.user[key] = this.user.patientData[key];
      });
    });


  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AccountInfoDialogComponent, {
      width: '250px',
      data: { user: this.user }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  logOut(): void {
    this.tokenService.removeJwtToken();
    this.router.navigate(['/']);
  }

}

@Component({
  selector: 'app-dialog-overview-example-dialog',
  templateUrl: 'account-info-dialog.html',
})
export class AccountInfoDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<AccountInfoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}


  onNoClick(): void {
    this.dialogRef.close();
  }

}

