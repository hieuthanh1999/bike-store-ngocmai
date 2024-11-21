package com.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entity.Order;
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String>{
	@Query(value = "SELECT MONTH(oncreate),sum(amount),sum(amountp) FROM ngocmaidb.order  group by MONTH(oncreate) order by MONTH(order.oncreate) asc",nativeQuery = true)
	public List<Object[]> listByMonth();
	@Query(value = "SELECT MONTH(oncreate),sum(amount),sum(amountp) FROM ngocmaidb.order WHERE oncreate BETWEEN ?1 AND ?2 AND status LIKE 'Hoàn thành' group by MONTH(oncreate) order by MONTH(order.oncreate) asc",nativeQuery = true)
	public List<Object[]> listByFromDateToDate(String fd,String ld);
	@Query(value = "SELECT c.name,month(o.oncreate),sum(o.amount),sum(o.amountp) FROM ngocmaidb.order o,product p,category c,orderdetail od WHERE od.productid = p.id and od.orderid = o.id and p.categoryid = c.id and o.oncreate BETWEEN ?1 AND ?2 group by month(o.oncreate)",nativeQuery = true)
	public List<Object[]> listByCategoryFromDateToDate(String fd,String ld);
	@Query(value ="SELECT p FROM Order p WHERE p.userid =?1")
	public Page<Order> findByUserId(int uid,Pageable pageable);
	@Query("SELECT o FROM Order o WHERE o.fullname LIKE %?1%")
    public Page<Order> findKeyword(String keyword,Pageable pageable);
	@Query(value = "SELECT sum(amount) FROM ngocmaidb.order WHERE status LIKE 'Hoàn thành'",nativeQuery = true)
	public int getTotal();
	@Query(value = "SELECT * FROM ngocmaidb.order WHERE status LIKE 'Hoàn thành'",nativeQuery = true)
	public Optional<List<Order>> checkCompleteOrder();
}
