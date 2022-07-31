import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Injectable } from '@angular/core';
import { HTTPService } from './HttpService';
import { ChatService } from './chat.service';
import { UserListModel } from './userlist';
import { UserlistService } from './userlist.service';

@Injectable({providedIn:'root'})

export class StompService{

    url = 'http://127.0.0.1:8080';
    private stompClient: any;
    private disabled = false;

    constructor(private _http: HTTPService, private chatService: ChatService,
        private userListService: UserlistService) {
    }

    connectToChat(userName:string){
        const socket = new SockJS('http://127.0.0.1:8080/gs-guide-websocket');
        this. stompClient = Stomp.over(socket);
        const _this = this;

        console.log('Connecting To Chat');

        this.stompClient.connect({}, function (frame: any) {
            _this.setConnected(true);
            console.log('Connected: ' + frame);
      
            _this.stompClient.subscribe(`/topic/messages/${userName}`, _this.messageCallback);
            _this.stompClient.subscribe(`/topic/users/${userName}`, _this.usersCallback);
            _this.userListService.userName = userName;
          });

        return;
    }

    sendMsg(from:string, text:string, date:string) {
        console.log('from: '+from+' selectedUser: '+this.userListService.selectedUser+  ' messsage:'+text)
        this.stompClient.send('/app/chat/' + this.userListService.selectedUser, {}, JSON.stringify({
            fromLogin: this.userListService.userName,
            message: text,
            date: date
        }));
    }

    messageCallback = (response: any) => { 
        let data = JSON.parse(response.body);
        console.log('Response from chat server connect to chat: ',data);
        this.chatService.addReceivedMessageToChat(data.fromLogin, data.message, data.date);
        this.userListService.addNewNotification(data.fromLogin);
    }

    usersCallback = (response: any) => {
        let data = response.body; 
        console.log('response.body: ',response.body)
        this.userListService.getIndexOfUser(data) == -1 ?
        this.userListService.users.push(new UserListModel(data)) : null;
        console.log('users form callback: ',' this.users: ',this.userListService.users, ' data: ',data);
    }

    registration(userName: string){
        let response = 'User Not logged';
        this._http.registration(userName).subscribe(res => {
            console.log('reg res '+res)
            res == 'User Registered' ? (
                this.connectToChat(userName),
                this.userListService.userName = userName,
                console.log('User Registered ' +userName),
                this.fetchAllUsers()
                 ) : null
        })

    }

    fetchAllUsers() {
        this._http.fetchAllUsers().subscribe( 
            res => {
                for(let username of res) 
                this.userListService.userName != username ? this.userListService.users.push(new UserListModel(username)) : null
                console.log('fetched all users ' + res);
            } )
    }
    


    setConnected(connected: boolean) {
        this.disabled = !connected;
    
        if (connected) {

          // this.greetings = [];
        }
      }

    disconnect() {
        if (this.stompClient != null) {
          this.stompClient.disconnect();
        }
    
        this.setConnected(false);
        console.log('Disconnected!');
      }
}
