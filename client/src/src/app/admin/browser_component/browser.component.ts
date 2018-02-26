import { Component, OnInit, ViewChild,Output,ElementRef,EventEmitter,ViewEncapsulation } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../../environments/environment';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {AdminService} from '../admin.service';
import { ListFiles } from '../../model/list.files';
import { User } from '../../model/user';
import {ShareService} from '../share_service/share.service';


@Component({
    selector:'browser-component',
    templateUrl: './browser.component.html',
  	styleUrls: ['./browser.component.scss'],
	encapsulation: ViewEncapsulation.None,
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
export class BrowserComponent  implements OnInit{
	 
   
   /*exit_app: string = environment.exit_app;
   list_inform_header: string  = list_inform_header;*/
   
   public listFilesInformingBrowser: ListFiles[] = [];
   private currentUser: User;
   
   
   
   constructor(private adminService: AdminService,private shareService:ShareService) { 
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		
		this.shareService.messageSource_2.subscribe((message: string) => {
			if(message !=='')
			this.getListNameFiles(message);
		});
   }
   
     ngOnInit() {
		//this.getListNameFiles(environment.path_informing);
	}
  
	getListNameFiles(p_path: string){
		console.log('dsdsds');
    	 this.adminService.listFilesInformingBrowser(p_path)
	 	 .then(res => {this.listFilesInformingBrowser = res});
	}
	
	/* Отправляем имя папки в компонент breadcrumbs*/
	send(v : any){
	  console.log('v '+ v);
	  this.shareService.messageSource.next(v);
	}
}