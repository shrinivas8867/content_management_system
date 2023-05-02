import { Component,Input } from '@angular/core';
import { Lists } from 'src/assets/content list';
import{ FormServiceService } from '../service/form-service.service'
import { RouteService } from '../service/route.service';
@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
  @Input()
  cont?:Lists[];

  constructor(private contservice:FormServiceService, private router:RouteService){}

  ngOnInit():void{
    console.log(this.cont)
    console.log()
  }
  

}
