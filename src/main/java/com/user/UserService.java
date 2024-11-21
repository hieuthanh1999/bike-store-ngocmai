package com.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository urepo;
	public Optional<User> getUserByName(String name) {
		return urepo.getUserByName(name);
	}
	public Optional<User> getUserByToken(String token) {
		return urepo.getUserByToken(token);
	}
	public User getUserByTokenV2(String token) {
		return urepo.getUserByTokenV2(token);
	}
	public Optional<User> getUserByEmail(String email) {
		return urepo.getUserByEmail(email);
	}
	public User getUserByEmailV2(String email) {
		return urepo.getUserByEmailV2(email);
	}
	public User getUserByNameV2(String name) {
		return urepo.getUserByNameV2(name);
	}
	public List<User> listAll(){
		return (List<User>) urepo.findAll();
	}
	public void add(User user) {
		urepo.save(user);
	}
	public void delete(int id) {
		urepo.deleteById(id);
	}
	public User get(int id) {
		return urepo.findById(id).get();
	}
	public Page<User> findAll(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        if(keyword!=null) {
            return urepo.findKeyword(keyword, pageable);
        }
        return urepo.findAll(pageable);
    }
}
