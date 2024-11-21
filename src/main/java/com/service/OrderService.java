package com.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.entity.Order;
import com.entity.OrderDetail;
import com.repo.OrderRepository;
import com.repo.ColorRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orepo;
	@Autowired
	private OrderDetailService odsv;
	@Autowired
	private ColorRepository srepo;
	 @Autowired
	    public JavaMailSender emailSender;
	public void add(Order order) throws MessagingException {
		String u = "Đã hủy";
		if(order.getStatus().equalsIgnoreCase(u)) {
			System.out.print("hashaghi");
			for(OrderDetail od:odsv.findByOrderId(order.getId())) {
				Color s = srepo.findColorByNameV2(od.getProduct().getId(),od.getSize());
				srepo.updateAmount((s.getAmount()+od.getAmount()), od.getProduct().getId(), od.getSize());
				
			}
		}
		if(order.getStatus().equalsIgnoreCase("Đang chờ")) {
		 	MimeMessage message = emailSender.createMimeMessage();
		 
	        boolean multipart = true;
	         
	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
	       
	        String htmlMsg = "<head>"
	        		+"<meta charset=\"UTF-8\"></head>"
	        		+"<body><h3>Vui lòng vào kiểm tra đơn hàng mưới tại địa chỉ<a href=\"doanbuihieu.herokuapp.com/admin/order/edit/"+order.getId()+"\">Này</a></h3>"
	                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'></body>";
	        
	        message.setContent(htmlMsg, "text/html; charset=UTF-8");
	        
	        helper.setTo("gacontapgayno1@gmail.com");
	        helper.setSubject("Bạn có đơn hàng mới!");
	         
	     
	        this.emailSender.send(message);
	}
		if(order.getStatus().equalsIgnoreCase("Đã xác nhận")||order.getStatus().equalsIgnoreCase("Đã thanh toán")) {
			 	MimeMessage message = emailSender.createMimeMessage();
			 
		        boolean multipart = true;
		         
		        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
		       
		        String htmlMsg = "<head>"
		        		+"<meta charset=\"UTF-8\"></head>"
		        		+"<body><h3>Mã đơn hàng của bạn là "+order.getId()+"</h3>"
		                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'></body>";
		        
		        message.setContent(htmlMsg, "text/html; charset=UTF-8");
		        
		        helper.setTo(order.getEmail());
		        helper.setSubject("Mã đơn hàng");
		         
		     
		        this.emailSender.send(message);
		}
		orepo.save(order);
	}
	public Order get(String id) {
		return orepo.findById(id).get();
	}
	public Optional<Order> getv2(String id) {
		return orepo.findById(id);
	}
	public List<Order> listAll() {
		return (List<Order>) orepo.findAll();
	}
	public Page<Order> listByUser(int uid,int pageNumber) {
	     Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		return (Page<Order>) orepo.findByUserId(uid,pageable);
	}
	public List<Object[]> listByMonth(){
		return orepo.listByMonth();
	}
	public void delete(String id) {
		orepo.deleteById(id);
	}
	public Page<Order> findAll(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        if(keyword!=null) {
            return orepo.findKeyword(keyword, pageable);
        }
        return orepo.findAll(pageable);
    }
}
