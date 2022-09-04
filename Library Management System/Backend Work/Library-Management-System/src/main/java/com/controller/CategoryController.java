package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Category;
import com.exception.ResourceNotFoundException;
import com.payload.ApiResponse;
import com.repository.CategoryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")		//  http://localhost:9090/category
public class CategoryController {
	
	@Autowired
	CategoryRepository catRepo;
	
	
//*************************************************** : CRUD Operation : *****************************************************************	  
//=======================================================================================================================================

	  	 //  Retrieve Operation:-  Op:1
	  
	  	 //	 http://localhost:9090/category/getAll
	  
		  @GetMapping("/getAll")
		  public ResponseEntity<List<Category>> getAllCategory() {
		    List<Category> category = new ArrayList<Category>();
		
		    	catRepo.findAll().forEach(category::add);
			
			    if (category.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }
			
			    return new ResponseEntity<>(category, HttpStatus.OK);
		  }
		  
	  
	  
//=======================================================================================================================================

		  //  Retrieve data by Id :-  Op:2
		  
		  //  http://localhost:9090/category/getById/{id}

		  @GetMapping("/getById/{id}")
		  public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
			  
			  Category category = catRepo.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
		
		    	return new ResponseEntity<>(category, HttpStatus.OK);
		  }
		  
	  
	  
//=======================================================================================================================================
		  
		  //  Insert Operation:-    Op:3
		  
		  //  http://localhost:9090/category/store
		  
		  @PostMapping("/store")
		  public ResponseEntity<Category> createCategory(@RequestBody Category category) { 
			  Category _category = catRepo.save(new Category(category.getCategoryName(), category.getDescription(), category.isAvailable()) );
		    
		    return new ResponseEntity<>(_category, HttpStatus.CREATED);
		  }
		  
	  
	  
//=======================================================================================================================================  

		  //  Update Operation:-   Op:4
		  
		  //  http://localhost:9090/category/update/{id}
		  
		  @PutMapping("/update/{id}")
		  public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
			  
			  Category _category = catRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
		
			    _category.setCategoryName(category.getCategoryName());
			    _category.setDescription(category.getDescription());
			    _category.setAvailable(category.isAvailable());
		    
		    return new ResponseEntity<>(catRepo.save(_category), HttpStatus.OK);
		  }
		  
		  
	  
//=======================================================================================================================================
		  
		  //  Delete Operation by Id:-   Op:5
		  
		  //  http://localhost:9090/category/delete/{id}
		  
		  @DeleteMapping("/delete/{id}")
		  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") long id) {
			  
			  Category category_id = catRepo.findById(id)
				        .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
			  
			  catRepo.deleteById(id);
		    
		    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully", true), HttpStatus.OK);
		  }
		  
		  
	  
//=======================================================================================================================================
		  
		  //  All Delete Operation:-   Op:6
			 
		  //  http://localhost:9090/category/deleteAll

		  @DeleteMapping("/deleteAll")
		  public ResponseEntity<HttpStatus> deleteAllTutorials() {
			  
			  catRepo.deleteAll();
		    
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
	  
		  
		  
//=======================================================================================================================================
//**************************************************** : User Define : *****************************************************************		  
//======================================================================================================================================
		  
		  //  Retrieve data by published Operation:-   Op:7
		  
		  //  http://localhost:9090/category/getAllBy/available
		  
		  @GetMapping("/getAllBy/available")
		  public ResponseEntity<List<Category>> findByAvailable() {
			  
		    List<Category> category = catRepo.findByAvailable(true);
		
		    if (category.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    return new ResponseEntity<>(category, HttpStatus.OK);
		  }
		  
	  
	  
//=======================================================================================================================================
		  
		  //  Retrieve data by title Operation:-   Op:8
		  
		  //  http://localhost:9090/category/getBy/{name}
		  
		  @GetMapping("/getBy/{name}")
		  public ResponseEntity<List<Category>> findByCategoryName(@PathVariable("name") String name) {
			  
		    List<Category> category = catRepo.findByCategoryName(name);
		
			    if (category.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }
		    
		    return new ResponseEntity<>(category, HttpStatus.OK);
		  }
		  
		  
//=======================================================================================================================================
		  
}
