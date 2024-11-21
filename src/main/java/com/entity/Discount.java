package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="discount")
@Entity
public class Discount {
	@Column(name = "id", nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int percent;
	private int amount;
	private boolean isenable;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public int getPercent() {
		return percent;
	}
	public int getAmount() {
		return amount;
	}	
	public void setId(int id) {
		this.id = id;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isIsenable() {
		return isenable;
	}
	public void setIsenable(boolean isenable) {
		this.isenable = isenable;
	}
	
	
}