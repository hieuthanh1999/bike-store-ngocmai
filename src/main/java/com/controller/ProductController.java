package com.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.entity.Color;
import com.library.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.UploadFirebase;
import com.entity.Category;
import com.entity.Product;
import com.repo.BannerRepository;
import com.repo.ProductRepository;
import com.service.CategoryService;
import com.service.ProductService;
import com.service.ColorService;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository prepo;
	@Autowired
	private ProductService psv;
	@Autowired
	private UploadFirebase uploadFirebase;
	@Autowired
	private ColorService ssv;
	@Autowired
	private CategoryService csv;
	@Autowired
	private BannerRepository brepo;
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	//Danh sách sản phẩm admin
	@RequestMapping(value = "/admin/product")
	public String listproduct(Model model) {
		String keyword = null;
		return listByPage(model, 1, keyword);
	}
	//Chi tiết sản phẩm frontend
	@RequestMapping(value = "/home/product/view/{pid}")
	public String productdetail(Model model, @PathVariable("pid") int pid) {
		Product p = psv.get(pid);
		String pt = p.getName();
		model.addAttribute("pageTitleUser", pt);
		List<Product> pp = psv.listProductByPrice(p.getPrice());
		List<Category> listc = csv.listAll();
		model.addAttribute("listc", listc);
		List<Color> s = ssv.listByProductAndAmount(pid);
		if (!s.isEmpty()) {
			p.setStatus("Còn hàng");
		} else
			p.setStatus("Hết hàng");

		model.addAttribute("s", s);
		p.setView(p.getView() + 1);
		psv.add(p);
		model.addAttribute("pp", pp);
		model.addAttribute("p", p);
		return "productdetail";
	}
	//Danh sách sản phẩm theo danh mục
	@RequestMapping(value = "/home/product/{cid}")
	public String listproductbycategory(Model model, @PathVariable("cid") int cid) {
		List<Category> listc = csv.listAll();
		model.addAttribute("listc", listc);
		String keyword = null;
		return listByPageUser(model, 1, keyword, cid);
	}
	//Danh sách sản phẩm frontend
	@GetMapping("/home/product/page/{pageNumber}")
	public String listByPageUser(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("keyword") String keyword, @Param("cid") int cid) {
		List<Category> listc = csv.listAll();
		model.addAttribute("listc", listc);
		Category category = csv.get(cid);
		String pageTitleUser = category.getName();
		model.addAttribute("pageTitleUser", pageTitleUser);
		Page<Product> page = psv.findAllByCategoryKeyword(currentPage, keyword, cid);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Product> list = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", list);
		model.addAttribute("cid", cid);
		model.addAttribute("listb2", brepo.list6banner());
		return "productbycategory";
	}
	//Danh sách sản phẩm backend
	@GetMapping("/admin/product/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("keyword") String keyword) {
		String pageTitle = "Product";
		model.addAttribute("pageTitle", pageTitle);
		List<Category> c = csv.listAll();
		Page<Product> page = psv.findAll(currentPage, keyword);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Product> list = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", list);
		model.addAttribute("c", c);
		return "product";
	}
	//Thêm sản phẩm mới
	@RequestMapping(value = "/admin/product/addproduct")
	public String addproduct(Model model) {
		String pageTitle = "Add Product";
		model.addAttribute("pageTitle", pageTitle);
		List<Category> c = csv.listAll();
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("c", c);
		return "addproduct";
	}
	//Lưu sản phẩm
	@RequestMapping(value = "/admin/product/saveproduct", method = RequestMethod.POST)
	public String saveproduct(@ModelAttribute("product") Product product, @RequestParam MultipartFile image,
			@RequestParam MultipartFile image2, @RequestParam MultipartFile image3, @RequestParam MultipartFile image4) {
		try {
			// cai nay la anh 1
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			String uploadDir = "static/images";
			product.setImg("/images/"+fileName);
			FileUploadUtil.saveFile(uploadDir, fileName, image);

			fileName = StringUtils.cleanPath(image2.getOriginalFilename());
			if (fileName != null) {
				product.setImg2(uploadDir+fileName);
				FileUploadUtil.saveFile(uploadDir, fileName, image2);
			}

			fileName = StringUtils.cleanPath(image3.getOriginalFilename());
			if (fileName != null) {
				product.setImg3(uploadDir+fileName);
				FileUploadUtil.saveFile(uploadDir, fileName, image3);
			}

			fileName = StringUtils.cleanPath(image4.getOriginalFilename());
			if (fileName != null) {
				product.setImg4(uploadDir+fileName);
				FileUploadUtil.saveFile(uploadDir, fileName, image4);
			}

//			String fileName = uploadFirebase.saveImage(image);
//			String imageUrl = "";
//			System.out.print(fileName);
//			if (fileName != null)
//				imageUrl = "https://firebasestorage.googleapis.com/v0/b/doan-8d608.appspot.com/o/"+fileName+"?alt=media&token="+fileName;
//			product.setImg(imageUrl);
//
//			// cai nay la anh 2
//			if (image2 != null) {
//				fileName = uploadFirebase.saveImage(image2);
//				if (fileName != null)
//					imageUrl = "https://firebasestorage.googleapis.com/v0/b/doan-8d608.appspot.com/o/"+fileName+"?alt=media&token="+fileName;
//			}
//			product.setImg2(imageUrl);
//
//			// cai nay la anh 3
//			if (image3 != null) {
//				fileName = uploadFirebase.saveImage(image3);
//				if (fileName != null)
//					imageUrl = "https://firebasestorage.googleapis.com/v0/b/doan-8d608.appspot.com/o/"+fileName+"?alt=media&token="+fileName;
//			}
//			product.setImg3(imageUrl);
//
//			// cai nay la anh 4
//			if (image4 != null) {
//				fileName = uploadFirebase.saveImage(image4);
//				if (fileName != null)
//					imageUrl = "https://firebasestorage.googleapis.com/v0/b/doan-8d608.appspot.com/o/"+fileName+"?alt=media&token="+fileName;
//			}
//			product.setImg4(imageUrl);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (product.getId() < 1) {
			Optional<Product> oc = prepo.findProductByName(product.getName());
			if (oc.isPresent()) {
				return "redirect:/error/07";
			}
			product.setOncreate(LocalDate.now());
			product.setOnupdate(LocalDate.now());

		} else
			product.setOnupdate(LocalDate.now());

		psv.add(product);
		return "redirect:/admin/product";
	}
	//Xóa sản phẩm
	@RequestMapping(value = "/admin/product/deleteproduct/{id}")
	public String deleteproduct(@PathVariable(name = "id") int id, HttpServletRequest request) {
		psv.delete(id);
		return "redirect:/admin/product";
	}
	//Sửa sản phẩm
	@RequestMapping(value = "/admin/product/edit/{id}")
	public String editproduct(@PathVariable(name = "id") int id, Model model) {
		String pageTitle = "Edit Product";
		model.addAttribute("pageTitle", pageTitle);
		List<Category> c = csv.listAll();
		Product sp = psv.get(id);
		model.addAttribute("product", sp);
		model.addAttribute("c", c);
		return "editproduct";
	}
}
