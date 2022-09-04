package com.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "category_description")
	private String description;
	
	@Column(name = "available")
	private boolean available;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "category_id")
	private Set<Books> books = new HashSet<>();    // One Category has many Books.
	
	
	/**
	 	{
	    "categoryName" : "Python",
	    "description" : "This is the full package of Python tutorial",
	    "available" : true
		}
	 */

//===============================================================================================
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public Set<Books> getBooks() {
		return books;
	}


	public void setBooks(Set<Books> books) {
		this.books = books;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", description=" + description + ", available="
				+ available + ", books=" + books + "]";
	}


	public Category(String categoryName, String description, boolean available) {
		super();
		this.categoryName = categoryName;
		this.description = description;
		this.available = available;
	}


	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	

	
	
	

}
