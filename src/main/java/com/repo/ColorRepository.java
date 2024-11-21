package com.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer>{
	@Query(value="SELECT * FROM color WHERE productid= ?1",nativeQuery = true)
	List<Color> findColorByProductId(int id);
	@Query(value="SELECT * FROM color WHERE productid= ?1 AND color=?2",nativeQuery = true)
	Optional<Color> findColorByName(int pid, String name);
	@Query(value="SELECT * FROM color WHERE productid= ?1 AND color=?2",nativeQuery = true)
    Color findColorByNameV2(int pid, String name);
	@Query(value = "SELECT * FROM color WHERE productid=?1 AND amount>0",nativeQuery = true)
	List<Color> findColorByProductAndAmount(int pid);
	@Query(value = "select sum(amount) from color s where s.color=?1 and productid=?2",nativeQuery = true)
	int countProductByColor(String color, int pid);
	@org.springframework.data.jpa.repository.Modifying
	@Transactional
	@Query(value="update color set amount = ?1 where productid= ?2 and color = ?3",nativeQuery = true)
	void updateAmount(int amout, int pid, String color);
}
