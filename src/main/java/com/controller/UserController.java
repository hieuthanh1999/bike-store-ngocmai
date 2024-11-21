package com.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Category;
import com.entity.Order;
import com.repo.BannerRepository;
import com.role.RoleRepository;
import com.service.CategoryService;
import com.service.OrderService;
import com.user.User;
import com.user.UserService;

@Controller
public class UserController {
	@Autowired
	private BannerRepository brepo;
	@Autowired
	private UserService usv;
	@Autowired
	private CategoryService catesv;
	@Autowired
	private RoleRepository rrepo;
	@Autowired
	private OrderService osv;
	@Autowired
	private CategoryService csv;
	@Autowired
	public JavaMailSender emailSender;

	// Đăng ký
	@GetMapping("/home/register")
	public String register(Model model) {
		String pageTitle = "Đăng ký";
		model.addAttribute("pageTitleUser", pageTitle);
		List<Category> listc = catesv.listAll();
		model.addAttribute("listc", listc);
		User u = new User();
		model.addAttribute("user", u);
		model.addAttribute("listb2", brepo.list6banner());
		return "register";
	}

	// Lưu tài khoản
	@PostMapping("/home/saveuser")
	public String homesaveuser(Model model, @ModelAttribute("user") User user) throws MessagingException {

		String token = UUID.randomUUID().toString();
		if (user.getId() <= 0) {
			user.setEnable(false);
			if (usv.getUserByName(user.getUsername()).isPresent()) {
				return "redirect:/error/09";
			}else
			if (usv.getUserByEmail(user.getEmail()).isPresent()) {
				return "redirect:/error/10";
			}else {
				user.setToken(token);
				user.setRole(rrepo.findByName("USER"));
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(user.getPassword()));
				user.setOncreate(LocalDate.now());
				MimeMessage message = emailSender.createMimeMessage();

				boolean multipart = true;

				MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

				String htmlMsg = "<meta charset=\"UTF-8\">" + "<h3>Chào mừng bạn đến với xe đạp Ngọc Mai </h3>"
						+ "<div>Xin chào bạn chúc bạn có một ngày vui vẻ, để kích hoạt tài khoản truy cập:<a href=\"http://localhost:8080/home/active/"+user.getToken()+"\">Link</a></div>"
						+ "<div>Bạn có 10p để xác thực đăng ký</div>";

				message.setContent(htmlMsg, "text/html;charset=utf-8");

				helper.setTo(user.getEmail());
				helper.setSubject("Chào mừng đến với thế giới giày Sneaker!");

				this.emailSender.send(message);
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(10 * 60 * 1000);
							if (usv.getUserByToken(token).isPresent()) {
								usv.getUserByTokenV2(token).setToken("");
								usv.add(usv.getUserByTokenV2(token));
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}.start();
			}
			
		}

		usv.add(user);

