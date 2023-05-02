import { Component } from '@angular/core';
import { Lists } from 'src/assets/content list';
import{ FormServiceService } from '../service/form-service.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-content',
  templateUrl: './view-content.component.html',
  styleUrls: ['./view-content.component.css']
})
export class ViewContentComponent {
cont:any=[];
statuslis:any[]=[]
constructor(private router:Router,private listservice:FormServiceService){}
view:boolean=false;

ngOnInit():void{
  console.log(localStorage.getItem('status'))
  if(localStorage.getItem('status')==='active'){
    console.log(localStorage.getItem('status'))
      if(localStorage.getItem('role')==='admin_admin'){
        console.log(localStorage.getItem('status'))
        this.listservice.getalldetails().subscribe({next:data=>{
          console.log(data);
          this.cont=data},
        error : err=>{
          alert("Failed to fetch All Details")
        }});
        if(localStorage.getItem('role')==='admin_admin'){
          this.listservice.getAlluser().subscribe({next:data=>
          {
            console.log(data);
            this.statuslis=data;
          },
          error:err=>{
            alert("Failed to fetch Status")
          }})
        }else{
          alert("not get role")
        }
        this.view=true;
      }

    else{  
      this.listservice.getdata().subscribe({next:data=>{
        console.log(data);
        this.cont=data}
      ,error:err=>{alert("Failed to Fetch Blogs due to Network Error!!!...")
                      this.router.navigateByUrl("/home")    
    }});
    this.view=false
    }
  }else{
    alert('This account is deactivated To Activate this account contact admin')
  }

}

onAddText(conts:any){
  this.cont.push(conts)
}

}
