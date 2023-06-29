package com.demo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prod_id;
	
	@Column(name="prod_name", length=50)
	private String prod_name;  
	
	@Column(name="prod_desc", length=500)
	private String prod_desc; 
	
	@Column(name="prod_price", length=20)
	private double prod_price; 
	
	@Column(name="prod_quantity", length=500)
	private int prod_quantity; 
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<Cart> carts;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
//	------------------------------------------------------------------
	 
	 public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(int prod_id, String prod_name, String prod_desc, double prod_price, int prod_quantity, Category category,
		List<Cart> carts, String image) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_desc = prod_desc;
		this.prod_price = prod_price;
		this.prod_quantity = prod_quantity;
		this.category = category;
		this.carts = carts;
		this.image = image;
	}

	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	public double getProd_price() {
		return prod_price;
	}
	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}
	public int getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_desc=" + prod_desc + ", prod_price="
				+ prod_price + ", prod_quantity=" + prod_quantity + ", category=" + category + ", carts=" + carts
				+ ", image=" + image + "]";
	}

	
}
