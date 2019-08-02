import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
import { OnInit } from '@angular/core';
import { PeopleDatabase } from '../people-database';
import { User } from '../../model/user';
import { ListExcelFiles } from '../../model/list.files.excel';
import { environment } from '../../../environments/environment';


@Component({
  moduleId: module.id,
  selector: 'sidenav-upload-data-component',
  templateUrl: './sidenav.upload.data.component.html',
  styleUrls: ['./sidenav.upload.data.component.scss'],
  providers: [PeopleDatabase]

})


export class SidenavUploadDataComponent   implements OnInit{

	private currentUser: User;
	progress_bar_upload = false;
	@Output() progress_bar_emit: EventEmitter<any> = new EventEmitter<any>();
	@ViewChild('sidenav_upload_data') variable_sidenave: any;
	public fileList : any = [];
  	public invalidFiles : any = [];
  	public listUploadedFiles: ListExcelFiles[] = [];
  	public flag: boolean = true;

  	_pull: string = environment.pull;
  	_drop: string = environment.drop;
  	data_not_found = environment.data_not_found;

	constructor(private personSearchIsurService: PeopleDatabase){}

ngOnInit():void {
 }

	init():void{
		  this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
	 	  this.getListNameUploadedFiles(this.currentUser['role'][0].id);
  	}

 open(){
 	this.variable_sidenave.toggle(true);
 }

 onFilesChange(fileList : Array<File>){
 	this.progress_bar_upload = true;
    this.fileList = fileList;
    this.personSearchIsurService.upload(fileList)
  	.then(result =>{
  		this.progress_bar_emit.emit({note :'false', result: result.status, status: result._body});
  		this.progress_bar_upload = false;
  		this.init();
  	});
  }

  onFileInvalids(fileList : Array<File>){
    this.invalidFiles = fileList;
    this.progress_bar_upload = true;
    this.progress_bar_emit.emit({note :'false', result: 404, status: '\u0414\u0430\u043D\u043D\u044B\u0439 \u0444\u043E\u0440\u043C\u0430\u0442 \u043D\u0435\u0434\u043E\u043F\u0443\u0441\u0442\u0438\u043C \u0434\u043B\u044F \u0437\u0430\u0433\u0440\u0443\u0437\u043A\u0438'});
    this.progress_bar_upload = false;
    this.init();
  }


  private getListNameUploadedFiles(data : number): void{
  		this.flag = true;
	 	 this.personSearchIsurService.listFiles2(data)
	 	 .then(res => {
	 	 this.listUploadedFiles = res;
	 	 this.flag = false;
	 	 });
}

downloadExcel(data: string):void{
this.personSearchIsurService.downloadExcel(data,this.currentUser['role'][0].id,'upload');

}


}
