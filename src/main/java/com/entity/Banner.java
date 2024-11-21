package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name="banner")
@Entity
public class Banner {
	@Column(name = "id", nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String url;
	private String title;
	public String getUrl() {
		return url;
	}
	public String getTitle() {
		return title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String img;
	private String detail;
	public int getId() {
		return id;
	}
	public String getImg() {
		return img;
	}
	public String getDetail() {
		return detail;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	
}