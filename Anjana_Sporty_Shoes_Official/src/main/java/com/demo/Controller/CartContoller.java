package com.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Entity.Cart;
import com.demo.Entity.Category;
import com.demo.Entity.Product;
import com.demo.Entity.User;
import com.demo.Service.CartService;
import com.demo.Service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartContoller {
	
	@Autowired
	private ProductService prodService;
	@Autowired
	private CartService cartService;
	
	@GetMapping("/myCartPage/{id}")
	public String goToCart(HttpSession session, Model model, @PathVariable int id) {
		Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Product selectedProduct = prodService.getProductById(id);
			model.addAttribute("selectedProduct", selectedProduct);
			return "middleCart";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
		
	}
	
	@PostMapping("/saveToCart")
	public String addToCart(HttpSession session, @RequestParam("prod_id") Product prod_id, 
			@RequestParam("user_id") User user_id,
			@RequestParam("prod_price") double prod_price) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			cartService.addToCart(prod_id, user_id, prod_price);
			session.setAttribute("msg", "Product Added To Cart!");
			return "redirect:/myCart";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}	
	}
	
	
	// Get Details to Remove Selected Product
	@GetMapping("/removeFromCartPage/{id}")
	public String removeFromCartPage(HttpSession session, Model model, @PathVariable int id) {
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Product selectedCartItem = prodService.getProductById(id);	// to display product details
	    	model.addAttribute("selectedCartItem", selectedCartItem);
	    	return "removeFromCart";
	    }
	    else {
	        return "redirect:/";
	    }
	}
	
	@PostMapping("/removeItemFromCart")
	public String removeItemFromCart(HttpSession session, @RequestParam("_method") String method, @RequestParam("prod_id") int productId) {
		if ("DELETE".equals(method)) {
			Integer user_id = (Integer) session.getAttribute("uid");
			int userId = (int) user_id;
		    if(user_id != null) {
		    	cartService.deleteProductFromCart(userId, productId);
		    	session.setAttribute("msg", "Item Removed from Cart");
		    	return "redirect:/myCart";
		    }
		    else {
		    	session.setAttribute("msg", "You have been Logged out");
		        return "redirect:/";
		    }
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
	    }
	}
	

	@GetMapping("/checkoutPage")
	public String checkoutPage(HttpSession session) {
		Integer user_id = (Integer) session.getAttribute("uid");
		if(user_id != null) {
			System.out.println("Controller");
		
			cartService.checkout(user_id);
			return "redirect:/checkoutDetails";
		}
		else {
			return "redirect:/";
		}
		
	}
	
	
}
