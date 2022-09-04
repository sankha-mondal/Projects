import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Books } from '../Interfaces/books';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(public http:HttpClient) { }  // DI for HttpClient

  loadAllBooks() : Observable<Books[]> {
    return this.http.get<Books[]>("http://localhost:9090/books/getAll");
  }

  storeBook(categoryId: number, book: Books) {
    return this.http.post("http://localhost:9090/books/store/"+categoryId, book);
  }

  deleteBook(id: number) {
    return this.http.delete("http://localhost:9090/books/delete/"+id);
  }

  updateBook(id: number, book: any) {
    return this.http.put("http://localhost:9090/books/update/"+id, book);
  }
  
}
