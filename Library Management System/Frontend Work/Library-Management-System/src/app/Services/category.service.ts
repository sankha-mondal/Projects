import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../Interfaces/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(public http:HttpClient) { }  // DI for HttpClient

  loadAllCategory() : Observable<Category[]> {
    return this.http.get<Category[]>("http://localhost:9090/category/getAll");
  }

  storeCategory(category: Category) {
    return this.http.post("http://localhost:9090/category/store", category);
  }

  deleteCategory(id: number) {
    return this.http.delete("http://localhost:9090/category/delete/"+id);
  }

  updatecategory(id: number, category: any) {
    return this.http.put("http://localhost:9090/category/update/"+id,category);
  }

}