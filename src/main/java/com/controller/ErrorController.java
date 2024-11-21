package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {
	//Thông báo 
	@ResponseBody
	@GetMapping("/error/{eid}")
	public String error(@PathVariable("eid")String errorcode) {
		if(errorcode.equalsIgnoreCase("01")) {
			return "Mã giảm giá không tồn tại!";
		}
		if(errorcode.equalsIgnoreCase("02")) {
			return "Mã giảm giá đã hết";
		}
		if(errorcode.equalsIgnoreCase("03")) {
			return "Mã giảm giá hết hạn";
		}
		if(errorcode.equalsIgnoreCase("04")) {
			return "Giỏ hàng trống!";
		}
		if(errorcode.equalsIgnoreCase("05")) {
			return "Tên danh mục đã tồn tại";
		}
		if(errorcode.equalsIgnoreCase("06")) {
			return "Giỏ hàng của bạn có số lượng vượt quá kho của chúng tôi. Vui lòng cập nhật lại số lượng!";
		}
		if(errorcode.equalsIgnoreCase("07")) {
			return "Tên sản phẩm đã tồn tại";
		}
		if(errorcode.equalsIgnoreCase("08")) {
			return "Size đã tồn tại";
		}
		if(errorcode.equalsIgnoreCase("09")) {
			return "Tên đăng nhập đã tồn tại";
		}
		if(errorcode.equalsIgnoreCase("10")) {
			return "Email đã tồn tại";
		}
		if(errorcode.equalsIgnoreCase("11")) {
			return "Bạn hãy xác thực email trong 10p!";
		}
		return "";
	}

}
