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
import org.springframework.web.bind.annotation.RestController;

import com.entity.Books;
import com.entity.Category;
import com.exception.ResourceNotFoundException;
import com.repository.BooksRepository;
import com.repository.CategoryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")       //  http://localhost:9090/books
public class BooksController {

	@Autowired
	BooksRepository bookRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
//====================================================== : CRUD : =============================================================

	  //  Retrieve All Books:-  Op:1
	
	  //  http://localhost:9090/books/getAll
  
	  @GetMapping("/getAll")
	  public ResponseEntity<List<Books>> getAllBooks() {
		  
	    List<Books> books = new ArrayList<Books>();
	    	bookRepo.findAll().forEach(books::add);
		
		    if (books.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		 return new ResponseEntity<>(books, HttpStatus.OK);
	  }
	
		
	
//======================================================================================================================================
	
	
	  //  Retrieve by Category-Id:-  Op:2

	  //  http://localhost:9090/books/getByCategory/{categoryId}

	  @GetMapping("/getByCategory/{categoryId}")
	  public ResponseEntity<List<Books>> getAllBooksByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
		  
	    Category category = catRepo.findById(categoryId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + categoryId));
	
		    List<Books> books = new ArrayList<Books>();
		    books.addAll(category.getBooks());
	    
	    return new ResponseEntity<>(books, HttpStatus.OK);
	  }


	  
//======================================================================================================================================
	  
	  //  Retrieve by Book-Id:-  Op:3
	  
	  //  http://localhost:9090/books/book/{bookId}

	  @GetMapping("/book/{bookId}")
	  public ResponseEntity<Books> getBooksByCategoryId(@PathVariable(value = "bookId") Long bookId) {
		  
	    Books book = bookRepo.findById(bookId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Book with id = " + bookId));
	
	    return new ResponseEntity<>(book, HttpStatus.OK);
	  }
	  
	  
	  
//======================================================================================================================================
		  
	  //  Insert Operation with Category-Id:-    Op:4
	  
	  //  http://localhost:9090/books/store/{categoryId}
	  
	  @PostMapping("/store/{categoryId}")
	  public ResponseEntity<Books> createBooks( @PathVariable(value = "categoryId") Long categoryId,
			  									@RequestBody Books bookRequest) {
		  
		  Books book = catRepo.findById(categoryId).map(category -> {
			  category.getBooks().add(bookRequest);
		      		return bookRepo.save(bookRequest);
		    }).orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + categoryId));
	
	    return new ResponseEntity<>(book, HttpStatus.CREATED);
	  }
	  
	  
//======================================================================================================================================
	  
	  //  Update Operation by Book-id:-   Op:5
	  
	  //  http://localhost:9090/books/update/{id}
	  
	  @PutMapping("/update/{id}")
	  public ResponseEntity<Books> updateComment(@PathVariable("id") long id, @RequestBody Books bookRequest) {
		  
	    Books book = bookRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("BookId " + id + "not found"));
	
	    book.setBookName(bookRequest.getBookName());
	
	    return new ResponseEntity<>(bookRepo.save(book), HttpStatus.OK);
	  }
	  
	  
//======================================================================================================================================
		  
	  //  Delete Operation by Id:-   Op:6
	  
	  //  http://localhost:9090/books/delete/{id}
	  
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		  
	    bookRepo.deleteById(id);
	
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  
	  
  
//======================================================================================================================================
//======================================================================================================================================	  
	  
}
