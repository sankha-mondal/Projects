import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBooksComponent } from './Pages/add-books/add-books.component';
import { AddCategoryComponent } from './Pages/add-category/add-category.component';
import { BooksComponent } from './Pages/books/books.component';
import { CategoryComponent } from './Pages/category/category.component';

const routes: Routes = [
  {path:'category', component:CategoryComponent},
  {path:'add-category', component: AddCategoryComponent},
  {path:'books', component: BooksComponent},
  {path:'add-books', component:AddBooksComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
