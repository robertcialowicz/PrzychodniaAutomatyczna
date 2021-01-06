import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {TokenService} from "./token.service";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenService.getStoredJwtToken()
    let newrequest = request.clone({
      headers: request.headers.set( 'Authorization', 'Bearer ' + token )
    });

    return next.handle(newrequest).pipe(catchError( err => {
      if (err.status === 401) {
        // TODO auto logout if 401 with AuthService
        location.reload(true);
      }
      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}
