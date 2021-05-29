import { Component } from '@angular/core';
import { UserLoginFormModel } from 'src/app/model/user-login-form.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { Router } from '@angular/router';
import { SignButtonToggleService } from 'src/app/shared/services/sign-button-toggle.service';
import { FormBuilder } from '@angular/forms';
import { LogService } from 'src/app/shared/services/log.service';
import { UserRestDataSourceService } from 'src/app/rest-api/user-rest-data-source.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  userLoginFormModel: UserLoginFormModel = new UserLoginFormModel();

  constructor(private authService: AuthService, private router: Router, private signToogleService: SignButtonToggleService,
              private formBuilder: FormBuilder,private logservice: LogService, private userRestDataSourceService: UserRestDataSourceService, ) {
                this.logservice.logDebugMessage(String('LoginComponent constructor: '));
    this.signToogleService.name = '';
    this.signToogleService.setLoggedIn(false);
  }
 
  loginForm = this.formBuilder.group({ email: this.userLoginFormModel.email , password: this.userLoginFormModel.password});

  submitForm() {
    if(this.loginForm.valid) {
      this.logservice.logDebugMessage(String('LoginComponent submitForm() '));
      this.authService.loginUser(this.userLoginFormModel.email.value ,this.userLoginFormModel.password.value).subscribe(
            res =>  { this.signToogleService.setLoggedIn(true);
                      this.signToogleService.setFullname();
                      console.log('login token',res)
                      this.userRestDataSourceService.getUser(res).subscribe(
                              res => {return  res != null ?  
                                              res.firstName  == null ?  this.router.navigateByUrl('firstSteps') : this.router.navigateByUrl('') // return this
                                                                                                :             
                                                                                                          this.router.navigateByUrl('login') } // or return this
                      )
                  }
           ) 
       }  
    }
  }

