package com.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.demo.Service.CategoryService;
import com.demo.Service.PdService;
import com.demo.Service.ProductService;
import com.demo.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService catService;
	@Autowired
	private ProductService prodService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private PdService pdService;
	
	@GetMapping("/")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/index")
	public String index(@RequestParam("user_name") String userName, @RequestParam("password") String password, 
			HttpSession session)  {
		User u = userService.findUser(userName, password);
		if (u != null) {
			session.setAttribute("uid", u.getUser_id());
			session.setAttribute("username", u.getUserName());
			return "redirect:/home";
		} else {
			session.setAttribute("msg", "Invalid Username or Password");
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.removeAttribute("uid");
	    session.setAttribute("msg", "You have been Logged out");
	    return "redirect:/";
	}
//	--------------------------------------------------------------------------------
	
	@GetMapping("/home")
	public String homePage(Model model, HttpSession session) {
//		List<Product> listProduct = prodService.showAllProduct();
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCat = catService.showAllCategory();
			List<Object[]> listProductWithCategory = prodService.getProductsWithCategory();
			model.addAttribute("listCat", listCat);
			model.addAttribute("listProductWithCategory", listProductWithCategory);
			return "home";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	
	@GetMapping("/listUsersPage")
	public String listUsersPage(Model model, @Param("keyword") String keyword, HttpSession session) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			if(keyword != null) {
				List<User> listUsers = userService.getAllUsers(keyword);
				model.addAttribute("listUsers", listUsers);
				model.addAttribute("keyword", keyword);
				return "listOfUsers";
			}
			else {
				List<User> listUsers = userService.getAllUsers();
				model.addAttribute("listUsers", listUsers);
				return "listOfUsers";
			}
			
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/addProductPage")
	public String addProductPage(Model model, HttpSession session) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCat = catService.showAllCategory();
			model.addAttribute("listCat", listCat);
			model.addAttribute("product", new Product());
			return "addProduct";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/addCategoryPage")
	public String addCategoryPage(Model model, HttpSession session) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCategory = catService.showAllCategory();
			model.addAttribute("listCategory", listCategory);
			return "addCategory";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/myCart")
	public String myCartPage(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("uid");
		if(userId != null) {
			List<Object[]> showingProductsInCart = cartService.showingProductsInCart(userId);
			model.addAttribute("showingProductsInCart", showingProductsInCart);
			Double totalPrice = cartService.getTotalPriceByUserId(userId);
			model.addAttribute("totalPrice", totalPrice);
			return "myCart";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/checkoutDetails")
	public String checkoutDetailsPage(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("uid");
		if(userId != null) {
			List<Category> listCat = catService.showAllCategory();
			model.addAttribute("listCat", listCat);
			List<Object[]> showPurchaseReport = pdService.showPurchaseReport(userId);
			model.addAttribute("showPurchaseReport", showPurchaseReport);
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
		return "checkoutDetails";
	}
	
	@RequestMapping("dateAsc")
	public String viewAsc(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("uid");
		if(userId != null) {
			List<Object[]> dateAsc = pdService.listAllByDateAscending(userId);
			model.addAttribute("showPurchaseReport", dateAsc);
			List<Category> listCat = catService.showAllCategory();
			model.addAttribute("listCat", listCat);
			return "checkoutDetails";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@RequestMapping("dateDesc")
	public String viewDesc(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("uid");
		if(userId != null) {
			List<Object[]> dateDesc = pdService.listAllByDateDescending(userId);
			model.addAttribute("showPurchaseReport", dateDesc);
			List<Category> listCat = catService.showAllCategory();
			model.addAttribute("listCat", listCat);
			return "checkoutDetails";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
		
	}

}
