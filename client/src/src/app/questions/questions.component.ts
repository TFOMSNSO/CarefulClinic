import { Component } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger } from '@angular/animations';
import { QuestionsService } from './questions.service';
import { OnInit } from '@angular/core';

import { Question } from '../model/question';

@Component({
  selector: 'questions-component',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss'],
  providers: [QuestionsService],
  animations: [

    trigger('listAnimation', [
      transition('* => *', [

        query(':enter', style({ opacity: 0 }), {optional: true}),

        query(':enter', stagger('300ms', [
          animate('1s ease-in', keyframes([
            style({opacity: 0, transform: 'translateY(-75%)', offset: 0}),
            style({opacity: .5, transform: 'translateY(35px)',  offset: 0.3}),
            style({opacity: 1, transform: 'translateY(0)',     offset: 1.0}),
          ]))]), {optional: true}),
        query(':leave', stagger('300ms', [
          animate('1s ease-in', keyframes([
            style({opacity: 1, transform: 'translateY(0)', offset: 0}),
            style({opacity: .5, transform: 'translateY(35px)',  offset: 0.3}),
            style({opacity: 0, transform: 'translateY(-75%)',     offset: 1.0}),
          ]))]), {optional: true})
      ])
    ]),
    trigger('explainerAnim', [
      transition('* => *', [
        query('.col', style({ opacity: 0, transform: 'translateX(-40px)' })),

        query('.col', stagger('500ms', [
          animate('800ms 1.2s ease-out', style({ opacity: 1, transform: 'translateX(0)' })),
        ])),

        query('.col', [
          animate(1000, style('*'))
        ])
        
      ])
    ])

  ]
  
})
export class QuestionsComponent  implements OnInit{
   
   
   questions_rest: Question[];
	
   items = [];

  constructor(private questionsService: QuestionsService) {
    this.items = ['Hey this is an item', 'Here is another one','This is awesome'];
  }
  pushItem() {
    this.items.push('Oh yeah that is awesome');
  }
  removeItem() {
    this.items.pop();
  }
  
   getQuestions(): void {
    this.questionsService.getAllQuestions().then(questions_rest => this.questions_rest = questions_rest);
   }
   
   
  
  ngOnInit(): void {
    this.getQuestions();
    console.log('TEST \n'+this.questions_rest);
  }

  

}
