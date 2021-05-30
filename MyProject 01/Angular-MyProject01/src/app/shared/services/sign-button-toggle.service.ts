import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { StorageTokenService } from './storage-token.service';
import { LogService } from './log.service';
import { LocalStorageService } from './local-storage.service';
import { StorageUserService } from './storage-user.service';


@Injectable()
export class SignButtonToggleService {

  constructor(private localStorageService: LocalStorageService, private storageTokenService :StorageTokenService, 
    private logservice: LogService , private storageUserService : StorageUserService) {
    this.logservice.logDebugMessage(String('SignButtonToggleService constructor: '));
     this.name = this.storageUserService.getFullname() != null ? `Welcome ${this.storageUserService.getFullname()}` : '';
  }

  public name; 
  public loggedIn: boolean = localStorage.getItem('token') != null ? true : false; 

  setFullname() {
    this.logservice.logDebugMessage(String('SignButtonToggleService setFullname() '));
    this.name = `Welcome ${this.storageUserService.getFullname()}`;
    this.storageTokenService.adminVerify();
  }

  removeToken() {
    this.localStorageService.removeToken();
  }

  setLoggedIn(status: boolean){
    this.logservice.logDebugMessage(String('SignButtonToggleService setLoggedIn() '));
    this.loggedIn = status;
  }
}
