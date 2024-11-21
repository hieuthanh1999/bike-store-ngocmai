package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.entity.Product;
import com.repo.ProductRepository;


@Service
public class ProductService {
	@Autowired
	private ProductRepository prepo;
	public Page<Product> findAll(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        if(keyword!=null) {
            return prepo.findKeyword(keyword, pageable);
        }
        return prepo.findAll(pageable);
    }
	public Page<Product> findAllByCategoryKeyword(int pageNumber, String keyword,int cid) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 9);
        if(keyword!=null) {
            return prepo.findKeywordCategory(keyword, cid, pageable);
        }
        return prepo.findProductPageCate(cid, pageable);
    }
	public List<Product> listByCategory(Category c){
		return prepo.findProductByCategory(c.getId());
	}
	public List<Product> listProductNew(){
		return prepo.findNewProduct();
	}
	public List<Product> listProductByView(){
		return prepo.findViewProduct();
	}
	public List<Product> listProductByPrice(int price){
		return prepo.findByPrice(price);
	}
	public void add(Product p) {
		
		prepo.save(p);
	}
	public void delete(int id) {
		prepo.deleteById(id);
	}
	
	public Product get(int id) {
		return prepo.findById(id).get();
	}
	public List<Product> listAll(){
		return (List<Product>) prepo.findAll();
	}
}
