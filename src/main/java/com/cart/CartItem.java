package com.cart;

import com.entity.Product;

public class CartItem {
    private final Product sp;
    private int soluong;
    private int thanhtien;
    private String color;
    public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Product getSp() {
        return sp;
    }
    public int getSoluong() {
        return soluong;
    }
    
    public int getThanhtien() {
    	if(sp.getSale()>0) {
    		thanhtien= sp.getSale()*soluong;
    	}else
    	thanhtien= sp.getPrice()*soluong;
        return thanhtien;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
    public CartItem(Product sp) {
        this.sp = sp;
        this.soluong=1;
        this.thanhtien=sp.getPrice();
   
    }
	
    
}
