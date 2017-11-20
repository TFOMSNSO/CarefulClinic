import {Directive, HostListener, HostBinding, EventEmitter, Output, Input} from '@angular/core';
//import {forEach} from "@angular/router/src/utils/collection";

@Directive({
  selector: '[appDnd]'
})
export class DndDirective {
  @Input() private allowed_extensions : Array<string> = [];
  @Output() private filesChangeEmiter : EventEmitter<File[]> = new EventEmitter();
  @Output() private filesInvalidEmiter : EventEmitter<File[]> = new EventEmitter();
  @HostBinding('style.background') private background = '#eee';

  constructor() { }

  @HostListener('dragover', ['$event']) public onDragOver(evt){
    evt.preventDefault();
    evt.stopPropagation();
    this.background = '#999';
  }

  @HostListener('dragleave', ['$event']) public onDragLeave(evt){
    evt.preventDefault();
    evt.stopPropagation();
    this.background = '#eee'
  }

  @HostListener('drop', ['$event']) public onDrop(evt){
    evt.preventDefault();
    evt.stopPropagation();
    this.background = '#eee';
    let files = evt.dataTransfer.files;
    let valid_files : Array<File> = [];
    let invalid_files : Array<File> = [];
    if(files.length > 0){
      for (let i = 0; i < files.length; i++) {
      		let file = files.item(i);
	      	let ext = file.name.split('.')[file.name.split('.').length - 1];
	      	console.log('wwwwwwww '+this.allowed_extensions);
	        if(this.allowed_extensions.lastIndexOf(ext) != -1){
	        console.log(files.length+' test1');
	          valid_files.push(file);
	        }else{
	        console.log('test2');
	          invalid_files.push(file);
	        }
      }
      
      valid_files.length ? this.filesChangeEmiter.emit(valid_files) : '';
      invalid_files.length ? this.filesInvalidEmiter.emit(invalid_files) : '';
    }
  }

}
