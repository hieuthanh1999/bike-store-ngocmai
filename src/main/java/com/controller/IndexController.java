package com.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cart.Cart;
import com.entity.Category;
import com.entity.Product;
import com.entity.Wishlist;
import com.repo.BannerRepository;
import com.repo.WishlistRepository;
import com.service.BannerService;
import com.service.CategoryService;
import com.service.ProductService;
import com.user.User;
import com.user.UserService;

@Controller
public class IndexController {
	@Autowired
	private CategoryService csv;
	@Autowired
	private ProductService psv;
	@Autowired
	private UserService usv;
	@Autowired
	private WishlistRepository wrepo;
	@Autowired
	private BannerRepository brepo;
	@Autowired
	private BannerService bsv;
	//Trang chủ
	@GetMapping(value = "/")
	public String index(Model model, HttpSession session, Cart cart) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String cName = authentication.getName();
		
		if (cName == "anonymousUser") {
			session.removeAttribute("u");
		} else {
			User u = usv.getUserByNameV2(cName);
			session.setAttribute("u", u);
			List<Wishlist> lw = wrepo.findByUser(u.getId());
			System.out.print(lw.size());
			if (lw.size() > 0) {
				session.setAttribute("nwishlist", lw.size());
			}
		}
		List<Category> listc = csv.listAll();
		model.addAttribute("listc", listc);
		String pageTitle = "Trang chủ";
		model.addAttribute("pageTitleUser", pageTitle);
		List<Product> lnew = psv.listProductNew();
		model.addAttribute("lnew", lnew);
		List<Product> lview = psv.listProductByView();
		model.addAttribute("lview", lview);
		model.addAttribute("banner1", bsv.get(1));
		model.addAttribute("banner2", bsv.get(6));
		model.addAttribute("listb1", brepo.list4banner());
		model.addAttribute("listb2", brepo.list6banner());
		int am = 0;
		if ((Cart) session.getAttribute("gioHang") == null) {
		} else {
			cart = (Cart) session.getAttribute("gioHang");
			am = cart.getItems().size();
		}
		session.setAttribute("amountmenu", am);
		return "index";
	}
	//Xử lý khi đăng nhập thành công
	@GetMapping("/successlogin")
	public String successlogin(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String cName = authentication.getName();

		if (cName == "anonymousUser") {
			session.removeAttribute("u");
		} else {
			User u = usv.getUserByNameV2(cName);
			u.setLastlogined(LocalDate.now());
			u.setLogined(u.getLogined() + 1);
			usv.add(u);
		}

		return "redirect:/";
	}
	//Đăng nhập 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,@RequestParam(value = "error",required = false)String er) {
		String pageTitle = "Đăng nhập";
		model.addAttribute("pageTitleUser", pageTitle);
		List<Category> listc = csv.listAll();
		model.addAttribute("listc", listc);
		if(er!=null) {
			model.addAttribute("err", "Sai tài khoản, mật khẩu hoặc tài khoản đã bị khóa!");
		}
		model.addAttribute("listb2", brepo.list6banner());
		return "login";
	}
}
