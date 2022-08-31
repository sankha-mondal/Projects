import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/Interfaces/user';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  // users:Array<User>=[];   // user array memory created..  OR
  users : any;

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";
  uName: string = "";
  uEmail: string = "";
  uPassword: string = "";
  uRole: string = "";
  url: string = "";
  searchText: string = "";

  pageNo: number = 1;
  count: number = 0;
  tableSize: number = 5;
  tableSizes: any = [5, 10, 15, 20];

  constructor(public us: UserService,             // DI for UserService 
              private _snackBar: MatSnackBar) { }  

  ngOnInit(): void {
    this.get_All_User();
  }

  // ================================== :Retrieve Operation: ======================================
  get_All_User(): void {
    this.us.getAllUser().subscribe(result=> {
      this.users=result;
      console.log("data comming...")
    })    
  }

  //==================================== :Delete Operation: =======================================
  delete_Record(uEmail: string): void {
    if(confirm("Are you sure..!?")) {
     this.us.deleteUser(uEmail).subscribe(result=> {
       this.deleteMsg=result;
       this.openSnackBar_delete(result);
       //alert(this.deleteMsg);
     },error=>console.log(error),()=> {
       this.get_All_User();
     })
    }
   }

   openSnackBar_delete(result: string) {
    this._snackBar.open(result, 'Ok', {
      duration: 5000,
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

  // ================================== :Update Operation: ========================================
  get_record_for_update(user: User): void {
    this.uName = user.uName;
    this.uEmail = user.uEmail;
    this.uPassword = user.uPassword;
    this.uRole = user.uRole;
    this.url = user.url; 
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
      alert(this.updateMsg);
    },error=> console.log(error),()=> {
      this.get_All_User();
    })
  }

// ================================== :Store Operation: ========================================
  store_User_In_Db(userRef_store:NgForm): void {
    let user = userRef_store.value;
    console.log(user);
    this.us.storeUser(user).subscribe(result=> {
      this.storeMsg=result;
      alert(this.storeMsg);
    },error=>console.log(error),()=> {
      this.get_All_User();
    })
    userRef_store.reset();
  }


// ======================  : Pagination Controls : ==================

// ======================  : Table Size Controls : ===================

    on_table_data_change(event: any) {
      this.pageNo = event;
      this.get_All_User();
    }

//  =====================  : Page Number Controls : ===================

    on_table_size_change(event: any) {
      this.tableSize = event.target.value;
      this.pageNo = 1;
      this.get_All_User();
    }


}
