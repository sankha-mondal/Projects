import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  hr: any = 12;
  min: any = 47;
  sec: any = 60;

  constructor() { }

  ngOnInit(): void {
    let count = sessionStorage.getItem("count_time");
    if(count == "start") {
      this.countdownsec();
      this.countdownmin();
      this.countdownhr();
    }
  } 

  countdownsec() {
    setInterval(() => {
      if (this.sec > 1) {
        this.sec--;
        this.sec = this.sec<10 ? '0'+this.sec : this.sec
        sessionStorage.setItem("fi_sec", this.sec);
      } else {
        this.sec = 60;
      }
    }, 1000);
  }

  countdownmin() {
    setInterval(() => {
      if (this.min > 1) {
        this.min--;
        this.min = this.min<10 ? '0'+this.min : this.min;
      } else {
        this.min = 60;
      }
    }, 60000);
  }

  countdownhr() {
    setInterval(() => {
      if (this.hr > 1) {
        this.hr--;
        this.hr = this.hr<10 ? '0'+this.hr : this.hr;
      } else {
        this.hr = 60;
      }
    }, 3600000);
  }

}
