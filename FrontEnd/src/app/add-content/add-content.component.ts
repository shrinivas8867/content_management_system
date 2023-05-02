import { Component, EventEmitter, Output, ɵsetAllowDuplicateNgModuleIdsForTest } from '@angular/core';
import { OutletContext } from '@angular/router';
import { Lists } from 'src/assets/content list';
import { RouteService } from '../service/route.service';
import { FormServiceService } from '../service/form-service.service';
@Component({
  selector: 'app-add-content',
  templateUrl: './add-content.component.html',
  styleUrls: ['./add-content.component.css']
})
export class AddContentComponent {
lists:Lists={};
view:boolean=false;



cont:Lists[]=[];
ids:number=0;
constructor(private listservice:FormServiceService, private routers:RouteService){}
@Output()
addText:EventEmitter<Lists>=new EventEmitter<Lists>();

ngOnInit():void{
  this.lists.contentid=1;
  this.listservice.getdata().subscribe({next:data=>{
    this.cont=data;
  
},
    
    error:err=>{alert("Failed to Fetch Content")}

  });
}
add(){
  this.view=!this.view;
}

addcont(){
  

  this.lists.date= new Date;
  if(this.lists.content==null){
    alert("Content is required")
    this.routers.navigatetoview();
  }else{
   this.listservice.addcontent(this.lists).subscribe({next:data=>{
    alert("Content Added Successfully")
    this.addText.emit(this.lists)
    this.lists={};
   },
   error:e=>{
    alert("Failed to Add Content")
   }
   });
}
}
}
