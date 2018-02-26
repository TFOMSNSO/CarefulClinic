import { Component,OnInit } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../environments/environment';
import {Router} from "@angular/router";

@Component({
    selector:'amdin-component',
    templateUrl: './admin.component.html',
  	styleUrls: ['./admin.component.scss'],
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
export class AdminComponent implements OnInit{
	 
   _admin_inform_mainsubmodul_title: string = environment.admin_inform_mainsubmodul_title;
   /*exit_app: string = environment.exit_app;
   list_inform_header: string  = list_inform_header;*/
   public location: any = '';
   
   constructor(private router: Router){
		 this.location = this.router.url;
		
	}
	
	ngOnInit() {
		//this.location = this.router.url;
		//console.log('this.location '+this.location);
	}
   
	
 
   
}