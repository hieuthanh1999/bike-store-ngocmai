package com.controller;

import java.io.IOException;
import java.time.LocalDate;

import java.util.List;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;


import com.entity.Color;
import com.library.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.PaypalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cart.Cart;
import com.cart.CartItem;
import com.cart.CartService;
import com.entity.Category;
import com.entity.Order;
import com.entity.OrderDetail;
import com.repo.BannerRepository;
import com.repo.DiscountRepository;
import com.repo.ColorRepository;
import com.service.CategoryService;
import com.service.OrderDetailService;
import com.service.OrderService;
import com.user.User;


@Controller
@RequiredArgsConstructor
public class OrderController {
	@Autowired
	private CategoryService catesv;
    @Autowired
    private CartService csv;
    @Autowired 
    private OrderService osv;
    @Autowired 
    private OrderDetailService odsv;
    @Autowired
    private ColorRepository srepo;
    @Autowired
	private BannerRepository brepo;
    @Autowired
    private DiscountRepository drepo;
	@Autowired
	private PaypalService paypalService;

    //Lịch sử đơn hàng
    @GetMapping("/home/orderhistory/")
    public String order(Model model,@Param("keyid") String keyid) {
    	String pageTitle = "Lịch sử mua hàng";
		model.addAttribute("pageTitleUser", pageTitle);
    	List<Category> listc = catesv.listAll();
		model.addAttribute("listc",listc);
		if(keyid != null) {
			String message ="";
			if(osv.getv2(keyid).isEmpty()) {
				message = "Mã đơn hàng không tồn tại";
			}else {
				model.addAttribute("order", osv.get(keyid));
				model.addAttribute("list", odsv.findByOrderId(keyid));
				message = "Mã đơn hàng của bạn là: "+ keyid +". Hãy lưu lại mã này để tra cứu đơn hàng";
			}
			
			model.addAttribute("message", message);
		}
		model.addAttribute("listb2", brepo.list6banner());
    	return "orderhistory";
    }
    //Thanh toán
    @PostMapping("/home/checkout")
    public String checkoutdiscount(@RequestParam("discount")String name,HttpSession session) throws IOException {
    	if(drepo.getByName(name).isEmpty()) {
    		
    		throw new IOException("Mã giảm giá không tồn tại");
    	}else {
    		if(drepo.getByNameV2(name).isIsenable()) {
    			if(drepo.getByNameV2(name).getAmount()>0) {
    				Cart cart = csv.getCart(session);
    				cart.setDiscount(drepo.getByNameV2(name));
    			}else {
    				throw new IOException("Mã giảm giá đã hết");
    			}
    		}else {
    			throw new IOException("Mã giảm giá hết hạn");
    		}
    	}
    	
    	return "redirect:/home/checkout";
    }
    //Thanh toán
	@GetMapping("/home/checkout")
	public String checkout(Model model,HttpSession session,Cart cart) {
		String pageTitle = "Thanh toán";
		model.addAttribute("pageTitleUser", pageTitle);
		List<Category> listc = catesv.listAll();
		model.addAttribute("listc",listc);
		Order order= new Order();
		model.addAttribute("order", order);
		if((Cart) session.getAttribute("gioHang") ==null) {
			return "redirect:/error/04";
		}
		cart = (Cart) session.getAttribute("gioHang");
		boolean isInvalid = true;
		for(CartItem ci:cart.getItems()) {
			if(srepo.countProductByColor(ci.getColor(),ci.getSp().getId())<ci.getSoluong()) {
				isInvalid = false;
				break;
			}
		}
		if(cart.getDiscount()!=null) {
			model.addAttribute("giam", (cart.getTong()-cart.getFinalprice()));
		}else {
			model.addAttribute("giam", 0);
		}
		if(!isInvalid) {
			return "redirect:/error/06";
		}
		int am =0;
		am=cart.getItems().size();
	    session.setAttribute("amountmenu",am);
	    model.addAttribute("listb2", brepo.list6banner());
		return "checkout";
	}
	//Thêm mã giao dịch
	@RequestMapping("/home/order/update/{oid}")
	public String updatemoneycode(@PathVariable("oid") String oid,@Param("moneycode") String moneycode) throws MessagingException {
		Order o = osv.get(oid);
		o.setMoneycode(moneycode);
		osv.add(o);
		return "redirect:/home/orderhistory/?keyid="+oid;
	}
	//Lưu đơn hàng
	@RequestMapping(value="/home/saveorder", method = RequestMethod.POST)
	public String addorder(Model model, HttpSession session, @ModelAttribute("order") Order order
			, HttpServletResponse response, HttpServletRequest request) throws MessagingException {
		if((Cart) session.getAttribute("gioHang") ==null) {
			return "redirect:/error/06";
		}
		 Cart cart = (Cart) session.getAttribute("gioHang");
		
		List<CartItem> cis = cart.getItems();
		boolean isInvalid = true;
		for(CartItem ci:cis) {
			if(srepo.countProductByColor(ci.getColor(),ci.getSp().getId())<ci.getSoluong()) {
				isInvalid = false;
				break;
			}
		}
		AtomicInteger total = new AtomicInteger();
		if(isInvalid) {
			order.setOncreate(LocalDate.now());
			order.setId(UUID.randomUUID().toString());
			System.out.print(order.getId());
			order.setAmount((int) cart.getFinalprice());
			order.setStatus("Đang chờ");
			User u = (User) session.getAttribute("u");
			if(u!=null) {
				order.setUserid(u.getId());
			}
			if(cart.getDiscount()!=null) {
				order.setDiscountname(cart.getDiscount().getName());
			}
			order.setAmountp(cart.getItems().size());
			
			osv.add(order);
			for(CartItem ci:cis) {
				OrderDetail od = new OrderDetail();
				od.setAmount(ci.getSoluong());
				if(ci.getSp().getSale()>0) {
					od.setPrice(ci.getSp().getSale());
				}else {
					od.setPrice(ci.getSp().getPrice());
				}
				
				od.setProduct(ci.getSp());
				od.setOrder(order);
				od.setSize(ci.getColor());
				Color s = srepo.findColorByNameV2(ci.getSp().getId(),ci.getColor());
				srepo.updateAmount((s.getAmount()-ci.getSoluong()), ci.getSp().getId(), ci.getColor());
				total.addAndGet(od.getPrice()*od.getAmount());
				odsv.add(od);
			}
		}else throw new IllegalStateException("Giỏ hàng của bạn có số lượng vượt quá kho của chúng tôi vui lòng tạo lại " +
				"giỏ hàng hoặc liên hệ trực tiếp với cửa hàng qua Messenger hoặc số điện thoại!");

		String id = order.getId();
		if(order.getMethodpayment().equals("paypal")){
			try {
				Payment payment = paypalService.createPayment(
						(double) (total.get()/25000),
						"USD",
						"paypal",
						"sale",
						"Thanh toán cho order:",
						Utils.getBaseURL(request) +"/pay-cancel",
						Utils.getBaseURL(request) + "/pay-success/" + id);
				for(Links links : payment.getLinks()){
					if(links.getRel().equals("approval_url")){
						response.sendRedirect(links.getHref());
						return "/";
					}
				}
			} catch (PayPalRESTException e) {
				System.out.println("Pay fail");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		csv.removeCart(session);
		return "redirect:/home/orderhistory/?keyid="+id;
		
	}

	@RequestMapping("/pay-cancel")
	public String cancelPay(){
		return "redirect:/home/checkout";
	}
	@RequestMapping("/pay-success/{id}")
	public String successPay(@PathVariable("id") String id) throws MessagingException {
		Order order = osv.get(id);
		order.setStatus("Đã thanh toán");
		osv.add(order);
		return "redirect:/home/orderhistory/?keyid="+id;
	}
	//Danh sách đơn hàng của khách hàng
	@GetMapping("/admin/order")
	public String listorder(Model model) {
		String keyword = null;
		return listByPage(model, 1, keyword);
	}
	//Danh sách đơn hàng của khách hàng
	@GetMapping("/admin/order/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("keyword") String keyword) {
		String pageTitle = "Order";
		model.addAttribute("pageTitle", pageTitle);
		List<Category> c = catesv.listAll();
		Page<Order> page = osv.findAll(currentPage, keyword);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Order> list = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", list);
		model.addAttribute("c", c);
		return "order";
	}
	//Lưu đơn hàng
	@PostMapping("/admin/order/save")
	public String adminsaveorder(@ModelAttribute("order")Order order) throws MessagingException {
		osv.add(order);
		//return "redirect:/admin/order/edit/"+order.getId();
		return "redirect:/admin/order/";
	}
	//Sửa đơn hàng
	@RequestMapping("/admin/order/edit/{oid}")
	public String editOrder(Model model,@PathVariable("oid")String oid) {
		
		model.addAttribute("pageTitle", "Edit Order");
		Order o = osv.get(oid);
		List<OrderDetail> lod = odsv.findByOrderId(oid);
		model.addAttribute("order", o);
		model.addAttribute("list", lod);
		return "editorder";
	}
	//Xóa đơn hàng
	@RequestMapping("/admin/deleteorder/{oid}")
	public String deleteorder(Model model,@PathVariable("oid")String oid) {
		
		osv.delete(oid);
		return "redirect:/admin/order";
	}
	//Xóa chi tiết đơn hàng
	@RequestMapping("/admin/deleteorderdetail/{oid}")
	public String deleteorderdetail(Model model,@PathVariable("oid")int oid) {
		
		odsv.delete(oid);
		return "redirect:/admin/order";
	}
}
