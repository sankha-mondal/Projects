import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from 'src/app/Services/email.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {

  constructor(private ems: EmailService,
              private snak: MatSnackBar) { }

  ngOnInit(): void {
  }

//=====================================  Template DF with 2-way bunding ==================================

  email = {
    recipient: "",
    subject: "",
    msgBody: ""
  }

  flag: boolean = false;

  send_email() {
      console.log("Sending Email...");
      console.log("DATA: ",this.email);
    if(this.email.recipient=='' || this.email.subject=='' || this.email.msgBody=='') {
      this.snak.open("fields should not be empty !!","OK");
    }
    else {
      this.flag = true; 
      this.ems.send_email(this.email).subscribe(
        result=> {
          // success
          this.flag = false;
          this.snak.open(result,"OK", {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }, error=> {
          // error
          this.flag = false;
          this.snak.open(error,"OK")
        }
      )
    }
  }


}
