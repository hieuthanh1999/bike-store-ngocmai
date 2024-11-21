package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Banner;
@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
	@Query(value="SELECT * FROM banner WHERE id BETWEEN 2 AND 5",nativeQuery = true)
	public List<Banner> list4banner();
	@Query(value="SELECT * FROM banner WHERE id BETWEEN 7 AND 12",nativeQuery = true)
	public List<Banner> list6banner();
}
