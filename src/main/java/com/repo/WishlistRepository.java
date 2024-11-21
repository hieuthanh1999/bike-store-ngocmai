package com.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entity.Wishlist;

@Repository
public interface WishlistRepository extends PagingAndSortingRepository<Wishlist, Integer>{
	@Query(value="SELECT * FROM wishlist WHERE userid = ?1",nativeQuery = true)
	public List<Wishlist> findByUser(int uid);
	@Query(value="SELECT * FROM wishlist WHERE userid =?1 AND productid=?2",nativeQuery = true)
	public Optional<Wishlist> findByUserAndProduct(int uid,int pid);
}
