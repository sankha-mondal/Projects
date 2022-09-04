import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Category } from 'src/app/Interfaces/category';
import { BookService } from 'src/app/Services/book.service';
import { CategoryService } from 'src/app/Services/category.service';

@Component({
  selector: 'app-add-books',
  templateUrl: './add-books.component.html',
  styleUrls: ['./add-books.component.css']
})
export class AddBooksComponent implements OnInit {

  categories: Array<Category> = [];
  id: number = 0;
  categoryName: string = "";
  search_category: string = "";

  constructor(private bs: BookService, private cs: CategoryService) { }

  ngOnInit(): void {
    this.get_All_category();
  }

  //=========================== : Store Books Operation : ================================
  get_record_for_store(category: Category) {
    this.id = category.id;    //  this is category_id
    this.categoryName = category.categoryName
    console.log(this.id)
  }
  store_book_In_Db(storeRef: NgForm) {
    let book = storeRef.value;
    console.log(book);
    if(confirm("Are you sure to add book in Category of "+this.categoryName+"...!?")) {
      this.bs.storeBook(this.id, book).subscribe(
          result=> alert("Book added successfully in Category of "+this.categoryName+"."),
          error=> console.log(error)
    )
    storeRef.reset();
    }
  }


  //==================== : Retrive Operation of Category with id : ========================
  get_All_category(): void {
      this.cs.loadAllCategory().subscribe(result=> {
      this.categories=result;
      console.log("data comming...")
    })    
  }


}
