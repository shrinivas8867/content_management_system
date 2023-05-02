import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { RegisterformComponent } from './registerform/registerform.component';
import { ViewContentComponent } from './view-content/view-content.component';
import { CanActivateGuard } from './can-activate.guard';
import { CanDeactivateGuard } from './can-deactivate.guard';
import { EditComponent } from './edit/edit.component';
import { ContentComponent } from './content/content.component';
import { UserdetailsComponent } from './userdetails/userdetails.component';

const routes: Routes = [
  {path:"home", component:HomeComponent},
  {path:"login", component:LoginComponent},
  {path:"content",component:ContentComponent},
  {path:"userdetails",component:UserdetailsComponent},
  {path:"viewcontents", component:ViewContentComponent,canActivate:[CanActivateGuard]},
  {path:"edit/:id",component:EditComponent,canDeactivate:[CanDeactivateGuard]},
  {path:"register", component:RegisterformComponent},
  {path:"",redirectTo:"/home",pathMatch:"full"},
  {path:"**",component:NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
