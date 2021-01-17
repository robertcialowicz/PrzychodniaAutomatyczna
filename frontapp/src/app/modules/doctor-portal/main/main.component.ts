import {Component, Inject, OnInit} from '@angular/core';
import {AccountInfoDialogComponent} from '../../patient-portal/main/main.component';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenService} from '../../../services/token.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ApiService} from '../../patient-portal/api.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  user: any;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private tokenService: TokenService,
              public dialog: MatDialog,
              public apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getUsers().subscribe(res => {
      this.user = res.find((u: any) => u.id === this.tokenService.decodeToken(this.tokenService.getStoredJwtToken()).sub);
      Object.keys(this.user.doctorData).forEach((key) => {
        this.user[key] = this.user.doctorData[key];
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
  templateUrl: 'account-doctor-info-dialog.html',
})
export class AccountDoctorInfoDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<AccountDoctorInfoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}


  onNoClick(): void {
    this.dialogRef.close();
  }

}
