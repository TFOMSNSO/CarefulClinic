import { Component,OnInit } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../../environments/environment';
import { ListFiles } from '../../model/list.files';
import {AdminService} from '../admin.service';
import {Router} from '@angular/router';
import { User } from '../../model/user';
import {ShareService} from '../share_service/share.service';



@Component({
    selector:'admin-menu-inform',
    templateUrl: './admin.menu.inform.component.html',
  	styleUrls: ['./admin.menu.inform.component.scss']
})
export class AdminMenuInformComponent implements OnInit {
	 
   /*list_insur_menu: string = environment.list_insur_menu;
   exit_app: string = environment.exit_app;
   list_inform_header: string  = list_inform_header;*/
   public listFiles: ListFiles[] = [];
   private currentUser: User;
   public listBrowserFiles = [];
   public location : string = '';
   
   
   constructor(private adminService: AdminService, private router: Router,private shareService:ShareService) { 
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
   }
   
  ngOnInit() {
   this.getListNameFiles();
   this.location = this.router.url;
  }
  
  getListNameFiles(){
    	 this.adminService.listFiles()
	 	 .then(res => {this.listFiles = res});
	}
 
	/* Отправляем нажатое меню в компонент breadcrumbs*/
	send(str : string){
	
		console.log('str '+str);
		console.log('str2 '+location.pathname);
		if(location.pathname =='/admin_cms'){
			this.router.navigate([location.pathname+str]);
			// send to breadcrumbs
			this.shareService.messageSource.next(str+'!Информирование');
		}else{
			let vr = location.pathname.replace('/admin_cms','');
			// send to browser.component
			this.shareService.messageSource_2.next(vr);
			// send to breadcrumbs
			this.shareService.messageSource.next(vr+'!Информирование!from_menu_admin');
		}	
	}

 
}