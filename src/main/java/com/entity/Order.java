package com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="`order`")
@Entity
public class Order {
	
	@Id
	@Column(name = "`id`", nullable = false,length = 100)
	private String id;
	@Column(name="`userid`")
	private int userid;
	@Column(name="`fullname`")
	private String fullname;
	@Column(name="`phone`")
	private String phone;
	@Column(name="`email`")
	private String email;
	@Column(name="`address`")
	private String address;
	@Column(name="`status`")
	private String status;
	@Column(name="`methodpayment`")
	private String methodpayment;
	@Column(name="`oncreate`")
	private LocalDate oncreate;
	@Column(name="`amount`")
	private int amount;
	@Column(name="`amountp`")
	private int amountp;
	@Column(name ="`moneycode`")
	private String moneycode;
	@Column(name ="`discountname`")
	private String discountname;
	
	public String getDiscountname() {
		return discountname;
	}
	public void setDiscountname(String discountname) {
		this.discountname = discountname;
	}
	public String getMoneycode() {
		return moneycode;
	}
	public void setMoneycode(String moneycode) {
		this.moneycode = moneycode;
	}
	public int getAmountp() {
		return amountp;
	}
	public void setAmountp(int amountp) {
		this.amountp = amountp;
	}
	public String getId() {
		return id;
	}
	public int getUserid() {
		return userid;
	}
	public String getFullname() {
		return fullname;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public String getStatus() {
		return status;
	}
	public String getMethodpayment() {
		return methodpayment;
	}
	public LocalDate getOncreate() {
		return oncreate;
	}
	public int getAmount() {
		return amount;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setMethodpayment(String methodpayment) {
		this.methodpayment = methodpayment;
	}
	public void setOncreate(LocalDate oncreate) {
		this.oncreate = oncreate;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
