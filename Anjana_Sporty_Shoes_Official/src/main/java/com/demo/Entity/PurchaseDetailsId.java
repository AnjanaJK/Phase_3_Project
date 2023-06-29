package com.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PurchaseDetailsId implements Serializable {
	
	private int purchaseId;
	private int prodId;
	
// -------------------------------------------
	
	public PurchaseDetailsId() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseDetailsId(int purchaseId, int prodId) {
		super();
		this.purchaseId = purchaseId;
		this.prodId = prodId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	
	

}
