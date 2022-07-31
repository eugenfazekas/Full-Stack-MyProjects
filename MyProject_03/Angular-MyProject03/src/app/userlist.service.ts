import { Injectable } from '@angular/core';
import { UserListModel } from './userlist';

@Injectable({
  providedIn: 'root'
})
export class UserlistService {

  userName = '';
  selectedUser: string = '';
  users: UserListModel[]; 

  constructor() { 
    this.users = [];
  }

  getIndexOfUser(userName: string): number {
    let index = -1;
    for(let i = 0; i < this.users.length ; i++) 
      userName ==  this.users[i].username ? index = i : null;
    console.log('getIndexOfUser index: ', index);
    return index;
  }

  addNewNotification(username: string) {
    let index = this.getIndexOfUser(username);
    let counter = this.users[index].newMessageCounter;
    counter != undefined ? counter++ : counter = 1;
    index != -1  && this.selectedUser != username ? (
      this.users[index].newMessage = true ,
      this.users[index].newMessageCounter = counter
      ) : null  ;
      console.log('addNewNotification newMessageCounter: ',this.users[index].newMessageCounter)
  }

  deleteNotification(username: string) {
    let index = this.getIndexOfUser(username);
    index!= -1 && this.users[index].newMessage == true ? (
      this.users[index].newMessage = false ,
      this.users[index].newMessageCounter = 0
    ) : null
  }

    setSelectedUser(user:string){
      this.selectedUser = user;
      this.deleteNotification(user);
  }
}
