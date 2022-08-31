package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.bean.User;
import com.config.AppConsts;
import com.response.UserResponse;
import com.service.UserService;


@RestController
@RequestMapping("users")    //  http://localhost:8080/users/
@CrossOrigin			    // Enable cors policies 
public class UserController {

	@Autowired
	UserService userService;
	
	
//======================================================================================================================================
	
//**************************************************** == CRUD == **********************************************************************
	
		//  Retrieve Operation:-  Op:1A
		
		// http://localhost:8080/users/findAll
		@GetMapping(value = "findAll",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> findAllUser() {
			return userService.findAllUser();
		}
		
	
//-------------------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Pagination Operation:-  Op:1B
		
		//  http://localhost:8080/users/pagination
		//  http://localhost:8080/users/pagination?pageNumber=1&pageSize=5  
		//  pageNumber & pageSize are Dynamic.
		@GetMapping("/pagination")
	    public List<User> user_pagi(@RequestParam(value = "pageNumber", defaultValue = AppConsts.PAGE_NUMBER, required = false) Integer pageNumber,
	                                @RequestParam(value = "pageSize", defaultValue = AppConsts.PAGE_SIZE, required = false) Integer pageSize) {
	        // PageRequest request = PageRequest.of(page - 1, size);
	        List<User> userAll = this.userService.findUser_Pagi(pageNumber, pageSize);
	        
	        return userAll;
	    } 
		
	
//------------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Pagination with Sort-Statically Operation:-  Op:1C
		
		//  http://localhost:8080/users/pagination-sort
		//  http://localhost:8080/users/pagination-sort?pageNumber=1&pageSize=5&soryBy=uName 
		//  pageNumber & pageSize are Dynamic.
		@GetMapping("/pagination-sort")
	    public List<User> user_pagi_sort(@RequestParam(value = "pageNumber", defaultValue = AppConsts.PAGE_NUMBER, required = false) Integer pageNumber,
	                                @RequestParam(value = "pageSize", defaultValue = AppConsts.PAGE_SIZE, required = false) Integer pageSize,
	                                @RequestParam(value = "sortBy", defaultValue = AppConsts.SORT_BY, required = false) String sortBy ){
	        // PageRequest request = PageRequest.of(page - 1, size);
	        List<User> userAll = this.userService.User_Pagi_Sort(pageNumber, pageSize, sortBy);
	        
	        return userAll;
	    }
		
	
//------------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Pagination with Sort-Dynamically Operation:-  Op:1D
		
		//  http://localhost:8080/users/pagination-sort-dirt
		//  http://localhost:8080/users/pagination-sort-dirt?pageNumber=1&pageSize=5&soryBy=uName&sortDirt=asc
		//  pageNumber, pageSize & sortDirt are Dynamic.
		@GetMapping("/pagination-sort-dirt")
	    public List<User> user_pagi_sort_dirt(@RequestParam(value = "pageNumber", defaultValue = AppConsts.PAGE_NUMBER, required = false) Integer pageNumber,
	                                @RequestParam(value = "pageSize", defaultValue = AppConsts.PAGE_SIZE, required = false) Integer pageSize,
	                                @RequestParam(value = "sortBy", defaultValue = AppConsts.SORT_BY, required = false) String sortBy,
	                                @RequestParam(value = "sortDirt", defaultValue = AppConsts.SORT_DIRT, required = false) String sortDirt ){
	        // PageRequest request = PageRequest.of(page - 1, size);
	        List<User> userAll = this.userService.User_Pagi_Sort_Dirt(pageNumber, pageSize, sortBy, sortDirt);
	        
	        return userAll;
	    }
		
		
	
//------------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Pagination-Modified Operation:-  Op:1E
		
		//  http://localhost:8080/users/pagination_Modified
		//  http://localhost:8080/users/pagination_Modified?pageNumber=1&pageSize=5  
		//  pageNumber & pageSize is Dynamic.
		//  We have to create separate i.e class UserResponse Class.
		@GetMapping("/pagination_Modified")
	    public UserResponse user_pagi_Modi(@RequestParam(value = "pageNumber", defaultValue = AppConsts.PAGE_NUMBER, required = false) Integer pageNumber,
	                                @RequestParam(value = "pageSize", defaultValue = AppConsts.PAGE_SIZE, required = false) Integer pageSize) {
	        // PageRequest request = PageRequest.of(page - 1, size);
	        // List<User> userAll = this.userService.findUser_Pagi(pageNumber, pageSize);
			UserResponse userResponse = this.userService.findUser_Pagi_Modi(pageNumber, pageSize);
	        
	        return userResponse;
	    } 
	
		
//=====================================================================================================================================
	
	
		//  Retrieve data by Id Operation type String:-  Op:2A
		
		//  http://localhost:8080/users/findUserByPK_1/{uEmail}
		@GetMapping(value = "findUserByPK_1/{uEmail}")
		public String findUserByPK_1(@PathVariable("uEmail") String uEmail) {
			return userService.findUserByPK_1(uEmail);
		}
		
	
//----------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Id Operation type User:-  Op:2B
		
		//  http://localhost:8080/users/findUserByPK_2/{uEmail}
		@GetMapping(value = "findUserByPK_2/{uEmail}",produces = MediaType.APPLICATION_JSON_VALUE)
		public User findUserByPK_2(@PathVariable("uEmail") String uEmail) {
			return userService.findUserByPK_2(uEmail);
		}

	
//======================================================================================================================================
	

		//  Insert Operation | By PK | By unique Email:-    Op:3
		
		//  http://localhost:8080/users/create
		@PostMapping(value = "create",consumes = MediaType.APPLICATION_JSON_VALUE)
		public String createAccount(@RequestBody User user) {
			System.out.println(user.getuEmail());
			return userService.createAccount(user);
		}
	

//======================================================================================================================================

	
		//  Update Operation:-   Op:4
		
		//  http://localhost:8080/users/updateUser
		@PutMapping(value = "updateUser",consumes = MediaType.APPLICATION_JSON_VALUE)
		public String updateUser(@RequestBody User user) {
			return userService.updateUser(user);
		}
	
	
//======================================================================================================================================	

		//  Delete Operation:-   Op:5
		
		//  http://localhost:8080/users/deleteUser/{uEmail}
		@DeleteMapping(value = "deleteUser/{uEmail}")
		public String deleteUser(@PathVariable("uEmail") String uEmail) {
			return userService.deleteUser(uEmail);
		}
	
	
//****************************************************************************************************************************************
//========================================================================================================================================
		
//***************************************************  == User Defined Query ==	**********************************************************
	
	
		//  Retrieve data by Name Operation:-   Op:6
		
		//  http://localhost:8080/users/findUserByName/{uName}
		@GetMapping(value = "findUserByName/{uName}",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> findUserByName(@PathVariable("uName") String uName){
			return userService.findUserByName(uName);
		}
		
		
		
//========================================================================================================================================	
	
		
		//  Retrieve data by Name & Password Operation:-   Op:7A
		
		//  http://localhost:8080/users/findUserByName_Pass_1/{uName}/{uPassword}
		@GetMapping(value = "findUserByName_Pass/{uName}/{uPassword}")
		public List<User> findUserByName_Pass(@PathVariable("uName") String uName,@PathVariable("uPassword") String uPassword) {
			return userService.findUserByName_Pass(uName,uPassword);
		}
		
		
	
//----------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve Message by Email & password Operation:-   Op:7B
		
		//  http://localhost:8080/users/findUserByEmail_Pass/{uEmail}/{uPassword}
		@GetMapping(value = "findUserByEmail_Pass/{uEmail}/{uPassword}")
		public String findUserByEmail_Pass(@PathVariable("uEmail") String uEmail, @PathVariable("uPassword") String uPassword) throws InterruptedException {
			Thread.sleep(5000);
			return userService.findUserByEmail_Pass(uEmail, uPassword);
		}
	
	
		
//========================================================================================================================================
	
		//  Query: Order By User Id asc/desc:-  Op:8
		
		//  http://localhost:8080/users/sortUserByEmail
		@GetMapping(value = "sortUserByEmail",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> sortUserByEmail(){
			return userService.sortUserByEmail();
		}
	
//========================================================================================================================================
	
		//  User defined | Query: Record deleted by Email:-  Op:9
		
		//  http://localhost:8080/users/deleteUserByName/{uName}
		@DeleteMapping(value = "deleteUserByName/{uName}")
		public String deleteUserByName(@PathVariable("uName") String uName) {
			return userService.deleteUserByName(uName);
		}
		
		
		
//========================================================================================================================================
	
		//  User defined | Query: Search Operation by like Name      Op:10
		
		//  http://localhost:8080/users/search/{keyword}
		@GetMapping(value = "search/{keyword}",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> search_by_name(@PathVariable("keyword") String keyword) {
			return userService.search_by_name(keyword);
		}
		
		
		
//========================================================================================================================================
		
		
		
		
	
//*****************************************************************************************************************************
	

}
