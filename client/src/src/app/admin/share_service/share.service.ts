import {Injectable}      from '@angular/core'
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class ShareService {

	constructor(){}
	
	public messageSource: BehaviorSubject<string> = new BehaviorSubject('');
	public messageSource_2: BehaviorSubject<string> = new BehaviorSubject('');
  
}