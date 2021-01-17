import {Inject, Injectable} from '@angular/core';
import {LOCAL_STORAGE, StorageService} from 'ngx-webstorage-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  STORAGE_KEY = 'jwtToken';

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService, private jwtHelperService: JwtHelperService) { }

  public storeJwtToken(token: string): void {
    this.storage.set(this.STORAGE_KEY, token);
    console.log(this.storage.get(this.STORAGE_KEY) || 'LocaL storage is empty');
  }

  public removeJwtToken(): void {
    this.storage.remove(this.STORAGE_KEY);
  }

  public getStoredJwtToken(): string {
    return this.storage.get(this.STORAGE_KEY);
  }

  public decodeToken(token: string): any {
    return this.jwtHelperService.decodeToken(token);
  }

}
