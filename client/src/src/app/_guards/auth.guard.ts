import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (localStorage.getItem('currentUser')) {
			let vl = JSON.parse(localStorage.getItem('currentUser'));
			if(route.url[0]+'' == 'admin_cms' && vl.username != 'Tfoms' && vl.role[0].role != 'Admin' ){
				this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
				return false;
			}
			
            // logged in so return true
            return true;
        }
	

        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }
	
	
}