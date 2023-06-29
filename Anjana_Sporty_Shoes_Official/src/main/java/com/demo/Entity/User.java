package com.demo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	@Column(name="user_name", length=30)
	private String userName;
	
	@Column(name="password", length=20)
	private String password;
	
	@Column(name="user_fname", length=20)
	private String user_fname;
	
	@Column(name="user_lname", length=20)
	private String user_lname;
	
	@Column(name="user_address", length=50)
	private String user_address;
	
	@Column(name="user_email", length=50)
	private String user_email;
	
	@OneToMany(mappedBy = "user")
    private List<Cart> cart;
	
	@OneToMany(mappedBy = "user")
    private List<Purchase> purchase;
	
	
//	------------------------------------------------------------------
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int user_id, String userName, String password, String user_fname, String user_lname, String user_address,
			String user_email, List<Cart> cart) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.password = password;
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.user_address = user_address;
		this.user_email = user_email;
		this.cart = cart;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", userName=" + userName + ", password=" + password + ", user_fname="
				+ user_fname + ", user_lname=" + user_lname + ", user_address=" + user_address + ", user_email="
				+ user_email + ", cart=" + cart + "]";
	}
	
}
