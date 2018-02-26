import { Component, OnInit } from "@angular/core";
import "rxjs/add/operator/filter";
import {ShareService} from '../share_service/share.service';
import {AdminService} from '../admin.service';


interface IBreadcrumb{
	url: string;
	title: string;
}

@Component({
  selector: "breadcrumb",
  styleUrls: ['./breadcrumb.component.scss'],
  template: `
    <ul class="breadcrumb">
      <li class="breadcrumb-item" *ngFor="let breadcrumb of breadcrumbs; let i = index " (click)="processing(breadcrumb.url, i)">
        {{breadcrumb.title}}
      </li>
    </ul>
  `
})
export class BreadcrumbComponent {
	public breadcrumbs : IBreadcrumb[] = [];
	
	constructor(private adminService: AdminService,private shareService:ShareService) { 
		this.shareService.messageSource.subscribe((message: string) => {
			if(message !== ''){
			 let tmp_mas = message.split('!');
			 if (tmp_mas.length == 3) this.breadcrumbs = [];
			 
				let breadcrumb: IBreadcrumb = {
					url : tmp_mas[0],
					title : tmp_mas[1]
				};
				
				this.breadcrumbs.push(breadcrumb);
            }
        });
	}

	processing(note:string, b:number): void{
		//console.log('processing '+ note);
		console.log(this.breadcrumbs+'\n'+b)
		this.breadcrumbs = this.breadcrumbs.slice(0,b+1);
		this.shareService.messageSource_2.next(note);
		
	}
}
