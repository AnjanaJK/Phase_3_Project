package com.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.Entity.Category;
import com.demo.Service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	
	@PostMapping("/addNew")
	public String addNewCategory(HttpSession session, @ModelAttribute Category category) {
	    Integer id = (Integer) session.getAttribute("uid");
	    if(id != null) {
	    	catService.addNewCategory(category);
	    	session.setAttribute("msg", "New Category Added Successfully");
			return "redirect:/home";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
		
	}
}
