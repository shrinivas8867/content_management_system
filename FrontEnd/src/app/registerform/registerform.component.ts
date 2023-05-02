import { Component,Output,EventEmitter } from '@angular/core';
import {  AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { List } from 'src/assets/list';
import { FormServiceService } from '../service/form-service.service';
import { Router } from '@angular/router';

@Component({ 
  selector: 'app-registerform',
  templateUrl: './registerform.component.html',
  styleUrls: ['./registerform.component.css']
})
export class RegisterformComponent {


  lists:List={};
  mustMatch:boolean=false;
  code:any=null;

  viewcode:boolean=false;

  genders:string[]=["Male","Female"];

  role:string[]=["role_user","admin_admin"];


  constructor (private fb: FormBuilder,private router:Router, private _snackBar: MatSnackBar, private listservice:FormServiceService){}

  forms=this.fb.group
  ({
      firstname:['',Validators.required],
      lastname:['',[Validators.required,Validators.minLength(2)]],
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)]],
      role:['',Validators.required],
      gender:['',Validators.required],
      age:['',[Validators.required,Validators.min(18)]],
      pnumber:['',[Validators.required,Validators.pattern('[7-9]{1}[0-9]{9}')]],
      
      
  });


  get eventFirst(){return this.forms.get('firstname');}
  get eventLast(){return this.forms.get('lastname');}
  get eventemail(){return this.forms.get('email');}
  get eventpassword(){return this.forms.get('password');}
  get eventrole(){return this.forms.get('role');}
  get eventgender(){return this.forms.get('gender');}
  get eventage(){return this.forms.get('age');}
  get eventphone(){return this.forms.get('pnumber');}
 
  

  ngOnInit():void{
 }

 @Output()
  addText: EventEmitter<List> = new EventEmitter<List>();


 


  adduser(){
    if(this.code==='abc123'|| this.code===null){
          console.log(this.forms.value);
          this.listservice.adddata(this.forms.value).subscribe({next:data=>{
            alert("User registered")

            console.log(data)
            this.router.navigateByUrl("/login")
            
          },
        error:e=>{
          alert("Failed to Register");
        }
        });
    }
  
  
}

}

