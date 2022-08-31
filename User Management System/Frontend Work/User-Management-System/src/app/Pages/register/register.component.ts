import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  storeMsg: string = "";

  constructor(private us: UserService, private router: Router, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  //====================== Template DF : Type-1 ===========================

    store_User_In_Db_1(userRef_store1:NgForm): void {
      let user = userRef_store1.value;
      console.log(user);
      this.us.storeUser(user).subscribe(result=> {
        this.storeMsg=result;
        alert(this.storeMsg);
      },error=>console.log(error),()=> {
        // this.get_All_User();
        this.router.navigate([""]);
      })
      userRef_store1.reset();
    }

  //=============== Template DF : Type-2 : Using Angular Material  ===========================

    store_User_In_Db_2(userRef_store2:NgForm): void {
      let user = userRef_store2.value;
      console.log(user);
      this.us.storeUser(user).subscribe(result=> {
        this.storeMsg = result;
        this.openSnackBar(result);
        //alert(this.storeMsg);
        this.router.navigate([""]);
      },error=>console.log(error),()=> {
      })
      userRef_store2.reset();
    }

    openSnackBar(result: string) {
      this._snackBar.open(result, 'Ok', {
        duration: 3000,
        horizontalPosition: "center",
        verticalPosition: "top",
      });
    }

}
