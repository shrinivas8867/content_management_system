import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AuthService } from '../service/auth.service';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  view:boolean=false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  

  constructor(private breakpointObserver: BreakpointObserver, private authservice:AuthService) {}

  logOut(){

    this.authservice.logout();
    this.view=!this.view
    alert("Thank You.... you were logout now")

  }

  ngOnInit():void{
    
  }

  buttonschange(){
    if(localStorage.getItem('jwt')=== null){
      this.view=false
     
  }else {
    this.view= true;
  }
  }


}
