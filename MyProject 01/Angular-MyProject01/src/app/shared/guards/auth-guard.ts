import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router} from "@angular/router";
import { StorageTokenService } from '../services/storage-token.service';
import { LogService } from '../services/log.service';

@Injectable()
export class AuthGuard {
  private jwt = new JwtHelperService();

  constructor(private storageTokenService: StorageTokenService, private router: Router,private logservice: LogService) { }
 
      canActivate(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): boolean {   
            this.logservice.logDebugMessage(String('AuthGuard canActivate() ')); 
              let token = this.storageTokenService.getToken();
              let decodedToken = this.jwt.decodeToken(token);
              let userAuth: boolean = false;
              if(token != '') {
                for(let auth of decodedToken.authorities){
                  if(decodedToken.authorities[auth] = 'user'){
                    userAuth = true;
                  }
                }
              }
              if(!this.jwt.isTokenExpired(token) && userAuth ) {
                return true ;
          } else {
            token = '';
          this.router.navigateByUrl('login');
                return false ;
      }
  }
}

