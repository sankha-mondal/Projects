import { Component, OnInit } from '@angular/core';
import { Category } from '../Interfaces/category';
import { CategoryService } from '../Services/category.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  categories:Array<Category>=[];   // category array memory created..
  searchText: string = "";

  constructor(private cs: CategoryService) { }

  ngOnInit(): void {
  }

  //=================================== : Retrive Operation : =======================================
  loadAllCategory(): void {
    this.cs.loadAllCategory().subscribe(result=> {
      this.categories = result;
    })    
  }

  //=============================== : Search Operation on Nav-bar : ==================================
  search(searchText: string) {
      sessionStorage.setItem("search_item", searchText);
      window.location.reload();
  }

}
