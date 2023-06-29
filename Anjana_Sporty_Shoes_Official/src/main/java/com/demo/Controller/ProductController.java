package com.demo.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.Entity.Product;
import com.demo.Service.CategoryService;
import com.demo.Service.PdService;
import com.demo.Service.ProductService;

import jakarta.servlet.http.HttpSession;

import com.demo.Entity.Category;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService prodService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private PdService pdService;
	
	@PostMapping("/addNew")
	public String addNewProduct(HttpSession session, @RequestParam("image") MultipartFile file, @RequestParam("prod_name") String prod_name, @RequestParam("prod_desc") String prod_desc, 
			@RequestParam("category.id") int categoryId, @RequestParam("prod_price") double prod_price) {
	    Integer id = (Integer) session.getAttribute("uid");
	    if(id != null) {
	    	Optional<Category> optionalCategory = catService.findCatId(categoryId);
			if(optionalCategory.isEmpty()) {
				throw new IllegalArgumentException("Invalid Category ID");
			}
			Category selectedCategory = optionalCategory.get();
			prodService.addNewProduct(file, prod_name, prod_desc, selectedCategory, prod_price);
			session.setAttribute("msg", "New Product Added Successfully");
			return "redirect:/home";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
	}
	
	
	// Delete Product from DB
	@GetMapping("/deleteProd/{id}")
	public String deleteProduct(HttpSession session, @PathVariable("id") int id) {
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	prodService.deleteProductById(id);
	    	session.setAttribute("msg", "Product Deleted Successfully");
			return "redirect:/home";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
	}
	
	// Edit Selected Product
	@GetMapping("/editProductInfoPage/{id}")
	public String editProductInfoPage(HttpSession session, Model model, @PathVariable int id) {
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Product selectedProduct = prodService.getProductById(id);
			List<Category> listCat = catService.showAllCategory();
			model.addAttribute("selectedProduct", selectedProduct);
			model.addAttribute("listCat", listCat);
			return "editProductInfo";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
	}
	
	@PostMapping("/updateProductInfo")
	public String updateProductInfo(HttpSession session, @RequestParam("image") MultipartFile file,@RequestParam("prod_id") int prod_id, 
			@RequestParam("prod_name") String prod_name, 
			@RequestParam("prod_desc") String prod_desc, 
			@RequestParam("category.id") int categoryId, 
			@RequestParam("prod_price") double prod_price) {
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Optional<Category> optionalCategory = catService.findCatId(categoryId);
			if(optionalCategory.isEmpty()) {
				throw new IllegalArgumentException("Invalid Category ID");
			}

			Category selectedCategory = optionalCategory.get();
			prodService.addUpdatedProduct(file, prod_id, prod_name, prod_desc, selectedCategory, prod_price);
			session.setAttribute("msg", "Product Updated Successfully");
			return "redirect:/home";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
	}
	
	// Perform filtering based on the selected category
	@GetMapping("/filter")
	public String filterProductsByCategory(@RequestParam(name = "category", required = false) int categoryId, Model model) {
	    if (categoryId == 0) {
	    	return "redirect:/home";
	    } else {
	    	Category selectedCategory = catService.getCategoryById(categoryId);
			model.addAttribute("selectedCategory", selectedCategory);
	        List<Object[]> filteredProducts = prodService.getProductsByCategoryId(categoryId);		// Filter products based on the selected category
	        List<Category> listCat = catService.showAllCategory();
			model.addAttribute("listCat", listCat);
		    model.addAttribute("products", filteredProducts);
		    return "filterHome";
	    } 
	}
	

	@GetMapping("/filterReport")
	public String filterPurchasedByCategory(@RequestParam(name = "category", required = false) int categoryId, Model model) {
	    if (categoryId == 0) {
	    	return "redirect:/checkoutDetails";
	    } else {
	    	Category selectedCategory = catService.getCategoryById(categoryId);
	        List<Object[]> filterPurchasedProducts = pdService.getProductsByCategoryId(categoryId);
	        List<Category> listCat = catService.showAllCategory();
	        
	        model.addAttribute("selectedCategory", selectedCategory);
			model.addAttribute("listCat", listCat);
		    model.addAttribute("filterPurchasedProducts", filterPurchasedProducts);
		    return "filterPurchase";
	    } 
	}
	
}
