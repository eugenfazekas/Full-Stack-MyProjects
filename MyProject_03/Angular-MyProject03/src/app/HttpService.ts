import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({providedIn:'root'})

export class HTTPService {

    url = 'http://localhost:8080';

    constructor(private _http: HttpClient){}

    registration(userName: string):Observable<string> {
       return  this._http.get(`${this.url}/registration/${userName}`,{ responseType:'text'});

    }

    fetchAllUsers():Observable<string[]>{
        return this._http.get<string[]>(this.url+'/fetchAllUsers');
    }
}