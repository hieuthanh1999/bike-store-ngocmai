package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Category;
import com.repo.CategoryRepository;
import com.service.CategoryService;
@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository crepo;
	@Autowired
	private CategoryService csv;
	//Danh sách danh mục sản phẩm
	@GetMapping(value="/admin/category")
	public String listcategory(Model model){
		String pageTitle = "Category";
		model.addAttribute("pageTitle", pageTitle);
		List<Category> listc = csv.listAll();
		model.addAttribute("list",listc);
		return "category";
	}
	//Thêm danh mục sản phẩm
	@RequestMapping(value="/admin/category/addcategory")
	public String addcatagory(Model model){
		String pageTitle = "Add Category";
		model.addAttribute("pageTitle", pageTitle);
		Category category = new Category();
		model.addAttribute("category",category);
		return "addcategory";
	}
	//Lưu danh mục sản phẩm
	@RequestMapping(value="/admin/category/savecategory", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String savecategory(@ModelAttribute("category") Category category){
		if(category.getId()<1) {
			Optional<Category> oc = crepo.findCategoryByName(category.getName());
			if(oc.isPresent()) {
				return "redirect:/error/05";
			}
		}
		csv.add(category);		
		return "redirect:/admin/category";
	}
	//Sửa danh mục sản phẩm
	@RequestMapping(value="/admin/category/edit/{id}")
	public String editcategory(@PathVariable(name="id")int id, Model model) {
		String pageTitle = "Edit Category";
		model.addAttribute("pageTitle", pageTitle);
		Category c = csv.get(id);
		model.addAttribute("category",c);
		return "editcategory";
	}
	//Xóa danh mục sản phẩm
	@RequestMapping(value="/admin/deletecategory/{id}")
	public String deletecatagory(@PathVariable(name="id")int id){
		try {
			csv.deleteById(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/category";
	}
	
}
