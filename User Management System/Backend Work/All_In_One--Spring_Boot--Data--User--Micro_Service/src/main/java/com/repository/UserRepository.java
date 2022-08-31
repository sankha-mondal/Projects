package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bean.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	// Page<User> findAllByOrderByProductId(Pageable pageable);
	
	//  Custom select:
	//  User defined method | Query: Find product based on condition | JPQL
	
	
	
	
	//  Retrieve data by Name Operation:-  Op:6
	
	@Query("select u from User u where u.uName = :uName")
	public List<User> findUserByName(@Param("uName") String uEmail);
	
	
	//  Retrieve data by Name & Password Operation:-  Op:7A
	
	@Query("select u from User u where u.uName = :uName and u.uPassword = :uPassword")
	public List<User> findUserByName_Pass(@Param("uName") String uName, @Param("uPassword") String uPassword);
	
	
	//  Retrieve data by Email & Password Operation:-  Op:7B
	
	@Query("select u from User u where u.uEmail = :uEmail and u.uPassword = :uPassword")
	public int findUserByEmail_Pass(@Param("uEmail") String uEmail,@Param("uPassword") String uPassword);
	
	
	//  Retrieve data Order By Id asc/desc:-   Op:8
	
	@Query("select u from User u order by u.uEmail desc")
	public List<User> sortUserByEmail();
	
	
	//  Query: Record deleted by Email | JPQL   Op:9
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("delete from User u where u.uName = :uName")
	public int deleteUserByName(@Param("uName") String uName);
	
	
	//  Query: Search Operation by like Name | JPQL   Op:10
	
	@Query("select u from User u where u.uName like :key")
	public List<User> searchByName(@Param("key") String title);
 


}
