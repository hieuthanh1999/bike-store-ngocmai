package com.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.entity.Category;
import com.entity.Wishlist;
import com.repo.BannerRepository;
import com.repo.WishlistRepository;
import com.service.CategoryService;
import com.service.ProductService;
import com.user.User;
import com.user.UserService;

@Controller
public class WishlistController {
	@Autowired
	private ProductService psv;
	@Autowired
	private BannerRepository brepo;
	@Autowired
	private CategoryService csv;
	@Autowired
	private UserService usv;
	@Autowired
	private WishlistRepository wrepo;
	//Danh sách mong muốn
	@GetMapping("/home/wishlist")
	public String wishlist(Model model,HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String cName = authentication.getName();
		
		User u = usv.getUserByNameV2(cName);
		List<Wishlist> lw = wrepo.findByUser(u.getId());
		model.addAttribute("lw", lw);
		session.setAttribute("nwishlist", lw.size());
		List<Category> listc = csv.listAll();
		model.addAttribute("listc",listc);
		String pageTitle = "Trang chủ";
		model.addAttribute("pageTitleUser", pageTitle);
		model.addAttribute("listb2", brepo.list6banner());
		return "wishlist";
	}
	//Thêm sản phẩm vào danh sách mong muốn 
	@GetMapping("/home/wishlist/add/{pid}")
	public String addwishlist(@PathVariable("pid") int pid) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String cName = authentication.getName();
		
		User u = usv.getUserByNameV2(cName);
		System.out.print(u.getId());
		if(wrepo.findByUserAndProduct(u.getId(), pid).isPresent()) {
			
		}else { 
			Wishlist w = new Wishlist();
			w.setProduct(psv.get(pid));
			w.setUser(u);
			w.setAmount(1);
			wrepo.save(w);
		}
		return "redirect:/";
	}
	//Xóa sản phẩm khỏi danh sách mong muốn 
	@GetMapping("/home/wishlist/del/{wid}")
	public String delwishlist(@PathVariable("wid")int wid) {
		wrepo.deleteById(wid);
		return "redirect:/home/wishlist";
	}
	
}