		return "redirect:/error/11";
	}
	//Xác nhận email, kích hoạt tài khoản
	@ResponseBody
	@GetMapping("/home/active/{token}")
	public String activeuser(@PathVariable("token") String token) {
		if (usv.getUserByToken(token).isPresent()) {
			User u = usv.getUserByTokenV2(token);
			u.setEnable(true);
			u.setToken("");
			usv.add(u);
		} else {
			return "Link xác nhận không hợp lệ hoặc đã hết hạn";
		}
		return "Tài khoản của bạn đã được kích hoạt!";
	}
	//Quên mật khẩu
	@ResponseBody
	@PostMapping("/home/forgotpassword")
	public String forgotpassword(@RequestParam("email") String email) throws MessagingException {
		if (usv.getUserByEmail(email).isPresent()) {
			User u = usv.getUserByEmailV2(email);
			String token = UUID.randomUUID().toString();
			u.setToken(token);
			MimeMessage message = emailSender.createMimeMessage();

			boolean multipart = true;

			MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

			String htmlMsg = "<meta charset=\"UTF-8\">" + "<h3>Chào mừng bạn đến với xe a Ngọc Mai </h3>"
					+ "<div>Xin chào bạn chúc bạn có một ngày vui vẻ,link set mật khẩu của bạn là:<a href=\"http://localhost:8080/home/newpassword/"+u.getToken()+"\">Link</a></div>"

			;

			message.setContent(htmlMsg, "text/html;charset=utf-8");

			helper.setTo(usv.getUserByEmailV2(email).getEmail());
			helper.setSubject("Chào mừng đến với thế giới giày Sneaker!");

			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(10 * 60 * 1000);
						if (usv.getUserByToken(token).isPresent()) {
//							usv.delete(usv.getUserByTokenV2(token).getId());
							usv.getUserByTokenV2(token).setToken("");
							usv.add(usv.getUserByTokenV2(token));
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}.start();
			this.emailSender.send(message);
		}
		return "Vui lòng kiểm tra email!";
	}
	//Đặt lại mật khẩu
	@GetMapping("/home/newpassword/{token}")
	public String resetpassword(@PathVariable("token") String token, Model model) {
		if (usv.getUserByToken(token).isPresent()) {
			User u = usv.getUserByTokenV2(token);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			u.setPassword(encoder.encode("123456"));
			u.setToken("");
			usv.add(u);
		} else {
			model.addAttribute("err", "Link xác nhận không hợp lệ hoặc đã hết hạn!");
			return "login";
		}
		model.addAttribute("err", "Mật khẩu mới của bạn là: 123456!");
		return "login";
	}
	//Trang quên mật khẩu
	@GetMapping("/home/forgotpassword")
	public String formfw(Model model) {
		List<Category> listc = catesv.listAll();
		model.addAttribute("listc", listc);
		String pageTitle = "Quên mật khẩu";
		model.addAttribute("pageTitleUser", pageTitle);
		return "forgotpassword";
	}
	//Thay đổi mật khẩu
	@GetMapping("/home/acount/changepassword")
	public String changepassword(Model model) {
		List<Category> listc = catesv.listAll();
		model.addAttribute("listc", listc);
		User u = new User();
		model.addAttribute("user", u);
		model.addAttribute("listb2", brepo.list6banner());
		String pageTitle = "Đổi mật khẩu";
		model.addAttribute("pageTitleUser", pageTitle);
		return "changepassword";
	}
	//Lưu mật khẩu mới
	@PostMapping("/home/acount/changepassword")
	public String savepassword(@ModelAttribute("user") User user, HttpSession session) throws IOException {
		User u = (User) session.getAttribute("u");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setPassword(encoder.encode(user.getPassword()));
		usv.add(u);
		
		return "redirect:/logout";
	}
	//Sửa tài khoản 
	@GetMapping("/home/acount/edit")
	public String editacount(Model model, HttpSession session) {
		List<Category> listc = catesv.listAll();
		model.addAttribute("listc", listc);
		model.addAttribute("user", (User) session.getAttribute("u"));
		model.addAttribute("listb2", brepo.list6banner());
		String pageTitle = "Sửa tài khoản";
		model.addAttribute("pageTitleUser", pageTitle);
		return "editacount";
	}
	//Chi tiết tài khoản
	@GetMapping("/home/acount/{pageNumber}")
	public String account(Model model, @PathVariable("pageNumber") int currentPage, HttpSession session) {
		String pageTitle = "Account";
		model.addAttribute("pageTitleUser", pageTitle);
		List<Category> listc = catesv.listAll();
		model.addAttribute("listc", listc);
		User u = (User) session.getAttribute("u");
		Page<Order> page = osv.listByUser(u.getId(), currentPage);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Order> list = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("list", list);
		model.addAttribute("listb2", brepo.list6banner());
		return "acount";
	}
	//Danh sách các tài khoản
	@RequestMapping(value = "/admin/user")
	public String listproduct(Model model) {
		String keyword = null;
		return listByPage(model, 1, keyword);
	}
	
	@GetMapping("/admin/user/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("keyword") String keyword) {
		String pageTitle = "Account";
		model.addAttribute("pageTitle", pageTitle);
		List<Category> c = csv.listAll();
		Page<User> page = usv.findAll(currentPage, keyword);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<User> list = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", list);
		model.addAttribute("c", c);
		return "user";
	}
}
