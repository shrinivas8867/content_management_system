import { Component,Input } from '@angular/core';
import { FormServiceService } from '../service/form-service.service';

@Component({
  selector: 'app-userdetails',
  templateUrl: './userdetails.component.html',
  styleUrls: ['./userdetails.component.css']
})
export class UserdetailsComponent {
   
  @Input()
  statuslist?:any[];


 
  constructor( ){ }



   ngOnINit():void{
    
    

    console.log('In userdetails')

     
  }





}
