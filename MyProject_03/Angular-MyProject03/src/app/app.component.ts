import { Component } from '@angular/core';
import { StompService } from './StompService';
import { FormBuilder } from '@angular/forms';
import { ChatService } from './chat.service';
import { ChatModel } from './chat';
import { UserlistService } from './userlist.service';

@Component({ 
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  user: string = 'User Not Logged!';
  message:string ='';
  selectedUser: string = '';
  todayDate : Date = new Date();

  constructor(private formBuilder:FormBuilder,
     public stompService: StompService,
     public chatService: ChatService,
     public userListService: UserlistService){
     }
  
  userNameForm = this.formBuilder.group({
    userName: ''
  });

  submitUserName() {
    let name = this.userNameForm.controls['userName'].value;
    name != null ? this.stompService.registration(name): null;
    this.userNameForm.reset();
    
  }

  selectUser(user:string) {
    this.userListService.setSelectedUser(user);
    this.chatService.setActiveChat(user);
  }

  sendMessage(){
    let s_user = this.userListService.selectedUser;
    let date = this.getDate().toString();
    if(s_user != '' && this.message != '' && s_user != this.userListService.userName){
      console.log('app.comp.sendmsg: s_user - message: '+s_user + ' '+this.message)
      this.stompService.sendMsg(s_user,this.message, date);
      this.chatService.addSendedMessageToChat(s_user,this.userListService.userName,this.message,date);
      this.message = '';
    }
  }

  getDate(): Date {
   return new Date();
  }
}