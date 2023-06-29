package com.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Category;
import com.demo.Repository.ICategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private ICategoryRepo catRepo;
	
	// Show All Category
	public List<Category> showAllCategory(){
		return catRepo.findAll();
	}
	
	// Add New Category
	public Category addNewCategory(Category category) {
		return catRepo.save(category);
	}
	
	// For Add/Edit Product - drop down
	public Optional<Category> findCatId(Integer category_id) {
		return catRepo.findById(category_id);
	}
	
	// Fetch selected category by ID
	public Category getCategoryById(int id) {
		Optional<Category> c = catRepo.findById(id);
		if(c.isPresent()) {
			return c.get();
		}
		return null;
	}

}
