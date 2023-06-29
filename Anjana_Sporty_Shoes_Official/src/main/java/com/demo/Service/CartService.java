package com.demo.Service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Cart;
import com.demo.Entity.CartId;
import com.demo.Entity.Product;
import com.demo.Entity.Purchase;
import com.demo.Entity.PurchaseDetails;
import com.demo.Entity.PurchaseDetailsId;
import com.demo.Entity.User;
import com.demo.Repository.ICartRepo;
import com.demo.Repository.IPurchaseDetails;
import com.demo.Repository.IPurchaseRepo;
import com.demo.Repository.IUserRepo;

import jakarta.transaction.Transactional;

@Service
public class CartService {
	
	@Autowired
	private ICartRepo cartRepo;
	@Autowired
	private IPurchaseDetails purchaseDetailsRepo;
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private IPurchaseRepo purchaseRepo;

	public void addToCart(Product prod_id, User user_id, double prod_price) {
		Cart cart = new Cart();
		CartId cartId = new CartId();
		
		cartId.setUserId(user_id.getUser_id());
	    cartId.setProdId(prod_id.getProd_id());
	    
	    cart.setCartId(cartId);
	    cart.setProduct(prod_id); // Set the product object
	    cart.setUser(user_id); // Set the user object
	    cart.setPrice(prod_price);
		
		cartRepo.save(cart);
	}

	public List<Cart> showAllCartItems() {
		return cartRepo.findAll();
	}
	
	// Cart page product display
	public List<Object[]> showingProductsInCart(int userId) {
		return cartRepo.showingProductsInCart(userId);
	}


	@Transactional
	public void deleteProductFromCart(int userId, int productId) {
		cartRepo.deleteByCartIdUserIdAndCartIdProdId(userId, productId);
	}

	public Double getTotalPriceByUserId(int userId) {
		return cartRepo.getTotalPriceByUserId(userId);
	}

	public void checkout(int user_id) {
		User user = userRepo.findById(user_id).orElse(null);
		List<Cart> cartItem = cartRepo.findByCartIdUserId(user_id);
		
		System.out.println("PRINTING...");
		
		for (Cart cartItems : cartItem) {
			
			// store to purchase
			Purchase purchase= new Purchase();
			
			purchase.setUser(user);
			purchase.setDateOfPurchase(new Date());

			purchaseRepo.save(purchase);

			// store to purchaseDetails
			PurchaseDetails purchaseDetails = new PurchaseDetails();
		    PurchaseDetailsId purchaseDetailsId = new PurchaseDetailsId();
		    
		    purchaseDetailsId.setPurchaseId(purchase.getPurch_id());
		    purchaseDetailsId.setProdId(cartItems.getProduct().getProd_id());
	        purchaseDetails.setPdId(purchaseDetailsId);
		    
			purchaseDetails.setPurchase(purchase);
		    purchaseDetails.setProduct(cartItems.getProduct());
		    purchaseDetails.setAmount(cartItems.getPrice());
		    purchaseDetails.setQuantity(cartItems.getQuantity());
			
			purchaseDetailsRepo.save(purchaseDetails);
			
			cartRepo.delete(cartItems);
			
		}
		
	}
	
}
