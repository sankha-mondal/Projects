import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email_store: string="";
  password_store: string="";
  progress: boolean = false;

  constructor(private us: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

//============================= Model DForm ================================ 

  loginRef1 = new FormGroup ({   // model dForm | for checking email & password
    email: new FormControl("", [Validators.required]),
    password: new FormControl("", [Validators.required])
  });

  checkUser() {                                      //  Model DF
    let login = this.loginRef1.value;
    this.progress = true;
    this.us.login(login.email, login.password).subscribe(
      result=> {
        if(result=="WELCOME") {
            
              if(login.email=="admin@ad.com" && login.password=="admin") {
                this.session(login.email, login.password);
                this.router.navigate(["user"]);
              } else {
                this.session(login.password, login.email);
                alert("Welcome user "+login.email);
                this.router.navigate([""]);
              }
        } else {
            this.progress = false;
            alert(result);
        }       
      }, error=> {
        console.log(error);
      }
    )
  }

  session(email: any, password: any) {
    sessionStorage.setItem("email_session", email);
    sessionStorage.setItem("password_session", password);
  }


//============================= Template DForm ================================ 

  loginUser(loginRef2: NgForm) {                   //  Template DF
    let login = loginRef2.value;
    this.progress = true;
    this.us.login(login.email, login.password).subscribe(
      result=> {
        if(result=="WELCOME") {
          sessionStorage.setItem("email_session", login.email);
          sessionStorage.setItem("password_session", login.password);
              if(login.email=="admin@ad.com" && login.password=="admin") {
                this.router.navigate(["user"]);
              } else {
                alert("Welcome user "+login.email);
                this.router.navigate(["products"]);
              }
        } else {
            this.progress = false;
            alert(result);
        }       
      }, error=> {
        console.log(error);
      }
    )
  }

  
}
