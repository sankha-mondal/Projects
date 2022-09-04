import { Component, OnInit } from '@angular/core';
import { Books } from 'src/app/Interfaces/books';
import { BookService } from 'src/app/Services/book.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books: Array<Books> = [];
  searchText: any;
  id: number = 0;
  bookName: string = ""; 


  constructor(private bs: BookService) { }

  ngOnInit(): void {
      this.loadAllBooks();
  }

  //=================================== : Retrive Operation : =======================================
  loadAllBooks(): void {
    this.bs.loadAllBooks().subscribe(result=> {
      this.books = result;
    })    
  }

  //==================================== : Delete Operation : =======================================
  delete_Record(id: number) {
    if(confirm("Are you sure...!?")) {
      this.bs.deleteBook(id).subscribe(
        result=> alert("Book deleted successfully..."),
        error=> console.log(error), 
        ()=> this.loadAllBooks() 
      )
    }
  } 

  //==================================== : Update Operation : =======================================
  get_record_for_update(book: Books): void {
    this.id = book.id;
    this.bookName = book.bookName;     
  }

  update_Book_In_Db() {
    let book = {
                  id : this.id,
                  bookName : this.bookName
                }
    this.bs.updateBook(this.id, book).subscribe(
      result=> alert("Book updated successfully..."),
      error=> console.log(error),
      ()=> this.loadAllBooks()
    )
  }














}
