import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserModel } from 'src/app/model/user.model';
import { StorageUserService } from 'src/app/shared/services/storage-user.service';
import { LogService } from 'src/app/shared/services/log.service';
import { UserRepository } from 'src/app/repository/user-repository';

@Component({
  selector: 'app-first-steps',
  templateUrl: './first-steps.component.html',
  styleUrls: ['./first-steps.component.css']
})
export class FirstStepsComponent implements OnInit {

  userModel : UserModel = new UserModel();
  editProfile: boolean = false;
  formSubmitted: boolean = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isOptional = false;

  constructor(private _formBuilder: FormBuilder, private storageUserService: StorageUserService ,
    private logservice: LogService, private userRepository: UserRepository) {}

  ngOnInit() {

    this.firstFormGroup = this._formBuilder.group({
      firstName: ['',[ Validators.required, Validators.minLength(3),Validators.maxLength(30)]],
      lastName: ['', [Validators.required, Validators.minLength(3),Validators.maxLength(30)]]
    });

    this.secondFormGroup = this._formBuilder.group({
      country: ['',[Validators.required, Validators.minLength(3),Validators.maxLength(30)]],
      city: ['',[Validators.required, Validators.minLength(3),Validators.maxLength(30)]],
      street: ['', [Validators.required, Validators.minLength(3),Validators.maxLength(30)]],
      number: ['', [Validators.required, Validators.minLength(1),Validators.maxLength(30)]]
    });
  }



  submitForm() {
    this.userModel.id = this.storageUserService.getId()  ; 
      Object.keys(this.firstFormGroup.controls).forEach(c => this.userModel[c] = this.firstFormGroup.controls[c].value);
      Object.keys(this.secondFormGroup.controls).forEach(c => this.userModel.address[c] = this.secondFormGroup.controls[c].value);
      if(this.firstFormGroup.valid && this.firstFormGroup.valid){
        this.logservice.logDebugMessage(String('EditUserDetailsComponent submitForm() '));
        this.userRepository.updateUser(this.userModel);
        this.formSubmitted = true;
        this.editProfile = false;
        localStorage.setItem('user', JSON.stringify(this.userModel));
      }
  }
}
