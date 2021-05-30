import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';

@Injectable()
export class StorageUserService {

  constructor(private storageService: LocalStorageService) {
   }

   getId() {
     return this.storageService.getId();
   }

   getFullname() {
     let fullName = this.storageService.getUser() != null ? this.storageService.getUser().fullName : '';
     return fullName;
   }
}
