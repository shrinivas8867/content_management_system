import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscriber } from 'rxjs';
import { List } from 'src/assets/list';
import { Lists } from 'src/assets/content list';

@Injectable({
  providedIn: 'root'
})
export class FormServiceService {
  URL:string="http://localhost:9500/Content/v2/";
  URL2:string="http://localhost:9500/content/v1/";
  constructor(private http:HttpClient){}
requests:any;

  adddata(content:any){
    return this.http.post<any>(this.URL+"registration",content);
  }

  logins(logindata:any){
    return this.http.post(this.URL2+"login",logindata);
  }

  getdata():Observable<Array<Lists>>{
    
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')

    });
    let request={headers:httpHeaders}
    return this.http.get<Array<Lists>>(`${this.URL}user/allcontent`,request);
  }
  getAlluser():Observable<Array<any>>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')

    });
    let request={headers:httpHeaders}
    return this.http.get<Array<any>>(`${this.URL2}admin/getall`,request);
    
  }
  getalldetails():Observable<Array<any>>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')

    });
    let request={headers:httpHeaders}
    return this.http.get<Array<any>>(`${this.URL}admin/alldetails`,request);
  }

  getdatabyid(id?:number):Observable<Lists>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<Lists>(`${this.URL}user/getById/${id}`,request);
  }
  getuserbyid(id?:any):Observable<Lists>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<Lists>(`${this.URL}admin/getUser/${id}`,request);
  }


  updatestatus(id?:any,contentss?:any){
    return this.http.put<any>(`${this.URL2}admin/update/${id}`,contentss);
  }
  getstatus(id?:any):Observable<any>{
    return this.http.get<any>(`${this.URL2}admin/getstatus/${id}`);
  }

  delete(id?:number){
    this.requests=null;
    if(localStorage.getItem('status')==='active'){
      let httpHeaders=new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
      })
       this.requests={headers:httpHeaders}
    }else{ 
      alert("This account is deactivated To Activate this account contact admin")
    }
    return this.http.delete<Lists>(`${this.URL}user/delete/${id}`,this.requests);
   
  
}
  editcontent(id?:number,content?:Lists){
    this.requests=null;
    if(localStorage.getItem('status')==='active'){
      let httpHeaders=new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
      })
       this.requests={headers:httpHeaders}
    }else{ 
      alert("This account is deactivated To Activate this account contact admin")
    }
    return this.http.put<Lists>(`${this.URL}user/update/${id}`,content,this.requests);
   
  }
  addcontent(content?:any){
    this.requests=null;
    if(localStorage.getItem('status')==='active'){
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
     this.requests={headers:httpHeaders}
  }else{ 
    alert("This account is deactivated To Activate this account contact admin")
  }

    return this.http.post<any>(`${this.URL}user/addProduct`,content,this.requests);
   
  }
 
}
