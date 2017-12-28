import { Component } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../environments/environment';

@Component({
    selector:'app-informirovanie',
    templateUrl: './listinformirovanieheader.component.html',
  	styleUrls: ['./listinformirovanieheader.component.scss'],
  	  animations: [
	trigger('flyInOut', [
            state('in', style({ opacity: 1, transform: 'translateX(0)' })),
            transition('void => *', [
                style({
                    opacity: 0,
                    transform: 'translateX(-100%)'
                }),
                animate('0.6s ease-in')
            ]),
            transition('* => void', [
                animate('0.2s 0.1s ease-out', style({
                    opacity: 0,
                    transform: 'translateX(100%)'
                }))
            ])
    ])
    
    ]
})
export class ListInformirovanieHeader{
	step = 0;

  setStep(index: number) {
    this.step = index;}

  nextStep() {
    this.step++;}

  prevStep() {
    this.step--;}

   /*list_insur_menu: string = environment.list_insur_menu;
   exit_app: string = environment.exit_app;
   list_inform_header: string  = list_inform_header;*/
}