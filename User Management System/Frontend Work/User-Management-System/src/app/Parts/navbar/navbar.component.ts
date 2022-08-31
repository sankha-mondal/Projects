import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  root = '/';
  f_login: boolean = false;
  f_logout: boolean = true;
  f_reg: boolean = false;
  // email: any;

  constructor(private router: Router) { }

  ngOnInit(): void {
    // this.email = sessionStorage.getItem("email_session");
    sessionStorage.setItem("count_time", "start");
    this.login_disable_2nd();
  }

  // =====================  Logout Operation  ========================

  logout() {
    this.f_login = false;
    this.f_reg = false;
    this.f_logout = true;
    sessionStorage.removeItem("email_session");
    sessionStorage.removeItem("password_session");
    this.router.navigate([""]);
  }

   // ==================  Login btn Operations  =======================

  login_disable_1st() {
      this.f_login = true;
      this.f_reg = true;
      this.f_logout = false;
  }

  login_disable_2nd() {      //  works at the time on page refreshing
    // sessionStorage.setItem("email_session", "dummy@dummy.com");
    let email = sessionStorage.getItem("email_session");
    if(email != null) {
      this.f_login = true;
      this.f_reg = true;
      this.f_logout = false;
    } 
  }

  logout_disable() {
    this.f_login = false;
    this.f_reg = false;
    this.f_logout = true;
  }
  

}
