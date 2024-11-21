package com.role;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	@Query(value="SELECT * FROM role WHERE name = ?1",nativeQuery = true)
	public Set<Role> findByName(String name);
	
}
