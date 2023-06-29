package com.demo.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.demo.Repository.IProductRepo;
import com.demo.Entity.Category;
import com.demo.Entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private IProductRepo prodRepo;
	
	// Add new Product to DB
	public void addNewProduct(MultipartFile file, String prod_name, String prod_desc, Category selectedCategory, double prod_price) {
		Product p = new Product();
		
		// -- for image
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// -- for others
		p.setProd_name(prod_name);
		p.setProd_desc(prod_desc);
		p.setCategory(selectedCategory);
		p.setProd_price(prod_price);
		
		prodRepo.save(p);
	}

	// Display all from Product table
	public List<Product> showAllProduct() {
		return prodRepo.findAll();
	}
	
	// Home page product display
	public List<Object[]> getProductsWithCategory() {
		return prodRepo.getProductsWithCategory();
	}
	
	// Delete selected product by ID
	public void deleteProductById(int id) {
		prodRepo.deleteById(id);
	}
	
	// Fetch product details of selected product by ID
	public Product getProductById(int id) {
		Optional<Product> p = prodRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		}
		return null;
	}


	// update Product in the DB
	public void addUpdatedProduct(MultipartFile file, int prod_id, String prod_name, String prod_desc, 
			Category selectedCategory, double prod_price) {
		Product p = new Product();
		
		// -- for image
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// -- for others
		p.setProd_id(prod_id);
		p.setProd_name(prod_name);
		p.setProd_desc(prod_desc);
		p.setCategory(selectedCategory);
		p.setProd_price(prod_price);
		
		prodRepo.save(p);		
	}

	// Filter - Category
	public List<Object[]> getProductsByCategoryId(int categoryId) {
		return prodRepo.findByCategory(categoryId);
	}
	
}
