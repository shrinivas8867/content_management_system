import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RouteService } from './service/route.service';
@Injectable({
  providedIn: 'root'
})
export class CanActivateGuard implements CanActivate {

  constructor(private http:HttpClient, private routerss:RouteService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      // let httpHeaders=new HttpHeaders({
      //   'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  
      // });
      if(localStorage.getItem('jwt')=== null){
          this.routerss.toHome();
        return false;
      }else {
        return true;
      }

    
  }
  
}
