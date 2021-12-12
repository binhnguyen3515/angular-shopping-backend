package com.binh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.binh.entity.Authority;

public interface Repository_Authority extends JpaRepository<Authority, String>{

	@Transactional
	@Modifying
	@Query("Delete from Authority where Username = ?1")
	void removeByUsername(String username);

	@Query("Select a from Authority a where a.account.username = ?1")
	List<Authority> findByUsername(String username);

	@Query(value="Select distinct count(*) "
			+ "from Authorities a "
			+ "where a.RoleId = 'CUST'",nativeQuery = true)
	Long getTotalCustomer();
	
}
