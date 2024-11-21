package com.controller;


import java.util.List;
import java.util.Optional;

import com.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Product;
import com.service.ProductService;
import com.service.ColorService;

@Controller
public class ColorController {
	@Autowired
	private ProductService psv;
	@Autowired
	private ColorService ssv;
	//Số lượng một sản phẩm theo size
	@GetMapping(value = "/admin/product/size/{pid}")
	public String listsizebyproductid(Model model,@PathVariable("pid") int pid) {
		Product p = psv.get(pid);
		String pageTitle="Số lượng" + p.getName();
		model.addAttribute("pageTitle", pageTitle);
		List<Color> s = ssv.listByProductId(pid);
		model.addAttribute("s", s);
		model.addAttribute("p", p);
		return "size";
	}
	//Thêm số lương theo size
	@RequestMapping(value="/admin/product/addsize/{pid}")
	public String addsize(Model model,@PathVariable("pid")int pid){
		String pageTitle = "Thêm size";
		model.addAttribute("pageTitle", pageTitle);
		Color color = new Color();
		model.addAttribute("size", color);
		Product p = psv.get(pid);
		model.addAttribute("product", p);
		return "addsize";
	}
	//Lưu size
	@RequestMapping(value = "/admin/product/size/savesize/{pid}",method = RequestMethod.POST)
	public String savesize(@ModelAttribute("size") Color color, @PathVariable("pid")int pid) {
		if(color.getId()<1) {
			Optional<Color> oc = ssv.listByName(pid, color.getColor());
			if(oc.isPresent()) {
				return "redirect:/error/08";
			}
			color.setP(psv.get(pid));
			ssv.add(color);
		}
		
		ssv.add(color);
		return "redirect:/admin/product/size/"+pid;
	}
	//Sửa size
	@RequestMapping(value="/admin/product/size/editsize/{id}")
	public String editproduct(@PathVariable(name="id")int id, Model model) {
		String pageTitle = "Edit size";
		model.addAttribute("pageTitle", pageTitle);
		Color s = ssv.get(id);
		int pid = s.getP().getId();
		model.addAttribute("p", s.getP());
		model.addAttribute("pid", pid);
		model.addAttribute("size", s);
		return "editsize";
	}
	//Xóa size
	@RequestMapping(value="/admin/product/deletesize/{id}")
	public String deleteproduct(@PathVariable(name="id")int id){
		int pid = ssv.get(id).getP().getId();
		ssv.delete(id);		
		return "redirect:/admin/product/size/"+pid;
	}
	
}
