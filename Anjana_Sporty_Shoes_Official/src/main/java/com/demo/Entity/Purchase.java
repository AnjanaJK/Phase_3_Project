package com.demo.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purch_id;
	
	@Column(name="dateOfPurchase")
	private Date dateOfPurchase;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
//	------------------------------------------------------------------
	
	public Purchase() {
		// TODO Auto-generated constructor stub
	}

	public Purchase(int purch_id, Date dateOfPurchase, User user) {
		super();
		this.purch_id = purch_id;
		this.dateOfPurchase = dateOfPurchase;
		this.user = user;
	}
	
	public int getPurch_id() {
		return purch_id;
	}
	
	public void setPurch_id(int purch_id) {
		this.purch_id = purch_id;
	}
	
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
