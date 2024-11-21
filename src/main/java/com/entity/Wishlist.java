package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.user.User;

@Table(name = "wishlist")
@Entity
public class Wishlist {
	@Column(name = "id", nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
    @JoinColumn(name = "userid")
    private User user;
	@ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;
	private int amount;
	public int getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}
	public int getAmount() {
		return amount;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
