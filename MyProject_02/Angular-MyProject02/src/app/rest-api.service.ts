import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QuizModel } from './quizModel';
import { QuizCounterModel } from './quizCounterModel';

@Injectable()
export class RestApiService {

  private _url: string = 'http://localhost:8080';

  private param1(german: string, hungarian: string) { 
    return new HttpParams()
           .set('german', german)
           .set('hungarian', hungarian);               
  }

  constructor(private _http: HttpClient) { }

  getQuiz():Observable<QuizModel> {
    return this._http.get<QuizModel>(`${this._url}/`,{});
  }

  counter_check(german: string, hungarian: string):Observable<QuizCounterModel> {
    return this._http.get<QuizCounterModel>(`${this._url}/check`,{ 'params':this.param1(german,hungarian)});
  }

}
