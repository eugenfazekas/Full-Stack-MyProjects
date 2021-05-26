import { Injectable,  Inject, InjectionToken } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../shared/services/token.service'; 
import { LogService } from '../shared/services/log.service';
import { UserAccount } from '../model/user.account';
import { Text } from '@angular/compiler/src/i18n/i18n_ast';

export const BASE_URL = new InjectionToken<string>('BaseUrl');

@Injectable()
export class UserRestDataSourceService {

  private baseURL: string ;

   param1(email: string) { 
     return new HttpParams()
            .set('email', email);
   }

  constructor(private tokenService: TokenService, private _http: HttpClient, @Inject(BASE_URL) _baseURL: string, private logservice: LogService) {
           this.logservice.logDebugMessage(String('UserRestDataSourceService constructor: '));
           this.baseURL = _baseURL; 
   }

  saveUser(user: UserAccount): Observable<String> {
    this.logservice.logDebugMessage(String('UserRestDataSourceService saveUser() '));
    return this._http.post(`${this.baseURL}/user/registerUser`,user,{ responseType: 'text'});
  }

  userExistCheck(email: string): Observable<boolean>{
    this.logservice.logDebugMessage(String('UserRestDataSourceService userExistCheck() '));
    return this._http.post<boolean>(`${this.baseURL}/user/userExistCheck`, this.param1(email));
  }

}
 
