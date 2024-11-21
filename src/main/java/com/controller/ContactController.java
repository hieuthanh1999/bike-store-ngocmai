package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.Category;
import com.entity.Contact;
import com.repo.BannerRepository;
import com.repo.ContactRepository;
import com.service.CategoryService;

@Controller
public class ContactController {
	@Autowired
	private CategoryService csv;
	@Autowired 
	private ContactRepository crepo;
	@Autowired
	private BannerRepository brepo;
	//Trang liên hệ
	@GetMapping(value = "/home/contact")
	public String contact(Model model) {
		
		String pageTitle = "Liên hệ";
		model.addAttribute("pageTitleUser", pageTitle);
		List<Category> listc = csv.listAll();
		model.addAttribute("listc",listc);
		Contact c = new Contact();
		model.addAttribute("contact", c);
		model.addAttribute("listb2", brepo.list6banner());
		return "contact";
	}
	//Lưu thông tin liên hệ 
	@PostMapping("/home/contact")
	public String savecontact(@ModelAttribute("contact")Contact c) {
		crepo.save(c);
		return "redirect:/";
	}
	@GetMapping("/admin/contact")
	public String admincontact(Model model) {
		List<Contact> list = crepo.findAll();
		model.addAttribute("list", list);
		return "contactadmin";
	}
	
}
