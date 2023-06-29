package com.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.Entity.PurchaseDetails;

@Repository
public interface IPurchaseDetails extends JpaRepository<PurchaseDetails, Integer>{

	// Fetch details from Purchase Table and PurchaseDetails Table
	@Query("SELECT purch.purch_id, purch.dateOfPurchase, p.prod_id, p.prod_name, u.userName, pd.amount, c.category_name \r\n"
			+ "FROM Purchase purch\r\n"
			+ "JOIN PurchaseDetails pd\r\n"
			+ "ON purch.purch_id = pd.pdId.purchaseId\r\n"
			+ "JOIN Product p\r\n"
			+ "ON p.prod_id = pd.pdId.prodId\r\n"
			+ "JOIN User u\r\n"
			+ "ON purch.user.user_id= u.user_id\r\n"
			+ "JOIN Category c\r\n"
			+ "ON c.category_id = p.category.category_id")
	List<Object[]> displayAllReport();
	

	@Query("SELECT purch.purch_id, purch.dateOfPurchase, p.prod_id, p.prod_name, u.userName, pd.amount, c.category_name \r\n"
		+ "FROM Purchase purch\r\n"
		+ "JOIN PurchaseDetails pd\r\n"
		+ "ON purch.purch_id = pd.pdId.purchaseId\r\n"
		+ "JOIN Product p\r\n"
		+ "ON p.prod_id = pd.pdId.prodId\r\n"
		+ "JOIN User u\r\n"
		+ "ON purch.user.user_id= u.user_id\r\n"
		+ "JOIN Category c\r\n"
		+ "ON c.category_id = p.category.category_id\r\n"
		+ "ORDER BY purch.dateOfPurchase")
	List<Object[]> findAllByOrderByDateAsc();

	
	@Query("SELECT purch.purch_id, purch.dateOfPurchase, p.prod_id, p.prod_name, u.userName, pd.amount, c.category_name \r\n"
		+ "FROM Purchase purch\r\n"
		+ "JOIN PurchaseDetails pd\r\n"
		+ "ON purch.purch_id = pd.pdId.purchaseId\r\n"
		+ "JOIN Product p\r\n"
		+ "ON p.prod_id = pd.pdId.prodId\r\n"
		+ "JOIN User u\r\n"
		+ "ON purch.user.user_id= u.user_id\r\n"
		+ "JOIN Category c\r\n"
		+ "ON c.category_id = p.category.category_id\r\n"
		+ "ORDER BY purch.dateOfPurchase DESC")
	List<Object[]> findAllByOrderByDateDesc();

	
	@Query("SELECT purch.purch_id, purch.dateOfPurchase, p.prod_id, p.prod_name, u.userName, pd.amount, c.category_name \r\n"
			+ "FROM Purchase purch\r\n"
			+ "JOIN PurchaseDetails pd\r\n"
			+ "ON purch.purch_id = pd.pdId.purchaseId\r\n"
			+ "JOIN Product p\r\n"
			+ "ON p.prod_id = pd.pdId.prodId\r\n"
			+ "JOIN User u\r\n"
			+ "ON purch.user.user_id= u.user_id\r\n"
			+ "JOIN Category c\r\n"
			+ "ON c.category_id = p.category.category_id\r\n"
			+ "WHERE c.category_id = :categoryId ORDER BY p.prod_id")
	List<Object[]> findByPurchasedByCategory(@Param("categoryId") int categoryId);
	
	
	
}
