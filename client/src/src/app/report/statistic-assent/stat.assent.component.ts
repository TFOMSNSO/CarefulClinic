import { Component,OnInit } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../../environments/environment';
import {Router} from "@angular/router";

@Component({
    selector:'assent-component',
    templateUrl: './stat.assent.component.html',
  	styleUrls: ['./stat.assent.component.scss'],
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
export class StatisticsAssentComponent implements OnInit{
	 
   _title_stat_assent: string = environment.title_stat_assent;
   /*exit_app: string = environment.exit_app;
   list_inform_header: string  = list_inform_header;*/
   
   constructor(){
	}
	
	ngOnInit() {
	
	}
   
	
 
   
}