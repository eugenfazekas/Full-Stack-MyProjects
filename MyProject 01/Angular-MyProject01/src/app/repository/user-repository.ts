import { Injectable } from '@angular/core';
import { UserModel } from '../model/user.model';
import { UserRestDataSourceService } from '../rest-api/user-rest-data-source.service';
import { LocalStorageService } from '../shared/services/local-storage.service';

@Injectable()
export class UserRepository {

  private user: UserModel = new UserModel();

  constructor(private userRestDataSource: UserRestDataSourceService, private localStorageService: LocalStorageService) {
      userRestDataSource.getUser().subscribe(
        res => this.user = res ,
        err => console.log(err)
      );
   }

   getUser() {
     return this.user;
   }

   updateUser(user: UserModel) {
    this.userRestDataSource.updateUser(user).subscribe(
      res => { this.user = res, this.localStorageService.setUserStorage(user)},
      err =>console.log(err)
    )
   }
}
