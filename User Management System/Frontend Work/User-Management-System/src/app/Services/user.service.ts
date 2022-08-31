import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http: HttpClient) { }   // DI for HttpClient


  getAllUser() : Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8080/users/findAll");
  }

  //  By default all HttpClient method return type is json then we have to write responseType as Text.
  storeUser(user:User) :Observable<string> {
    return this.http.post("http://localhost:8080/users/create",user,{responseType:'text'});
  }

  deleteUser(uEmail: string):Observable<string> {
    return this.http.delete("http://localhost:8080/users/deleteUser/"+uEmail,{responseType:"text"});
  }

  updateUser(user:any):Observable<string> {
    return this.http.put("http://localhost:8080/users/updateUser",user,{responseType:"text"})
  }

  login(uEmail: any, uPassword: any) :Observable<string> {
    return this.http.get("http://localhost:8080/users/findUserByEmail_Pass/"+uEmail+"/"+uPassword,{responseType:"text"})
  } 
  
}
