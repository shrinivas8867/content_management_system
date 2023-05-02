import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouteService } from '../service/route.service';
import { Lists } from 'src/assets/content list';
import { FormServiceService } from '../service/form-service.service';
import { FormControl } from '@angular/forms';
import {  AbstractControl, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent {
  
  constructor(private fb: FormBuilder,private activateRoute:ActivatedRoute,private formservice:FormServiceService,private routeService:RouteService){}
  statuss:any={}
  lists: any={}
  cont:Lists[]=[];
  ststus:boolean=false;
  viewButton:boolean=false;
  ids:any;
   
  // lists=this.fb.group({
  //   contentid:[''],
  //   content:[''],
  //   description:[''],
  //   date:['']
  // })

  // get eventContentid(){return this.lists.get('contentid');}
  // get eventContent(){return this.lists.get('content');}
  // get eventDescription(){return this.lists.get('description');}
  // get eventData(){return this.lists.get('date');}



  ngOnInit():void{
    if(localStorage.getItem('status')==='active'){
    this.activateRoute.paramMap.subscribe(params=>{
      let id =params.get("id")?? 0;
      console.log(params);
      console.log(id);
      this.ids=id


      if(localStorage.getItem('role')=='admin_admin'){
        this.formservice.getuserbyid(id).subscribe(data=>{
          console.log(data);
          this.lists=data;
          this.ststus=true;
        })
        this.formservice.getstatus(id).subscribe(data=>{
          console.log(data);
          this.statuss=data;
        })
        console.log(this.statuss.status)
        if(this.statuss.statu=='active'){
          this.viewButton=true;
          console.log('in'+this.viewButton)
        }else{
          this.viewButton=!this.viewButton;
          console.log('out'+this.viewButton)
        }
      }else{
      this.formservice.getdatabyid(+id).subscribe(datas=>{
        console.log(datas);
        this.lists=datas;
        
      })}
    }); 
     
  }else{
    this.ststus=false;
    alert("This account is deactivated")
    this.routeService.toHome()
  }
  
  }

  activate(){
    this.lists.status='active'
    this.formservice.updatestatus(this.ids,this.lists).subscribe(data=>{
      
      this.viewButton=false;
      console.log('in active function'+this.viewButton)
      alert ("This account is Activated");

    })
  }
  deactivate(){
    this.lists.status='deactive'
    this.formservice.updatestatus(this.ids,this.lists).subscribe(data=>{
      this.viewButton=true;
      console.log('in deactive function'+this.viewButton)
      alert ("This account is Dectivated");
    })
  }

   

  editCont(){
    this.formservice.editcontent(this.lists?.contentid,this.lists).subscribe(data=>{
      alert("Content edited Successfully")
      this.lists=data;
      this.lists.date=new Date()
      this.routeService.navigatetoview();
    })
  }

  deleteCont(){
    this.formservice.delete(this.lists.contentid).subscribe(data=>{
      alert("The Content has been deleted")
      this.lists=data;
      this.routeService.navigatetoview();
    })
  }

}
