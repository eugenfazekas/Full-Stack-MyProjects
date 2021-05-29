import { Component, OnInit } from '@angular/core';
import { LogService } from 'src/app/shared/services/log.service';
import { StorageTokenService } from 'src/app/shared/services/storage-token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  reloadcheck: boolean = false;

  constructor(private tokenService : StorageTokenService, private logservice: LogService) {
    this.logservice.logDebugMessage(String('HomeComponent constructor: '));
   }

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.reloadcheck == true ?  window.location.reload() : null ;
    this.reloadcheck = true ; 
  }

}
