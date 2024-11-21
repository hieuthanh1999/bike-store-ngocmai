package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entity.OrderDetail;

@org.springframework.transaction.annotation.Transactional
@Repository
public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Integer>{

	@Query(value="SELECT * FROM orderdetail WHERE orderid = ?1",nativeQuery = true)
	public List<OrderDetail> findByOrderId(String id);
	@Query(value="SELECT * FROM orderdetail o group by o.productid",nativeQuery = true)
	public List<OrderDetail> findByProduct();
	@Query(value="SELECT productid,sum(price*amount) FROM orderdetail o group by o.productid",nativeQuery = true)
	public List<Object[]> findByProductV2();
	
}
