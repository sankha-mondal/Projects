package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.response.UserResponse;
import com.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
//===========================================================================================================================
	
	
//**************************************************** == CRUD == ***********************************************************
	
		//  Retrieve Operation:-  Op:1A
		
		public List<User> findAllUser() {
			return userRepository.findAll();
		}
	
//---------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Pagination Operation:-  Op:1B
		
		public List<User> findUser_Pagi(Integer pageNumber, Integer pageSize) {
			
			Pageable p = PageRequest.of(pageNumber, pageSize);		// creating pageable object with page number & size
			Page<User> userPage = this.userRepository.findAll(p);	// returns Page of User 
			
			List<User> allUser_pagi = userPage.getContent();
			
			return allUser_pagi;
		}

//---------------------------------------------------------------------------------------------------------------------
	
		// Retrieve data by Pagination with Sort-Statically Operation:-  Op:1C
		
		public List<User> User_Pagi_Sort(Integer pageNumber, Integer pageSize, String sortBy) {
			
			Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());  // ascending() & descending()	Statically.
			Page<User> userPage = this.userRepository.findAll(p);	// returns Page of User 
			
			List<User> allUser_pagi_sort = userPage.getContent();
			
			return allUser_pagi_sort;
		}
		
//---------------------------------------------------------------------------------------------------------------------
		
		// Retrieve data by Pagination with Sort-Dynamically Operation:-  Op:1D
		
		public List<User> User_Pagi_Sort_Dirt(Integer pageNumber, Integer pageSize, String sortBy, String sortDirt) {
			
			//	Sort sort = null;
			//	if(sortDirt.equalsIgnoreCase("asc")) {
			//		sort = Sort.by(sortBy).ascending();
			//	} else {
			//		sort = Sort.by(sortBy).descending();
			//	}  
			
			// OR
			
			Sort sort = (sortDirt.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
			
			Pageable p = PageRequest.of(pageNumber, pageSize, sort);  // ascending() & descending()	Dynamically.
			Page<User> userPage = this.userRepository.findAll(p);	  // returns Page of User 
			
			List<User> allUser_pagi_sort_dirt = userPage.getContent();
			
			return allUser_pagi_sort_dirt;
		}
				
//---------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by Pagination-Modified Operation:-  Op:1E
		
		public UserResponse findUser_Pagi_Modi(Integer pageNumber, Integer pageSize) {
			
			Pageable p = PageRequest.of(pageNumber, pageSize);		// creating pageable object with page number & size
			Page<User> userPage = this.userRepository.findAll(p);	// returns Page of User 
			
			List<User> allUser_pagi = userPage.getContent();
			
			UserResponse userResponse = new UserResponse();
			
			userResponse.setContent(allUser_pagi);
			userResponse.setPageNumber(userPage.getNumber());
			userResponse.setPageSize(userPage.getSize());
			userResponse.setTotalElements(userPage.getTotalElements());
			userResponse.setTotalPages(userPage.getTotalPages());
			userResponse.setFirstPage(userPage.isFirst());
			userResponse.setLastPage(userPage.isLast());
			
			return userResponse;
		}
	
	
//=================================================================================================================================
	
		//  Retrieve data by PK Operation type String:-  Op:2A
		
		public String findUserByPK_1(String uEmail) {
			Optional<User> op = userRepository.findById(uEmail);
				if(op.isPresent()) {
						User u = op.get();
						return "Name: "+u.getuName()+"\nEmail: "+u.getuEmail()+"\nPassword: "+u.getuPassword();
				} else {
					return "User doesn't exist...";
				}
		}
	
//---------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve data by PK Operation type User:-  Op:2B
		
		public User findUserByPK_2(String uEmail) {
			Optional<User> op =  userRepository.findById(uEmail);
				if(op.isPresent()) {
					User u = op.get();
						return u;
				} else {
					return null;
				}
		}
	
	
//==============================================================================================================================	
	
		
		//  Insert Operation | By PK | By unique Email:-    Op:3
		
		public String createAccount(User user) {
			boolean res = userRepository.existsById(user.getuEmail());
			System.out.println(res);
			if(res) {
				return "User details didn't Stored...\nYou already Registered...";
			} else {
				userRepository.save(user);
				return "User details Stored successfully...";
			}
		}

	
	
//================================================================================================================================	
	
	
		//  Update Operation:-    Op:4
		
		public String updateUser(User user) {
			Optional<User> op =  userRepository.findById(user.getuEmail());
				if(op.isPresent()) {
					User u = op.get();
					u.setuName(user.getuName());
					u.setuPassword(user.getuPassword());
					u.setUrl(user.getUrl());
					userRepository.saveAndFlush(u);
					return "User details updated successfully...";
				} else {
					return "User not present...";
				}
		}

	
//=================================================================================================================================	
	
	
		//  Delete Operation:-    Op:5
		
		public String deleteUser(String uEmail) {
			Optional<User> op =  userRepository.findById(uEmail);
				if(op.isPresent()) {
						User u = op.get();
						userRepository.delete(u);
						return "User details deleted successfully...";
				} else {
					return "User not present...";
				}
		}
	
	
////*************************************************************************************************************************	

////************************************************** == User Defined == ***************************************************
	
	
		//  Retrieve data by Name Operation:-   Op:6
	
		public List<User> findUserByName(String uName) {
			return userRepository.findUserByName(uName);
		}
	
//=============================================================================================================================	
	
		//  Retrieve data by Name & Password Operation:-  Op:7A
		
		public List<User> findUserByName_Pass(String uName, String uPassword) {
			return userRepository.findUserByName_Pass(uName, uPassword);
		}
	
//---------------------------------------------------------------------------------------------------------------------------
	
		//  Retrieve Message by Email & password Operation:-   Op:7B
	
		public String findUserByEmail_Pass(String uEmail, String uPassword) {
		Optional<User> op = userRepository.findById(uEmail);
		System.out.println(op);
		//System.out.println(User);
			if(op.isPresent()) {
				User u = op.get();
				if(u.getuPassword().equals(uPassword)) {
					return "WELCOME";
				} else {
					return "Password may be worng";
				}
			} else {
				return "Email or Password may be worng";
			}
		}
	
	
//==========================================================================================================================	
	
		//  User defined | Query: Order By product asc/desc:-  Op:8
		 
		public List<User> sortUserByEmail() {
			return userRepository.sortUserByEmail();
		}
	
//==========================================================================================================================
	
		//  User defined | Query: Record deleted by Email      Op:9
		
		public String deleteUserByName(String uName) {
			if(userRepository.deleteUserByName(uName)>0) {
				return "User details deleted successfully...";
			} else {
				return "User not present...";
			}
		}
	
//==========================================================================================================================
		
		//  User defined | Query: Search Operation by like Name      Op:10
	
		public List<User> search_by_name(String keyword) {	
			return userRepository.searchByName("%"+keyword+"%");
			
		}
		
		
		
		
		
		
		
		
	
	
	
	
	
//  Retrieve data by Email & Password Operation:-  {Use when Email is PK}
	
//	public String findUserByEmail1(String uEmail, String uPassword) {
//		Optional<User> op = userRepository.findById(uEmail);
//		System.out.println(op);
//		//System.out.println(User);
//			if(op.isPresent()) {
//				User u = op.get();
//				if(u.getuPassword().equals(uPassword)) {
//					return "Welcome User";
//				} else {
//					return "Password may be worng";
//				}
//			} else {
//				return "Email or Password may be worng";
//			}
//		}
	
	
//************************************************************************************************************************************	
	
}

