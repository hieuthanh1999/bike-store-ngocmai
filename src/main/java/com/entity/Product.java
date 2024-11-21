package com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name="product")
@Entity
public class Product {
	@Column(name = "id", nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	private int sale;
	private String img;
	private String img2;
	private String img3;
	private String img4;
	public String getImg2() {
		return img2;
	}
	public String getImg3() {
		return img3;
	}
	public String getImg4() {
		return img4;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public void setImg4(String img4) {
		this.img4 = img4;
	}
	private String detail;
	private String code;
	private LocalDate oncreate;
	private LocalDate onupdate;
	private String color;
	private String status;
	private int view;
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category c;

	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getSale() {
		return sale;
	}
	public String getImg() {
		return img;
	}
	public String getDetail() {
		return detail;
	}
	public String getCode() {
		return code;
	}
	public LocalDate getOncreate() {
		return oncreate;
	}
	public LocalDate getOnupdate() {
		return onupdate;
	}
	public String getColor() {
		return color;
	}
	public String getStatus() {
		return status;
	}
	public int getView() {
		return view;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Category getC() {
		return c;
	}
	public void setC(Category c) {
		this.c = c;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setOncreate(LocalDate oncreate) {
		this.oncreate = oncreate;
	}
	public void setOnupdate(LocalDate onupdate) {
		this.onupdate = onupdate;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Product() {
		super();
	}
	
	
}
