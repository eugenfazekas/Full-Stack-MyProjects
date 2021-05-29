import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { StorageTokenService } from './storage-token.service';
import { LogService } from './log.service';
import { LocalStorageService } from './local-storage.service';


@Injectable()
export class SignButtonToggleService {

  constructor(private localStorageService: LocalStorageService, private storageTokenService :StorageTokenService, private logservice: LogService) {
    this.logservice.logDebugMessage(String('SignButtonToggleService constructor: '));
   // this.token = tokenService.getDecodedToken();
     this.name = this.token ? `Welcome ${this.token.fullName}` : '';
  }

  private token;
  public name; 
  public loggedIn: boolean = localStorage.getItem('token') != null ? true : false; 

  setFullname() {
    this.logservice.logDebugMessage(String('SignButtonToggleService setFullname() '));
    this.token = this.storageTokenService.getDecodedToken();
    this.name = `Welcome ${this.token.fullName}`;
    this.storageTokenService.adminVerify();
  }

  removeToken() {
    this.logservice.logDebugMessage(String('SignButtonToggleService removeToken() '));
    localStorage.removeItem('token');
    this.localStorageService.setToken('');
  }

  setLoggedIn(status: boolean){
    this.logservice.logDebugMessage(String('SignButtonToggleService setLoggedIn() '));
    this.loggedIn = status;
  }
}
