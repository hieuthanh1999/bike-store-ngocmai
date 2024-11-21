package com.repo;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entity.Order;
import com.user.User;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.username=?1")
	public Optional<User> getUserByName(String name);
	@Query("SELECT u FROM User u WHERE u.username=?1")
	public User getUserByNameV2(String name);
	@Query("SELECT u FROM User u WHERE u.token=?1")
	public Optional<User> getUserByToken(String token);
	@Query("SELECT u FROM User u WHERE u.token=?1")
	public User getUserByTokenV2(String name);
	@Query("SELECT u FROM User u WHERE u.username LIKE %?1%")
    public Page<User> findKeyword(String keyword,Pageable pageable);
	@Query("SELECT u FROM User u WHERE u.email=?1")
	public Optional<User> getUserByEmail(String email);
	@Query("SELECT u FROM User u WHERE u.email=?1")
	public User getUserByEmailV2(String email);
}

