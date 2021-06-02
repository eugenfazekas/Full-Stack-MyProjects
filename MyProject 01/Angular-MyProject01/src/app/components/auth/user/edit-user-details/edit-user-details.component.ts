import { Component, OnInit } from '@angular/core';
import { UserRepository } from 'src/app/repository/user-repository';
import { FormBuilder, Validators } from '@angular/forms';
import { UserModel } from 'src/app/model/user.model';

import { LogService } from 'src/app/shared/services/log.service';
import { ImageService } from 'src/app/shared/services/image.service';
import { LoggedUserService } from 'src/app/shared/services/logged-user.service';

@Component({
  selector: 'app-edit-user-details',
  templateUrl: './edit-user-details.component.html',
  styleUrls: ['./edit-user-details.component.css']
})
export class EditUserDetailsComponent {

  firstName: string = 'User';
  userModel : UserModel = new UserModel();
  editProfile: boolean = false;
  formSubmitted: boolean = false;
  hide = true;

  constructor(public userRepository: UserRepository, private formBuilder: FormBuilder, private loggedUserService: LoggedUserService,
      private logservice: LogService, public imageService: ImageService) {
      this.logservice.logDebugMessage(String('EditUserDetailsComponent constructor: '));
      this.firstName = loggedUserService.getUser() != null ? loggedUserService.getUser().firstName : 'User';
  }

    editUserDetailsForm = this.formBuilder.group({ 
            firstName: ['',[ Validators.minLength(3) ]],
            lastName: ['',[ Validators.minLength(3) ]],
            address : this.formBuilder.group({
                country: ['',[ Validators.minLength(2) ]],
                city: ['',[ Validators.minLength(3) ]],
                street: ['',[ Validators.minLength(3) ]],
                number: ['',[ Validators.minLength(1) ]],
             })
        });

     pacthEditUserDetailsForm(res: UserModel) {
          this.editUserDetailsForm.controls['firstName'].patchValue(res.firstName);
          this.editUserDetailsForm.controls['lastName'].patchValue(res.lastName);
          res.address ? this.editUserDetailsForm.patchValue({address : { country : res.address.country }}) : '';
          res.address ? this.editUserDetailsForm.patchValue({address : { city : res.address.city }}): '';
          res.address ? this.editUserDetailsForm.patchValue({address : { street : res.address.street }}): '';
          res.address ? this.editUserDetailsForm.patchValue({address : { number : res.address.number }}): '';
     }         

    enableEdit() {
      this.logservice.logDebugMessage(String('EditUserDetailsComponent enableEdit()'));
      this.editProfile = true;
      this.pacthEditUserDetailsForm(this.userRepository.getUser());
    }

    submitForm() {
      this.userModel.id = this.loggedUserService.getId(); 
      Object.keys(this.editUserDetailsForm.controls)
            .forEach(c => this.userModel[c] = this.editUserDetailsForm.controls[c].value);
      if(this.editUserDetailsForm.valid){
        this.logservice.logDebugMessage(String('EditUserDetailsComponent submitForm() '));
        this.userRepository.updateUser(this.userModel);
        this.formSubmitted = true;
        this.editProfile = false;
      }
    } 
    
}