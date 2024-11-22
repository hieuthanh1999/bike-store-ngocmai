package com.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cart.Cart;

import com.cart.CartService;
import com.entity.Category;
import com.entity.Product;
import com.repo.BannerRepository;
import com.repo.DiscountRepository;
import com.service.CategoryService;
import com.service.ProductService;

@Controller
public class CartController {
	@Autowired
	private CategoryService catesv;
    @Autowired 
    private ProductService spsv;
    @Autowired 
    private CartService csv;
    @Autowired 
	private DiscountRepository drepo;
	@Autowired
	private BannerRepository brepo;
	//Thêm mã giảm giá
    @PostMapping("/home/cart")
    public String checkoutdiscount(@RequestParam("discount")String name,HttpSession session) throws IOException {
    	if(drepo.getByName(name).isEmpty()) {
    		return "redirect:/error/01";
    	}else {
    		if(drepo.getByNameV2(name).isIsenable()) {
    			if(drepo.getByNameV2(name).getAmount()>0) {
    				Cart cart = csv.getCart(session);
    				cart.setDiscount(drepo.getByNameV2(name));
    			}else {
    				return "redirect:/error/02";
    			}
    		}else {
    			return "redirect:/error/03";
    		}
    	}
    	return "redirect:/home/cart";
    }
    //Xem giỏ hàng
    @GetMapping("/home/cart")
    public String cartview(Model model,Cart cart,HttpSession session) {
    	String pageTitle = "Giỏ hàng";
		model.addAttribute("pageTitleUser", pageTitle);
    	List<Category> listc = catesv.listAll();
		model.addAttribute("listc",listc);
		if((Cart) session.getAttribute("gioHang") ==null) {
			return "redirect:/error/04";
		}
		cart = (Cart) session.getAttribute("gioHang");
		if(cart.getDiscount()!=null) {
			model.addAttribute("giam", (cart.getTong()-cart.getFinalprice()));
		}else {
			model.addAttribute("giam", 0);
		}
		
//		for(CartItem ci:cart.getItems()) {
//			System.out.print(ci.getSize());
//		}
		int am =0;
		am=cart.getItems().size();
	    session.setAttribute("amountmenu",am);
	    model.addAttribute("listb2", brepo.list6banner());
    	return "cart";
    }
    //Thêm sản phẩm vào giỏ hàng
    @RequestMapping("/cart")
    public String add(HttpSession session,@RequestParam("id")int id,
    		@RequestParam(value="soluong",required = false, defaultValue = "1")int soluong,@Param(value="color") String color) {
        Product sp = spsv.get(id);
        Cart cart = csv.getCart(session);
        cart.addItem(sp,soluong,color);
        return "redirect:/home/cart";
    }
    //Xóa sản phẩm khỏi giỏ hàng
    @RequestMapping("/remove/{id}")
    public String remove(HttpSession session,@PathVariable(name = "id") int id) {
    	Product sp = spsv.get(id);
        Cart cart = csv.getCart(session);
        cart.removeItem(sp);
        return "redirect:/home/cart";
    }
    //Cập nhật số lượng sản phẩm
    @RequestMapping("/update/{id}")	
    public String update(HttpSession session,@PathVariable(name = "id") int id,@RequestParam("soluong") int soluong) {
    	Product sp = spsv.get(id);
        Cart cart = csv.getCart(session);
        cart.updateItem(sp, soluong);
        
        return "redirect:/home/cart";
    }
    @RequestMapping(value = { "/updatecart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpSession session, //
            Model model, //
            @ModelAttribute("cart") Cart cart) {
    	
        Cart carts = csv.getCart(session);

        carts.updateQuantity(cart);
       
        return "redirect:/home/cart";
    }

}
