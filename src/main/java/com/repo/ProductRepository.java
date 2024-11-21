package com.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entity.Product;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
	@Query("SELECT c FROM Product c WHERE c.name =?1")
	Optional<Product> findProductByName(String name);
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    public Page<Product> findKeyword(String keyword,Pageable pageable);
	@Query(value="SELECT * FROM product  WHERE categoryid = ?1",nativeQuery = true)
    public Page<Product> findProductPageCate(int cid,Pageable pageable);
	@Query(value="SELECT * FROM product  WHERE categoryid = ?1 ORDER BY oncreate DESC LIMIT 8",nativeQuery=true)
	List<Product> findProductByCategory(int id);
	@Query(value="SELECT * FROM product ORDER BY oncreate DESC LIMIT 8",nativeQuery=true)
	List<Product> findNewProduct();
	@Query(value="SELECT * FROM product ORDER BY view DESC LIMIT 8",nativeQuery=true)
	List<Product> findViewProduct();
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1% AND categoryid=?2")
    public Page<Product> findKeywordCategory(String keyword,int cid,Pageable pageable);
	@Query(value="SELECT * FROM product WHERE price = ?1 LIMIT 4",nativeQuery = true)
	public List<Product> findByPrice(int price);
	//Thống kê
	
}
