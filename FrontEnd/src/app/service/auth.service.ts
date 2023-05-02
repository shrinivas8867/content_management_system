import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isLoggedIn: boolean=false;

  login(logdetails:any){
    this.isLoggedIn = logdetails===localStorage.getItem('jwt');
    return this.isLoggedIn;
  }
  logout(){
     localStorage.removeItem('jwt');
  }
}
