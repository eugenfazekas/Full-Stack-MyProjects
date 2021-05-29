import { Injectable } from '@angular/core';
import { LogService } from './log.service';
import { UserModel } from 'src/app/model/user.model';

@Injectable()
export class LocalStorageService {

  private auth_token: string;
  private id: string;
  private userModel = new UserModel();

  constructor( private logservice: LogService ) { 
    this.logservice.logDebugMessage(String('TokenService constructor: '));
    this.initStorage();
   }

   initStorage() {
    this.logservice.logDebugMessage(String('TokenService getLocalToken()'));
    this.auth_token = localStorage.getItem('token') != null ? localStorage.getItem('token') : '';  
    this.id = localStorage.getItem('id') != null ? localStorage.getItem('id') : '';   
    let user = localStorage.getItem('id') != null ? localStorage.getItem('user') : '';
    this.userModel = user != '' ? JSON.parse(user) : null;
       return ;
   }

   setToken(token: string) {
    this.logservice.logDebugMessage(String('TokenService setToken()'));
     this.auth_token = token;
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
}
