package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>{

	@Query(value="SELECT * FROM discount WHERE name = ?1",nativeQuery = true)
	public Optional<Discount> getByName(String name);
	@Query(value="SELECT * FROM discount WHERE name = ?1",nativeQuery = true)
	public Discount getByNameV2(String name);
	
}
