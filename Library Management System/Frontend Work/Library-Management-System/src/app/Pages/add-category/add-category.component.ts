import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryService } from 'src/app/Services/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  storeMsg: string="";

  constructor(private cs: CategoryService) { }

  ngOnInit(): void {
  }

  //=========================== : Store Category Operation : ================================

  store_category_In_Db(storeRef: NgForm) {
      let category = storeRef.value;
      console.log(category);
      this.cs.storeCategory(category).subscribe(result=> {
        alert("Category added successfully...");
      },error=> console.log(error)
      )
      storeRef.reset();
  }

}
