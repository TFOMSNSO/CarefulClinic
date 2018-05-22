import { Component } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../environments/environment';

@Component({
    selector:'app-header',
    templateUrl: './header.component.html',
  	styleUrls: ['./header.component.scss'],
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
export class HeaderComponent{
   list_insur_menu: string = environment.list_insur_menu;
   exit_app: string = environment.exit_app;
   list_inform_header: string  = environment.list_inform_header;
   statistics_inform_header: string  = environment.statistics_inform_header;
   statistics_assent_header: string  = environment.statistics_assent_header;
   
   _menu_main_page:string  = environment.menu_main_page;
   _menu_report_page:string  = environment.menu_report_page;
   _menu_admin:string  = environment.menu_admin;
   _menu_admin_inform_allyear:string  = environment.menu_admin_inform_allyear;
   _sp3_menu: string = environment.sp3_menu;
   _sp3_menu_expertisa: string = environment.sp3_menu_expertisa;
   _sp3_menu_d_reestr: string = environment.sp3_menu_d_reestr;
   
 

    public LOGO = require("./logo_small.png");
}