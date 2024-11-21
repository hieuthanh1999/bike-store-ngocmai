package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Banner;
import com.repo.BannerRepository;

@Service
public class BannerService {
	@Autowired
	private BannerRepository brepo;
	public Banner get(int id) {
		return brepo.findById(id).get();
	}
	

}
