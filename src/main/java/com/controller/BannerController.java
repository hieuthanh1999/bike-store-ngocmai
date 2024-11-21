package com.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.UploadFirebase;
import com.entity.Banner;
import com.repo.BannerRepository;
import com.service.BannerService;

@Controller
public class BannerController {
	@Autowired
	private UploadFirebase uploadFirebase;
	@Autowired
	private BannerRepository brepo;
	@Autowired
	private BannerService bsv;
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	//Danh sách ảnh giao diện
	@GetMapping("/admin/banner")
	public String banner(Model model) {
		String pageTitle = "Banner";
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("list", brepo.findAll());
		return "banner";
	}
	//Sửa ảnh giao diện 
	@GetMapping("/admin/banner/edit/{bid}")
	public String editbanner(Model model,@PathVariable("bid")int id) {
		String pageTitle = "Edit Banner";
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("banner", bsv.get(id));
		return "editbanner";
	}
	//Lưu ảnh giao diện 
	@PostMapping("/admin/banner/save")
	public String savebanner(@ModelAttribute("banner")Banner b, @RequestParam MultipartFile image) throws IOException {
		try {
			// cai nay la anh 1
			String fileName = uploadFirebase.saveImage(image);
			String imageUrl = "";
			System.out.print(fileName);
			if (fileName != null)
				imageUrl = "https://firebasestorage.googleapis.com/v0/b/doan-8d608.appspot.com/o/"+fileName+"?alt=media&token="+fileName;
			b.setImg(imageUrl);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		brepo.save(b);
		return "redirect:/admin/banner";
		
	}
	

}
