package com.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
	
	
}
