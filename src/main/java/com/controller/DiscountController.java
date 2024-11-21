package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.Discount;
import com.repo.DiscountRepository;

@Controller
public class DiscountController {
	@Autowired 
	private DiscountRepository drepo;
	//Danh sách mã giảm giá 
	@GetMapping("/admin/discount")
	public String discount(Model model) {
		String pageTitle = "Discount";
		model.addAttribute("pageTitle", pageTitle);
		
		model.addAttribute("list", drepo.findAll());
		return "discount";
	}
	//Sửa mã giảm giá
	@GetMapping("/admin/discount/edit/{did}")
	public String editdiscount(Model model,@PathVariable("did") int did) {
		String pageTitle = "Edit Discount";
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("discount", drepo.findById(did));
		return "editdiscount";
	}
	//Thêm mã giảm giá
	@GetMapping("/admin/discount/adddiscount")
	public String adddiscount(Model model) {
		String pageTitle = "Add Discount";
		model.addAttribute("pageTitle", pageTitle);
		Discount d = new Discount();
		model.addAttribute("discount", d);
		return "adddiscount";
	}
	//Lưu mã giảm giá
	@PostMapping("/admin/discount/savediscount")
	public String savediscount(@ModelAttribute("discount")Discount d) {
		drepo.save(d);
		return "redirect:/admin/discount";
	}
	//Xóa mã giảm giá
	@DeleteMapping("/admin/deletediscount/{did}")
	public String deletediscount(@PathVariable("did")int did) {
		drepo.deleteById(did);
		return "redirect:/admin/discount";
	}
}
