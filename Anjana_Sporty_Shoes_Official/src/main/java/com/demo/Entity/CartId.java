package com.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartId implements Serializable {
	
    private int userId;
    private int prodId;
	

// -------------------------------------------
    public CartId() {
		// TODO Auto-generated constructor stub
	}


	public CartId(int userId, int prodId) {
		super();
		this.userId = userId;
		this.prodId = prodId;
	}
	
	
	public int getUserId() {
		return userId;
	}
	
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public int getProdId() {
		return prodId;
	}
	
	
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
    
    
    
    
    
}
