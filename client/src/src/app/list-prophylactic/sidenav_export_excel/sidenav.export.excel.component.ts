import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
import { OnInit } from '@angular/core';
import { PeopleDatabase } from '../people-database';
import { ListExcelFiles } from '../../model/list.files.excel';
import { User } from '../../model/user';
import {DomSanitizer} from '@angular/platform-browser';
import * as FileSaver from 'file-saver';


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
	
		constructor(private personSearchIsurService: PeopleDatabase,private sanitizer:DomSanitizer){}
	
	ngOnInit():void {
//	this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
//	 this.getListNameFiles(this.currentUser['role'][0].id);
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
	 	 this.personSearchIsurService.listFiles(data)
	 	 .then(res => {this.listExcelFiles = res});
	 }

test():void{


}

downloadExcel(data: string):void{
this.personSearchIsurService.downloadExcel(data,this.currentUser['role'][0].id,'download');

}



}