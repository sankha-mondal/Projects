import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/Interfaces/category';
import { CategoryService } from 'src/app/Services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories:Array<Category>=[];   // category array memory created..
  
  searchText: any;
  id: number = 0;
  categoryName: string = "";
  description: string = "";
  available: boolean = false;
  

  constructor(private cs: CategoryService) { }

  ngOnInit(): void {
      this.loadAllCategory()
      //================= : Search Operation data from Nav-bar : ===============
      let search = sessionStorage.getItem("search_item");
      console.log("searching object: "+search);
      this.searchText = search;
      sessionStorage.removeItem("search_item")
  }

  //=================================== : Retrive Operation : =======================================
  loadAllCategory(): void {
    this.cs.loadAllCategory().subscribe(result=> {
      this.categories = result;
    })    
  }

  //==================================== : Delete Operation : =======================================
  deleteRec(id: number) {
      if(confirm("Are you sure...!?")) {
        this.cs.deleteCategory(id).subscribe(
          result=> alert("Category deleted successfully..."),
          error=> console.log(error), 
          ()=> this.loadAllCategory() 
        )
      }
  }

  //==================================== : Update Operation : =======================================
  get_record_for_update(category: Category): void {
    this.id = category.id;
    this.categoryName = category.categoryName;
    this.description = category.description;
    this.available = category.available;     
  }

  update_Category_In_Db() {
    let category = {
                    id : this.id,
                    categoryName : this.categoryName,
                    description : this.description,
                    available : this.available
                  }
    this.cs.updatecategory(this.id, category).subscribe(
      result=> alert("Category updated successfully..."),
      error=> console.log(error),
      ()=> this.loadAllCategory()
    )
  }

  
  


}
