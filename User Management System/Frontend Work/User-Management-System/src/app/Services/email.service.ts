import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private base_url: string = "http://localhost:8181/email";

  constructor(private http: HttpClient) { }

  send_email(data: any) {
      return this.http.post(`${this.base_url}/sendMail`,data,{responseType:"text"});
  }
}
