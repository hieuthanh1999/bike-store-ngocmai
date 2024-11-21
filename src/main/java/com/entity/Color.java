package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="size")
@Entity
public class Color {
	@Column(name = "id", nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="productid")
	private Product p;
	
	private String color;
	private int amount;
	public int getId() {
		return id;
	}
	
	public String getColor() {
		return color;
	}
	public int getAmount() {
		return amount;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setColor(String size) {
		this.color = color;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}
	
	
	
	
}