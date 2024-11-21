package com.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.OrderDetail;
import com.repo.OrderDetailRepository;

@Service
public class OrderDetailService {
	@Autowired
	SessionFactory factory;
	@Autowired
	private OrderDetailRepository orepo;
	public void add(OrderDetail odetail) {
		orepo.save(odetail);
	}
	public List<OrderDetail> findByOrderId(String id) {
		return orepo.findByOrderId(id);
	}
	public List<OrderDetail> listByProduct(){
		return orepo.findByProduct();
	}
	public List<Object[]> listByProductV2(){
		return orepo.findByProductV2();
	}
	public void delete(int id) {
		orepo.deleteById(id);
	}
}
