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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Category;
import com.repo.OrderRepository;
import com.service.CategoryService;
import com.service.OrderDetailService;
import com.service.OrderService;
import com.service.ProductService;
import com.user.User;
import com.user.UserService;

@Controller
public class AdminController {
	@Autowired
	private OrderDetailService odsv;
	@Autowired
	private OrderService osv;
	@Autowired
	private UserService usv;
	@Autowired
	private ProductService psv;
	@Autowired
	private OrderRepository orepo;
	@Autowired
	private CategoryService csv;
	//Trang chủ admin
	@GetMapping(value = "/admin")
	public String showadmin(Model model, HttpSession session) {
		String pageTitle = "Admin";
		model.addAttribute("pageTitle", pageTitle);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String cName = authentication.getName();

		if (cName == "anonymousUser") {
			session.removeAttribute("u");
		} else {
			User u = usv.getUserByNameV2(cName);
			session.setAttribute("u", u);
			u.setLastlogined(LocalDate.now());
			u.setLogined(u.getLogined() + 1);
			usv.add(u);
		}
		model.addAttribute("numberproduct", psv.listAll().size());
		model.addAttribute("numberuser", usv.listAll().size());
		List<Category> listc = csv.listAll();
		model.addAttribute("listc", listc);
		if (orepo.checkCompleteOrder().isPresent()) {
			model.addAttribute("total", orepo.getTotal());
		} else {
			model.addAttribute("total", 0);
		}
		String fd= "01/01/2020";
		String ld= LocalDate.now().toString();
		System.out.print(ld);
		model.addAttribute("numberorder", osv.listAll().size());
		if(session.getAttribute("fd")!=null) {
			 fd = session.getAttribute("fd").toString();
			 ld = session.getAttribute("ld").toString();	
		}
		model.addAttribute("list", orepo.listByFromDateToDate(fd, ld));
//		model.addAttribute("list2", orepo.listByCategoryFromDateToDate(fd, ld));
		session.removeAttribute("fd");
		session.removeAttribute("ld");
		return "admin";
	}
	//Chọn khoảng thời gian thống kê
	@PostMapping("/admin")
	public String getdatestatistical(@RequestParam("firstdate") String fd, @RequestParam("lastdate") String ld,
			HttpSession session) {
		session.setAttribute("fd", fd);
		session.setAttribute("ld", ld);
		return "redirect:/admin";
	}
	//Thống kê
	@GetMapping("/admin/statistical")
	public String statistical(Model model) {
		model.addAttribute("list", odsv.listByProduct());
		for (Object[] od : odsv.listByProductV2()) {
			System.out.println(od[0]);
		}
		return "statistical";
	}
	//Thống kê
	@GetMapping("/admin/month")
	public String statisticalmonth(Model model) {
		model.addAttribute("list", osv.listByMonth());

		return "month";
	}
	//Kích hoạt tài khoản
	@GetMapping("/admin/user/enable/{uid}")
	public String enbleuser(@PathVariable("uid") int uid) {
		User u = usv.get(uid);
		u.setEnable(true);
		usv.add(u);
		return "redirect:/admin/user";
	}
	//Block tài khoản
	@GetMapping("/admin/user/disable/{uid}")
	public String disableuser(@PathVariable("uid") int uid) {
		User u = usv.get(uid);
		u.setEnable(false);
		usv.add(u);
		return "redirect:/admin/user";
	}
	//Xóa tài khoán
	@GetMapping("/admin/user/delete/{uid}")
	public String deleteuser(@PathVariable("uid") int uid) {
		usv.delete(uid);
		return "redirect:/admin/user";
	}

}
