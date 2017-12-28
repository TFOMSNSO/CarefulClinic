import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
import { OnInit } from '@angular/core';
import { PeopleDatabase } from '../people-database';
import { ListExcelFiles } from '../../model/list.files.excel';
import { User } from '../../model/user';
import {DomSanitizer} from '@angular/platform-browser';
import * as FileSaver from 'file-saver';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../../environments/environment';


@Component({
  moduleId: module.id,
  selector: 'sidenav_export_excel-component',
  templateUrl: './sidenav.export.excel.component.html',
  styleUrls: ['./sidenav.export.excel.component.scss'],
  providers: [PeopleDatabase]
  
})


export class SidenavExportExcelComponent   implements OnInit{

	@ViewChild('sidenav_export_excel') variable_sidenave: any;
	public listExcelFiles: ListExcelFiles[] = [];
	private currentUser: User;
	public flag: boolean = true;
	data_not_found = environment.data_not_found;
	
		constructor(private personSearchIsurService: PeopleDatabase,private sanitizer:DomSanitizer){}
	
	ngOnInit():void {
  }

sanitize(url:string){
    return this.sanitizer.bypassSecurityTrustUrl(url);
 }
  init():void{
	  this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
	 this.getListNameFiles(this.currentUser['role'][0].id);
  }

 open(){
 	this.variable_sidenave.toggle(true);
 }
 
    private getListNameFiles(data : number): void{
    	 this.flag = true;
	 	 this.personSearchIsurService.listFiles(data)
	 	 .then(res => {this.listExcelFiles = res});
	 	 this.flag = false;
	 }


downloadExcel(data: string):void{
this.personSearchIsurService.downloadExcel(data,this.currentUser['role'][0].id,'download');
}


}