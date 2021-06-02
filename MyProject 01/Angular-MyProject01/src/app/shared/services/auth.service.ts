import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AUTH_URL, UserRestDataSourceService } from '../../rest-api/user-rest-data-source.service';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LogService } from './log.service';
import { LoggedUserService } from './logged-user.service';

@Injectable()
export class AuthService {

  private baseURL: string ;
  private helper = new JwtHelperService();

  private param(email: string, password: string) {
    return new HttpParams()
    .set('grant_type', 'password')
    .set('username', email)
    .set('password', password)
    .set('scope', 'read')
  }

  private header() {

    let authorization: string = 'Basic '+ btoa('client:secret');
      return new HttpHeaders()
            .set('Authorization', authorization)
         }

  constructor(private _http: HttpClient, @Inject(AUTH_URL) _baseURL: string, private loggedUserService: LoggedUserService, private logservice: LogService) { 
                       this.baseURL = _baseURL;
                       this.logservice.logDebugMessage(String('AuthService constructor: '));
                  }               
  
  loginUser(user: string, pass: string): Observable<string> {
    this.logservice.logDebugMessage(String('AuthService loginUser() '));
    return this._http.post(`${this.baseURL}/oauth/token`,{},{'headers': this.header() ,'params' : this.param(user,pass) }).pipe(

              map( (response: any) => {
                      const decodedToken = this.helper.decodeToken(response.access_token);
                      this.loggedUserService.setToken(response.access_token);
                      this.loggedUserService.setId(decodedToken.user_name);  
                      this.loggedUserService.adminCheck(response.access_token);  
                           return decodedToken.user_name;
                  }
                )
            );
  }
  
}
