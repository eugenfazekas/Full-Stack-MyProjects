import { Injectable, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { LogService } from './log.service';
import { LocalStorageService } from './local-storage.service';

@Injectable()
export class StorageTokenService {

  private jwt = new JwtHelperService();
  private subject = new Subject<any>();
  
  constructor( private logservice: LogService , private storageService: LocalStorageService ) { 
    this.logservice.logDebugMessage(String('TokenService constructor: '));
   }

   getToken() {
    this.logservice.logDebugMessage(String('TokenService getToken()'));
    return this.storageService.getToken()
   }

  getDecodedToken() {
    this.logservice.logDebugMessage(String('TokenService getDecodedToken()'));
    let decodedToken = this.jwt.decodeToken(this.storageService.getToken());
        return decodedToken != null ? decodedToken : '' ;
   }

  getOptions() {
    this.logservice.logDebugMessage(String('TokenService getOptions()'));
    return new HttpHeaders()
         .set('Authorization', `Bearer ${this.storageService.getToken()}`)
         }

  getAdmin(): boolean {
    this.logservice.logDebugMessage(String('TokenService getAdmin()'));
      let check = false;
      if(this.getDecodedToken().authorities != undefined) {
        let authorities = this.getDecodedToken().authorities;
        for(let auth of authorities){
          auth.authority == 'admin' ?  check = true : null;
                }
       }
       return check;
  }       

  getAdminObs() :Observable<any> {
    this.logservice.logDebugMessage(String('TokenService getAdminObs()'));
    return this.subject.asObservable();
  }    
        
  adminVerify() {
    this.logservice.logDebugMessage(String('TokenService adminVerify()'));
    if(this.getDecodedToken().authorities != undefined) {
    let authorities = this.getDecodedToken().authorities;
    for(let auth of authorities){
      auth.authority == 'admin' ?  this.subject.next(true) : null;
            }
          }
      }
      
   getFirstName() {
    this.logservice.logDebugMessage(String('TokenService getFirstName()'));
    let decodedToken = this.jwt.decodeToken(this.storageService.getToken());
    let name = decodedToken != null ? decodedToken.fullName : '' ;
    let fullName: string[];
    fullName = decodedToken != '' ? name.split(" ") : [];
    return fullName.length > 0 ? fullName[0] : '';
   } 
}


