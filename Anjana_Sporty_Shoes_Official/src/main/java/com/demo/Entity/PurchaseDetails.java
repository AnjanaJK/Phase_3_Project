package com.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class PurchaseDetails {
	
	@EmbeddedId
    private PurchaseDetailsId pdId;

	@Column(name="amount")
	private double amount;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@MapsId("purchaseId")
    @JoinColumn(name = "purch_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("prodId")
    @JoinColumn(name = "prod_id")
    private Product product;
// -------------------------------------------
    
    public PurchaseDetails() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseDetails(PurchaseDetailsId pdId, double amount, int quantity, Purchase purchase, Product product) {
		super();
		this.pdId = pdId;
		this.amount = amount;
		this.quantity = quantity;
		this.purchase = purchase;
		this.product = product;
	}

	public PurchaseDetailsId getPdId() {
		return pdId;
	}

	public void setPdId(PurchaseDetailsId pdId) {
		this.pdId = pdId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
}
