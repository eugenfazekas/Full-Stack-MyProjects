import { Injectable } from '@angular/core';
import { LogService } from './log.service';
import { UserModel } from 'src/app/model/user.model';
import { UserStorage } from 'src/app/model/user.storage';

@Injectable()
export class LocalStorageService {

  private auth_token: string;
  private id: string;
  private userStorage ;

  constructor( private logservice: LogService ) { 
    this.logservice.logDebugMessage(String('TokenService constructor: '));
    this.initStorage();
   }

   initStorage() {
    this.logservice.logDebugMessage(String('TokenService getLocalToken()'));
    this.auth_token = localStorage.getItem('token') != null ? localStorage.getItem('token') : '';  
    this.id = localStorage.getItem('id') != null ? localStorage.getItem('id') : '';   
    let user = localStorage.getItem('user') != null ? localStorage.getItem('user') : '';
    this.userStorage = user != '' ? JSON.parse(user) : null;
       return ;
   }

   setToken(token: string) {
    this.logservice.logDebugMessage(String('TokenService setToken()'));
     this.auth_token = token;
   }

   removeToken() {
    this.logservice.logDebugMessage(String('LocalStorageService removeToken() '));
    localStorage.removeItem('token');
    this.auth_token = '';
  }

   setUserStorage(user: UserModel) {
      this.userStorage = new UserStorage();
      this.userStorage.fullName = user.firstName + ' ' +user.lastName;
      Object.keys(user).forEach(c => { if(user[c] != undefined)  this.userStorage[c] = user[c] } );
      localStorage.setItem('user', JSON.stringify(this.userStorage));
  }

   setId(id: string) {
     this.id = id;
   }

   getToken() {
     return this.auth_token;
   }

   getId() {
     return this.id;
   }

   getUser() {
     return this.userStorage;
   }
}
