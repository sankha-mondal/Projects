import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from 'src/app/Interfaces/user';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  users:Array<User>=[];   // user array memory created..
  updateMsg: string = "";
  storeMsg: string = "";
  uName: string = "";
  uEmail: string = "";
  uPassword: string = "";
  uRole: string = "";
  url: string = "";

  constructor(private us: UserService, 
              private routen: Router,
              private _snackBar: MatSnackBar
              ) { }

  ngOnInit(): void {
    this.get_All_User();
  }

  // ================================== :Retrieve Operation: ======================================
  get_All_User(): void {
    this.us.getAllUser().subscribe(result=> {
      this.users=result;
      console.log("data comming for profile como...")
      console.log(result);   // details for all users
      this.get_record_for_update();
    })    
  }

  // ================================ :Update Profile Operation: ====================================
  get_record_for_update() {
    let email = sessionStorage.getItem("email_session");
    for(let i=0; i<this.users.length; i++) {
      
      //   console.log(this.users);
      if(this.users[i].uEmail == email) {
        console.log("email getting for particular person "+email);
        this.uEmail = email;
        this.uName = this.users[i].uName;
        this.uRole = this.users[i].uRole;
        this.uPassword = this.users[i].uPassword;
        this.url = this.users[i].url;
      }
    }   
  }

  update_User_In_Db() {
    let user = { 
                uName : this.uName,
                uEmail : this.uEmail,
                uPassword : this.uPassword,
                uRole : this.uRole,
                url : this.url
              }
    this.us.updateUser(user).subscribe(result=> {
        this.updateMsg=result;
        this.openSnackBar_update(result);
        //alert(this.updateMsg);
        this.routen.navigate(["products"]);
      }, error=> console.log(error)
    )
  }
 
  openSnackBar_update(result: string) {
    this._snackBar.open(result, 'Ok', {
      duration: 3000,
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

}
