import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BASE_URL } from '../../rest-api/user-rest-data-source.service';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TokenService } from './token.service';
import { LogService } from './log.service';

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
    console.log(authorization);
      return new HttpHeaders()
            .set('Authorization', authorization)
            .set("Access-Control-Allow-Origin", "*")
            .set("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS")
            .set("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
         }

  constructor(private _http: HttpClient, @Inject(BASE_URL) _baseURL: string, private tokenService: TokenService, private logservice: LogService) { 
                       this.baseURL = _baseURL;
                       this.logservice.logDebugMessage(String('AuthService constructor: '));
                  }               
  
  loginUser(user: string, pass: string): Observable<Boolean> {
    this.logservice.logDebugMessage(String('AuthService loginUser() '));
    return this._http.post(`${this.baseURL}/oauth/token`,{},{'headers': this.header() ,'params' : this.param(user,pass) }).pipe(

              map( (response: any) => {
                      this.tokenService.setToken(response.access_token);
                      const decodedToken = this.helper.decodeToken(response.access_token);
                      console.log(decodedToken);
                      localStorage.setItem('token',response.access_token);
                           return true;
                  }
                )
            );
  }
  
}
