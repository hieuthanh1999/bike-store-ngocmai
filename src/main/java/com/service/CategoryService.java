package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.repo.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository crepo;
	public List<Category> listAll(){
		return (List<Category>)crepo.findAll();
	}
	public void add(Category c) {
	
		crepo.save(c);
	}
	public void delete(Category c) {
		crepo.delete(c);
	}
	public void deleteById(int id) {
		crepo.deleteById(id);
	}
	public Category get(int id) {
		return crepo.findById(id).get();
	}
}
