package com.cart;

import java.util.ArrayList;
import java.util.List;

import com.entity.Discount;
import com.entity.Product;

public class Cart {
    private final List<CartItem> items;
    private int tong;
    private Discount discount;
    private  long finalprice;
    public Discount getDiscount() {
		return discount;
	}
	public float getFinalprice() {
		if(discount!=null) {
			float finalprice = getTong()-(getTong()*discount.getPercent()/100);
			return finalprice;
		}else {
			return getTong();
		}
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
    public Cart() {
        items = new ArrayList<CartItem>();
        tong = 0;
    }
    public CartItem getItem(Product sp) {
        for(CartItem item:items) {
            if(item.getSp().getId()==sp.getId()) {
                return item;
            }
        }
        return null;
    }
    public List<CartItem> getItems(){
        return items;
    }
    public int getItemCount() {
        return items.size();
    }
    public void addItem(CartItem item) {
        addItem(item.getSp(),item.getSoluong(),item.getColor());
    }
    public void addItem(Product sp,int soluong,String size) {
        CartItem item = getItem(sp);
        if(item!=null&&item.getColor()==size) {
        		item.setSoluong(item.getSoluong()+soluong);
        }
        else {
            item =new CartItem(sp);
            item.setSoluong(soluong);
            item.setColor(size);
            items.add(item);
        }
    }
    public void updateItem(Product sp,int soluong) {
        CartItem item = getItem(sp);
        if(item!=null) {
            item.setSoluong(soluong);
        }
    }
    public void updateQuantity(Cart cartForm) {
        if (cartForm != null) {
            List<CartItem> lines = cartForm.getItems();
            for (CartItem line : lines) {
                this.updateItem(line.getSp(), line.getSoluong());
            }
        }
    }
    public void removeItem(Product sp) {
        CartItem item = getItem(sp);
        if(item!=null) {
            items.remove(item);
        }
    }
    public void clear() {
        items.clear();
        tong=0;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
    public int getTong() {
        tong=0;
        for(CartItem item:items) {
            tong+=item.getThanhtien();
        }
        return tong;
    }
}
