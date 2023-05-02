import { Component } from '@angular/core';
import {  AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../service/auth.service';


import { FormServiceService } from '../service/form-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

   

  constructor(private fb: FormBuilder,private authservice:AuthService,private listservice:FormServiceService,private router:Router, private navbar:NavbarComponent){}

  log=this.fb.group({
    email:['',Validators.required],
    password:['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)]]
  });

  get eventemail(){return this.log.get('email');}
  get eventpassword(){return this.log.get('password');}

  respons:any;
  login(){
    this.listservice.logins(this.log.value).subscribe({next: 
      data=>{
        console.log(data);
        this.respons=data;
        if(this.respons.status==='active'){
          console.log(this.respons.token);
          console.log(this.respons.role);
          console.log(this.respons.message);
          console.log(this.respons.status);
          localStorage.setItem("jwt",this.respons.token);
          localStorage.setItem("role",this.respons.role);
          localStorage.setItem("status",this.respons.status);
          alert("Login done");
          this.navbar.buttonschange();
          if((this.respons.role=="role_user"|| this.respons.role=="admin_admin")&& this.authservice.login(this.respons.token)){
            this.router.navigateByUrl("/viewcontents")
          }
        }else{
        alert("This account is deactivated To Activate this account contact admin")
        }
      },error:e=>{
        alert("Email or Password Is Wrong");
      }
    
    });
  }



}
