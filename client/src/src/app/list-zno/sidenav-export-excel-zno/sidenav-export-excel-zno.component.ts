import { Component, OnInit, ViewChild } from '@angular/core';
import {PeopleDatabase} from "../../list-prophylactic/people-database";
import {ListExcelFiles} from "../../model/list.files.excel";
import {environment} from "../../../environments/environment";
import {User} from "../../model/user";
import {DomSanitizer} from '@angular/platform-browser';


@Component({
  moduleId: module.id,
  selector: 'app-sidenav-export-excel-zno',
  templateUrl: './sidenav-export-excel-zno.component.html',
  styleUrls: ['./sidenav-export-excel-zno.component.css'],
  providers: [PeopleDatabase]
})
export class SidenavExportExcelZnoComponent implements OnInit {

  @ViewChild('sidenav_export_excel') variable_sidenave: any;
  public listExcelFiles: ListExcelFiles[] = [];
  private currentUser: User;
  public flag: boolean = true;
  data_not_found = environment.data_not_found;

  constructor(private personSearchIsurService: PeopleDatabase,private sanitizer:DomSanitizer){}
  ngOnInit() {
  }

  sanitize(url:string){
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  init():void{
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.getListNameFiles(this.currentUser['role'][0].id);
  }

  open(){
    console.log('export excel zno open()');
    this.variable_sidenave.toggle(true);
  }

  downloadExcel(data: string):void{
    this.personSearchIsurService.downloadExcel(data,this.currentUser['role'][0].id,'download');
  }

  private getListNameFiles(data : number): void{
    this.flag = true;
    this.personSearchIsurService.listFiles(data)
      .then(res => {this.listExcelFiles = res;console.log(JSON.stringify(this.listExcelFiles))});
    this.flag = false;
  }

}
