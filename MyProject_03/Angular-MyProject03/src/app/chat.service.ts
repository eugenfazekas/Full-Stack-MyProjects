import { Injectable } from '@angular/core';
import { ChatModel } from './chat';
import { MessageModel } from './message';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private userChats: ChatModel[] ;
  public userChat = new ChatModel();

  constructor() {
    this.userChat.messages = []; 
  this.userChats = []  }

  getIndexOfChat(userName: string): number {
    let index = -1;
    for(let i = 0; i < this.userChats.length ; i++) 
      userName ==  this.userChats[i].username ? index = i : null;
    console.log('getIndexOfChat index: ', index);
    return index;
  }

  createNewChat(userName: string) {
    this.userChats.push(new ChatModel(userName));
    console.log('Created new chat for with user: ', userName);
  }

  addSendedMessageToChat(s_user: string, myUserName:string, message:string, date: string) {
    let index = this.getIndexOfChat(s_user);
    index == -1 ? ( this.createNewChat(s_user),  
                    index = this.getIndexOfChat(s_user),
                    this.userChats[index].messages = [],
                    this.userChats[index].messages?.push(new MessageModel(myUserName,message,date)),
                    this.setActiveChat(s_user),
                    console.log('Added first message with user: ',s_user),
                    console.log('this.userChat: ',this.userChat),
                    console.log('this.userChat.messages?.length: ',this.userChat.messages?.length)
                  ) : ( 
                      this.userChats[index].messages?.push(new MessageModel(myUserName,message,date)),
                      this.setActiveChat(s_user),
                      console.log('Added message with user: ',s_user) ,
                      console.log('this.userChat: ',this.userChat),
                      console.log('this.userChat.messages?.length: ',this.userChat.messages?.length)
                  );

  }

  addReceivedMessageToChat(userName: string, message:string, date:string) {
    let index = this.getIndexOfChat(userName);
    index == -1 ? ( this.createNewChat(userName),  
                    index = this.getIndexOfChat(userName),
                    this.userChats[index].messages = [],
                    this.userChats[index].messages?.push(new MessageModel(userName,message,date)),
                    this.setActiveChat(userName),
                    console.log('Added first message with user: ',userName),
                    console.log('this.userChat: ',this.userChat),
                    console.log('this.userChat.messages?.length: ',this.userChat.messages?.length)
                  ) : ( 
                      this.userChats[index].messages?.push(new MessageModel(userName,message,date)),
                      this.setActiveChat(userName),
                      console.log('Added message with user: ',userName) ,
                      console.log('this.userChat: ',this.userChat),
                      console.log('this.userChat.messages?.length: ',this.userChat.messages?.length)
                  );

  }

  setActiveChat(userName: string) {
    let index = this.getIndexOfChat(userName)
    this.userChat = this.userChats[index];
  }
}
