import { Component } from '@angular/core';
import { RestApiService } from '../rest-api.service';

@Component({
  selector: 'app-root',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent {

  initStartQuizButtonCheck :boolean = true;
  quizGoodQuestionCounter:number = 0;
  quizQuestionsCounter:number = 0;
  quizCounter:number = 0;
  selectedResponse: string;
  checkedResponse: boolean = false;
  responseStatus: boolean = false;
  nextButtonText: string = 'Kerek egy uj kviz kerdest';
  responseText: String = '';
  public quiz = {
    word: '',
    responses : ["",]
  }

  constructor(private _api:RestApiService) { 
    this.selectedResponse = '';
    this._api.counter_check("lieben","null").subscribe(
       res => {
         this.quizCounter = res.quizCounter;
         this.quizGoodQuestionCounter = res.quizGoodQuestionCounter;
         this.quizQuestionsCounter = res.quizQuestionCounter;
      }
    )
  }

  next() {
    this.checkedResponse == false ? 
        (this.checkQuizQuestion(), this.checkedResponse = true) : 
        ( this.getNewQuiz(), this.checkedResponse = false)
  }

  getNewQuiz() {
    this.nextButtonText = 'Ellenorzom a valaszt';
    this.initStartQuizButtonCheck = false;
    this._api.getQuiz().subscribe(
      res => { this.quiz.word = res.quizWord;
               this.quiz.responses = res.quizResponses }
    );
  }

  checkQuizQuestion() {
    this._api.counter_check(this.quiz.word,this.selectedResponse).subscribe(
      res => {
        res.quizQuestionCounter > this.quizQuestionsCounter ?
        this.responseStatus = true : this.responseStatus = false;
        this.quizCounter = res.quizCounter;
        this.quizGoodQuestionCounter = res.quizGoodQuestionCounter;
        this.quizQuestionsCounter = res.quizQuestionCounter;
        this.responseText = res.goodResponse == null ? 
        'Helyes volt a valasz!': `A helyes valasz "${res.goodResponse}" volt`;
        this.nextButtonText = 'Kerek egy uj kviz kerdest';
        console.log(res)
      }
    )
  }
}
