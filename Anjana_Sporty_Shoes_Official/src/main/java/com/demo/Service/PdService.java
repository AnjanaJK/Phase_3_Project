package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Repository.IPurchaseDetails;


@Service
public class PdService {
	
	@Autowired
	private IPurchaseDetails pdRepo;

	public List<Object[]> showPurchaseReport(int userId) {
		return pdRepo.displayAllReport();
	}

	public List<Object[]> listAllByDateAscending(int userId) {
		return pdRepo.findAllByOrderByDateAsc();
	}
	
	public List<Object[]> listAllByDateDescending(int userId) {
		return pdRepo.findAllByOrderByDateDesc();
	}

	// Filter - Category
	public List<Object[]> getProductsByCategoryId(int categoryId) {
		return pdRepo.findByPurchasedByCategory(categoryId);
	}
	
}
